package com.rihoko.movieapp.data

import android.content.Context
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.rihoko.movieapp.DIThisLater
import java.lang.Exception

class BackgroundDBUpdateWorker(context: Context, workerParameters: WorkerParameters) :
    CoroutineWorker(context, workerParameters) {
    override suspend fun doWork(): Result {
        val repository = DIThisLater.movieRepository
        return try {
            repository.updateCache()
            Result.success()
        }catch (exception : Exception){
            Log.d(this::class.java.simpleName,exception.message!!)
            Result.retry()
        }
    }
}