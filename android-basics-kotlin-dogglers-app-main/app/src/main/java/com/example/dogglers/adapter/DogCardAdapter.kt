/*
* Copyright (C) 2021 The Android Open Source Project.
*
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
*
*     http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*/
package com.example.dogglers.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.dogglers.R
import com.example.dogglers.const.Layout
import com.example.dogglers.data.DataSource
import com.example.dogglers.model.Dog

/**
 * Adapter to inflate the appropriate list item layout and populate the view with information
 * from the appropriate data source
 */
class DogCardAdapter(
    private val context: Context?,
    private val layout: Int
): RecyclerView.Adapter<DogCardAdapter.DogCardViewHolder>() {

    private val dogList: List<Dog> = DataSource.dogs

    

    /**
     * Initialize view elements
     */
    class DogCardViewHolder(view: View?): RecyclerView.ViewHolder(view!!) {
        val imageViewItem: ImageView = view!!.findViewById(R.id.image_view_item)
        val dogNameTV: TextView = view!!.findViewById(R.id.tv_name_item)
        val dogAgeTV: TextView = view!!.findViewById(R.id.tv_age_item)
        val dogHobbiesTV: TextView = view!!.findViewById(R.id.tv_hobbies_item)
    }

    /**
     * Bind views elements from XML to their counterparts in code, depending on the layout value
     * in DogCardAdapter constructor
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DogCardViewHolder {
        val adapterLayout:View = when(layout){
            Layout.GRID -> LayoutInflater.from(parent.context)
                .inflate(R.layout.grid_list_item, parent, false)
            else -> LayoutInflater.from(parent.context)
                .inflate(R.layout.vertical_horizontal_list_item, parent, false)
        }
        return DogCardViewHolder(adapterLayout)
    }

    override fun getItemCount(): Int = dogList.size

    /**
     * Bind data in dogList to their views in code using DogCardViewHolder
     */
    override fun onBindViewHolder(holder: DogCardViewHolder, position: Int) {
        // Get the data at the current position
        val item = dogList[position]
        // Set the image resource for the current dog
        holder.imageViewItem.setImageResource(item.imageResourceId)
        // Set the text for the current dog's name
        holder.dogNameTV.text = item.name
        // Set the text for the current dog's age + Set the text for the current dog's hobbies
        val resources = context?.resources
        holder.dogAgeTV.text = resources?.getString(R.string.dog_age, item.age)
        holder.dogHobbiesTV.text = resources?.getString(R.string.dog_hobbies, item.hobbies)
    }
}
