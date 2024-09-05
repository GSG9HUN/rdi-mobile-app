package eu.tutorials.animelistapp.presentation.ui.detailsScreen.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun Tag(name: String) {
    Card(
        shape = RoundedCornerShape(16.dp),
        modifier = Modifier.padding(4.dp),
        elevation = CardDefaults.cardElevation(2.dp)
    ) {
        Text(
            text = name,
            style = MaterialTheme.typography.bodySmall,
            color = Color.White,
            modifier = Modifier
                .background(MaterialTheme.colorScheme.primary)
                .padding(vertical = 4.dp, horizontal = 8.dp)
        )
    }
}