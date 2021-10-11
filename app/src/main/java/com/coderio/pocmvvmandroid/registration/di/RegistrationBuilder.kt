package com.coderio.pocmvvmandroid.registration.di

import com.coderio.pocmvvmandroid.mainapplication.di.scopes.FragmentScope
import com.coderio.pocmvvmandroid.registration.LoginFragment
import com.coderio.pocmvvmandroid.registration.di.module.LoginModule
import com.coderio.pocmvvmandroid.registration.service.RegistrationService
import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector
import retrofit2.Retrofit

@Module
abstract class RegistrationBuilder {

    @FragmentScope
    @ContributesAndroidInjector(modules = [LoginModule::class])
    abstract fun bindLoginFragment(): LoginFragment

    @Module
    companion object {
        @Provides
        fun provideRegistrationService(retrofit: Retrofit): RegistrationService {
            return retrofit.create(RegistrationService::class.java)
        }
    }
}