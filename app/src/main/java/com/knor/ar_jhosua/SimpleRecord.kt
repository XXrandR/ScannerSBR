package com.knor.ar_jhosua

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.knor.ar_jhosua.BaseApp as BaseApp1

class SimpleRecord : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_simple_record)
        

        val intent = Intent(this,BaseApp1::class.java)
        startActivity(intent)
    }
}