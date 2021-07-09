package com.surepass.exampleapp

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.surepass.surepassesign.InitSDK
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val SAMPLE_APP = "Sample App"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button.setOnClickListener {
            startEsignProcess()
        }

    }

    fun startEsignProcess(){

        val token = "TOKEN"
        val fullVerificationIntent = Intent(this, InitSDK::class.java)

        fullVerificationIntent.putExtra("token", token)
        fullVerificationIntent.putExtra("env", "PREPROD")

        startActivityForResult(
            fullVerificationIntent,
            10001
        )
    }

    private fun setResponseOnTextView(resp : String){
        responseView.text = resp
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        when (requestCode) {
            10001 -> {
                val eSignResponse = data!!.getStringExtra("signedResponse");
                Log.d(SAMPLE_APP, "eSign Response $eSignResponse")
                setResponseOnTextView(eSignResponse)
            }
        }
    }
}
