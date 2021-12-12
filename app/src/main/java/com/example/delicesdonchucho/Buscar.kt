package com.example.delicesdonchucho

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.delicesdonchucho.databinding.ActivityBuscarBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class Buscar : AppCompatActivity() {
    private lateinit var binding: ActivityBuscarBinding
    private lateinit var database: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBuscarBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.Buscarboton.setOnClickListener{
            val codigoPro : String = binding.CodProducto.text.toString()
            if (codigoPro.isNotEmpty()){

                readData(codigoPro)

            }else{

                Toast.makeText(this,"Ingrese el codigo del producto", Toast.LENGTH_SHORT).show()
            }

        }


        binding.buttonvolver1.setOnClickListener {
            val intent = Intent(this, Producto::class.java)
            startActivity(intent)
        }
    }

    private fun readData(codigoPro: String) {
        database = FirebaseDatabase.getInstance().getReference("Productos")
        database.child(codigoPro).get().addOnSuccessListener{

            if (it.exists()){

                val productoname = it.child("nombrePro").value
                val productoprecio = it.child("precio").value
                Toast.makeText(this,"Busqueda existosa", Toast.LENGTH_SHORT).show()

                binding.CodProducto.text.clear()
                binding.tvname.text = productoname.toString()
                binding.tvprecio.text = productoprecio.toString()


            }else{

                Toast.makeText(this,"El producto no existe", Toast.LENGTH_SHORT).show()
            }

        }.addOnFailureListener{

            Toast.makeText(this,"Fallo al buscar el producto", Toast.LENGTH_SHORT).show()
        }
    }
}