package com.example.workingwithdatabase
import android.R
import android.content.Intent
import android.os.Build

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.annotation.RequiresApi
import com.example.workingwithdatabase.MainActivity.Constant.extraDate
import com.example.workingwithdatabase.databinding.ActivityMainBinding

lateinit var binding: ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var customArrayAdapter : ArrayAdapter<CustomModule>
    lateinit var dataBaseHelper : DataBaseHelper


    @RequiresApi(Build.VERSION_CODES.P)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val searching = binding.searchTxt
        val userName = binding.userNameTxt
        val userAge = binding.userAgeTxt
        var a = false

        dataBaseHelper = DataBaseHelper(this@MainActivity)
        showCostumerOnListView()



        binding.switch1.setOnCheckedChangeListener { _, isChecked -> a = isChecked }

        binding.addBtn.setOnClickListener {
            var customModule = CustomModule()
            try {
                customModule = CustomModule(-1,userName.text.toString(),userAge.text.toString().toInt(),a)
                Toast.makeText(this,customModule.toString(),Toast.LENGTH_SHORT).show()
            }
            catch (e : Exception){
                Toast.makeText(this,"Error creating customer",Toast.LENGTH_SHORT).show()
            }

            dataBaseHelper = DataBaseHelper(this@MainActivity)

            if((userName.text.toString()!="")&&(userAge.text.toString().toInt()!=0)){
                var success = dataBaseHelper.addOne(customModule)
                Toast.makeText(this,"success : $success",Toast.LENGTH_SHORT).show()
            }else {
                Toast.makeText(this, "success : false ", Toast.LENGTH_SHORT).show()
            }
            showCostumerOnListView()

        }
        binding.searchBtn.setOnClickListener {

           /* val intent = Intent(this@MainActivity,MainActivity2::class.java)
            val data = searching.text.toString()
            intent.putExtra(extraDate,data)
            startActivity(intent)*/
            dataBaseHelper = DataBaseHelper(this@MainActivity)
            val r =  dataBaseHelper.searchSomeone(searching.toString())

            Toast.makeText(this,r, Toast.LENGTH_SHORT).show()


        }


        binding.listView.setOnItemClickListener { adapterView, _, i, _ ->

            val clickedCustomer = adapterView.getItemAtPosition(i)
            dataBaseHelper.deleteOne(clickedCustomer as CustomModule)
            showCostumerOnListView()
            Toast.makeText(this,clickedCustomer.name,Toast.LENGTH_SHORT).show()

        }




    }

    @RequiresApi(Build.VERSION_CODES.P)
    private fun showCostumerOnListView() {
        customArrayAdapter = ArrayAdapter<CustomModule>(
            this@MainActivity,
            R.layout.simple_list_item_1,
            dataBaseHelper.getEveryone()
        )
        binding.listView.adapter = customArrayAdapter
    }

    object Constant{
        const val extraDate = "extraData"
    }


}