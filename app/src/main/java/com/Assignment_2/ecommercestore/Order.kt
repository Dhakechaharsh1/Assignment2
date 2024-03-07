package com.Assignment_2.ecommercestore

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class Order : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order)
    }

    fun goToProduct(view : View) {
        val intent= Intent(this,ProductActivity::class.java)
        startActivity(intent)
    }
}