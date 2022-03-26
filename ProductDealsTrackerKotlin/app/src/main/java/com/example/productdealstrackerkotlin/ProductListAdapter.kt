package com.example.productdealstrackerkotlin
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.cardview_layout.view.*
import kotlin.random.Random


class ProductListAdapter(private val productListArray : List<CardData>, private val context: Context) : RecyclerView.Adapter<ProductListAdapter.ProductListViewHolder>() {

    class ProductListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {


        val imageView : ImageView = itemView.findViewById(R.id.productImage)
        val productName : TextView = itemView.findViewById(R.id.productName)
        val offerAvail : TextView = itemView.findViewById(R.id.offerAvailability)
        val productPrice : TextView = itemView.findViewById(R.id.productPrice)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductListViewHolder {

        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.card_layout_new,
            parent,false)

        return ProductListViewHolder(itemView)

    }

    override fun onBindViewHolder(holder: ProductListViewHolder, position: Int) {

        val currentItem = productListArray[position]
//        var r = Random()
//
//        var red: Int = r.nextInt(255 - 0 + 1) + 0
//        var green: Int = r.nextInt(255 - 0 + 1) + 0
//
//        var draw = GradientDrawable()
//        draw.shape = GradientDrawable.RECTANGLE
//        draw.setColor(Color.rgb(red, green)
//
//        holder.viewInside.setBackground(draw)
//        holder.cardView.setCardBackgroundColor()
//        var card: CardView = itemView.findViewById(com.mullr.neurd.R.id.learn_def_card)

//        cardview.setBackgroundColor(Color.parseColor("#EAEDED"));

        holder.productName.text = currentItem.productName
        holder.offerAvail.text = currentItem.offerAvail
        holder.productPrice.text = currentItem.productPrice
        Glide.with(this.context).load(currentItem.imageURL).into(holder.imageView);

    }

    override fun getItemCount() = productListArray.size

}