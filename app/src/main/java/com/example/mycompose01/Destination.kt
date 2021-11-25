package com.example.mycompose01

sealed class Destination(
    val ruta: String
){
    object MainActivity: Destination("MainActivity")
    object SecondActivity: Destination("SecondScreen/{name}"){
        fun createRoute(name:String) = "SecondScreen/$name"
    }
}
