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

class MainActivity : AppCompatActivity(), AddProductBottomSheetFragment.BottomSheetListener {

    val dummyList = ArrayList<CardData>()
    private val adapter = ProductListAdapter(dummyList,this)
    lateinit var toggle : ActionBarDrawerToggle //variable for the 3line sign at Top-left corner
    private val db = Database(this, null)

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

    // Pass user input URL to addProduct() on button click
    override fun onAddItemButtonClicked(url_input: String) {
        doAsync {
            val card: CardData = FlipKartScraper.addProduct(url_input)
            db.addURL(url_input)
            dummyList.add(0, card)
            adapter.notifyItemInserted(0)

        }
    }
}