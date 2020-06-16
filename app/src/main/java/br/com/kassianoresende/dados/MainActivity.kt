package br.com.kassianoresende.dados

import android.graphics.drawable.AnimationDrawable
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rootLayout.setOnClickListener {
            animateDice {
                val number = (1..6).random()
                imageView.setImageResource(number.getDice())
                textViewNumber.text = number.toString()
            }
        }
    }

    private fun animateDice(callback: () -> Unit) {
        imageView.setImageResource(R.drawable.sprite_list)
        val animation = imageView.drawable as AnimationDrawable
        animation.onAnimationFinished {
            callback.invoke()
        }
        animation.start()
    }

    private fun Int.getDice(): Int = when(this) {
            1 -> R.drawable.dice_1
            2 -> R.drawable.dice_2
            3 -> R.drawable.dice_3
            4 -> R.drawable.dice_4
            5 -> R.drawable.dice_5
            6 -> R.drawable.dice_6
            else -> R.drawable.empty_dice
        }

    private fun AnimationDrawable.onAnimationFinished(block: () -> Unit) {
        var duration: Long = 0
        for (i in 0..numberOfFrames) {
            duration += getDuration(i)
        }
        Handler().postDelayed({
            block()
        }, duration)
    }
}