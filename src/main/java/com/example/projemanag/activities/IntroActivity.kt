package com.example.projemanag.activities

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.AppCompatButton
import com.example.projemanag.R

class IntroActivity : BaseActivity() {
    private var btn_sign_up_intro:AppCompatButton?=null
    private var btn_sign_in_intro:AppCompatButton?=null

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intro)
        btn_sign_up_intro=findViewById(R.id.btn_sign_up_intro)
        btn_sign_in_intro=findViewById(R.id.btn_sign_in_intro)

        btn_sign_up_intro!!.setOnClickListener {
            startActivity(Intent(this, SignUpActivity::class.java))
        }

        btn_sign_in_intro!!.setOnClickListener {
            startActivity(Intent(this, SignInActivity::class.java))
        }



    }
}