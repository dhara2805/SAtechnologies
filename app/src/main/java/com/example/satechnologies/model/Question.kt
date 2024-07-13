package com.example.satechnologies.model

data class Question(
    val answerChoices: List<AnswerChoice>,
    val id: Int,
    val name: String,
    var selectedAnswerChoiceId: Any
)