package com.example.gebruiker.studentportal

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

class RecyclerAdapter internal constructor(
        context: Context
) : RecyclerView.Adapter<RecyclerAdapter.PortalViewHolder>() {

    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var portals = emptyList<Portal>() // Cached copy of words

    inner class PortalViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val portalItemView: TextView = itemView.findViewById(R.id.textView)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PortalViewHolder {
        val itemView = inflater.inflate(R.layout.recylerview_grid, parent, false)
        return PortalViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: PortalViewHolder, position: Int) {
        val current = portals[position]

        holder.portalItemView.text = current.title

    }

    internal fun setPortals(portals: List<Portal>) {
        this.portals = portals
        notifyDataSetChanged()
    }

    override fun getItemCount() = portals.size
}