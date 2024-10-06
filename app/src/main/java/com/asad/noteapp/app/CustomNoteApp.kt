package com.asad.noteapp.app

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.hilt.work.HiltWorkerFactory
import androidx.work.Configuration
import androidx.work.ListenableWorker
import androidx.work.WorkerFactory
import androidx.work.WorkerParameters
import com.asad.noteapp.core.data.NoteAppNotificationManager
import com.asad.noteapp.core.data.ReminderWorker
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject


@HiltAndroidApp
class CustomNoteApp : Application(), Configuration.Provider {
//    @Inject lateinit var workerFactory : HiltWorkerFactory
//
//    override val workManagerConfiguration: Configuration
//        get() = Configuration.Builder()
//            .setWorkerFactory(workerFactory)
//            .build()


    //    @Inject
//    lateinit var workerFac tory: HiltWorkerFactory
//
//    override val workManagerConfiguration: Configuration
//        get() = Configuration.Builder()
//            .setWorkerFactory(workerFactory)
//            .build()

    @Inject
    lateinit var customWorkerFactory: CustomWorkerFactory
    override val workManagerConfiguration: Configuration
        get() = Configuration.Builder()
            .setMinimumLoggingLevel(Log.DEBUG)
            .setWorkerFactory(customWorkerFactory)
            .build()
}

class CustomWorkerFactory @Inject constructor(
    private val notificationManager: NoteAppNotificationManager
) : WorkerFactory() {
    override fun createWorker(
        appContext: Context,
        workerClassName: String,
        workerParameters: WorkerParameters
    ): ListenableWorker =
        ReminderWorker(
            context = appContext,
            params = workerParameters,
            notificationManager = notificationManager
        )
}