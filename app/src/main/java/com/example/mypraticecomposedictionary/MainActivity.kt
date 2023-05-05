package com.example.mypraticecomposedictionary

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.example.mypraticecomposedictionary.controller.MainController
import com.example.mypraticecomposedictionary.ui.theme.MyPraticeComposeDictionaryTheme
import com.example.mypraticecomposedictionary.viewmodel.MainViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyPraticeComposeDictionaryTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val mainViewModel = MainViewModel()
                    MainController(
                        modifier = Modifier.fillMaxSize(1F),
                        mainViewModel = mainViewModel
                    )
                }
            }
        }
    }
}