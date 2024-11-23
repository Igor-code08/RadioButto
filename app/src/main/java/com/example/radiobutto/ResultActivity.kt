package com.example.radiobutto

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class ResultActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.result_activity)

        val resultTextView: TextView = findViewById(R.id.resultTextView)
        val descriptionTextView: TextView = findViewById(R.id.descriptionTextView)

        // Получаем итоговый результат из Intent
        val result = intent.getIntExtra("result", 0)

        // Определяем характеристику в зависимости от результата
        val description = when (result) {
            in 400..500 -> "Отлично! Вы настоящий знаток истории!"
            in 300..399 -> "Хорошо! Вы знаете историю, но есть куда расти."
            in 200..299 -> "Удовлетворительно. Рекомендуем освежить знания."
            in 100..199 -> "Неудовлетворительно. История требует изучения."
            else -> "Плохо. Попробуйте ещё раз!"
        }

        resultTextView.text = "Ваш результат: $result баллов"
        descriptionTextView.text = description
    }
}