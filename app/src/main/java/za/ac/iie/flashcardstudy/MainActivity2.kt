package za.ac.iie.flashcardstudy

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class MainActivity2 : AppCompatActivity() {

    private val questions = arrayOf(
        "Did Hitler become the leader of the Nazi Party in 1930?",
        "Was Adolf Hitler born in 1889 in Austria-Hungary?",
        "Did Hitler's invasion of Poland in 1939 trigger World War Two?",
        "Did Hitler commit suicide on April 30, 1945?",
        "Did Hitler's implementation of the policy lead to the extermination of millions of Jewish people?"
    )

    private val answers = arrayOf(true, false, true, true, true)
    private var currentIndex = 0
    private var score = 0

    private lateinit var questionTextView: TextView
    private lateinit var feedbackTextView: TextView
    private lateinit var trueButton: Button
    private lateinit var falseButton: Button
    private lateinit var nextButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main2)

        // Linking views
        questionTextView = findViewById(R.id.txtQuestionDiscription)
        feedbackTextView = findViewById(R.id.txtFeedback)
        trueButton = findViewById(R.id.btnTrue)
        falseButton = findViewById(R.id.btnFalse)
        nextButton = findViewById(R.id.btnNext)

        loadQuestion()

        trueButton.setOnClickListener {
            checkAnswer(true)
        }

        falseButton.setOnClickListener {
            checkAnswer(false)
        }

        nextButton.setOnClickListener {
            goToNextQuestion()
        }
    }

    private fun loadQuestion() {
        questionTextView.text = questions[currentIndex]
        feedbackTextView.text = ""
    }

    private fun checkAnswer(userAnswer: Boolean) {
        if (userAnswer == answers[currentIndex]) {
            score++
            feedbackTextView.text = "Correct!"
        } else {
            feedbackTextView.text = "Incorrect!"
        }

        trueButton.isEnabled = false
        falseButton.isEnabled = false
    }

    private fun goToNextQuestion() {
        currentIndex++
        if (currentIndex < questions.size) {
            loadQuestion()
            trueButton.isEnabled = true
            falseButton.isEnabled = true
        } else {
            val intent = Intent(this, MainActivity3::class.java)
            intent.putExtra("score", score)
            intent.putExtra("questions", questions)
            intent.putExtra("answers", answers)
            startActivity(intent)
            finish()
        }
    }
}
