package com.example.workingwithdatabase

data class CustomModule(val id :Int ,val name :String,val age :Int,val isActive :Boolean){
    constructor(): this (0,"None",0,false)

}
