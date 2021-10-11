package com.coderio.pocmvvmandroid.registration.service

import com.coderio.pocmvvmandroid.registration.model.LoginRequestData
import com.coderio.pocmvvmandroid.registration.model.LoginResponseData
import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.POST

interface RegistrationService {

    @POST("apimock/signin")
    fun login(@Body loginData: LoginRequestData): Single<LoginResponseData>
}