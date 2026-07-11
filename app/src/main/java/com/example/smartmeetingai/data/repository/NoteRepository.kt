package com.example.smartmeetingai.data.repository

import com.example.smartmeetingai.data.model.Note
import kotlinx.coroutines.flow.Flow

interface NoteRepository {

    fun getNotes(): Flow<List<Note>>

    suspend fun insert(note: Note)

    suspend fun delete(note: Note)

    suspend fun getNote(id: Long): Note?
}