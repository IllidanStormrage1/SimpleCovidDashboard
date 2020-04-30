package ru.zkv.covid19app.presentation.view

import android.os.Bundle
import moxy.MvpAppCompatActivity
import moxy.MvpView
import ru.zkv.covid19app.R

class MainActivity : MvpAppCompatActivity(), MvpView {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }
}
