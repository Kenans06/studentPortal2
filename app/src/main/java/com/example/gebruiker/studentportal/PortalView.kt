package com.example.gebruiker.studentportal

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.webkit.WebView
import android.widget.TextView
import kotlinx.coroutines.experimental.*

class PortalView : AppCompatActivity() {

    var mPosition: String? = null

    private lateinit var mPortalViewModel: PortalViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.portal_view)

        mPosition = intent.getStringExtra("position")
        mPortalViewModel = ViewModelProviders.of(this).get(PortalViewModel::class.java)

        var url: String? = null
        (GlobalScope as CoroutineScope).launch(Dispatchers.IO) {
            url = mPortalViewModel.getUrl(mPosition!!.toInt() + 1)
        }

        val myWebView = WebView(this)
        setContentView(myWebView)
        myWebView.loadUrl("http://"+url)

    }
}