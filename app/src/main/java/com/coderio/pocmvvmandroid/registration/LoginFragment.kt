package com.coderio.pocmvvmandroid.registration

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.coderio.pocmvvmandroid.R
import com.coderio.pocmvvmandroid.common.Outcome
import com.coderio.pocmvvmandroid.common.dialogs.base.BaseBottomSheetDialog
import com.coderio.pocmvvmandroid.common.extentions.hide
import com.coderio.pocmvvmandroid.common.extentions.show
import com.coderio.pocmvvmandroid.common.extentions.isNotEmpty
import com.coderio.pocmvvmandroid.common.extentions.onTextChanged
import com.coderio.pocmvvmandroid.common.protocol.ProtocolAction
import com.coderio.pocmvvmandroid.registration.viewmodel.LoginViewModel
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_login.*
import javax.inject.Inject

class LoginFragment: BaseBottomSheetDialog(){

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    lateinit var viewModel: LoginViewModel

    companion object {
        fun newInstance() = LoginFragment()
    }

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(LoginViewModel::class.java)
        lifecycle.addObserver(viewModel)
        listenToObserver()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mainContainer?.layoutParams = FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, getScreenHeight())
        loginButton.isEnabled = false
        setValidations()
        setClickListeners()
    }

    fun exampleFunction() {

    }

    private fun listenToObserver() {
        viewModel.outcome.observe(this, Observer {outcome ->
            when(outcome){
                is Outcome.Success -> onSuccess(outcome.data)
                is Outcome.Failure -> onError(outcome.e)
                else -> { }
            }
        })
    }

    private fun onSuccess(data: LoginActions) {
        when (data) {
            is LoginActions.OnLoginResponse -> {
                if (data.token.isNotEmpty()) {
                    communication.onFragmentEvent(ProtocolAction.OnLoginOk(data.token))
                    dismiss()
                } else {
                    loginErrorTextView.show()
                }
            }
        }
    }

    private fun onError(error: Throwable) {
        error.printStackTrace()
        Toast.makeText(context, error.message, Toast.LENGTH_SHORT).show()
    }

    private fun setClickListeners() {
        loginButton?.setOnClickListener {
            viewModel.loginUser(userEditText.text.toString(), passwordEditText.text.toString())
        }
    }

    private fun setValidations() {
        userEditText?.onTextChanged {
            loginButton?.isEnabled = it.isNotEmpty() && passwordEditText.isNotEmpty()
            loginErrorTextView.hide()
        }
        passwordEditText.onTextChanged {
            loginButton?.isEnabled = it.isNotEmpty() && userEditText.isNotEmpty()
            loginErrorTextView.hide()
        }
    }
}