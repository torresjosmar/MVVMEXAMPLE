package com.coderio.pocmvvmandroid.registration.di.module

import com.coderio.pocmvvmandroid.common.dialogs.FragmentsController
import com.coderio.pocmvvmandroid.mainapplication.di.scopes.ActivityScope
import com.coderio.pocmvvmandroid.registration.RegistrationActivity
import dagger.Module
import dagger.Provides

@Module
class RegistrationActivityModule {

    @Provides
    @ActivityScope
    fun provideFragmentsController(activity: RegistrationActivity) = FragmentsController(activity)
}