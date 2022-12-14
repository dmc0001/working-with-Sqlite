package com.example.workingwithdatabase

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import androidx.annotation.RequiresApi
import com.example.workingwithdatabase.databinding.ActivityMain2Binding

class MainActivity2 : AppCompatActivity() {
    private lateinit var binding: ActivityMain2Binding
    private lateinit var customArrayAdapter : ArrayAdapter<CustomModule>
    private lateinit var dataBaseHelper : DataBaseHelper

    @RequiresApi(Build.VERSION_CODES.P)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMain2Binding.inflate(layoutInflater)
        setContentView(binding.root)
        val i  = intent.getStringExtra(MainActivity.Constant.extraDate)
        val txt:String = i.toString()



        dataBaseHelper = DataBaseHelper(this@MainActivity2)


        if (dataBaseHelper.searchSomeone(txt).toString()!= "[]") {

            binding.textView.visibility = View.GONE
            showCostumerSearched(txt)
        }else{
            binding.textView.text = "No one found"
        }







    }

    @RequiresApi(Build.VERSION_CODES.P)
    private fun showCostumerSearched(searching : String ){
        customArrayAdapter = ArrayAdapter<CustomModule>(
            this,
            android.R.layout.simple_list_item_1,
            dataBaseHelper.searchSomeone(searching)
        )
        binding.listView.adapter = customArrayAdapter
    }

}