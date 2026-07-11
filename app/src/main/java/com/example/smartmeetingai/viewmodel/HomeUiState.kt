package com.example.smartmeetingai.viewmodel

import com.example.smartmeetingai.ai.models.AnalysisResult
import com.example.smartmeetingai.data.model.Note

data class HomeUiState(

    val notes: List<Note> = emptyList(),

    val selectedNote: Note? = null,

    val analysis: AnalysisResult? = null,

    val isLoading: Boolean = false,

    val error: String? = null
)