package com.example.adminside

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.adminside.databinding.ActivityMainBinding
import com.example.adminside.databinding.ActivityUpdateBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class UpdateActivity : AppCompatActivity() {
    private lateinit var binding: ActivityUpdateBinding
    private lateinit var databaseReference: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityUpdateBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.updateButton.setOnClickListener {
            val referencevechicleNumber=binding.referenceVechicleNumber.text.toString()
            val updateownerName=binding.updateOwnerName.text.toString()
            val updatevechicleBrand=binding.updateVechicleBrand.text.toString()
            val updateRto=binding.updatevechicleRto.text.toString()

            updateData(referencevechicleNumber,updateownerName,updatevechicleBrand,updateRto)
        }
    }

    private fun updateData(vechicleNumber:String,ownerName:String,vechicleBrand:String,vechicleRto:String){
        databaseReference= FirebaseDatabase.getInstance().getReference("vechicle Information")
        val vechicleData=mapOf<String,String>("ownername" to ownerName,"vechicleBrand" to vechicleBrand,"vechicleRto" to vechicleRto)
        databaseReference.child(vechicleNumber).updateChildren(vechicleData).addOnSuccessListener {
            binding.referenceVechicleNumber.text.clear()
            binding.updateOwnerName.text.clear()
            binding.updateVechicleBrand.text.clear()
            binding.updatevechicleRto.text.clear()
            Toast.makeText(this,"Successfully Updated", Toast.LENGTH_SHORT).show()

        }.addOnFailureListener {
            Toast.makeText(this, "Unable to Update", Toast.LENGTH_SHORT).show()
        }
    }
}