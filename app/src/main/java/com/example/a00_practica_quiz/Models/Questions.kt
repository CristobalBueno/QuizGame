package com.example.a00_practica_quiz.Models

data class QuestionList(val allQuestions: ArrayList<Question>)

data class Question(val title: String, val category: String, val answers: ArrayList<Answer>)

data class Answer(val text: String, val isCorrect: Boolean)