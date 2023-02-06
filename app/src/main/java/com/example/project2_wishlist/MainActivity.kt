package com.example.project2_wishlist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private var items = mutableListOf<Item>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val itemsRv = findViewById<RecyclerView>(R.id.ItemsRv)
        val nameEdit = findViewById<EditText>(R.id.NameEdit)
        val priceEdit = findViewById<EditText>(R.id.PriceEdit)
        val urlEdit = findViewById<EditText>(R.id.URLEdit)
        val adapter =ItemAdapter(items)
        itemsRv.adapter = adapter
        itemsRv.layoutManager = LinearLayoutManager(this)
        findViewById<Button>(R.id.SubmitButton).setOnClickListener {
            val name = nameEdit.text.toString()
            var price = priceEdit.text.toString()
            val url = urlEdit.text.toString()
            if (name.isNotEmpty() && price.isNotEmpty() && url.isNotEmpty()) {
                price = String.format("%.2f",price.toFloat())
                nameEdit.text.clear()
                priceEdit.text.clear()
                urlEdit.text.clear()
                items.add(Item(name, price, url))
                adapter.notifyDataSetChanged()
            }
            else {
                Toast.makeText(this, "All Field Must Be Filled.", Toast.LENGTH_SHORT).show()
            }

        }
    }
}