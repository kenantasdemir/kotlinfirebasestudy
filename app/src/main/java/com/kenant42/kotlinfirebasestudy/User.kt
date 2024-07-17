package com.kenant42.kotlinfirebasestudy

import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
data class User(var user_name:String? = "",var user_age:Int? = 0) {
}