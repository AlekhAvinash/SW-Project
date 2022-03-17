package com.example.productdealstrackerkotlin
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.cardview_layout.view.*
import org.jetbrains.anko.find

class ProductListAdapter(private val productListArray : List<CardData>) : RecyclerView.Adapter<ProductListAdapter.ProductListViewHolder>() {

    class ProductListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val imageView : ImageView = itemView.findViewById(R.id.card_image_view)
        val productName : TextView = itemView.findViewById(R.id.card_text_line1)
        val productPrice : TextView = itemView.findViewById(R.id.card_text_line2)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductListViewHolder {

        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.cardview_layout,
            parent,false)

        return ProductListViewHolder(itemView)

    }

    override fun onBindViewHolder(holder: ProductListViewHolder, position: Int) {

        val currentItem = productListArray[position]
        holder.imageView.setImageResource(currentItem.imageResource)
        holder.productName.text = currentItem.productName
        holder.productPrice.text = currentItem.productPrice

    }

    override fun getItemCount() = productListArray.size

}