package com.coderio.pocmvvmandroid.common.protocol

sealed class ProtocolAction {
    class OnLoginOk(val token: String): ProtocolAction()
}