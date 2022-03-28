package com.example.productdealstrackerkotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.*
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.doAsync
import org.jsoup.Jsoup

class MainActivity : AppCompatActivity(), AddProductBottomSheetFragment.BottomSheetListener {

    private val dummyList = ArrayList<CardData>()
    private val adapter = ProductListAdapter(dummyList,this)

    lateinit var toggle : ActionBarDrawerToggle //variable for the 3line sign at Top-left corner

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Navigation Menu
        val drawerLayout : DrawerLayout = findViewById(R.id.drawerLayout)
        val navView : NavigationView = findViewById(R.id.nav_view)

        toggle = ActionBarDrawerToggle(this,drawerLayout,R.string.open,R.string.close)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        navView.setNavigationItemSelectedListener {

            when(it.itemId){
                R.id.nav_home -> Toast.makeText(applicationContext,"Clicked Home",Toast.LENGTH_SHORT).show()
                R.id.nav_message -> Toast.makeText(applicationContext,"Clicked Message",Toast.LENGTH_SHORT).show()
                R.id.nav_trash -> Toast.makeText(applicationContext,"Clicked Delete",Toast.LENGTH_SHORT).show()
                R.id.nav_settings -> Toast.makeText(applicationContext,"Clicked Settings",Toast.LENGTH_SHORT).show()
                R.id.nav_login -> Toast.makeText(applicationContext,"Clicked Login",Toast.LENGTH_SHORT).show()
                R.id.nav_share -> Toast.makeText(applicationContext,"Clicked Share",Toast.LENGTH_SHORT).show()
                R.id.nav_rate_us -> Toast.makeText(applicationContext,"Clicked Rate us",Toast.LENGTH_SHORT).show()

            }
            true
        }

        // Adapter For List View
        recycler_view.adapter = adapter
        recycler_view.layoutManager = LinearLayoutManager(this)
        recycler_view.setHasFixedSize(true)

        title = "Product Deals Tracker" // App Title

        //Bottom Sheet Fragment
        var addProductBottomSheet = AddProductBottomSheetFragment()
        var floatingButton = findViewById<FloatingActionButton>(R.id.add_fab)

        floatingButton.setOnClickListener{

            addProductBottomSheet.show(supportFragmentManager,"BottomSheet")

        }

    }

    //Navigation Menu Item - On Selection
    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        if(toggle.onOptionsItemSelected(item)){
            return true
        }
        return super.onOptionsItemSelected(item)
    }


    // Function To Scrape Product Details
    fun addProduct(URL: String){

        doAsync {

                //var URL = "https://www.flipkart.com/google-pixel-4a-just-black-128-gb/p/itm023b9677aa45d?pid=MOBFUSBNAZGY7HQU&lid=LSTMOBFUSBNAZGY7HQUWHTF0C&marketplace=FLIPKART&q=google+pixel+4a&store=tyy%2F4io&srno=s_1_1&otracker=AS_QueryStore_OrganicAutoSuggest_1_6_na_na_na&otracker1=AS_QueryStore_OrganicAutoSuggest_1_6_na_na_na&fm=search-autosuggest&iid=87da2522-c27b-45e7-8c3c-8b8238412bc2.MOBFUSBNAZGY7HQU.SEARCH&ppt=hp&ppn=homepage&ssid=j0j3vbccc00000001647534271493&qH=680e649af610418f"
                var doc = Jsoup.connect(URL.toString()).get()

                var productTitle = doc.getElementsByClass("B_NuCI").text()
                var pt: Array<String> = productTitle.split(" ").toTypedArray()
                val p = pt.filter({pt.indexOf(it) < 6})
                productTitle = p.joinToString(" ")

                println(productTitle)

                var productPrice = doc.getElementsByClass("_30jeq3 _16Jk6d").text()
                var offerVal = doc.getElementsByClass("rd9nIL").text()

                println(offerVal)

                var offerAvail: String

                if (offerVal == "Available offers"){
                    offerAvail = "Offer Available"
                }
                else{
                    offerAvail = "No Offers"
                }

                var imgURL = doc.getElementsByClass("_396cs4 _2amPTt _3qGmMb _3exPp9").attr("srcSet").substringBefore(" 2x").substringBefore("?q=70")
                println(imgURL)

                val newProduct = CardData(
                    imgURL, productTitle, offerAvail,productPrice
                )

                dummyList.add(0,newProduct)
                adapter.notifyItemInserted(0)

            }

    }

    // Pass user input URL to addProduct() on button click
    override fun onAddItemButtonClicked(url_input: String) {
        addProduct(url_input)
    }
}