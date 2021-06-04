package com.rustamaliiev.sarmatapp.ui.entity

sealed class IntentCheckedResult {
    object Success : IntentCheckedResult() {
        val result
            get() = true
    }

    object Error : IntentCheckedResult()
}




