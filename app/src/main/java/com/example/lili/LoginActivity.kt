package com.example.lili

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.lili.model.UserPreferences
import com.example.lili.model.Usuario
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
                        val usuario = validateLogin(correo, password)
                        if (usuario != null) {
                            // Si es exitoso, redirigir a MainActivity con el nombre del usuario
                            val intent = Intent(this, MainActivity::class.java).apply {
                                putExtra("USER_NAME", usuario.nombre)  // Pasar el nombre del usuario
                            }
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

    // Función para validar el login y devolver el usuario
    private fun validateLogin(correo: String, password: String): Usuario? {
        return runBlocking {
            // Obtener la lista de usuarios almacenados
            val usuarios = userPreferences.userListFlow.first()

            // Buscar si hay un usuario con el correo y la contraseña proporcionados
            return@runBlocking usuarios.find { it.correo == correo && it.password == password }
        }
    }
}