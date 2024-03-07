package com.Assignment_2.ecommercestore

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val iv_ecommerce_store: ImageView = findViewById(R.id.ecommerce_store)
        iv_ecommerce_store.alpha = 0f
        iv_ecommerce_store.animate().setDuration(1500).alpha(1f).withEndAction {
            val i = Intent(this,Login::class.java)
            startActivity(i)
            overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out)
            finish()
        }
    }


}