package com.example.lili.screens

import DataProvider.recetaList
import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lili.DetalleActivity
import com.example.lili.R
import com.example.lili.RegistroActivity
import com.example.lili.model.Receta

@Composable
fun MainScreen(){

    val context = androidx.compose.ui.platform.LocalContext.current

    Column(
        modifier = Modifier.fillMaxSize().padding(bottom = 80.dp)
    ){
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

        LazyColumn(
            modifier = Modifier.weight(1f),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
           items(recetaList){ receta ->
               RecipeItem(receta = receta)
           }
        }


    }

}

@Composable
fun RecipeItem(receta: Receta) {
    val context = LocalContext.current

    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
            .clickable {
                // Navega a DetalleActivity pasando los detalles de la receta
                val intent = Intent(context, DetalleActivity::class.java)
                intent.putExtra("name", receta.name)
                intent.putExtra("description", receta.description)
                intent.putExtra("imageResId", receta.imageResId)
                intent.putStringArrayListExtra("steps", ArrayList(receta.steps))
                intent.putExtra("preparationTime", receta.preparationTime)
                intent.putExtra("calories", receta.calories)
                intent.putExtra("status", receta.status)
                context.startActivity(intent)
            },
        color = Color(0xFFFFD9E4)
    ) {
        Row(
            modifier = Modifier.padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = receta.imageResId),
                contentDescription = receta.name,
                modifier = Modifier.size(64.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = receta.name,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF6F334C),
                    maxLines = 1
                )
                Text(
                    text = receta.description,
                    color = Color(0xFFB2849A),
                    maxLines = 2
                )
                Text(text = "Tiempo de preparación: ${receta.preparationTime}", color = Color(0xFFB2849A))
                Text(text = "Calorías: ${receta.calories}", color = Color(0xFFB2849A))
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MainScreenPreview(){
    MainScreen()
}
