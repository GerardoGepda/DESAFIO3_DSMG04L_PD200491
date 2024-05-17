package com.example.desafio3_dsmg04l_pd200491

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.desafio3_dsmg04l_pd200491.adapters.ResourceAdapter
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore

class MainActivity : AppCompatActivity() {
    private val db = Firebase.firestore
    private lateinit var resourceList: ArrayList<Map<String, Any>>
    private lateinit var resouceRecyclerView: RecyclerView;
    private lateinit var qty: TextView;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        resouceRecyclerView = findViewById(R.id.rcvResourceList);
        resouceRecyclerView.layoutManager = LinearLayoutManager(this)
        resouceRecyclerView.setHasFixedSize(true)
        qty = findViewById(R.id.tvReousercesQty)

        //cargando los datos desde la db
        db.collection("resources").get().addOnSuccessListener { respose ->
            resourceList = arrayListOf<Map<String, Any>>()
            for (resource in respose) {
                val resourceData = resource.data.toMutableMap()
                resourceData["id"] = resource.id
                resourceList.add(resourceData)
            }

            qty.text = "Cantidad de recursos: ${resourceList.size}";
            resouceRecyclerView.adapter = ResourceAdapter(resourceList)
            Toast.makeText(this, "Recursos cargados...", Toast.LENGTH_SHORT).show()
        }
    }
}