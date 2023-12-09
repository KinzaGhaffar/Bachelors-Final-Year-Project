package com.example.speechrecificationapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import java.util.*
import android.text.TextUtils
import android.text.TextWatcher
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.view.WindowManager
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.*
import com.chaquo.python.PyObject
import com.chaquo.python.Python
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import java.util.regex.Matcher
import java.util.regex.Pattern

@Suppress("DEPRECATION")
class Signup_Activity : AppCompatActivity()
{
    val SPLASH_SCREEN = 5000
    private var mIsShowPass = false
    private var mIsShowConfirmPass = false

    private lateinit var topAnimation : Animation
    private lateinit var bottomAnimation : Animation

    private lateinit var auth: FirebaseAuth
    private lateinit var databaseReference: DatabaseReference

    //lateinit var googleSignInClient : GoogleSignInClient
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(R.layout.activity_signup_)

//        val TextVoice_id = findViewById<TextView>(R.id.TextVoice)
//        TextVoice_id.text=getpython()

        //for successful login..without this line user will not be able to login
        auth = FirebaseAuth.getInstance()

        topAnimation = AnimationUtils.loadAnimation(this, R.anim.top_animations)
        bottomAnimation = AnimationUtils.loadAnimation(this,R.anim.bottom_animations)


        val logo_id = findViewById<ImageView>(R.id.logo)
        val entername_id = findViewById<EditText>(R.id.entername)
        val enteremail_id = findViewById<EditText>(R.id.enteremail)
        //val btnGoogle_id = findViewById<Button>(R.id.btnGoogle)


        val enterpassword_id = findViewById<EditText>(R.id.enterpassword)
        val enterconfirmpassword_id = findViewById<EditText>(R.id.enterconfirmpassword)
        val btnSignUp_id = findViewById<Button>(R.id.btnSignUp)
        val btnLogin_id = findViewById<Button>(R.id.btnLogin)
        val signup_password_visibility_id = findViewById<ImageView>(R.id.signup_password_visibility)
        val signup_confirm_password_visibility_id = findViewById<ImageView>(R.id.signup_confirm_password_visibility)


        logo_id.animation = topAnimation
        entername_id.animation = topAnimation
        enteremail_id.animation = topAnimation
        enterpassword_id.animation = bottomAnimation
        signup_password_visibility_id.animation = bottomAnimation
        enterconfirmpassword_id.animation = bottomAnimation
        signup_confirm_password_visibility_id.animation = bottomAnimation
        btnSignUp_id.animation = bottomAnimation
        btnLogin_id.animation = bottomAnimation
//        btnGoogle_id.animation = bottomAnimation

//        //Animation on ChattingApp Icon
//        chat_animated_view_id.setOnClickListener{
//            chat_animated_view_id.animate().apply {
//                duration=1000
//                rotationYBy(360f)
//            }.withEndAction{
//                chat_animated_view_id.animate().apply {
//                    duration=1000
//                    rotationYBy(360f)
//                }.start()
//            }
//        }
//        auth = FirebaseAuth.getInstance()


        signup_password_visibility_id.setOnClickListener {
            mIsShowPass = !mIsShowPass
            showPassword(mIsShowPass)
        }

        signup_confirm_password_visibility_id.setOnClickListener {
            mIsShowConfirmPass = !mIsShowConfirmPass
            showConfirmPassword(mIsShowConfirmPass)
        }

        btnSignUp_id.setOnClickListener{

            val userName = entername_id.text.toString()
            val email = enteremail_id.text.toString()
            val password = enterpassword_id.text.toString()
            val confirmPassword = enterconfirmpassword_id.text.toString()

            if(TextUtils.isEmpty(userName) || TextUtils.isEmpty(email) || TextUtils.isEmpty(password) || TextUtils.isEmpty(confirmPassword))
            {
                Toast.makeText(applicationContext,"All fields are required",Toast.LENGTH_SHORT).show()
            }

            else if(password!=confirmPassword)
            {
                Toast.makeText(applicationContext,"password not match...!!!",Toast.LENGTH_SHORT).show()
            }
            else{
                registerUser(userName,email,password)
            }


            ////////////////////////username/////////////////////////
            entername_id.addTextChangedListener(object :TextWatcher
            {
                override fun afterTextChanged(s: Editable?){
                }
                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int){
                }
                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int)
                {
                    if(usernameValidate(entername_id.text.toString()))
                        btnSignUp_id.isEnabled = true
                else
                {
                    btnSignUp_id.isEnabled = false
                    entername_id.setError("Please enter only alphabets..!!")
                }
                }

                private fun usernameValidate(text: String): Boolean
                {
                    var p:Pattern= Pattern.compile("[a-z,A-Z]*")
                    var m:Matcher = p.matcher(text)
                    return m.matches()
                }
            })


            ////////////////////////////email/////////////////////
            enteremail_id.addTextChangedListener(object : TextWatcher
            {
                override fun afterTextChanged(s: Editable?){
                }
                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int){
                }
                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int)
                {
                    if(android.util.Patterns.EMAIL_ADDRESS.matcher(enteremail_id.text.toString()).matches())
                    {
                        btnSignUp_id.isEnabled = true
                    }
                    else
                    {
                        btnSignUp_id.isEnabled = false
                        enteremail_id.setError("Invalid Email")
                    }
                }
            })
            ////////////////////////////////////////////
        }
        //when clickon login button, it will move to login activity
        val btnlogin_id=findViewById<Button>(R.id.btnLogin)
        btnlogin_id.setOnClickListener {
            val intent = Intent(this,LoginActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun showConfirmPassword(isShowConfirmPasswird: Boolean)
    {
        val enterconfirmpassword_id = findViewById<EditText>(R.id.enterconfirmpassword)
        val signup_confirm_password_visibility_id = findViewById<ImageView>(R.id.signup_confirm_password_visibility)
        if(isShowConfirmPasswird)
        {
            enterconfirmpassword_id.transformationMethod = HideReturnsTransformationMethod.getInstance()
            signup_confirm_password_visibility_id.setImageResource(R.drawable.ic_visibility_off)
        }
        else
        {
            enterconfirmpassword_id.transformationMethod = PasswordTransformationMethod.getInstance()
            signup_confirm_password_visibility_id.setImageResource(R.drawable.ic_eye)
        }
        enterconfirmpassword_id.setSelection(enterconfirmpassword_id.text.toString().length)
    }

    private fun showPassword(isShow: Boolean)
    {
        val enterpassword_id = findViewById<EditText>(R.id.enterpassword)
        val signup_password_visibility_id = findViewById<ImageView>(R.id.signup_password_visibility)
        if(isShow)
        {
            enterpassword_id.transformationMethod = HideReturnsTransformationMethod.getInstance()
            signup_password_visibility_id.setImageResource(R.drawable.ic_visibility_off)
        }
        else
        {
            enterpassword_id.transformationMethod = PasswordTransformationMethod.getInstance()
            signup_password_visibility_id.setImageResource(R.drawable.ic_eye)
        }
        enterpassword_id.setSelection(enterpassword_id.text.toString().length)
    }

    private fun getpython():String{
        val python:Python = Python.getInstance()
        val pythonFile:PyObject = python.getModule("textblob")
        return pythonFile.callAttr("Hello","Kinza").toString()
    }
    //add users
    private fun registerUser(userName:String,email:String,password:String)
    {
        auth.createUserWithEmailAndPassword(email,password)
            .addOnCompleteListener(this){
                if (it.isSuccessful)
                {
                    val user: FirebaseUser? = auth.currentUser
                    val userId:String = user!!.uid

                    databaseReference = FirebaseDatabase.getInstance().getReference("Users").child(userId)

                    val hashMap:HashMap<String,String> = HashMap()
                    hashMap.put("userId",userId)
                    hashMap.put("userName",userName)
                    hashMap.put("profileImage","")

                    databaseReference.setValue(hashMap).addOnCompleteListener(this){
                        if (it.isSuccessful)
                        {
                            /* open home activity */
                            val intent = Intent(this, Dashboard::class.java)
                            startActivity(intent)
                            finish()
                        }
                    }
                }
            }
    }
}