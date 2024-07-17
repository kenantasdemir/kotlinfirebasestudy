package com.kenant42.kotlinfirebasestudy

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.firebase.FirebaseApp
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.kenant42.kotlinfirebasestudy.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    val db = FirebaseDatabase.getInstance()
    val ref = db.getReference("users")
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        FirebaseApp.initializeApp(applicationContext)

        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        updateUser("Kenan", 22, "-O204mz9f6aS82sYglI-")
        insertUser("Kenan", 24)
        isEqual("Kenan")
    }

    fun insertUser(name: String, age: Int) {
        val user = User(name, age)
        ref.push().setValue(user)
    }


    fun removeUser(key: String) {
        ref.child(key).removeValue()
    }

    fun updateUser(name: String, age: Int, key: String) {
        var updateInfo = HashMap<String, Any>()
        updateInfo["user_name"] = name
        updateInfo["user_age"] = age
        ref.child(key).updateChildren(updateInfo)
    }

    fun getUser() {
        ref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                for (postSnapshot in snapshot.children) {
                    val user = postSnapshot.getValue(User::class.java)
                    if (user != null) {
                        val key = postSnapshot.key
                        Log.e("KEY ", key.toString())
                        Log.e("NAME ", user.user_name.toString())
                        Log.e("AGE ", user.user_age.toString())
                    }
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Log.e("ERROR ", error.message)
            }
        })
    }

    fun isEqual(keyWord: String) {
        val query = ref.orderByChild("user_name").equalTo(keyWord)

        query.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                for (postSnapshot in snapshot.children) {
                    val user = postSnapshot.getValue(User::class.java)
                    if (user != null) {
                        val key = postSnapshot.key
                        Log.e("KEY ", key.toString())
                        Log.e("NAME ", user.user_name.toString())
                        Log.e("AGE ", user.user_age.toString())
                    }
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Log.e("ERROR ", error.message)
            }
        })
    }

    fun fetchLimitToLast() {
        val query = ref.orderByChild("user_name").limitToLast(5)
        query.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                for (postSnapshot in snapshot.children) {
                    val user = postSnapshot.getValue(User::class.java)
                    if (user != null) {
                        val key = postSnapshot.key
                        Log.e("KEY ", key.toString())
                        Log.e("NAME ", user.user_name.toString())
                        Log.e("AGE ", user.user_age.toString())
                    }
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Log.e("ERROR ", error.message)
            }
        })
    }

    fun queryRange() {
        val query = ref.orderByChild("user_name").startAt(30.0).endAt(45.0)
        query.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                for (postSnapshot in snapshot.children) {
                    val user = postSnapshot.getValue(User::class.java)
                    if (user != null) {
                        val key = postSnapshot.key
                        Log.e("KEY ", key.toString())
                        Log.e("NAME ", user.user_name.toString())
                        Log.e("AGE ", user.user_age.toString())
                    }
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Log.e("ERROR ", error.message)
            }
        })
    }
}