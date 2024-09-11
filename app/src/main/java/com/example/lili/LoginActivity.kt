package com.example.lili

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.lili.model.UserPreferences
import com.example.lili.screens.LoginScreen
import com.example.lili.ui.theme.LiliTheme
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking

class LoginActivity : ComponentActivity() {
    private lateinit var userPreferences: UserPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Inicializar UserPreferences
        userPreferences = UserPreferences(this)

        setContent {
            LiliTheme {
                LoginScreen(
                    onLoginClick = { correo, password ->
                        // Ejecutar el proceso de login
                        val isLoginSuccessful = validateLogin(correo, password)
                        if (isLoginSuccessful) {
                            // Si es exitoso, redirigir a MainActivity
                            val intent = Intent(this, MainActivity::class.java)
                            startActivity(intent)
                            finish()
                        } else {
                            // Mostrar mensaje de error
                            Toast.makeText(this, "Correo o contraseña incorrectos", Toast.LENGTH_LONG).show()
                        }
                    },
                    onForgotPasswordClick = {
                        // Redirigir a OlvideActivity
                        val intent = Intent(this, OlvideActivity::class.java)
                        startActivity(intent)
                    }
                )
            }
        }
    }

    // Función para validar el login
    private fun validateLogin(correo: String, password: String): Boolean {
        return runBlocking {
            // Obtener la lista de usuarios almacenados
            val usuarios = userPreferences.userListFlow.first()

            // Buscar si hay un usuario con el correo y la contraseña proporcionados
            val usuario = usuarios.find { it.correo == correo && it.password == password }

            return@runBlocking if (usuario != null) {
                // Si existe un usuario válido, guardar en las preferencias como usuario autenticado
                userPreferences.saveCurrentUser(usuario)
                true
            } else {
                false
            }
        }
    }
}