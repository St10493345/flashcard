package za.ac.iie.flashcardstudy

import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity3 : AppCompatActivity() {

    private lateinit var scoreTextView: TextView
    private lateinit var reviewButton: Button
    private lateinit var exitButton: Button
    private lateinit var ImageView:ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main3)

        scoreTextView = findViewById(R.id.txtScores)
        reviewButton = findViewById(R.id.btnReview)
        exitButton = findViewById(R.id.btnExit)

        val score = intent.getIntExtra("score", 0)
        val questions = intent.getStringArrayExtra("questions")
        val answers = intent.getBooleanArrayExtra("answers")

        scoreTextView.text = "You go $score out of ${questions?.size}"

        reviewButton.setOnClickListener {
            val reviewMessage = StringBuilder()
            if (questions != null && answers != null) {
                for (i in questions.indices) {
                    reviewMessage.append("${questions[i]}-Answer:${answers[i]}/n")
                }
                scoreTextView.text = reviewMessage.toString()

            }

            exitButton.setOnClickListener {
                finishAffinity()//close the app
            }
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}