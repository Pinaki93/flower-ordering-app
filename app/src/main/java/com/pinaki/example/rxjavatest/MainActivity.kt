package com.pinaki.example.rxjavatest

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.pinaki.example.rxjavatest.fragments.FlowerListingFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager
                .beginTransaction()
                .replace(R.id.frame, FlowerListingFragment())
                .commit()

    }
}
