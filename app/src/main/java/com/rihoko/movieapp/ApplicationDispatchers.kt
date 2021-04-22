package com.rihoko.movieapp

import kotlinx.coroutines.*

object ApplicationDispatchers {
    var workerDispatcher = CoroutineScope(Dispatchers.Default + SupervisorJob())
    var ioDispatcher = CoroutineScope(Dispatchers.IO + Job())
    var uiDispatcher = CoroutineScope(Dispatchers.Main + Job())
    fun refreshIoDispatcher() {
        ioDispatcher.cancel()
        ioDispatcher = CoroutineScope(Dispatchers.IO + Job())
    }

    fun initDispatchers() {
        cancelDispatchers()
        workerDispatcher = CoroutineScope(Dispatchers.Default + SupervisorJob())
        ioDispatcher = CoroutineScope(Dispatchers.IO + Job())
        uiDispatcher = CoroutineScope(Dispatchers.Main + Job())
    }

    fun cancelDispatchers() {
        workerDispatcher.cancel()
        ioDispatcher.cancel()
        ioDispatcher.cancel()
    }
}