package com.example.smartmeetingai.data.repository

import android.util.Log
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CloudRepositoryImpl @Inject constructor() : CloudRepository {
    override suspend fun syncNotes() {
        Log.d(
            "CloudRepository",
            "Syncing notes..."
        )

        // TODO:
        // Upload notes to cloud
    }
}