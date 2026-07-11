package com.example.smartmeetingai.ai

import com.example.smartmeetingai.ai.models.AnalysisResult

interface AiEngine {
    val engineName: String

    suspend fun analyze(text: String): AnalysisResult

    fun isAvailable(): Boolean
}
