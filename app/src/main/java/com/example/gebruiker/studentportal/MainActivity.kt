package com.example.gebruiker.studentportal

import android.app.Activity
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main2.*

class MainActivity : AppCompatActivity() {

    private val newPortalActivityRequestCode = 1
    private lateinit var mPortalViewModel: PortalViewModel
    private var mPortalText: Portal? = null
    private var id: Int = 1


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        setSupportActionBar(toolbar)

        val fab = findViewById<FloatingActionButton>(R.id.fab)

        fab.setOnClickListener {
            val intent = Intent(this@MainActivity, AddPortal::class.java)
            startActivityForResult(intent, newPortalActivityRequestCode)
        }

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)

        val adapter = RecyclerAdapter(this)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = GridLayoutManager(this, 3)

        mPortalViewModel = ViewModelProviders.of(this).get(PortalViewModel::class.java)
        mPortalViewModel.allPortals.observe(this, Observer { portals ->
            // Update the cached copy of the words in the adapter.
            portals?.let { adapter.setPortals(it) }
        })


        recyclerView.addOnItemClickListener(object : OnItemClickListener {
            override fun onItemClicked(position: Int, view: View) {

                val intent = Intent(baseContext, PortalView::class.java)
                intent.putExtra("position", position.toString())
                startActivity(intent)

            }
        })

    }

    interface OnItemClickListener {
        fun onItemClicked(position: Int, view: View)
    }

    private fun RecyclerView.addOnItemClickListener(onClickListener: OnItemClickListener) {
        this.addOnChildAttachStateChangeListener(object : RecyclerView.OnChildAttachStateChangeListener {
            override fun onChildViewDetachedFromWindow(view: View?) {
                view?.setOnClickListener(null)
            }

            override fun onChildViewAttachedToWindow(view: View?) {
                view?.setOnClickListener {
                    val holder = getChildViewHolder(view)
                    onClickListener.onItemClicked(holder.adapterPosition, view)
                }
            }
        })
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, intentData: Intent?) {
        super.onActivityResult(requestCode, resultCode, intentData)

        if (requestCode == newPortalActivityRequestCode && resultCode == Activity.RESULT_OK) {
            intentData?.let { data ->
                mPortalText = Portal(id, data.getStringExtra(AddPortal.TITLEREPLY), data.getStringExtra(AddPortal.URLREPLY))

                mPortalViewModel.insert(mPortalText!!)
                id++

                Toast.makeText(applicationContext, mPortalText?.url, Toast.LENGTH_LONG).show()
            }
        } else {
            Toast.makeText(
                    applicationContext,
                    "Empty, not saved",
                    Toast.LENGTH_LONG
            ).show()
        }

    }


}