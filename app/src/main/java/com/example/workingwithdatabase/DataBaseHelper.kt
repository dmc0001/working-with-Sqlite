package com.example.workingwithdatabase


import android.content.ContentValues
import android.content.Context

import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.os.Build
import androidx.annotation.RequiresApi

@RequiresApi(Build.VERSION_CODES.P)
class DataBaseHelper(
    context: Context?
) : SQLiteOpenHelper(context, "customer.db", null, 1) {


    //this is called the first time a database is accessed. there should be code in here to create a new database
    override fun onCreate(p0: SQLiteDatabase?) {
        val createTableStatement =
            "CREATE TABLE $CUSTOMER_TABLE ($COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT, $COLUMN_CUSTOMER_NAME TEXT, $COLUMN_CUSTOMER_AGE INT, $COLUMN_ACTIVE_CUSTOMER BOOL)"

        p0!!.execSQL(createTableStatement)

    }

    //this is called the database version number changes. it prevents previous users apps from breaking when change database design
    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        TODO("Not yet implemented")
    }

    fun addOne(customModule: CustomModule): Boolean {

        val db: SQLiteDatabase = this.writableDatabase
        val cv = ContentValues()
        cv.put(COLUMN_CUSTOMER_NAME, customModule.name)
        cv.put(COLUMN_CUSTOMER_AGE, customModule.age)
        cv.put(COLUMN_ACTIVE_CUSTOMER, customModule.isActive)
        val insert = db.insert(CUSTOMER_TABLE, null, cv)

        return insert > -1

    }

    fun deleteOne(customModule: CustomModule): Boolean {

        val db: SQLiteDatabase = this.writableDatabase
        val queryString = "DELETE FROM $CUSTOMER_TABLE WHERE $COLUMN_ID = ${customModule.id}"
        val cursor = db.rawQuery(queryString, null)
        return cursor.moveToFirst()

    }

    fun getEveryone(): List<CustomModule> {
        val returnList = ArrayList<CustomModule>()
        //get data from database
        val queryString = "SELECT * FROM $CUSTOMER_TABLE"
        val db: SQLiteDatabase = this.readableDatabase
        val cursor = db.rawQuery(queryString, null)
        if (cursor.moveToFirst()) {
            do {
                val customerID: Int = cursor.getInt(0)
                val customerName: String = cursor.getString(1)
                val customerAge: Int = cursor.getInt(2)
                val customerActive: Boolean = cursor.getInt(3) == 1
                val customModule =
                    CustomModule(customerID, customerName, customerAge, customerActive)
                returnList.add(customModule)

            } while (cursor.moveToNext())
        } else {
            //failure. do not anything to the list.

        }

        cursor.close()
        db.close()

        return returnList

    }
    //search someone by name and return the result as a list
    fun searchSomeone( text: String): List<CustomModule> {
        val returnList = ArrayList<CustomModule>()
        //get data from database
        val queryString = "SELECT * FROM $CUSTOMER_TABLE WHERE $COLUMN_CUSTOMER_NAME LIKE '%$text%' "
        val db: SQLiteDatabase = this.readableDatabase
        val cursor = db.rawQuery(queryString,null)
       // val cursor = db.rawQuery(queryString, null)
        if (cursor.moveToFirst()) {
            do {
                val customerID: Int = cursor.getInt(0)
                val customerName: String = cursor.getString(1)
                val customerAge: Int = cursor.getInt(2)
                val customerActive: Boolean = cursor.getInt(3) == 1
                val customModule =
                    CustomModule(customerID, customerName, customerAge, customerActive)
                returnList.add(customModule)

            } while (cursor.moveToNext())
        } else {
            //failure. do not anything to the list.

        }

        cursor.close()
        db.close()

        return returnList

    }



    companion object {
        const val CUSTOMER_TABLE: String = "CUSTOMER_TABLE"
        const val COLUMN_ID: String = "ID"
        const val COLUMN_CUSTOMER_NAME: String = "CUSTOMER_NAME"
        const val COLUMN_CUSTOMER_AGE: String = "CUSTOMER_AGE"
        const val COLUMN_ACTIVE_CUSTOMER: String = "ACTIVE_CUSTOMER"


    }

}


