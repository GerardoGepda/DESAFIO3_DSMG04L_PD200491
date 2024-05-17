package com.example.desafio3_dsmg04l_pd200491

import android.content.Intent
import android.graphics.drawable.Drawable
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore

class ResourceActivity : AppCompatActivity() {
    private val db = Firebase.firestore
    private lateinit var resourceId: String

    private lateinit var resourceName: TextView
    private lateinit var resourceType: TextView
    private lateinit var resourceDesc: TextView
    private lateinit var resourceImg: ImageView
    private lateinit var btnLink: Button
    private lateinit var btnClose: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_resource)

        //obteniendo el id del recurso a ver
        resourceId = intent.getStringExtra("resourceId").toString()

        //obteniendo los elementos del layout
        resourceImg = findViewById(R.id.imgVResourceViewImg)
        resourceName = findViewById(R.id.tvResourceViewName)
        resourceType = findViewById(R.id.tvResourceViewType)
        resourceDesc = findViewById(R.id.tvResourceViewDesc)

        btnLink = findViewById(R.id.btnLink)
        btnClose = findViewById(R.id.btnClose)

        //cerrando activity
        btnClose.setOnClickListener {
            Toast.makeText(this, "Cerrando recurso", Toast.LENGTH_SHORT).show()
            finish()
        }

        db.collection("resources").document(resourceId).get().addOnSuccessListener { response ->
            setResourceData(response.data as Map<String, Any>)
        }
    }

    fun setResourceData(resource: Map<String, Any>) {
        resourceName.text = resource["title"].toString()
        resourceType.text = resource["type"].toString()
        resourceDesc.text = resource["desc"].toString()

        Glide.with(this)
            .load(resource["img"].toString())
            .centerCrop()
            .into(resourceImg)

        btnLink.setOnClickListener {
            val openURL = Intent(Intent.ACTION_VIEW)

            Toast.makeText(this, "Visitando recurso", Toast.LENGTH_SHORT).show()

            openURL.data = Uri.parse(resource["link"].toString())
            startActivity(openURL)
        }
    }
}