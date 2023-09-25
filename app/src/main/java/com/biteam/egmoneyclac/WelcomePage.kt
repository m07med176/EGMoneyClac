package com.biteam.egmoneyclac

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class WelcomePage : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome_page)
        supportActionBar!!.hide()

        lifecycleScope.launch {
            delay(500)
            startActivity( Intent(applicationContext, MainActivity::class.java))
            finish()
        }

    }
}