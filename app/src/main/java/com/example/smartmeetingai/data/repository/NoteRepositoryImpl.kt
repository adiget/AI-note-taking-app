package com.example.smartmeetingai.data.repository

import com.example.smartmeetingai.data.local.NoteDao
import com.example.smartmeetingai.data.mapper.toEntity
import com.example.smartmeetingai.data.mapper.toNote
import com.example.smartmeetingai.data.model.Note

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NoteRepositoryImpl @Inject constructor(
    private val dao: NoteDao
) : NoteRepository {

    override fun getNotes(): Flow<List<Note>> {
        return dao.getNotes()
            .map { entities ->
                entities.map { entity ->
                    entity.toNote()
                }
            }
    }

    override suspend fun insert(
        note: Note
    ) {
        dao.insert(
            note.toEntity()
        )
    }

    override suspend fun delete(
        note: Note
    ) {
        dao.delete(
            note.toEntity()
        )
    }

    override suspend fun getNote(
        id: Long
    ): Note? {
        return dao.getNote(id)?.toNote()
    }
}