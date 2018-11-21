package com.example.gebruiker.studentportal

import android.app.Activity
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.EditText
import android.content.Intent
import android.text.TextUtils
import android.widget.Button
import kotlinx.android.synthetic.main.add_portal.*


class AddPortal : AppCompatActivity() {

    private var mTitle: EditText? = null
    private var mUrl: EditText? = null

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_portal)

        mTitle = findViewById(R.id.editTextTitle)
        mUrl = findViewById(R.id.editTextUrl)

        val button = findViewById<Button>(R.id.button)
        button.setOnClickListener {
            val replyIntent = Intent()
            if (TextUtils.isEmpty(mTitle!!.text) || TextUtils.isEmpty(mUrl!!.text)) {
                setResult(Activity.RESULT_CANCELED, replyIntent)
            } else {
                val title = mTitle!!.text.toString()
                val url = mUrl!!.text.toString()
                replyIntent.putExtra(TITLEREPLY, title)
                replyIntent.putExtra(URLREPLY, url)
                setResult(Activity.RESULT_OK, replyIntent)
            }
            finish()
        }
    }

    companion object {
        const val TITLEREPLY = "portal title"
        const val URLREPLY = "portal url"
    }

}