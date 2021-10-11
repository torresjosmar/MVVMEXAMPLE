package com.coderio.pocmvvmandroid.registration.viewmodel

import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.OnLifecycleEvent
import androidx.lifecycle.Lifecycle
import com.coderio.pocmvvmandroid.common.Outcome
import com.coderio.pocmvvmandroid.common.extentions.applySchedulers
import com.coderio.pocmvvmandroid.common.extentions.plusAssign
import com.coderio.pocmvvmandroid.registration.LoginActions
import com.coderio.pocmvvmandroid.registration.controller.RegistrationController
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class LoginViewModel @Inject constructor(
    private val registrationController: RegistrationController
): ViewModel(), LifecycleObserver {

    val outcome by lazy { MutableLiveData<Outcome<LoginActions>>() }
    private val disposable = CompositeDisposable()

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    fun onStop() {
        disposable.dispose()
    }

    fun loginUser(userName: String, password: String) {
        disposable += registrationController.loginUser(userName, password)
            .applySchedulers()
            .subscribe({response ->
                outcome.postValue(Outcome.success(LoginActions.OnLoginResponse(response.token)))
            }, {
                outcome.postValue(Outcome.failure(it))
            })
    }
}