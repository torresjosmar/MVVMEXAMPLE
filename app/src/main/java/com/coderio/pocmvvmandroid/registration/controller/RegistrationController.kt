package com.coderio.pocmvvmandroid.registration.controller

import com.coderio.pocmvvmandroid.registration.model.LoginRequestData
import com.coderio.pocmvvmandroid.registration.repository.RegistrationRepository
import javax.inject.Inject

class RegistrationController @Inject constructor(
    private val registrationRepository: RegistrationRepository
) {

    fun loginUser(userName: String, password: String) =
        registrationRepository.login(LoginRequestData(userName, password))
}