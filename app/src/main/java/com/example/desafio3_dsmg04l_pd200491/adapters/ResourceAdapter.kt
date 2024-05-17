package com.example.desafio3_dsmg04l_pd200491.adapters

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.desafio3_dsmg04l_pd200491.R
import com.example.desafio3_dsmg04l_pd200491.ResourceActivity

class ResourceAdapter(private val resourceList: ArrayList<Map<String, Any>>): RecyclerView.Adapter<ResourceAdapter.ResourceViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ResourceViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.resource_card_item, parent, false);

        return ResourceViewHolder(itemView, resourceList);
    }

    override fun getItemCount(): Int {
        return resourceList.size;
    }

    override fun onBindViewHolder(holder: ResourceViewHolder, position: Int) {
        val currentItem = resourceList[position];
        Glide.with(holder.resourceImg.context)
            .load(currentItem["img"].toString())
            .centerCrop()
            .into(holder.resourceImg)

        holder.resourceName.text = currentItem["title"].toString();
        holder.resourceType.text = currentItem["type"].toString();
        holder.resourceButton.setOnClickListener {
            Toast.makeText(holder.resourceButton.context, "Accediendo al recurso", Toast.LENGTH_SHORT).show()
            val intent = Intent(holder.resourceButton.context, ResourceActivity::class.java)
            intent.putExtra("resourceId", currentItem["id"].toString())
            holder.resourceButton.context.startActivity(intent)
        }
    }

    class ResourceViewHolder(itemView: View, testList: ArrayList<Map<String, Any>>): RecyclerView.ViewHolder(itemView) {
        val resourceImg: ImageView = itemView.findViewById(R.id.imgVResourceItem)
        val resourceName: TextView = itemView.findViewById(R.id.tvResourceItemName)
        val resourceType: TextView = itemView.findViewById(R.id.tvResourceItemType)
        val resourceButton: Button = itemView.findViewById(R.id.btnResourceItem)
    }

}