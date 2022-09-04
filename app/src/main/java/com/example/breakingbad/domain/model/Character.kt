package com.example.breakingbad.domain.model

data class Character(
    val id: Int,
    val name: String,
    val birthday: String,
    val occupation: List<String>,
    val img: String,
    val nickname: String,
    val portrayed: String
)
