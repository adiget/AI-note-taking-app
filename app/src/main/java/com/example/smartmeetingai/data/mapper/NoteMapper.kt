package com.example.smartmeetingai.data.mapper

import com.example.smartmeetingai.data.local.NoteEntity
import com.example.smartmeetingai.data.model.Note

fun NoteEntity.toNote() = Note(
    id = id,
    title = title,
    content = content,
    createdAt = createdAt
)

fun Note.toEntity() = NoteEntity(
    id = id,
    title = title,
    content = content,
    createdAt = createdAt
)