package com.Assignment_2.ecommercestore

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.firebase.ui.database.FirebaseRecyclerAdapter
import com.firebase.ui.database.FirebaseRecyclerOptions
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference

class ProductAdapter(options: FirebaseRecyclerOptions<Product>, private val onItemClick: (Product) -> Unit)
    : FirebaseRecyclerAdapter<Product, ProductAdapter.MyViewHolder>(options) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val inflater  = LayoutInflater.from(parent.context)
        return MyViewHolder(inflater, parent)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int, model: Product) {

        holder.txtName.text = model.name.toString();
        holder.txtType.text = model.type.toString();
        holder.txtPrice.text = model.price.toString();

        val storeref : StorageReference = FirebaseStorage.getInstance().getReferenceFromUrl(model.image.toString())
        Glide.with(holder.imgPhoto.context).load(storeref).into(holder.imgPhoto);

        holder.itemView.setOnClickListener {
            onItemClick(model)
        }

    }

    class MyViewHolder(inflater: LayoutInflater, parent: ViewGroup)
        : RecyclerView.ViewHolder(inflater.inflate(R.layout.activity_product_item, parent, false))
    {

        // Declare references to the TextView widgets
        val txtName: TextView = itemView.findViewById(R.id.txtName)
        val txtType: TextView = itemView.findViewById(R.id.txtType)
        val txtPrice : TextView = itemView.findViewById(R.id.txtPrice)
        val imgPhoto : ImageView = itemView.findViewById(R.id.imgPhoto)
        val productItem : CardView = itemView.findViewById(/* id = */ R.id.productItem)


    }
}