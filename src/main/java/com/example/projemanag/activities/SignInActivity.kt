package com.example.projemanag.activities

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.widget.Toast
import com.example.projemanag.R
import com.example.projemanag.firebase.FirestoreClass
import com.example.projemanag.models.User
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_sign_in.*

class SignInActivity : BaseActivity() {
    private lateinit var auth:FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)
        auth=FirebaseAuth.getInstance()

        setupActionBar()

        btn_sign_in.setOnClickListener {
            signInRegisteredUser()
        }
    }

    private fun setupActionBar() {
        setSupportActionBar(toolbar_sign_in_activity)

        val actionBar = supportActionBar
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true)
            actionBar.setHomeAsUpIndicator(R.drawable.ic_black_color_back_24dp)
        }

        toolbar_sign_in_activity.setNavigationOnClickListener { onBackPressed() }
    }

    private fun signInRegisteredUser(){
        val email:String=et_email_sign_in.text.toString().trim{it<=' '}
        val password:String=et_password_sign_in.text.toString().trim{it<=' '}

        if(validateForm(email,password)){
            showProgressDialog(resources.getString(R.string.please_wait))
            auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) { task ->
                    hideProgressDialog()
                    if (task.isSuccessful) {

                        FirestoreClass().loadUserData(this)
                    } else {
                        // If sign in fails, display a message to the user.
                        Log.w("Sign In", "createUserWithEmail:failure", task.exception)
                        Toast.makeText(this@SignInActivity, "Authentication failed.",
                            Toast.LENGTH_SHORT).show()
                    }
                }


        }
    }

//    private fun signInRegisteredUser() {
//        // Here we get the text from editText and trim the space
//        val email: String = et_email_sign_in.text.toString().trim { it <= ' ' }
//        val password: String = et_password_sign_in.text.toString().trim { it <= ' ' }
//
//        if (validateForm(email, password)) {
//            // Show the progress dialog.
//            showProgressDialog(resources.getString(R.string.please_wait))
//
//            // Sign-In using FirebaseAuth
//            FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password)
//                .addOnCompleteListener { task ->
//                    hideProgressDialog()
//                    if (task.isSuccessful) {
//
//                        Toast.makeText(
//                            this@SignInActivity,
//                            "You have successfully signed in.",
//                            Toast.LENGTH_LONG
//                        ).show()
//
//                        startActivity(Intent(this@SignInActivity, MainActivity::class.java))
//                    } else {
//                        Toast.makeText(
//                            this@SignInActivity,
//                            task.exception!!.message,
//                            Toast.LENGTH_LONG
//                        ).show()
//                    }
//                }
//        }
//    }

    private fun validateForm(email: String, password: String): Boolean {
        return if (TextUtils.isEmpty(email)) {
            showErrorSnackBar("Please enter email.")
            false
        } else if (TextUtils.isEmpty(password)) {
            showErrorSnackBar("Please enter password.")
            false
        } else {
            true
        }
    }

    fun signInSuccess(user:User){
        hideProgressDialog()
        startActivity(Intent(this@SignInActivity,MainActivity::class.java ))
        finish()
    }
}