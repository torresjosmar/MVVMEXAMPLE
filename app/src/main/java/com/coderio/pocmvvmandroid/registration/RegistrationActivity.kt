package com.coderio.pocmvvmandroid.registration

import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment
import com.coderio.pocmvvmandroid.R
import com.coderio.pocmvvmandroid.common.CommunicationActivity
import com.coderio.pocmvvmandroid.common.dialogs.FragmentsController
import com.coderio.pocmvvmandroid.common.extentions.show
import com.coderio.pocmvvmandroid.common.protocol.ProtocolAction
import com.coderio.pocmvvmandroid.main.MainActivity
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_registration.*

class RegistrationActivity: CommunicationActivity() {

    private lateinit var hostFragment: NavHostFragment
    private val fragmentsController by lazy { FragmentsController(this) }
    private var token = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)
        hostFragment = supportFragmentManager.findFragmentById(R.id.mainNavigationFragment) as NavHostFragment
        setListeners()
    }

    override fun onFragmentEvent(action: ProtocolAction) {
        when (action) {
            is ProtocolAction.OnLoginOk -> showLoginOk(action.token)
            else -> { }
        }
    }

    private fun setListeners() {
        loginButton.setOnClickListener {
            fragmentsController.showLoginFragment()
        }
        startAppButton.setOnClickListener {
            startActivity(MainActivity.createIntent(this, token))
            finish()
        }
    }

    private fun showLoginOk(token: String) {
        this.token = token
        loginTextView?.show()
        loginButton?.isEnabled = false
        startAppButton.isEnabled = true
    }

    private fun callLoginExampleFunction() {
        hostFragment.requireFragmentManager().fragments.lastOrNull { it is LoginFragment }?.let {
            (it as LoginFragment).exampleFunction()
        }
    }
}