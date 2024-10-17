package eu.tutorials.animelistapp

import android.content.Context
import android.widget.Toast

fun createNetworkChangeReceiver(context: Context): NetworkChangeReceiver {
    return NetworkChangeReceiver { isConnected ->
        if (isConnected) {
            Toast.makeText(context, "Internet connection is returned.", Toast.LENGTH_LONG).show()
        } else {
            Toast.makeText(context, "No internet connection.", Toast.LENGTH_LONG).show()
        }
    }
}
