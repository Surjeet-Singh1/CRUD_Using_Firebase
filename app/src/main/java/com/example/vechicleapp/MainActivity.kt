package com.example.vechicleapp

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.vechicleapp.databinding.ActivityMainBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

// Here we will retrive the data from the database
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var databaseReference: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.searchbutton.setOnClickListener {
            val searchVechicleNumber=binding.searchVechicleNumber.text.toString()
            if(searchVechicleNumber.isNotEmpty()){
                readData(searchVechicleNumber)
            }else{
                Toast.makeText(this,"Please Enter Vechicle Number", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun readData(vechicleNumer:String){
        databaseReference= FirebaseDatabase.getInstance().getReference("vechicle Information")
        databaseReference.child(vechicleNumer).get().addOnSuccessListener {
            if(it.exists()){
                val ownerName=it.child("ownername").value
                val vechicleBrand=it.child("vechicleBrand").value
                val vechicleRto=it.child("vechicleRto").value
                Toast.makeText(this,"Results Found", Toast.LENGTH_SHORT).show()
                binding.searchVechicleNumber.text.clear()
                binding.readOwnerName.text=ownerName.toString()
                binding.readVechicleBrand.text=vechicleBrand.toString()
                binding.readVechicleRto.text=vechicleRto.toString()
            }else{
                Toast.makeText(this,"Results Not Found", Toast.LENGTH_SHORT).show()
            }
        }.addOnFailureListener {
            Toast.makeText(this,"Failed", Toast.LENGTH_SHORT).show()
            }
    }

}