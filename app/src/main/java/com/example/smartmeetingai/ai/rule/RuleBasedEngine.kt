package com.example.smartmeetingai.ai.rule

import com.example.smartmeetingai.ai.ActionItemExtractor
import com.example.smartmeetingai.ai.AiEngine
import com.example.smartmeetingai.ai.KeywordExtractor
import com.example.smartmeetingai.ai.SummaryEngine
import com.example.smartmeetingai.ai.models.AnalysisResult
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RuleBasedEngine @Inject constructor(

    private val summaryEngine: SummaryEngine,
    private val keywordExtractor: KeywordExtractor,
    private val actionExtractor: ActionItemExtractor

) : AiEngine {

    override val engineName = "Rule Based"

    override fun isAvailable() = true

    override suspend fun analyze(
        text: String
    ): AnalysisResult {

        return AnalysisResult(

            summary = summaryEngine.summarize(text),

            keywords = keywordExtractor.extract(text),

            actionItems = actionExtractor.extract(text)

        )
    }
}