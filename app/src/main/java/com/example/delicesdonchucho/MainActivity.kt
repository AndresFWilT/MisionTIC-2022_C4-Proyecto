package com.example.delicesdonchucho

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.delicesdonchucho.databinding.ActivityMainBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.Thread.sleep


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var database: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        sleep(2000)
        setTheme(R.style.Theme_DelicesDonChucho)
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setup()
    }
    private fun setup(){
        title = "Licores Artesanales Don Chucho"

        //val btnIr = findViewById<Button>(R.id.btnIr)
        btnIr.setOnClickListener {
            if (EmaileditText.text.isNotEmpty() && PasswordEditText.text.isNotEmpty()) {

                FirebaseAuth.getInstance()
                    .createUserWithEmailAndPassword(EmaileditText.text.toString(),
                        PasswordEditText.text.toString()).addOnCompleteListener {

                        if (it.isSuccessful){
                            showHome(it.result?.user?.email ?: " ", ProviderType.BASIC )
                        }else{
                            showAlert()
                        }
                    }
            }
        }

        btnPrincipal.setOnClickListener {
            if (EmaileditText.text.isNotEmpty() && PasswordEditText.text.isNotEmpty()) {

                FirebaseAuth.getInstance()
                    .signInWithEmailAndPassword(EmaileditText.text.toString(),
                        PasswordEditText.text.toString()).addOnCompleteListener {

                        if (it.isSuccessful){
                            showHome(it.result?.user?.email ?: " ", ProviderType.BASIC )
                        }else{
                            showAlert2()
                        }
                    }
            }
        }

    }

    private fun showAlert(){
        val builder=AlertDialog.Builder(this)
        builder.setTitle("Error")
        builder.setMessage("El usuario ya se encuentra registrado")
        builder.setPositiveButton("Aceptar", null)
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }

    private fun showAlert2() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Error")
        builder.setMessage("El usuario no se encuentra registrado")
        builder.setPositiveButton("Aceptar", null)
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }

    private fun showHome(
        email: String,
        provider: ProviderType
    ) {
        val homeIntent = Intent(this, Principal::class.java).apply {
            intent.putExtra("email", email)
            intent.putExtra("provider", provider.name)

        }
        startActivity(homeIntent)
    }
}
