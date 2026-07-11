package com.example.smartmeetingai.ai

import javax.inject.Inject

class ActionItemExtractor
    @Inject
    constructor() {
        private val actionWords =
            listOf(
                "will",
                "should",
                "must",
                "need",
                "assign",
                "complete",
                "finish",
                "review",
                "update",
                "fix",
            )

        fun extract(text: String): List<String> {
            return text
                .split(".", "\n")
                .map {
                    it.trim()
                }
                .filter {
                        sentence ->

                    actionWords.any {
                        sentence.contains(it, true)
                    }
                }
        }
    }
