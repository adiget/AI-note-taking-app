package com.example.smartmeetingai.data.repository

interface CloudRepository {
    suspend fun syncNotes()
}