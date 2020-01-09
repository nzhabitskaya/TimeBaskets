package com.mobile.android.chameapps.timebaskets.ui.main.impl

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.mobile.android.chameapps.timebaskets.R
import com.mobile.android.chameapps.timebaskets.application.MyApplication
import com.mobile.android.chameapps.timebaskets.ui.timetable.impl.TimetableFragment
import com.mobile.android.chameapps.timebaskets.ui.main.MainActivityContract
import javax.inject.Inject


class MainActivity : AppCompatActivity(),
    MainActivityContract.View {

    @Inject
    lateinit var presenter: MainActivityContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        injectDependency()
        presenter.attach(this)

        setFragment(TimetableFragment.newInstance())
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_basic, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            Toast.makeText(applicationContext, item.title, Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(applicationContext, item.title, Toast.LENGTH_SHORT).show()
        }
        return super.onOptionsItemSelected(item)
    }

    fun setFragment(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .commit()
    }

    private fun injectDependency() {
        (application as MyApplication).getAppComponent(this)!!.inject(this)
    }
}