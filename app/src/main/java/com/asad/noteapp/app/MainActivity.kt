package com.asad.noteapp.app

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.asad.noteapp.app.navigation.NoteApp
import com.asad.noteapp.theme.NoteAppTheme
import dagger.hilt.android.AndroidEntryPoint

const val TAG = "MAIN_ACTIVITY"

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        processIntent(intent = intent)
        setContent {
            NoteAppTheme() {
                NoteApp()
            }
        }
    }

    private fun processIntent(intent: Intent) {
        if (intent?.data?.scheme == "open" && intent.data?.host == "noteapp") {
            Log.d(TAG, "onCreate : scheme: ${intent.data?.scheme}")
            Log.d(TAG, "onCreate : host: ${intent.data?.host}")
            Log.d(TAG, "onCreate : pathSegments: ${intent.data?.pathSegments}")
        }
//        (intent.extras?.get("route") as String?)?.let {}
    }
}



