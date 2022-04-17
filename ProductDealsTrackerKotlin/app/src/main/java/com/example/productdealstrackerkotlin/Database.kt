package com.example.productdealstrackerkotlin

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import java.util.ArrayList

class Database(context: Context, factory: SQLiteDatabase.CursorFactory?) :
    SQLiteOpenHelper(context, "tracker", factory, 1) {

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL("DROP TABLE IF EXISTS items")
        db.execSQL("CREATE TABLE items (link TEXT PRIMARY KEY)")
    }
    override fun onUpgrade(db: SQLiteDatabase, p1: Int, p2: Int) {
        db.execSQL("DROP TABLE IF EXISTS items")
    }
    fun isExist(): Boolean {
        val db = this.readableDatabase
        val cursor = db.rawQuery(
            "select DISTINCT tbl_name from sqlite_master where tbl_name = 'items'",
            null
        )
        var out = false
        if (cursor != null && cursor.count > 0) {
            out = true
        }
        cursor.close()
        return out
    }

    fun addURL(URL: String) {
        val values = ContentValues()
        values.put("link", URL)
        val db = this.writableDatabase
        db.insert("items", null, values)
        db.close()
    }

    fun getURL(): MutableList<String> {
        val results: MutableList<String> = ArrayList()
        val db = this.readableDatabase
        val c = db.rawQuery("SELECT link FROM items", null)
        if (c != null) {
            if (c.moveToFirst()) {
                do {
                    results.add(c.getString(c.getColumnIndex("link")))
                } while (c.moveToNext())
            }
        }
        c.close()
        return results
    }
    }