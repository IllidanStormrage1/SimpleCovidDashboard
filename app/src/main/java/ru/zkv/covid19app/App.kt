package ru.zkv.covid19app

import android.app.Application
import ru.zkv.covid19app.di.component.AppComponent

class App : Application() {

    companion object {
        lateinit var appComponent: AppComponent
            private set
    }

    override fun onCreate() {
        super.onCreate()
        appComponent = AppComponent.create(this)
    }
}