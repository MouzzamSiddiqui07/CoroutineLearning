package com.example.coroutinelearning

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {

    private val RESULT1: String = "Result1"

    // create the instance member of that text view and button
    private lateinit var resultTextView: TextView
    private lateinit var clickButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // init the instance members and views
        resultTextView = findViewById(R.id.resultTextView)
        clickButton = findViewById(R.id.clickMeButton)

        // /when click on the button
        clickButton.setOnClickListener {
            // perform task when click  on the button
            CoroutineScope(Dispatchers.IO).launch {
                fakeApiRequest()
            }
        }
    }

// fake api request
    private suspend fun fakeApiRequest() {
        val result1 = getResult1fromApi()

// when we want to change the context
        setTextOnMainThread(result1)
    }

    private suspend fun setTextOnMainThread(result1: String) {
        withContext(Dispatchers.Main) {
            setTextToTextView(result1)
        }
    }

// set text to the text view
    private fun setTextToTextView(text: String) {
        val newText = resultTextView.text.toString() + "\n" + text
        resultTextView.text = newText
    }

    // fetch result from fake api
    private suspend fun getResult1fromApi(): String {
        val methodName = "getResult1FromAPi"
        logThread(methodName)
        delay(1000)
        return RESULT1
    }

    private fun logThread(methodName: String) {
        Log.d("kkk", "method name :$methodName")
    }
}
