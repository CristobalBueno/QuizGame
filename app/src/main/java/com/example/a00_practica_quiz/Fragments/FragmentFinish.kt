package com.example.a00_practica_quiz.Fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.a00_practica_quiz.Models.DataHolder
import com.example.a00_practica_quiz.Models.Match

import com.example.a00_practica_quiz.R
import io.paperdb.Paper
import kotlinx.android.synthetic.main.fragment_finish.*

/**
 * A simple [Fragment] subclass.
 */
class FragmentFinish : Fragment() {

    var allScores: ArrayList<Match> = arrayListOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_finish, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val savedScores: ArrayList<Match> = Paper.book().read("scores", arrayListOf())
        allScores = savedScores

        val match: Match = DataHolder.currenMatch
        allScores.add(match)

        Paper.book().write("scores", allScores)

        allScores.forEach{partida ->
            Log.v("miapp","Usuario: ${partida.name}, puntos: ${partida.score}")
        }

        val myScores = savedScores.filter {
            it.name == match.name && it.score > match.score
        }

        if(myScores.isNotEmpty()){

        }else {

        }
        text_Correct.text = "Aciertos: ${DataHolder.correctAnswers}"

        text_Failed.text = "Erróneas: ${DataHolder.failedAnswers}"
    }

}
