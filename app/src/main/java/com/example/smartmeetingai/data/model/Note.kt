package com.example.smartmeetingai.data.model

data class Note(
    val id: Long = 0,
    val title: String,
    val content: String,
    val createdAt: Long,
    val summary: String? = null,
    val keywords: List<String> = emptyList(),
    val actionItems: List<String> = emptyList(),
)
