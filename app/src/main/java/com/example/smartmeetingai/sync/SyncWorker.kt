package com.example.smartmeetingai.sync

import android.content.Context
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.example.smartmeetingai.data.repository.CloudRepository
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject

@HiltWorker
class SyncWorker @AssistedInject constructor(
    @Assisted
    appContext: Context,

    @Assisted
    params: WorkerParameters,

    private val cloudRepository: CloudRepository

) : CoroutineWorker(
    appContext,
    params
) {

    override suspend fun doWork(): Result {
        return try {
            cloudRepository.syncNotes()
            Result.success()
        } catch (e: Exception) {
            Result.retry()
        }
    }
}