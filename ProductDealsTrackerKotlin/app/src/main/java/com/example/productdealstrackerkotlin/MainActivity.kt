package com.example.productdealstrackerkotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import com.google.android.material.textfield.TextInputEditText
import org.jetbrains.anko.doAsync
import org.jsoup.Jsoup

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        title = "Web Scraper"
        val checkPriceBtn = findViewById<Button>(R.id.checkPriceButton)
        val url = findViewById<TextInputEditText>(R.id.UrlInputEditText)
        val scrapedResultTextView = findViewById<TextView>(R.id.scrapedPriceOutput)

        checkPriceBtn.setOnClickListener {

            doAsync {

                var URL = url.text
                var doc = Jsoup.connect(URL.toString()).get()

                var productTitle = doc.getElementsByClass("B_NuCI")
                //println(productTitle.text())

                var productPrice = doc.getElementsByClass("_30jeq3 _16Jk6d")
                //println(productPrice.text())

                var productDetails = "${productTitle.text()} \n Current Price: ${productPrice.text()}"

                scrapedResultTextView.text = productDetails

            }

        }
    }
}