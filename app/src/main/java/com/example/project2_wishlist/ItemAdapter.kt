package com.example.project2_wishlist

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.text.SpannableString
import android.text.style.UnderlineSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView

class ItemAdapter(private val items: MutableList<Item>) : RecyclerView.Adapter<ItemAdapter.ViewHolder>() {
    // Provide a direct reference to each of the views within a data item
    // Used to cache the views within the item layout for fast access
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        // TODO: Create member variables for any view that will be set
        // as you render a row.
        val iName: TextView
        val iPrice: TextView
        val iURL: TextView
        val iView: View

        // We also create a constructor that accepts the entire item row
        // and does the view lookups to find each sub-view
        init {
            // TODO: Store each of the layout's views into
            // the public final member variables created above
            iName = itemView.findViewById(R.id.ItemName)
            iPrice = itemView.findViewById(R.id.ItemPrice)
            iURL = itemView.findViewById(R.id.ItemURL)
            iView = itemView.findViewById(R.id.ItemView)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        // Inflate the custom layout
        val contactView = inflater.inflate(R.layout.wish_item, parent, false)
        // Return a new holder instance
        return ViewHolder(contactView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        // Get the data model based on position
        val item = items[position]
        // Set item views based on views and data model
        holder.iName.text = item.name
        holder.iPrice.text = "$"+item.price
        val spanurl = SpannableString(item.url)
        spanurl.setSpan(UnderlineSpan(), 0, spanurl.length, 0)
        holder.iURL.text = spanurl
        holder.iView.setOnClickListener {
            try {
                val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(item.url))
                ContextCompat.startActivity(it.context, browserIntent, null)
            } catch (e: ActivityNotFoundException) {
                Toast.makeText(it.context, "Invalid URL for " + item.name, Toast.LENGTH_LONG).show()
            }
        }
        holder.iView.setOnLongClickListener {
            items.removeAt(position)
            notifyDataSetChanged()
            return@setOnLongClickListener true
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }
}