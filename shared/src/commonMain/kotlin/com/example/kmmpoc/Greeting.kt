package com.example.kmmpoc

class Greeting {
    fun greeting(): String {
        return "Hello, ${Platform().platform}!"
    }
}