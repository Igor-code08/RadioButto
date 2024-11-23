package com.example.radiobutto

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView

class QuestionActivity : AppCompatActivity() {

    private val questions = listOf(
        Question(
            "В каком году началась Великая Отечественная война?",
            listOf("1941", "1939", "1945"),
            0 // Индекс правильного ответа
        ),
        Question(
            "Кто был первым президентом Российской Федерации?",
            listOf("Борис Ельцин", "Владимир Путин", "Михаил Горбачёв"),
            0
        ),
        Question(
            "Какой город был столицей Руси до Москвы?",
            listOf("Новгород", "Киев", "Владимир"),
            1
        ),
        Question(
            "Какое событие произошло в 1812 году?",
            listOf("Отечественная война", "Крымская война", "Первая мировая война"),
            0
        ),
        Question(
            "Кто написал произведение 'Война и мир'?",
            listOf("Лев Толстой", "Александр Пушкин", "Фёдор Достоевский"),
            0
        )
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_question)

        val questionTextView: TextView = findViewById(R.id.questionTextView)
        val radioGroup: RadioGroup = findViewById(R.id.radioGroup)
        val option1: RadioButton = findViewById(R.id.option1)
        val option2: RadioButton = findViewById(R.id.option2)
        val option3: RadioButton = findViewById(R.id.option3)
        val nextButton: Button = findViewById(R.id.nextButton)

        // Получаем текущий результат и индекс вопроса из Intent
        val currentResult = intent.getIntExtra("result", 0)
        val questionIndex = intent.getIntExtra("questionIndex", 0)

        // Загружаем текущий вопрос
        val currentQuestion = questions[questionIndex]
        questionTextView.text = currentQuestion.text
        option1.text = currentQuestion.options[0]
        option2.text = currentQuestion.options[1]
        option3.text = currentQuestion.options[2]

        nextButton.setOnClickListener {
            // Проверяем, какой вариант выбран
            val selectedOptionIndex = when (radioGroup.checkedRadioButtonId) {
                R.id.option1 -> 0
                R.id.option2 -> 1
                R.id.option3 -> 2
                else -> -1
            }

            // Если выбран правильный ответ, добавляем баллы
            var newResult = currentResult
            if (selectedOptionIndex == currentQuestion.correctAnswerIndex) {
                newResult += 100 // Начисляем баллы за правильный ответ
            }

            // Если это последний вопрос, переходим к ResultActivity
            if (questionIndex == questions.size - 1) {
                val resultIntent = Intent(this, ResultActivity::class.java)
                resultIntent.putExtra("result", newResult)
                startActivity(resultIntent)
            } else {
                // Иначе переходим к следующему вопросу
                val nextQuestionIntent = Intent(this, QuestionActivity::class.java)
                nextQuestionIntent.putExtra("result", newResult)
                nextQuestionIntent.putExtra("questionIndex", questionIndex + 1)
                startActivity(nextQuestionIntent)
            }
        }
    }

    data class Question(
        val text: String,
        val options: List<String>,
        val correctAnswerIndex: Int
    )
}
