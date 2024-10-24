package eu.tutorials.animelistapp

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dagger.hilt.android.AndroidEntryPoint
import eu.tutorials.animelistapp.constants.DESTINATION
import eu.tutorials.animelistapp.constants.MAIN_PAGE
import eu.tutorials.animelistapp.presentation.ui.myProfileScreen.ContentCardAdapter
import eu.tutorials.animelistapp.presentation.ui.myProfileScreen.MyProfileViewModel
import eu.tutorials.animelistapp.presentation.ui.myProfileScreen.MyProfileViewModelState
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MyProfileActivity : ComponentActivity() {
    private val myProfileViewModel: MyProfileViewModel by viewModels()
    private lateinit var networkMonitor: NetworkMonitor

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        networkMonitor = NetworkMonitor(applicationContext)

        setContentView(R.layout.profile)
        initViews()
        setupCategorySpinner()
        observeViewModel()
    }

    override fun onDestroy() {
        super.onDestroy()

        removeListeners()
    }

    private fun initViews() {
        val backButton = findViewById<Button>(R.id.back_button)
        val saveButton = findViewById<Button>(R.id.save_button)
        val notes = findViewById<EditText>(R.id.notes_field)
        val gender = findViewById<Spinner>(R.id.gender)
        val enableDarkMode = findViewById<CheckBox>(R.id.dark_mode)
        val receiveNotification = findViewById<CheckBox>(R.id.notification)

        backButton.setOnClickListener {
            handleBackButtonClick(notes, gender, enableDarkMode, receiveNotification)
        }

        saveButton.setOnClickListener {
            handleSaveButtonClick(notes, gender, enableDarkMode, receiveNotification)
        }

        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        val recyclerView: RecyclerView = findViewById(R.id.favorite_recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(this)

        val adapter = ContentCardAdapter { url ->
            onClick(url)
        }
        recyclerView.adapter = adapter
    }

    private fun setupCategorySpinner() {
        val category = findViewById<Spinner>(R.id.category_spinner)
        category.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View?,
                position: Int,
                id: Long,
            ) {
                val selectedCategory = parent.getItemAtPosition(position) as String
                updateRecyclerViewForCategory(selectedCategory)
            }

            override fun onNothingSelected(parent: AdapterView<*>) {}
        }
    }

    private fun updateRecyclerViewForCategory(category: String) {
        val adapter =
            (findViewById<RecyclerView>(R.id.favorite_recycler_view)).adapter as ContentCardAdapter
        when (category) {
            "Anime" -> adapter.submitList(myProfileViewModel.uiState.value.topFiveAnimes)
            "Manga" -> adapter.submitList(myProfileViewModel.uiState.value.topfiveMangas)
        }
    }

    private fun observeViewModel() {
        val enableDarkMode = findViewById<CheckBox>(R.id.dark_mode)
        val receiveNotification = findViewById<CheckBox>(R.id.notification)
        val notes = findViewById<EditText>(R.id.notes_field)
        val gender = findViewById<Spinner>(R.id.gender)

        lifecycleScope.launch {
            myProfileViewModel.uiState.collect { uiState ->
                updateUI(uiState, enableDarkMode, receiveNotification, notes, gender)
            }
        }
    }

    private fun updateUI(
        uiState: MyProfileViewModelState,
        enableDarkMode: CheckBox,
        receiveNotification: CheckBox,
        notes: EditText,
        gender: Spinner,
    ) {
        enableDarkMode.isChecked = uiState.enableDarkMode
        receiveNotification.isChecked = uiState.receiveNotifications
        notes.setText(uiState.note.toString())

        val genderOptions = resources.getStringArray(R.array.genders)
        val genderIndex = genderOptions.indexOf(uiState.gender)
        gender.setSelection(genderIndex)
    }

    private fun handleBackButtonClick(
        notes: EditText,
        gender: Spinner,
        enableDarkMode: CheckBox,
        receiveNotification: CheckBox,
    ) {
        setData(notes, gender, enableDarkMode, receiveNotification)
        onClick(MAIN_PAGE)
    }

    private fun handleSaveButtonClick(
        notes: EditText,
        gender: Spinner,
        enableDarkMode: CheckBox,
        receiveNotification: CheckBox,
    ) {
        setData(notes, gender, enableDarkMode, receiveNotification)
        saveButtonClick()
    }

    override fun onStart() {
        super.onStart()
        networkMonitor.startMonitoring()
    }

    override fun onStop() {
        super.onStop()
        networkMonitor.stopMonitoring()
    }

    private fun onClick(url: String?) {
        saveButtonClick()
        intent = Intent(applicationContext, MainActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK).putExtra(DESTINATION, url)
        applicationContext.startActivity(intent)
    }

    private fun saveButtonClick() {
        myProfileViewModel.updateProfileData()
        Toast.makeText(this, "Data saved", Toast.LENGTH_LONG).show()
    }

    private fun setData(
        notes: EditText,
        gender: Spinner,
        enableDarkMode: CheckBox,
        receiveNotification: CheckBox,
    ) {
        myProfileViewModel.uiState.value.note = notes.text.toString()
        myProfileViewModel.uiState.value.gender = gender.selectedItem.toString()
        myProfileViewModel.uiState.value.receiveNotifications = receiveNotification.isChecked
        myProfileViewModel.uiState.value.enableDarkMode = enableDarkMode.isChecked
    }

    private fun removeListeners() {
        val backButton = findViewById<Button>(R.id.back_button)
        val saveButton = findViewById<Button>(R.id.save_button)

        backButton.setOnClickListener(null)
        saveButton.setOnClickListener(null)
    }
}