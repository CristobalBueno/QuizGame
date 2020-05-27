package com.example.a00_practica_quiz.Fragments


import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.example.a00_practica_quiz.Activity.HomeQuestionsActivity
import com.example.a00_practica_quiz.Models.DataHolder
import com.example.a00_practica_quiz.Models.Match

import render.animations.*
import com.example.a00_practica_quiz.R
import com.google.android.material.button.MaterialButton
import io.paperdb.Paper
import kotlinx.android.synthetic.main.fragment_question.*


/**
 * A simple [Fragment] subclass.
 */
class FragmentQuestion : Fragment(), View.OnClickListener {

    var currentQuestion = DataHolder.position
    lateinit var timer: CountDownTimer
    var currentPoints: Long = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_question, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Paper.init(context)
        DataHolder.currenMatch.name = DataHolder.username.toString()

        button_option_1.setOnClickListener(this)
        button_option_2.setOnClickListener(this)
        button_option_3.setOnClickListener(this)
        button_option_4.setOnClickListener(this)

        updateViewQuiz(currentQuestion)

        countDownTimer_ProgressBar(20000, progressTimer)
    }

    override fun onDestroy() {
        super.onDestroy()
        //Stop Countdown
        timer.cancel()
    }

    fun countDownTimer_ProgressBar(maxTime: Long, progressTimer: ProgressBar){
        progressTimer.max= maxTime.toInt()
        progressTimer.progress = maxTime.toInt()

        timer = object: CountDownTimer(maxTime, 1000) {

            override fun onTick(millisUntilFinished: Long) {
                pointMacth(millisUntilFinished)

                progressTimer.progress -= 1000
                Log.w("miapp","Time: ${progressTimer.progress}")
            }

            override fun onFinish() {
                nextQuiz()
            }
        }
        timer.start()
    }

    fun updateViewQuiz(currentQuestion: Int){
        button_next.isEnabled = false
        button_next.alpha = 0F

        text_Usuario.text = DataHolder.username
        animateUser()

        text_Question.text = DataHolder.dataQuestion.allQuestions[currentQuestion].title
        text_Category.text = DataHolder.dataQuestion.allQuestions[currentQuestion].category

        button_option_1.text = DataHolder.dataQuestion.allQuestions[currentQuestion].answers[0].text
        button_option_2.text = DataHolder.dataQuestion.allQuestions[currentQuestion].answers[1].text
        button_option_3.text = DataHolder.dataQuestion.allQuestions[currentQuestion].answers[2].text
        button_option_4.text = DataHolder.dataQuestion.allQuestions[currentQuestion].answers[3].text
    }

    override fun onClick(click: View?) {
        val allButton = arrayListOf(button_option_1,button_option_2,button_option_3,button_option_4)
        val buttonSelected = click as MaterialButton

        allButton[0].tag = DataHolder.dataQuestion.allQuestions[currentQuestion].answers[0].isCorrect
        allButton[1].tag = DataHolder.dataQuestion.allQuestions[currentQuestion].answers[1].isCorrect
        allButton[2].tag = DataHolder.dataQuestion.allQuestions[currentQuestion].answers[2].isCorrect
        allButton[3].tag = DataHolder.dataQuestion.allQuestions[currentQuestion].answers[3].isCorrect

        logicQuiz(buttonSelected, allButton)

    }

    fun pointMacth(points: Long){
        //puntuacion de los milisegundos:
        currentPoints = points
    }

    fun logicQuiz(buttonselected: MaterialButton, allButton: ArrayList<Button>){

        allButton.forEach{button->
            val button = button as MaterialButton

            if(button == buttonselected){

                when(buttonselected.tag){
                    true -> {
                        //Sumamos los puntos de la partida actual
                        DataHolder.currenMatch.score += currentPoints

                        buttonselected.setBackgroundColor(ContextCompat.getColor(context!!,R.color.colorCorrect))
                        DataHolder.correctAnswers += 1
                    }
                    false -> {
                        buttonselected.setBackgroundColor(ContextCompat.getColor(context!!,R.color.colorError))
                        DataHolder.failedAnswers += 1
                    }
                }

            }else if (button.tag == true) {
                button.setBackgroundColor(ContextCompat.getColor(context!!, R.color.colorGrey))
                button.strokeWidth = 4
                button.strokeColor = ContextCompat.getColorStateList(context!!, R.color.colorCorrect)
            }else {
                button.setBackgroundColor(ContextCompat.getColor(context!!, R.color.colorGrey))
            }
            button.isEnabled = false
        }

        nextQuiz()
    }

    fun nextQuiz(){
        val totalQuestion = DataHolder.dataQuestion.allQuestions.size

        button_next.isEnabled = true
        button_next.alpha = 1F
        button_next.setBackgroundColor(ContextCompat.getColor(context!!, R.color.colorGrey))
        button_next.text = "Siguiente"
        timer.cancel()

        Log.w("miapp", "PREGUNTA: ${DataHolder.position}")
        button_next.setOnClickListener {
            if(currentQuestion == totalQuestion -1 ){
                (activity as HomeQuestionsActivity).gotoFragment(FragmentFinish())
            }else{
                (activity as HomeQuestionsActivity).gotoFragment(FragmentCountdownAnimation())
            }
        }
        DataHolder.position += 1
    }

    fun animateUser(){
        // Declare TextView
        val user: TextView = (activity as HomeQuestionsActivity).findViewById(R.id.text_Usuario)
        // Create Render Class
        val render = Render(context!!)
        // Set Animation
        render.setAnimation(Zoom().In(user))
        render.setDuration(1400)
        render.start()
    }
}
