package com.example.delicesdonchucho

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.example.delicesdonchucho.databinding.ActivityPrincipalBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import kotlinx.android.synthetic.main.activity_principal.*

enum class ProviderType{
    BASIC
}

class Principal : AppCompatActivity() {

    private lateinit var binding: ActivityPrincipalBinding
    private lateinit var database: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPrincipalBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val btnProducto : ImageView = findViewById<ImageButton>(R.id.btnProducto)
        btnProducto.setOnClickListener {
            val intent1 = Intent(this, Producto::class.java)
            startActivity(intent1)
        }
        val btnProducto2 : ImageView = findViewById<ImageButton>(R.id.btnProducto2)
        btnProducto2.setOnClickListener {
            val intent2 = Intent(this, Producto::class.java)
            startActivity(intent2)
        }
        val btnProducto3 : ImageView = findViewById<ImageButton>(R.id.btnProducto3)
        btnProducto3.setOnClickListener {
            val intent3 = Intent(this, Producto::class.java)
            startActivity(intent3)
        }

        setup()
    }


    private fun setup(){
        title = "Inicio"

        LogOutButton.setOnClickListener {
            FirebaseAuth.getInstance().signOut()
            onBackPressed()


        }

    }

}