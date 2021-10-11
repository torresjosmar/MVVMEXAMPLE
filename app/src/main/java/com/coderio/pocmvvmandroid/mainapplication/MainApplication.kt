package com.coderio.pocmvvmandroid.mainapplication

import com.coderio.pocmvvmandroid.mainapplication.di.component.DaggerMainComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication

class MainApplication : DaggerApplication() {

    companion object {
        lateinit var instance: MainApplication
            private set
    }

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerMainComponent.builder()
            .application(this)
            .build().apply { inject(this@MainApplication) }
    }
}