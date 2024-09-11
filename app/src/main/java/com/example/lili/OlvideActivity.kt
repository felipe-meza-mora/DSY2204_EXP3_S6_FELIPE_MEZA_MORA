package com.example.lili

import OlvideScreen
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.lili.ui.theme.LiliTheme

class OlvideActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LiliTheme {
                OlvideScreen(
                    onBackToLogin = {
                        // Redirigir al LoginActivity cuando se cierra el modal
                        val intent = Intent(this, LoginActivity::class.java)
                        startActivity(intent)
                        finish()
                    },
                    onVolverClick = {
                        val intent = Intent(this, LoginActivity::class.java)
                        startActivity(intent)
                    }
                )
            }
        }
    }
}

