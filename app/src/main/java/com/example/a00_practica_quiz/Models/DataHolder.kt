package com.example.a00_practica_quiz.Models

object DataHolder {

    //Share Preferences
    val user: String = "username"
    val DBName = "KotlinQuiz"

    var username: String? = null

    var position  = 0

    //Cogemos para nuestra variable global (y transmitir datos facilmente) los datos de Quiz/allquestion (lo que tenemos del Json)
    val dataQuestion: QuestionList = Quiz().allQuestions

    var correctAnswers = 0
    var failedAnswers = 0

    var currenMatch: Match = Match()

}