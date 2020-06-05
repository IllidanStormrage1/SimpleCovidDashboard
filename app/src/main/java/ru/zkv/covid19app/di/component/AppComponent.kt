package ru.zkv.covid19app.di.component

import android.content.Context
import dagger.BindsInstance
import dagger.Component
import ru.zkv.covid19app.di.module.NetworkModule
import ru.zkv.covid19app.presentation.view.MainActivity
import ru.zkv.covid19app.presentation.view.mainFragment.MainFragment
import javax.inject.Singleton

@Singleton
@Component(modules = [NetworkModule::class])
interface AppComponent {

    fun inject(mainFragment: MainFragment)

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun setContext(context: Context): Builder

        fun build(): AppComponent
    }

    companion object {
        fun create(context: Context): AppComponent =
            with(DaggerAppComponent.builder()) {
                setContext(context)
                build()
            }
    }
}