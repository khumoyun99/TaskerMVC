package com.example.taskermvc.models

data class Task(
    val id: Int = 0,
    val description: String,
    val type: Type,
    val date: String,
    val clock: String,
    val accomplished:Boolean
)
