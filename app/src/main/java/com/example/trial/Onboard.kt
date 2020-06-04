package com.example.trial

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import androidx.core.view.get
import androidx.viewpager2.widget.ViewPager2
import kotlinx.android.synthetic.main.activity_onboard.*

class Onboard : AppCompatActivity() {

    private val introSliderAdapter = IntroSliderAdapter(
        listOf(
            IntroSlide(
                "EASY INFORMATION ACCESS",
                "All the student information at the tip of your finger",
                R.drawable.onboard1
            ),
            IntroSlide(
                "TRACK PROGRESS",
                "Track student records easily, and get all the student information online",
                R.drawable.onboard2
            ),
            IntroSlide(
                "PAY FEE CHAP CHAP WAY",
                "Allows you to pay school via Mpesa Push menu hence saving you time and keeping your student in class",
                R.drawable.onboard1
            )
        )

    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_onboard)
        introSliderViewPager.adapter = introSliderAdapter
        setupIndicators()
        setCurrentIndicator(0)
        introSliderViewPager.registerOnPageChangeCallback(object:
            ViewPager2.OnPageChangeCallback(){

            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                setCurrentIndicator(position)
            }
        }

        )
        buttonNext.setOnClickListener{
            if(introSliderViewPager.currentItem +1 < introSliderAdapter.itemCount){
                introSliderViewPager.currentItem +=1
            }else{
                Intent(applicationContext,signup::class.java).also{
                    startActivity(it)
                    finish()
                }
            }
        }
        textskipIntro.setOnClickListener{
            Intent(applicationContext,signup::class.java).also{
                startActivity(it)
                finish()
            }

        }


    }

    private fun setupIndicators(){
        val indicators = arrayOfNulls<ImageView>(introSliderAdapter.itemCount)
        val layoutParams: LinearLayout.LayoutParams =
            LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
        layoutParams.setMargins(8,0,8,0)
        for(i in indicators.indices){
            indicators[i] = ImageView(applicationContext)
            indicators[i].apply {
                this?.setImageDrawable(
                    ContextCompat.getDrawable(
                        applicationContext,
                        R.drawable.indicator_inactive
                    )
                )
                this?.layoutParams = layoutParams
            }
            indicatorsContainer.addView(indicators[i])
        }


    }
    private fun setCurrentIndicator(index:Int){
        val childCount = indicatorsContainer.childCount
        for(i in 0 until childCount){
            val imageView = indicatorsContainer.get(i) as  ImageView

            if(i == index){
                imageView.setImageDrawable(
                    ContextCompat.getDrawable(
                        applicationContext,
                        R.drawable.indicator_active
                    )
                )
            }else{
                imageView.setImageDrawable(
                    ContextCompat.getDrawable(
                        applicationContext,
                        R.drawable.indicator_inactive
                    )
                )

            }
        }
    }
}
