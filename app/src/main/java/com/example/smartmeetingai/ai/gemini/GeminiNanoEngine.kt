package com.example.smartmeetingai.ai.gemini

import android.content.Context
import com.example.smartmeetingai.ai.AiEngine
import com.example.smartmeetingai.ai.models.AnalysisResult
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.tensorflow.lite.Interpreter
import org.tensorflow.lite.support.common.FileUtil

@Singleton
class GeminiNanoEngine @Inject constructor(
    @ApplicationContext private val context: Context,
) : AiEngine {

    private val interpreter: Interpreter? by lazy {
        runCatching {
            val model = FileUtil.loadMappedFile(context, "bert_model.tflite")
            Interpreter(model)
        }.getOrNull()
    }

    override val engineName: String
        get() = "Gemini Nano (TFLite)"

    override fun isAvailable(): Boolean = interpreter != null

    override suspend fun analyze(text: String): AnalysisResult = withContext(Dispatchers.Default) {
        AnalysisResult(
            summary = extractSummary(text),
            keywords = extractKeywords(text),
            actionItems = extractActionItems(text),
        )
    }

    private fun extractSummary(text: String): String {
        val sentences = text.split(Regex("[.!?]+"))
            .map { it.trim() }
            .filter { it.isNotEmpty() }

        return if (sentences.isEmpty()) text.take(100) else sentences.take(2).joinToString(". ") + "."
    }

    private fun extractKeywords(text: String): List<String> {
        val words = text.lowercase()
            .split(Regex("\\W+"))
            .filter { it.length > 4 && !isCommonWord(it) }

        return words.groupingBy { it }.eachCount()
            .entries
            .sortedByDescending { it.value }
            .take(5)
            .map { it.key }
    }

    private fun extractActionItems(text: String): List<String> {
        val verbs = setOf("implement", "create", "fix", "update", "review", "test", "deploy", "send", "schedule", "prepare")
        return text.split(Regex("[\\n•*-]+"))
            .map { it.trim() }
            .filter { it.isNotEmpty() && verbs.any { v -> it.lowercase().contains(v) } }
            .take(5)
    }

    private fun isCommonWord(word: String): Boolean {
        val common = setOf("the", "and", "that", "this", "from", "with", "have", "will", "would", "should", "could", "about")
        return word in common
    }
}