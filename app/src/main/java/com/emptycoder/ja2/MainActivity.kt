package com.emptycoder.ja2

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.Switch
import android.widget.Toast
import com.emptycoder.ja2.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), View.OnClickListener {

    lateinit var binding: ActivityMainBinding
    var player1 = 0
    var player2 = 1
    var activePlayer = player1
    var gameActive = true

     lateinit var fillPos : IntArray
    lateinit var b0: Button
    lateinit var b1: Button
    lateinit var b2: Button
    lateinit var b3: Button
    lateinit var b4: Button
    lateinit var b5: Button
    lateinit var b6: Button
    lateinit var b7: Button
    lateinit var b8: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        fillPos = intArrayOf(-1,-1,-1,-1,-1,-1,-1,-1,-1)

         b0 = binding.b0
         b1 = binding.b1
         b2 = binding.b2
         b3 = binding.b3
         b4 = binding.b4
         b5 = binding.b5
         b6 = binding.b6
         b7 = binding.b7
         b8 = binding.b8

        b0.setOnClickListener(this)
        b1.setOnClickListener(this)
        b2.setOnClickListener(this)
        b3.setOnClickListener(this)
        b4.setOnClickListener(this)
        b5.setOnClickListener(this)
        b6.setOnClickListener(this)
        b7.setOnClickListener(this)
        b8.setOnClickListener(this)

        defaultBg()
        binding.restartBtn.setOnClickListener { restart() }

    }

    override fun onClick(v: View?) {

        if(!gameActive)
            return

        val btnClicked = findViewById<Button>(v!!.id)
        var tagClicked = Integer.parseInt(btnClicked.tag.toString())

        if (fillPos[tagClicked] != -1)
            return

        fillPos[tagClicked] = activePlayer

        if (activePlayer == player1) {
                binding.player2form.setBackgroundResource(R.drawable.p2active_bg)
                binding.player1form.setBackgroundResource(R.drawable.noactive_bg)
                binding.player2form.setTextColor(Color.RED)
                binding.player1form.setTextColor(Color.BLACK)

                btnClicked.setText("o")
                btnClicked.backgroundTintList = getColorStateList(R.color.green)
                activePlayer = player2
        }else{
                binding.player1form.setBackgroundResource(R.drawable.p1active_bg)
                binding.player2form.setBackgroundResource(R.drawable.noactive_bg)
                binding.player1form.setTextColor(Color.GREEN)
                binding.player2form.setTextColor(Color.BLACK)

                btnClicked.setText("x")
                btnClicked.backgroundTintList = getColorStateList(R.color.red)
                activePlayer = player1
            }
            check()


    }//end of onclick
    fun check(){
        var winPos =arrayOf(intArrayOf(0,1,2),intArrayOf(3,4,5),intArrayOf(6,7,8),
                intArrayOf(0,3,6),intArrayOf(1,4,7),intArrayOf(2,4,8),intArrayOf(0,4,8), intArrayOf(2,4,6))

        for(i in 0 until winPos.size){
            var value0 = winPos[i][0]
            var value1 = winPos[i][1]
            var value2 = winPos[i][2]

            if(fillPos[value0] == fillPos[value1] && fillPos[value1] == fillPos[value2]){

                if(fillPos[value0] != -1){
                    gameActive = false
                    if(fillPos[value0] == player1){
                        Toast.makeText(this,"Player 1 is winner",Toast.LENGTH_LONG).show()
                    }else if(fillPos[value0]== player2){
                        Toast.makeText(this,"Player 2 is winner",Toast.LENGTH_LONG).show()
                    }
                    return
                }
            }//end of if

            //check draw
            var count = 0
            for(i in 0 until fillPos.size){
                if(fillPos[i] == -1)
                    count++
            }
            if(count == 0){
                Toast.makeText(this,"Draw match",Toast.LENGTH_LONG).show()
            }
        }
    }//end of check

    //game restart
    private fun restart(){
       activePlayer = player1
        gameActive = true
        defaultBg()
       for(i in 0..fillPos.size-1){
           fillPos[i] = -1
            when(i){
                0 -> {
                        b0.setText("")
                        b0.backgroundTintList = getColorStateList(R.color.purple_700)
                     }
                1 ->{
                        b1.setText("")
                        b1.backgroundTintList = getColorStateList(R.color.purple_700)
                    }
                2 -> {
                        b2.setText("")
                        b2.backgroundTintList = getColorStateList(R.color.purple_700)
                    }
                3 ->{
                        b3.setText("")
                        b3.backgroundTintList = getColorStateList(R.color.purple_700)
                    }
                4 -> {
                        b4.setText("")
                        b4.backgroundTintList = getColorStateList(R.color.purple_700)
                    }
                5 -> {
                        b5.setText("")
                        b5.backgroundTintList = getColorStateList(R.color.purple_700)
                    }
                6 -> {
                        b6.setText("")
                        b6.backgroundTintList = getColorStateList(R.color.purple_700)
                    }
                7 -> {
                        b7.setText("")
                        b7.backgroundTintList = getColorStateList(R.color.purple_700)
                    }
                8 -> {
                        b8.setText("")
                        b8.backgroundTintList = getColorStateList(R.color.purple_700)
                    }
            }
       }
    }//end of restart

    private fun defaultBg(){
        binding.player1form.setBackgroundResource(R.drawable.p1active_bg)
        binding.player1form.setTextColor(Color.GREEN)
        binding.player2form.setBackgroundResource(R.drawable.noactive_bg)
    }
}

