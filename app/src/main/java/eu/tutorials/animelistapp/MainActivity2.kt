package eu.tutorials.animelistapp

import android.annotation.SuppressLint
import android.content.Intent
import android.content.IntentFilter
import android.net.ConnectivityManager
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
import eu.tutorials.animelistapp.presentation.ui.myProfileScreen.ContentCardAdapter
import eu.tutorials.animelistapp.presentation.ui.myProfileScreen.MyProfileViewModel
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity2 : ComponentActivity() {
    private val myProfileViewModel: MyProfileViewModel by viewModels()
    private lateinit var networkChangeReceiver: NetworkChangeReceiver

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        networkChangeReceiver = createNetworkChangeReceiver(this)

        setContentView(R.layout.profile)
        val backButton = findViewById<Button>(R.id.back_button)
        val saveButton = findViewById<Button>(R.id.save_button)
        val notes = findViewById<EditText>(R.id.notes_field)
        val gender = findViewById<Spinner>(R.id.gender)
        val category = findViewById<Spinner>(R.id.category_spinner)
        val enableDarkMode = findViewById<CheckBox>(R.id.dark_mode)
        val receiveNotification = findViewById<CheckBox>(R.id.notification)
        backButton.setOnClickListener {
            setData(notes, gender, enableDarkMode, receiveNotification)
            onClick(null)
        }

        saveButton.setOnClickListener {
            setData(notes, gender, enableDarkMode, receiveNotification)
            saveButtonClick()
        }

        val recyclerView: RecyclerView = findViewById(R.id.favorite_recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(this)

        val adapter = ContentCardAdapter { url ->
            onClick(url)
        }
        recyclerView.adapter = adapter
        category.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View?,
                position: Int,
                id: Long,
            ) {
                val selectedCategory = parent.getItemAtPosition(position) as String

                if (selectedCategory == "Anime") {
                    adapter.submitList(myProfileViewModel.uiState.value.topFiveAnimes)
                } else if (selectedCategory == "Manga") {
                    adapter.submitList(myProfileViewModel.uiState.value.topfiveMangas)
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>) {}
        }
        lifecycleScope.launch {
            myProfileViewModel.uiState.collect { uiState ->
                adapter.submitList(uiState.topFiveAnimes)
                enableDarkMode.isChecked = uiState.enableDarkMode
                receiveNotification.isChecked = uiState.receiveNotifications
                notes.setText(uiState.note.toString())

                val selectedGender = uiState.gender
                val genderOptions = resources.getStringArray(R.array.genders)
                val genderIndex = genderOptions.indexOf(selectedGender)
                gender.setSelection(genderIndex)
            }
        }
    }

    override fun onStart() {
        super.onStart()
        val filter = IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION)
        registerReceiver(networkChangeReceiver, filter)
    }

    override fun onStop() {
        super.onStop()
        unregisterReceiver(networkChangeReceiver)
    }

    private fun onClick(url: String?) {
        saveButtonClick()
        intent = Intent(applicationContext, MainActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK).putExtra("destination", url)
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
}