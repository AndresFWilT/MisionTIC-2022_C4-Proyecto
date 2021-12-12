package com.example.delicesdonchucho

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.delicesdonchucho.databinding.ActivityEliminarBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_eliminar.*

class Eliminar : AppCompatActivity() {
    private lateinit var binding: ActivityEliminarBinding
    private lateinit var database: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEliminarBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.Eliminarboton.setOnClickListener{
            var codigos: String = binding.CodigoProd.text.toString()

            if (codigos.isNotEmpty())

                deleteBase(codigos)

            else

                Toast.makeText(this,"Ingrese el codigo del producto", Toast.LENGTH_SHORT).show()

        }

        buttonvolver2.setOnClickListener {
            val intent = Intent(this, Producto::class.java)
            startActivity(intent)
        }
    }

    private fun deleteBase(codigos: String) {
        database = FirebaseDatabase.getInstance().getReference("Productos")
        database.child(codigos).removeValue().addOnSuccessListener {

            binding.CodigoProd.text.clear()
            Toast.makeText(this,"El producto ha sido eliminado correctamente", Toast.LENGTH_SHORT).show()

        }.addOnFailureListener{

            Toast.makeText(this,"Fallo al eliminar el producto", Toast.LENGTH_SHORT).show()
        }

    }
}