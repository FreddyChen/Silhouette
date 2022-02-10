package com.freddy.silhouette.example

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.freddy.silhouette.widget.button.SleImageButton
import com.freddy.silhouette.widget.button.SleTextButton

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<SleTextButton>(R.id.stb_1).setOnClickListener { }
        findViewById<SleTextButton>(R.id.stb_2).setOnClickListener { }
        findViewById<SleTextButton>(R.id.stb_3).setOnClickListener { }
        findViewById<SleImageButton>(R.id.sib_1).setOnClickListener { }
        findViewById<SleImageButton>(R.id.sib_2).setOnClickListener { }
        findViewById<SleImageButton>(R.id.sib_3).setOnClickListener { }
    }
}