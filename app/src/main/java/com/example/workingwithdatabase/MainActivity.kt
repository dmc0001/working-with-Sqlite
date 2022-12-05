package com.example.workingwithdatabase
import android.database.sqlite.SQLiteDatabase

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.workingwithdatabase.databinding.ActivityMainBinding

lateinit var binding: ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        val userName = binding.userNameTxt
        val userAge = binding.userAgeTxt
        val swActiveCustomer = binding.switch1

        setContentView(binding.root)

        binding.addBtn.setOnClickListener {
            try {
                val customModule = CustomModule(-1,userName.text.toString(),userAge.text.toString().toInt(),swActiveCustomer.text.toString().toBoolean())
                Toast.makeText(this,customModule.toString(),Toast.LENGTH_SHORT).show()
            }
            catch (e : Exception){
                Toast.makeText(this,"Error creating customer",Toast.LENGTH_SHORT).show()
            }




        }
        binding.viewAllBtn.setOnClickListener {
            Toast.makeText(this,"view all",Toast.LENGTH_SHORT).show()
        }




    }


}