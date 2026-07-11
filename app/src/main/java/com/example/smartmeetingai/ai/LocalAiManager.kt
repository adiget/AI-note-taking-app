package com.example.smartmeetingai.ai

import com.example.smartmeetingai.ai.gemini.GeminiNanoEngine
import com.example.smartmeetingai.ai.rule.RuleBasedEngine
import com.example.smartmeetingai.ai.models.AnalysisResult
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocalAiManager
    @Inject
    constructor(
        private val geminiNanoEngine: GeminiNanoEngine,
        private val ruleBasedEngine: RuleBasedEngine,
    ) {


    suspend fun analyze(text: String): AnalysisResult {
        return if (geminiNanoEngine.isAvailable()) {
            geminiNanoEngine.analyze(text)
        } else {
            ruleBasedEngine.analyze(text)
        }
    }
    }
