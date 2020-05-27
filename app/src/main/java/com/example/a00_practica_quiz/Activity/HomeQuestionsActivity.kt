package com.example.a00_practica_quiz.Activity


import android.os.Bundle
import com.example.a00_practica_quiz.AbstractClass.BaseActivity
import com.example.a00_practica_quiz.Fragments.FragmentCountdownAnimation
import com.example.a00_practica_quiz.R

class HomeQuestionsActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_questions)

        gotoFragment(FragmentCountdownAnimation())
    }
}
