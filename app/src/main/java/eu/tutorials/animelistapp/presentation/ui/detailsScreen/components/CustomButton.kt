package eu.tutorials.animelistapp.presentation.ui.detailsScreen.components

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun CustomButton(text: String, onClick: () -> Unit) {
    androidx.compose.material3.Button(onClick = {
        onClick()
    }) {
        Text(text)
    }
}