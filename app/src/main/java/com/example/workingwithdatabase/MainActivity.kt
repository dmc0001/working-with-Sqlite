package com.example.workingwithdatabase
import android.database.sqlite.SQLiteDatabase
import android.os.Build

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.annotation.RequiresApi
import com.example.workingwithdatabase.databinding.ActivityMainBinding

lateinit var binding: ActivityMainBinding

class MainActivity : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.P)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        val userName = binding.userNameTxt
        val userAge = binding.userAgeTxt
        val swActiveCustomer = binding.switch1

        setContentView(binding.root)

        binding.addBtn.setOnClickListener {
            var customModule = CustomModule()
            try {
                customModule = CustomModule(-1,userName.text.toString(),userAge.text.toString().toInt(),swActiveCustomer.text.toString().toBoolean())
                Toast.makeText(this,customModule.toString(),Toast.LENGTH_SHORT).show()
            }
            catch (e : Exception){
                Toast.makeText(this,"Error creating customer",Toast.LENGTH_SHORT).show()
                val customModule = CustomModule(-1,"Error",0,false)
            }

          val dataBaseHelper = DataBaseHelper(this@MainActivity)
            var success = dataBaseHelper.addOne(customModule)
            Toast.makeText(this,"success : $success",Toast.LENGTH_SHORT).show()

        }
        binding.viewAllBtn.setOnClickListener {


            val dataBaseHelper = DataBaseHelper(this@MainActivity)
            val everyone = dataBaseHelper.getEveryone()
            Toast.makeText(this,everyone.toString(),Toast.LENGTH_SHORT).show()


        }




    }


}