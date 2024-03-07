package com.Assignment_2.ecommercestore

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.firebase.ui.database.FirebaseRecyclerOptions
import com.google.firebase.database.FirebaseDatabase

class ProductActivity : AppCompatActivity() {

    private var adapter: ProductAdapter? = null

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product)

        val query = FirebaseDatabase.getInstance().reference.child("products");
        val options: FirebaseRecyclerOptions<Product> = FirebaseRecyclerOptions.Builder<Product>().setQuery(query, Product::class.java).build();
        adapter = ProductAdapter(options) { item -> showDetails(item) }

        val rView : RecyclerView = findViewById(R.id.rView);
        rView.layoutManager = GridLayoutManager(this, 2)
        rView.adapter = adapter

    }

    private fun showDetails(item: Product) {
        val intent = Intent(this, DetailActivity::class.java)
        intent.putExtra("name", item.name);
        intent.putExtra("type", item.type);
        intent.putExtra("price", item.price);
        intent.putExtra("image", item.image);
        intent.putExtra("description", item.description);
        startActivity(intent)
    }

    override fun onStart() {
        super.onStart();
        adapter?.startListening()
    }
}