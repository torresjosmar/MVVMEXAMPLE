package com.coderio.pocmvvmandroid.mainapplication.di.component

import android.app.Application
import com.coderio.pocmvvmandroid.main.products.di.ProductsBuilder
import com.coderio.pocmvvmandroid.mainapplication.MainApplication
import com.coderio.pocmvvmandroid.mainapplication.di.ActivityBuilder
import com.coderio.pocmvvmandroid.mainapplication.di.module.MainModule
import com.coderio.pocmvvmandroid.registration.di.RegistrationBuilder
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        MainModule::class,
        ActivityBuilder::class,
        RegistrationBuilder::class,
        ProductsBuilder::class
    ]
)
interface MainComponent : AndroidInjector<DaggerApplication> {

    fun inject(app: MainApplication)

    override fun inject(instance: DaggerApplication)

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): MainComponent
    }
}