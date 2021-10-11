package com.coderio.pocmvvmandroid.registration

sealed class LoginActions {
    class OnLoginResponse(val token: String): LoginActions()
}