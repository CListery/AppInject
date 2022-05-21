package com.yh.demo

import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import android.view.WindowManager
import androidx.appcompat.app.AlertDialog
import com.yh.appbasic.ViewBindingActivity
import com.yh.appbasic.logger.LogsManager
import com.yh.demo.databinding.ActSecBinding

/**
 * Created by CYH on 2020-03-13 14:36
 */
class SecAct : ViewBindingActivity<ActSecBinding>() {
    
    override fun binderCreator(savedInstanceState: Bundle?) = ActSecBinding.inflate(layoutInflater)
    
    override fun ActSecBinding.onInit(savedInstanceState: Bundle?) {
        fab.setOnClickListener {
            showAlert()
        }
    }
    
    private fun showAlert() {
        val alertDialog =
            AlertDialog.Builder(
                mCtx,
                androidx.appcompat.R.style.Theme_AppCompat_Light_Dialog_Alert
            ).setTitle("haha").create()
        if(Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            alertDialog.apply {
                window?.setType(WindowManager.LayoutParams.TYPE_SYSTEM_ALERT)
            }
        } else {
            if(Settings.canDrawOverlays(mCtx)) {
                alertDialog.apply {
                    if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                        window?.setType(WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY)
                    } else {
                        window?.setType(WindowManager.LayoutParams.TYPE_SYSTEM_ALERT)
                    }
                    show()
                }
            } else {
                val intent = Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION)
                intent.data = Uri.parse("package:${packageName}")
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                startActivity(intent)
            }
        }
    }
    
    override fun onDestroy() {
        LogsManager.get().setDefLoggerConfig(
            libConfig = Pair(true, Log.VERBOSE),
            appConfig = Pair(false, Log.VERBOSE)
        )
        
        super.onDestroy()
    }
}