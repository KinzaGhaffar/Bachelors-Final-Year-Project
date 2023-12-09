package com.example.speechrecificationapp.model
import com.google.firebase.database.Exclude

data class Therapist(
    var name:String? = null,
    var imageUrl:String? = null,
    var description:String? = null,
    var contact:String? = null,
    var experiece:String? = null,
    var time:String? = null,
    @get:Exclude
    @set:Exclude
    var key:String? = null
)