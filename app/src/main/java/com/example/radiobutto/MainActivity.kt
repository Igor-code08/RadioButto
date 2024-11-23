package com.example.radiobutto

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val startButton: Button = findViewById(R.id.startButton)

        startButton.setOnClickListener {
            // Переход на первый вопрос
            val intent = Intent(this, QuestionActivity::class.java)
            intent.putExtra("result", 0) // Начальный счет равен 0
            intent.putExtra("questionIndex", 0) // Начинаем с первого вопроса
            startActivity(intent)
        }
    }
}