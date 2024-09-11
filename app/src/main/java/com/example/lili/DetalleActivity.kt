package com.example.lili

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.example.lili.screens.DetalleScreen


class DetalleActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val name = intent.getStringExtra("name") ?: ""
        val description = intent.getStringExtra("description") ?: ""
        val imageResId = intent.getIntExtra("imageResId", 0)
        val steps = intent.getStringArrayListExtra("steps") ?: arrayListOf()
        val preparationTime = intent.getStringExtra("preparationTime") ?: ""
        val calories = intent.getIntExtra("calories", 0)
        val status = intent.getStringExtra("status") ?: ""

        setContent {
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = MaterialTheme.colorScheme.background
            ) {
                DetalleScreen(
                    name = name,
                    description = description,
                    imageResId = imageResId,
                    steps = steps,
                    preparationTime = preparationTime,
                    calories = calories,
                    status = status
                )
            }
        }
    }
}

