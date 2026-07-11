package com.example.smartmeetingai.ai

import javax.inject.Inject

class KeywordExtractor
    @Inject
    constructor() {
        private val stopWords =
            setOf(
                "the", "a", "an", "is", "are", "was", "were",
                "to", "and", "of", "for", "on", "at",
                "this", "that", "it", "in", "we", "our",
                "with", "be", "by", "as", "from",
            )

        fun extract(text: String): List<String> {
            return text
                .lowercase()
                .replace("[^a-zA-Z ]".toRegex(), "")
                .split(" ")
                .filter {
                    it.length > 3 &&
                        !stopWords.contains(it)
                }
                .groupingBy { it }
                .eachCount()
                .toList()
                .sortedByDescending {
                    it.second
                }
                .take(8)
                .map {
                    it.first.replaceFirstChar(Char::uppercase)
                }
        }
    }
