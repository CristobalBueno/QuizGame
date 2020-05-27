package com.example.a00_practica_quiz.Models

import com.google.gson.Gson

class Quiz {

    //Inicializamos GSON
    var gson = Gson()

    val jsonString = """{
  "allQuestions": [
    {
      "title": "¿Cuál es una variable inmutable en kotlin?",
      "category": "Variables",
      "answers": [
        {
          "text": "var",
          "isCorrect": false
        },
        {
          "text": "let",
          "isCorrect": false
        },
        {
          "text": "val",
          "isCorrect": true
        },
        {
          "text": "kot",
          "isCorrect": false
        }
      ]
    },
    {
      "title": "¿A qué tipo de variables se le aplica lateinit?",
      "category": "Variables",
      "answers": [
        {
          "text": "var",
          "isCorrect": true
        },
        {
          "text": "let",
          "isCorrect": false
        },
        {
          "text": "val",
          "isCorrect": false
        },
        {
          "text": "kot",
          "isCorrect": false
        }
      ]
    },
    {
      "title": "¿A qué tipo de variables se le aplica lazy?",
      "category": "Variables",
      "answers": [
        {
          "text": "var",
          "isCorrect": false
        },
        {
          "text": "let",
          "isCorrect": false
        },
        {
          "text": "val",
          "isCorrect": true
        },
        {
          "text": "kot",
          "isCorrect": false
        }
      ]
    },
    {
      "title": "Cuando se lanza tu aplicación, ¿qué función se ejecuta primero?",
      "category": "Variables",
      "answers": [
        {
          "text": "onStart()",
          "isCorrect": false
        },
        {
          "text": "onView()",
          "isCorrect": false
        },
        {
          "text": "onCreate()",
          "isCorrect": true
        },
        {
          "text": "onCreateNow()",
          "isCorrect": false
        }
      ]
    },
    {
      "title": "Las variables que son objeto, ¿pueden tener más objetos dentro?",
      "category": "Variables",
      "answers": [
        {
          "text": "Sí",
          "isCorrect": true
        },
        {
          "text": "No",
          "isCorrect": false
        },
        {
          "text": "Solo 1 objeto más",
          "isCorrect": false
        },
        {
          "text": "Los objetos no existen",
          "isCorrect": false
        }
      ]
    }
  ]
}"""
    //allQuestions realmente va al DataHolder, para poder transmitir datos facilmente.
    var allQuestions: QuestionList = gson.fromJson(jsonString, QuestionList::class.java)
}