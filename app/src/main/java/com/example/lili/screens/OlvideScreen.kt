import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lili.model.UserPreferences
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OlvideScreen(onBackToLogin: () -> Unit, onVolverClick: () -> Unit) {
    var correo by remember { mutableStateOf(TextFieldValue("")) }
    var edad by remember { mutableStateOf(TextFieldValue("")) }
    var showPasswordModal by remember { mutableStateOf(false) }
    var recoveredPassword by remember { mutableStateOf("") }
    var userNotFoundError by remember { mutableStateOf(false) } // Para manejar el error de usuario no encontrado

    var correoError by remember { mutableStateOf(false) }
    var edadError by remember { mutableStateOf(false) }

    // Para interactuar con UserPreferences
    val context = LocalContext.current
    val userPreferences = remember { UserPreferences(context) }
    val coroutineScope = rememberCoroutineScope()

    // Validación de campos
    fun validateFields(): Boolean {
        correoError = correo.text.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(correo.text).matches()
        edadError = edad.text.isEmpty() || edad.text.toIntOrNull()?.let { it < 18 } ?: true
        return !correoError && !edadError
    }

    // Función para recuperar la contraseña desde UserPreferences
    fun recuperarContrasena(correo: String, edad: Int) {
        coroutineScope.launch {
            val usuarios = userPreferences.userListFlow.first() // Obtiene la lista de usuarios desde UserPreferences
            val usuario = usuarios.find { it.correo == correo && it.edad == edad }

            if (usuario != null) {
                recoveredPassword = usuario.password
                showPasswordModal = true
                userNotFoundError = false
            } else {
                userNotFoundError = true // Mostrar error si no se encuentra al usuario
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Icono para volver al login, más arriba
        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.TopStart
        ) {
            IconButton(onClick = { onVolverClick() }) {
                Icon(
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = "Volver al login",
                    tint = Color(0xFF6F334C),
                    modifier = Modifier.size(32.dp)
                )
            }
        }

        Spacer(modifier = Modifier.height(50.dp))

        Icon(
            imageVector = Icons.Filled.Lock,
            contentDescription = "Icono de seguridad",
            tint = Color(0xFF6F334C),
            modifier = Modifier.size(80.dp) // Hacer el candado más grande
        )

        Spacer(modifier = Modifier.height(24.dp))

        // Input Correo
        OutlinedTextField(
            value = correo,
            onValueChange = {
                correo = it
                correoError = it.text.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(it.text).matches()
            },
            label = { Text("Correo Electrónico", color = Color(0xFF6F334C)) },
            isError = correoError,
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = Color(0xFF6F334C),
                cursorColor = Color(0xFF6F334C)
            )
        )
        if (correoError) {
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = "Correo inválido", color = Color(0xFFB2849A), fontSize = 18.sp, modifier = Modifier.padding(start = 8.dp))
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Input Edad
        OutlinedTextField(
            value = edad,
            onValueChange = {
                edad = it
                edadError = it.text.isEmpty() || it.text.toIntOrNull()?.let { it < 18 } ?: true
            },
            label = { Text("Edad", color = Color(0xFF6F334C)) },
            isError = edadError,
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = Color(0xFF6F334C),
                cursorColor = Color(0xFF6F334C)
            )
        )
        if (edadError) {
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = "Debes ser mayor de 18 años", color = Color(0xFFB2849A), fontSize = 18.sp, modifier = Modifier.padding(start = 8.dp))
        }

        if (userNotFoundError) {
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = "Usuario no encontrado", color = Color.Red, fontSize = 16.sp)
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Botón de Recuperar Contraseña
        Button(
            onClick = {
                if (validateFields()) {
                    val edadInt = edad.text.toIntOrNull() ?: 0
                    recuperarContrasena(correo.text, edadInt)
                }
            },
            enabled = !correoError && !edadError,
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF6F334C),
                contentColor = Color.White
            )
        ) {
            Text(text = "Recuperar Contraseña", fontSize = 20.sp)
        }
    }

// Modal de Contraseña Recuperada
    if (showPasswordModal) {
        AlertDialog(
            onDismissRequest = {
                showPasswordModal = false
                onBackToLogin()
            },
            title = {
                Text(
                    text = "¡Contraseña Recuperada!",
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF6F334C),
                    fontSize = 20.sp
                )
            },
            text = {
                Column(
                    modifier = Modifier
                        .padding(vertical = 16.dp)
                        .fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Tu contraseña es:",
                        fontSize = 18.sp,
                        color = Color(0xFFB2849A)
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = recoveredPassword,
                        fontWeight = FontWeight.Bold,
                        fontSize = 22.sp,
                        color = Color(0xFF6F334C)
                    )
                }
            },
            confirmButton = {
                Button(
                    onClick = {
                        showPasswordModal = false
                        onBackToLogin() // Redirigir al login
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF6F334C),
                        contentColor = Color.White
                    )
                ) {
                    Text("Aceptar", fontSize = 18.sp)
                }
            },
            shape = RoundedCornerShape(16.dp),
            modifier = Modifier
                .padding(16.dp)
                .wrapContentSize()
        )
    }
}
