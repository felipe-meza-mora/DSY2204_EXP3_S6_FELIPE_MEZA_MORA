package com.example.lili.screens

import android.content.Intent
import android.view.animation.OvershootInterpolator
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import com.example.lili.LoginActivity
import com.example.lili.MainActivity
import com.example.lili.R
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(){
    val context = LocalContext.current
    val scale = remember {
        androidx.compose.animation.core.Animatable(0f)
    }
    LaunchedEffect(key1 = true) {
        scale.animateTo(
            targetValue = 0.9f,
            animationSpec = tween(
                durationMillis = 800,
                easing = {
                    OvershootInterpolator(8f).getInterpolation(it)
                }
            )
        )

        delay(1500L)
        val intent = Intent(
            context,
            LoginActivity::class.java
        )
        context.startActivity(intent)

    }
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .scale(scale.value)
    ) {
        Image(painter = painterResource(id = R.drawable.logo),
            contentDescription = "Logo"
        )
        Text(
            text = "Bienvenidos",
            style = MaterialTheme.typography.titleLarge,
            color = Color(0xFF6F334C)
        )

    }
    
}
