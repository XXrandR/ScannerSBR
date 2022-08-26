package com.knor.ar_jhosua

import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.Toast
import net.kuama.documentscanner.exceptions.MissingSquareException
import net.kuama.documentscanner.presentation.BaseScannerActivity
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*


class BaseApp : BaseScannerActivity() {


    override fun onError(throwable: Throwable) {
        when(throwable){
            is MissingSquareException -> Toast.makeText(this, "ATTEMPT TO CONVERTED", Toast.LENGTH_LONG)
                .show()
            else -> Toast.makeText(this, "ERROR IN SOMETHING", Toast.LENGTH_LONG).show()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        setContentView(R.layout.activity_main2)

        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_VISIBLE
        actionBar?.hide()

        val camera: Button? = findViewById(R.id.btnSome)
    }

    override fun onDocumentAccepted(bitmap: Bitmap) {
        // WRITE THE IMAGE
        saveImage(bitmap)
        Toast.makeText(this, "Image Generated Correctly!...It's great!",Toast.LENGTH_SHORT)
    }

    override fun onClose() {
        finish()
    }

    // FUNCTION TO SAVE THE IMAGE
    private fun saveImage(imgBitmap : Bitmap) : Uri {

        val timeStamp : String = SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())

        val fileName : String = "recognizement_${timeStamp}.jpg"

        val storagePath : String = filesDir.toString()
        val storageDir = File(storagePath)
        if(!storageDir.exists()) {
            Log.i("TAG", "Directory is newly created")
            storageDir.mkdirs()
        }
        if (storageDir.exists()) {
            Log.i("TAG", "Directory is available")
        }

        // Save image to storage
        val imageFile = File(storageDir,fileName)
        Log.i("TAG", "File created with path ${imageFile.absolutePath}")
        try {
            val fOut = FileOutputStream(imageFile)
            imgBitmap.compress(Bitmap.CompressFormat.JPEG, 50, fOut)
            fOut.flush()
            fOut.close()
            Log.i("TAG", "Image file created.")
        } catch (e: IOException) {
            e.printStackTrace()
            Log.i("TAG", "Image file not success.")
        }

        // Parse the gallery image url to uri
        return Uri.parse(imageFile.absolutePath)
    }
}