package com.example.a00_practica_quiz.Fragments

import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.a00_practica_quiz.Activity.HomeQuestionsActivity
import com.example.a00_practica_quiz.Models.DataHolder

import render.animations.*
import com.example.a00_practica_quiz.R
import kotlinx.android.synthetic.main.fragment_countdown_animation.*

/**
 * A simple [Fragment] subclass.
 */
class FragmentCountdownAnimation : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_countdown_animation, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        text_title.text = "¿Estás preparado, ${DataHolder.username}?"
        text_number.text = "3"
        animateNumber()
    }

    fun animateNumber(){
        // Declare TextView
        val animation_number: TextView = (activity as HomeQuestionsActivity).findViewById(R.id.text_number)

        // Create Render Class
        val render = Render(context!!)

        // Set Animation
        render.setAnimation(Zoom().In(animation_number))
        render.setDuration(1000)
        render.start()

        Handler().postDelayed({
            text_number.text = "2"
            render.setAnimation(Zoom().In(animation_number))
            render.setDuration(1000)
            render.start()

            // Goes to the next activity with a little delay
            Handler().postDelayed({
                render.setAnimation(Zoom().In(animation_number))
                render.setDuration(1000)
                render.start()
                text_number.text = "1"

                Handler().postDelayed({
                    (activity as HomeQuestionsActivity).gotoFragment(FragmentQuestion())
                }, 1000)

            }, 1000)

        }, 1000)
    }


}
