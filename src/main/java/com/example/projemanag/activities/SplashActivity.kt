package com.example.projemanag.activities

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.TextView
import com.example.projemanag.R
import com.example.projemanag.firebase.FirestoreClass


class SplashActivity : AppCompatActivity() {
    private var tv_app_name:TextView?=null
    @SuppressLint("SuspiciousIndentation")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        tv_app_name=findViewById(R.id.tv_app_name)

        val typeface:Typeface = Typeface.createFromAsset(assets, "bl.ttf")
        tv_app_name?.typeface = typeface

            Handler().postDelayed({

                var currentUserId=FirestoreClass().getCurrentUserId()
                if(currentUserId.isNotEmpty()){
                    startActivity(Intent(this@SplashActivity, MainActivity::class.java))
                }else{
                    startActivity(Intent(this@SplashActivity, IntroActivity::class.java))
                }
                finish()
            },2500)

    }
}