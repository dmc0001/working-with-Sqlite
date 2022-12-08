package com.example.workingwithdatabase

import android.R
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.annotation.RequiresApi
import com.example.workingwithdatabase.databinding.ActivityMain2Binding
import com.example.workingwithdatabase.databinding.ActivityMainBinding

class MainActivity2 : AppCompatActivity() {
    lateinit var binding: ActivityMain2Binding
    private lateinit var customArrayAdapter : ArrayAdapter<CustomModule>
    lateinit var dataBaseHelper : DataBaseHelper

    @RequiresApi(Build.VERSION_CODES.P)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMain2Binding.inflate(layoutInflater)
        setContentView(binding.root)
        val i  = intent.getStringExtra(MainActivity.Constant.extraDate)
        val txt:String = i.toString()
        //binding.textView.text=txt

        //val clickedCustomer = CustomModule()




        customArrayAdapter = ArrayAdapter<CustomModule>(
            this@MainActivity2,
            R.layout.simple_list_item_1,
            dataBaseHelper.searchSomeone(txt)
        )
        binding.listView.adapter = customArrayAdapter

        Toast.makeText(this,txt, Toast.LENGTH_SHORT).show()

    }

}