package com.example.lili.model

data class Receta (
    val name: String,
    val description: String,
    val imageResId: Int,
    val steps: List<String>,
    val preparationTime: String,
    val calories: Int,
    val status: String
)