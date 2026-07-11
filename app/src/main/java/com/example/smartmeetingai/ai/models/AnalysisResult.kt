package com.example.smartmeetingai.ai.models

data class AnalysisResult(
    val summary: String,
    val keywords: List<String>,
    val actionItems: List<String>,
)
