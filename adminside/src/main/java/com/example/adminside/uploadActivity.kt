package com.example.adminside

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.adminside.databinding.ActivityUploadBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

//database to data connection is done here

class uploadActivity : AppCompatActivity() {
    private lateinit var binding: ActivityUploadBinding

    private lateinit var databaseReference: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       binding= ActivityUploadBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.savebutton.setOnClickListener {
            val ownerName=binding.uploadownername.text.toString()
            val vechicleBrand=binding.uploadvechiclebrand.text.toString()
            val vechicleRto=binding.uploadvechiclerto.text.toString()
            val vechicleNumber=binding.uploadvechiclenumber.text.toString()

            databaseReference= FirebaseDatabase.getInstance().getReference("vechicle Information")

            val vechicleData= vechicleData(ownerName,vechicleBrand,vechicleRto,vechicleNumber)
            databaseReference.child(vechicleNumber).setValue(vechicleData).addOnSuccessListener {
                binding.uploadownername.text.clear()
                binding.uploadvechiclebrand.text.clear()
                binding.uploadvechiclerto.text.clear()
                binding.uploadvechiclenumber.text.clear()


                Toast.makeText(this,"Saved",Toast.LENGTH_SHORT).show()

                val intent= Intent(this@uploadActivity,MainActivity::class.java)
                startActivity(intent)
                finish()
            }.addOnFailureListener {
                Toast.makeText(this,"Failed",Toast.LENGTH_SHORT).show()
            }
        }
    }
}