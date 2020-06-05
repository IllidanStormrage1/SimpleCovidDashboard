package ru.zkv.covid19app.presentation.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ru.zkv.covid19app.R
import ru.zkv.covid19app.presentation.view.mainFragment.MainFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        savedInstanceState ?: addMainFragment()
    }

    private fun addMainFragment() {
        supportFragmentManager
            .beginTransaction()
            .add(R.id.container, MainFragment.newInstance(), MainFragment.TAG)
            .addToBackStack(null)
            .commit()
    }
}
