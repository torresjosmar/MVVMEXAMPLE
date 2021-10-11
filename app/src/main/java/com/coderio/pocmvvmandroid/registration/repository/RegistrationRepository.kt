package com.coderio.pocmvvmandroid.registration.repository

import com.coderio.pocmvvmandroid.registration.model.LoginRequestData
import com.coderio.pocmvvmandroid.registration.service.RegistrationService
import javax.inject.Inject

class RegistrationRepository@Inject constructor(
    private val registrationService: RegistrationService
) {

    fun login(loginData: LoginRequestData) = registrationService.login(loginData)
}