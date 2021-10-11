package com.coderio.pocmvvmandroid.mainapplication.di

import com.coderio.pocmvvmandroid.main.MainActivity
import com.coderio.pocmvvmandroid.registration.RegistrationActivity
import com.coderio.pocmvvmandroid.mainapplication.di.scopes.ActivityScope

import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module(includes = [])
abstract class ActivityBuilder {
    @ActivityScope
    @ContributesAndroidInjector
    abstract fun bindRegistrationActivity(): RegistrationActivity

    @ActivityScope
    @ContributesAndroidInjector
    abstract fun bindMainActivity(): MainActivity
}