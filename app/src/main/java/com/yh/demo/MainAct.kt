package com.yh.demo

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View

/**
 * Created by CYH on 2020-03-13 14:24
 */

class MainAct : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.act_main)
    }

    fun goNext(view: View) {
        startActivity(Intent(this, SecAct::class.java))
    }


}