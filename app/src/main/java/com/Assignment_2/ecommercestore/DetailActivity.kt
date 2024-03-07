package com.Assignment_2.ecommercestore

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import com.bumptech.glide.Glide
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference

class DetailActivity : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val name = intent.getStringExtra("name") ?: ""
        val type = intent.getStringExtra("type") ?: ""
        val price = intent.getStringExtra("price") ?: ""
        val image = intent.getStringExtra("image") ?: ""
        val description = intent.getStringExtra("description") ?: ""

        val detailName: TextView = findViewById(R.id.cTitle)
        val detailType: TextView = findViewById(R.id.cSubTitle)
        val detailDescription: TextView = findViewById(R.id.detailDescription)
        val detailPrice: TextView = findViewById(R.id.detailPrice)
        val detailPhoto: ImageView = findViewById(R.id.detailPhoto)

        detailName.text = name;
        detailType.text = type;
        detailDescription.text = description;
        detailPrice.text = price;

        val storeRef : StorageReference = FirebaseStorage.getInstance().getReferenceFromUrl(image)
        Glide.with(detailPhoto.context).load(storeRef).into(detailPhoto);

    }

    fun goToCheckout(view : View) {
        val i = Intent(this,CheckoutActivity::class.java)
        startActivity(i)
    }
}