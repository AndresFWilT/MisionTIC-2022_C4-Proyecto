package com.example.delicesdonchucho

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.delicesdonchucho.databinding.ActivityModificarBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class Modificar : AppCompatActivity() {
    private lateinit var binding: ActivityModificarBinding
    private lateinit var database: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityModificarBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnModificar1.setOnClickListener {
            val codigo1 = binding.editTextCodigo1.text.toString()
            val nombrePro1 = binding.editTextNombre1.text.toString()
            val precio1 = binding.editTextValor1.text.toString()

            database = FirebaseDatabase.getInstance().getReference("Productos")
            val Productos = Products(codigo1, nombrePro1, precio1)
            database.child(codigo1).setValue(Productos).addOnSuccessListener {

                binding.editTextCodigo1.text.clear()
                binding.editTextNombre1.text.clear()
                binding.editTextValor1.text.clear()

                Toast.makeText(this,"El producto ha sido modificado correctamente", Toast.LENGTH_SHORT).show()

            }.addOnFailureListener {

                Toast.makeText(this, "Error al modificar", Toast.LENGTH_SHORT).show()
            }
        }

        binding.volver.setOnClickListener{
            val intent = Intent(this, Producto::class.java)
            startActivity(intent)
        }
    }
}