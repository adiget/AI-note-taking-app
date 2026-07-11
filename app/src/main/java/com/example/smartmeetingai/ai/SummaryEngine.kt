package com.example.smartmeetingai.ai

import javax.inject.Inject

class SummaryEngine
    @Inject
    constructor() {
        fun summarize(text: String): String {
            val sentences =
                text
                    .split(".", "!", "?")
                    .map { it.trim() }
                    .filter { it.isNotBlank() }

            if (sentences.isEmpty()) {
                return "No summary available."
            }

            return sentences
                .take(3)
                .joinToString(". ") + "."
        }
    }
