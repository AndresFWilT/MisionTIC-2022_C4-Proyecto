package com.example.delicesdonchucho

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.delicesdonchucho.databinding.ActivityProductoBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase


class Producto : AppCompatActivity() {

    private lateinit var binding: ActivityProductoBinding
    private lateinit var database: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProductoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnAgregar.setOnClickListener {
            val codigo = binding.editTextCodigo.text.toString()
            val nombrePro = binding.editTextNombre.text.toString()
            val precio = binding.editTextValor.text.toString()

            database = FirebaseDatabase.getInstance().getReference("Productos")
            val Productos = Products(codigo, nombrePro, precio)
            database.child(codigo).setValue(Productos).addOnSuccessListener {

                binding.editTextCodigo.text.clear()
                binding.editTextNombre.text.clear()
                binding.editTextValor.text.clear()

                Toast.makeText(this,"El producto ha sido guardado correctamente", Toast.LENGTH_SHORT).show()

            }.addOnFailureListener {

                Toast.makeText(this, "Error al guardar", Toast.LENGTH_SHORT).show()
            }
        }

        binding.btnModificar.setOnClickListener{
            val intent = Intent(this, Modificar::class.java)
            startActivity(intent)
        }


        binding.btnBuscar.setOnClickListener {
            val intent = Intent(this, Buscar::class.java)
            startActivity(intent)
        }


        binding.btnEliminar.setOnClickListener {
            val intent = Intent(this, Eliminar::class.java)
            startActivity(intent)
        }

        binding.buttonvolver.setOnClickListener{
            val intent = Intent(this, Principal::class.java)
            startActivity(intent)
        }

    }

}