package com.example.satechnologies.model

data class Category(
    val id: Int,
    val name: String,
    val questions: List<Question>
)