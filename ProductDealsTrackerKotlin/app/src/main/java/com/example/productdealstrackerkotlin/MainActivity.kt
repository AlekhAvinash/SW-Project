package com.example.productdealstrackerkotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.textfield.TextInputEditText
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.doAsync
import org.jsoup.Jsoup

class MainActivity : AppCompatActivity() {

    private val dummyList = ArrayList<CardData>()
    private val adapter = ProductListAdapter(dummyList)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recycler_view.adapter = adapter
        recycler_view.layoutManager = LinearLayoutManager(this)
        recycler_view.setHasFixedSize(true)


            title = "Product Deals Tracker"
//        val checkPriceBtn = findViewById<Button>(R.id.checkPriceButton)
//        val url = findViewById<TextInputEditText>(R.id.UrlInputEditText)
//        val scrapedResultTextView = findViewById<TextView>(R.id.scrapedPriceOutput)
//
//        checkPriceBtn.setOnClickListener {
//
//            doAsync {
//
//                var URL = url.text
//                var doc = Jsoup.connect(URL.toString()).get()
//
//                var productTitle = doc.getElementsByClass("B_NuCI")
//                //println(productTitle.text())
//
//                var productPrice = doc.getElementsByClass("_30jeq3 _16Jk6d")
//                //println(productPrice.text())
//
//                var productDetails = "${productTitle.text()} \n Current Price: ${productPrice.text()}"
//
//                scrapedResultTextView.text = productDetails
//
//            }
//
//        }


    }

    fun addProduct(view : View){

        doAsync {

                var URL = "https://www.flipkart.com/google-pixel-4a-just-black-128-gb/p/itm023b9677aa45d?pid=MOBFUSBNAZGY7HQU&lid=LSTMOBFUSBNAZGY7HQUWHTF0C&marketplace=FLIPKART&q=google+pixel+4a&store=tyy%2F4io&srno=s_1_1&otracker=AS_QueryStore_OrganicAutoSuggest_1_6_na_na_na&otracker1=AS_QueryStore_OrganicAutoSuggest_1_6_na_na_na&fm=search-autosuggest&iid=87da2522-c27b-45e7-8c3c-8b8238412bc2.MOBFUSBNAZGY7HQU.SEARCH&ppt=hp&ppn=homepage&ssid=j0j3vbccc00000001647534271493&qH=680e649af610418f"
                var doc = Jsoup.connect(URL.toString()).get()

                var productTitle = doc.getElementsByClass("B_NuCI")
                //println(productTitle.text())

                var productPrice = doc.getElementsByClass("_30jeq3 _16Jk6d")
                //println(productPrice.text())

                val newProduct = CardData(
                    R.drawable.ic_android_phone,
                    productTitle.text(), productPrice.text()
                )

                dummyList.add(0,newProduct)
                adapter.notifyItemInserted(0)

            }

    }

    // FOR TESTING - NOT REQUIRED
    private fun generateDummyList(size : Int) : ArrayList<CardData>{

        val list = ArrayList<CardData>()

        for(i in 0 until size){

            val item = CardData(R.drawable.ic_android_phone, "Item $i","Price")
            list += item

        }

        return list
    }

}