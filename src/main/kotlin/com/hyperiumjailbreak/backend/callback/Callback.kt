package com.hyperiumjailbreak.backend.callback

class Callback {
    private var callbackText = ""
    private var callbackCode = 0

    fun sendText(callback: String) {
        callbackText = callback
    }

    fun sendCode(code: Int) {
        if (code > 2 || code == 0) {
            return
        }
        callbackCode = code
    }

    fun isSuccess() : Boolean {
        if (callbackCode == 1) {
            return true
        }
        return false
    }

    fun getCallbackCode() : Int = callbackCode

    fun getCallbackText() : String = callbackText
}