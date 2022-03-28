package com.example.productdealstrackerkotlin
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide


class ProductListAdapter(private val productListArray : MutableList<CardData>, private val context: Context) : RecyclerView.Adapter<ProductListAdapter.ProductListViewHolder>() {

    class ProductListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {


        val imageView : ImageView = itemView.findViewById(R.id.productImage)
        val productName : TextView = itemView.findViewById(R.id.productName)
        val offerAvail : TextView = itemView.findViewById(R.id.offerAvailability)
        val productPrice : TextView = itemView.findViewById(R.id.productPrice)
        //val site: ImageView = itemView.findViewById(R.id.site)
        val dlt: ImageView = itemView.findViewById(R.id.dlt)

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
        Glide.with(this.context).load(currentItem.imageURL).into(holder.imageView)

        // For go to site
        /*holder.site.setOnClickListener {
            val uri = Uri.parse("http://www.google.com") // missing 'http://' will cause crashed

            val intent = Intent(Intent.ACTION_VIEW, uri)
            context.startActivity(intent)
        }*/

        holder.dlt.setOnClickListener{deleteItem(position)}
    }


    private fun deleteItem(index: Int){
        productListArray.removeAt(index)
        notifyDataSetChanged()
    }
    override fun getItemCount() = productListArray.size

}