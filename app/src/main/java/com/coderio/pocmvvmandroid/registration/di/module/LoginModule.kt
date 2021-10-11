package com.coderio.pocmvvmandroid.registration.di.module

import androidx.lifecycle.ViewModel
import com.coderio.pocmvvmandroid.mainapplication.di.scopes.ViewModelKey
import com.coderio.pocmvvmandroid.registration.viewmodel.LoginViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class LoginModule {

    @Binds
    @IntoMap
    @ViewModelKey(LoginViewModel::class)
    abstract fun bindLoginViewModel(viewModel: LoginViewModel): ViewModel
}