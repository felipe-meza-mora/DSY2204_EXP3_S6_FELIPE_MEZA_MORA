package com.example.lili.screens

import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lili.R
import com.example.lili.RegistroActivity


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(onLoginClick: (String, String) -> Unit, onForgotPasswordClick: () -> Unit) {
    val context = androidx.compose.ui.platform.LocalContext.current // Obtener el contexto
    var correo by remember { mutableStateOf(TextFieldValue("")) }
    var password by remember { mutableStateOf(TextFieldValue("")) }

    var correoError by remember { mutableStateOf(false) }
    var passwordError by remember { mutableStateOf(false) }

    // Validaciones
    fun validateFields() {
        correoError = correo.text.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(correo.text).matches()
        passwordError = password.text.isEmpty()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = 80.dp),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Encabezado con logo y texto
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .height(80.dp),
            color = Color(0xFFFFD9E4)
        ) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier.fillMaxSize()
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.logo),
                        contentDescription = "Logo",
                        modifier = Modifier.size(60.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "Lili Recetas",
                        color = Color(0xFF6F334C),
                        fontSize = 30.sp,
                        fontWeight = FontWeight.Bold,
                    )
                }
            }
        }

        // Formulario de inicio de sesión
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Input Correo
            OutlinedTextField(
                value = correo,
                onValueChange = {
                    correo = it
                    validateFields()
                },
                label = { Text("Correo Electrónico", color = Color(0xFF6F334C), fontSize = 18.sp) },
                isError = correoError,
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = Color(0xFF6F334C),
                    cursorColor = Color(0xFF6F334C)
                )
            )
            if (correoError) {
                Text(text = "Correo inválido", color = Color(0xFFB2849A), fontSize = 18.sp)
            }

            // Input Contraseña
            OutlinedTextField(
                value = password,
                onValueChange = {
                    password = it
                    validateFields()
                },
                label = { Text("Contraseña", color = Color(0xFF6F334C), fontSize = 18.sp) },
                isError = passwordError,
                modifier = Modifier.fillMaxWidth(),
                visualTransformation = PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = Color(0xFF6F334C),
                    cursorColor = Color(0xFF6F334C)
                )
            )
            if (passwordError) {
                Text(text = "La contraseña no puede estar vacía", color = Color(0xFFB2849A), fontSize = 18.sp)
            }

            // Botón de Iniciar Sesión
            Button(
                onClick = {
                    validateFields()
                    if (!correoError && !passwordError) {
                        onLoginClick(correo.text, password.text) // Pasar el correo y la contraseña al hacer clic
                    }
                },
                enabled = !correoError && !passwordError,
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF6F334C),
                    contentColor = Color.White
                )
            ) {
                Text(text = "Iniciar Sesión", fontSize = 20.sp)
            }

            //Botón registro
            Button(
                onClick = {
                    val intent = Intent(context, RegistroActivity::class.java)
                    context.startActivity(intent)
                },
                modifier = Modifier
                    .fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF6F334C),
                    contentColor = Color.White
                )
            ) {
                Text(text = "Regístrate", fontSize = 20.sp, fontWeight = FontWeight.Bold)
            }
        }

        // Recuperar contraseña
        TextButton(
            onClick = { onForgotPasswordClick() },
            modifier = Modifier.padding(horizontal = 16.dp)
        ) {
            Text(
                text = "¿Olvidaste tu contraseña?",
                color = Color(0xFF6F334C),
                fontSize = 18.sp
            )
        }
    }
}
