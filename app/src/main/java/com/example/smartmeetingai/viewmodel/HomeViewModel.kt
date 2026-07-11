package com.example.smartmeetingai.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope

import com.example.smartmeetingai.ai.LocalAiManager
import com.example.smartmeetingai.data.model.Note
import com.example.smartmeetingai.data.repository.NoteRepository

import dagger.hilt.android.lifecycle.HiltViewModel

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

import kotlinx.coroutines.launch

import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(

    private val repository: NoteRepository,

    private val aiManager: LocalAiManager

) : ViewModel() {

    private val _uiState = MutableStateFlow(HomeUiState())

    val uiState: StateFlow<HomeUiState> =
        _uiState.asStateFlow()

    init {

        observeNotes()

    }

    private fun observeNotes() {

        viewModelScope.launch {

            repository.getNotes().collect { notes ->

                _uiState.update {

                    it.copy(notes = notes)

                }

            }

        }

    }

    fun addNote(

        title: String,

        content: String

    ) {

        viewModelScope.launch {

            repository.insert(

                Note(

                    title = title,

                    content = content,

                    createdAt = System.currentTimeMillis()

                )

            )

        }

    }

    fun selectNote(

        note: Note

    ) {

        _uiState.update {

            it.copy(

                selectedNote = note,

                analysis = null,

                error = null

            )

        }

    }

    fun analyzeSelectedNote() {

        val note = _uiState.value.selectedNote ?: return

        viewModelScope.launch {

            _uiState.update {

                it.copy(

                    isLoading = true,

                    error = null

                )

            }

            try {

                val result =
                    aiManager.analyze(note.content)

                _uiState.update {

                    it.copy(

                        analysis = result,

                        isLoading = false

                    )

                }

            } catch (e: Exception) {

                _uiState.update {

                    it.copy(

                        isLoading = false,

                        error = e.message

                    )

                }

            }

        }

    }

    fun deleteNote(

        note: Note

    ) {

        viewModelScope.launch {

            repository.delete(note)

        }

    }

    fun restoreNote(

        note: Note

    ) {

        viewModelScope.launch {

            repository.insert(note)

        }

    }

}