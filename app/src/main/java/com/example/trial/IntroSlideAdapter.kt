package com.example.trial

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class IntroSliderAdapter (private val introSlides: List<IntroSlide>):
    RecyclerView.Adapter<IntroSliderAdapter.IntroslideviewHolder>(){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IntroslideviewHolder {
        return IntroslideviewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.slide_item_container,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return introSlides.size
    }

    override fun onBindViewHolder(holder: IntroslideviewHolder, position: Int) {
        holder.bind(introSlides[position])
    }

    inner class IntroslideviewHolder(view: View) : RecyclerView.ViewHolder(view){

        private val textTitle = view.findViewById<TextView>(R.id.textTitle)
        private val textDescription = view.findViewById<TextView>(R.id.textDescription)
        private val imageIcon = view.findViewById<ImageView>(R.id.imagesslideIcon)

        fun bind(introSlide: IntroSlide ){
            textTitle.text = introSlide.title
            textDescription.text = introSlide.description
            imageIcon.setImageResource(introSlide.icon)

        }

    }
}
