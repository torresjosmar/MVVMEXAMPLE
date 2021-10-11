package com.coderio.pocmvvmandroid.main

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment
import com.coderio.pocmvvmandroid.R
import com.coderio.pocmvvmandroid.common.CommunicationActivity
import com.coderio.pocmvvmandroid.common.TOKEN
import com.coderio.pocmvvmandroid.common.dialogs.FragmentsController
import com.coderio.pocmvvmandroid.common.protocol.ProtocolAction
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity: CommunicationActivity() {

    companion object {
        fun createIntent(context: Context, token: String): Intent {
            return Intent(context, MainActivity::class.java).apply {
                putExtra(TOKEN, token)
            }
        }
    }

    private lateinit var hostFragment: NavHostFragment
    private val fragmentsController by lazy { FragmentsController(this) }
    private var token = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        hostFragment = supportFragmentManager.findFragmentById(R.id.mainNavigationFragment) as NavHostFragment
        setListeners()
        token = intent.getStringExtra(TOKEN) ?: ""
    }

    override fun onFragmentEvent(action: ProtocolAction) {
        when(action) {

        }
    }

    private fun setListeners() {
        listProductsButton.setOnClickListener {
            fragmentsController.showProductsFragment(token)
        }
    }
}