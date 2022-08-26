package com.knor.ar_jhosua

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.knor.ar_jhosua.SimpleRecord

class SimpleRecord : AppCompatActivity() {

    companion object{
        private const val CAMERA_PERMISSION_CODE = 100
        private const val STORAGE_PERMISSION_CODE = 101
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_simple_record)

        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_VISIBLE
        actionBar?.hide()

        val open : Button? = findViewById(R.id.buttonSTART)

        checkPermission(
            Manifest.permission.CAMERA,
            CAMERA_PERMISSION_CODE)

        open?.setOnClickListener {
            val intent = Intent(this,BaseApp::class.java)
            startActivity(intent)
        }

    }

    /////////////////////////////////////////
    private fun checkPermission(permission: String, requestCode: Int) {
        if (ContextCompat.checkSelfPermission(this@SimpleRecord, permission) == PackageManager.PERMISSION_DENIED) {

            // Requesting the permission
            ActivityCompat.requestPermissions(this@SimpleRecord, arrayOf(permission), requestCode)
        } else {
            Toast.makeText(this@SimpleRecord, "Permission already granted", Toast.LENGTH_SHORT).show()
        }
    }

    // This function is called when the user accepts or decline the permission.
    // Request Code is used to check which permission called this function.
    // This request code is provided when the user is prompt for permission.
    override fun onRequestPermissionsResult(requestCode: Int,
                                            permissions: Array<String>,
                                            grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == com.knor.ar_jhosua.SimpleRecord.CAMERA_PERMISSION_CODE) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this@SimpleRecord, "Camera Permission Granted", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this@SimpleRecord, "Camera Permission Denied", Toast.LENGTH_SHORT).show()
            }
        } else if (requestCode == com.knor.ar_jhosua.SimpleRecord.STORAGE_PERMISSION_CODE) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this@SimpleRecord, "Storage Permission Granted", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this@SimpleRecord, "Storage Permission Denied", Toast.LENGTH_SHORT).show()
            }
        }
    }
}