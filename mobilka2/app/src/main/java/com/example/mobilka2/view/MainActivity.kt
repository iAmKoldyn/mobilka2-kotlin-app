package com.example.mobilka2.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.mobilka2.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, GiphyFragment())
                .commit()
        }
    }
}
