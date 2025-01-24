package com.spase_y.habittracker.main.navigation_fragment.d

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.spase_y.habittracker.R

class ShareAppAdapter(
    private val apps: List<ShareApp>,
    private val onAppSelected: (ShareApp) -> Unit
) : RecyclerView.Adapter<ShareAppAdapter.ShareAppViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShareAppViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_share_app, parent, false)
        return ShareAppViewHolder(view)
    }

    override fun onBindViewHolder(holder: ShareAppViewHolder, position: Int) {
        val app = apps[position]
        holder.bind(app)
    }

    override fun getItemCount(): Int = apps.size

    inner class ShareAppViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val iconView: ImageView = itemView.findViewById(R.id.iconView)
        private val nameView: TextView = itemView.findViewById(R.id.nameView)

        fun bind(app: ShareApp) {
            iconView.setImageDrawable(app.icon)
            nameView.text = app.name

            itemView.setOnClickListener {
                onAppSelected(app)
            }
        }
    }
}