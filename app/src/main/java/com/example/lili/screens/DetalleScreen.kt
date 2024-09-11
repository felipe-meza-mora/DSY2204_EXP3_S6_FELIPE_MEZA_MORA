package com.example.lili.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lili.R

@Composable
fun DetalleScreen(
    name: String,
    description: String,
    imageResId: Int,
    steps: List<String>,
    preparationTime: String,
    calories: Int,
    status: String
) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {0
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

        Surface(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFFFD9E4))
        ) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                item {

                    Image(
                        painter = painterResource(id = imageResId),
                        contentDescription = name,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(200.dp)
                    )
                }

                item {

                    Text(
                        text = name,
                        fontSize = 30.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF6F334C)
                    )
                }

                item {
                    //
                    Text(
                        text = description,
                        fontSize = 18.sp,
                        color = Color(0xFFB2849A)
                    )
                }

                item {

                    Column {
                        Text(
                            text = "Tiempo de preparación: $preparationTime",
                            fontSize = 16.sp,
                            color = Color(0xFF6F334C)
                        )
                        Text(
                            text = "Calorías: $calories",
                            fontSize = 16.sp,
                            color = Color(0xFF6F334C)
                        )
                        Text(
                            text = "Estado: $status",
                            fontSize = 16.sp,
                            color = Color(0xFF6F334C)
                        )
                    }
                }

                item {

                    Text(
                        text = "Pasos para preparar:",
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF6F334C)
                    )
                }

                items(steps) { step ->

                    Text(
                        text = step,
                        fontSize = 16.sp,
                        color = Color(0xFFB2849A),
                        modifier = Modifier.padding(bottom = 4.dp)
                    )
                }
            }
        }
    }
}
