package com.example.a00_practica_quiz.Activity


import android.os.Bundle
import android.os.Handler
import android.widget.ImageView
import com.example.a00_practica_quiz.AbstractClass.BaseActivity
import com.example.a00_practica_quiz.R
import render.animations.*

class AnimationActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_animation)

        animationLogo()



    }

    fun animationLogo(){
        // Declare TextView
        val image: ImageView = findViewById(R.id.image_Logo)

        // Create Render Class
        val render = Render(this)

        // Set Animation
        render.setAnimation(Zoom().In(image))
        render.setDuration(1400)
        render.start()

        Handler().postDelayed({
            render.setAnimation(Attention().Tada(image))
            render.setDuration(1000)
            render.start()

            // Goes to the next activity with a little delay
            Handler().postDelayed({
                gotoActivity(StartActivity())
            }, 1500)

        }, 1400)
    }
}
