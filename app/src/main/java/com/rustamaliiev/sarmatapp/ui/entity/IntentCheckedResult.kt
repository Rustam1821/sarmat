package com.rustamaliiev.sarmatapp.ui.entity

sealed class IntentCheckedResult {
    object Success : IntentCheckedResult()
    object Error : IntentCheckedResult()
}




