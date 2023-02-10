package com.example.kt_permission

import android.Manifest
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog

class MainActivity : AppCompatActivity() {


    private val cameraResultLauncher: ActivityResultLauncher<String> =

        ///Show Request dialog
        registerForActivityResult(ActivityResultContracts.RequestPermission()) {


                isGranted ->

            if (isGranted) {

                Toast.makeText(this, "Permission Granted", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(this, "Permission Denied", Toast.LENGTH_LONG).show();

            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val requestBtn: Button = findViewById(R.id.request_btn);

        requestBtn.setOnClickListener {


            if (shouldShowRequestPermissionRationale(
                    Manifest.permission.CAMERA
                )
            ) {

                showRationalDialog(
                    "App requires Camera Access",
                    "Camera cannot be used because permission has not been granted"

                );


            } else {
                cameraResultLauncher.launch(Manifest.permission.CAMERA);
            }
        }


    }

    private fun showRationalDialog(title: String, message: String) {


        val builder: AlertDialog.Builder = AlertDialog.Builder(this);


        builder.setTitle(title)
            .setMessage(message)
            .setPositiveButton("Cancel") {
                    dialog, _ ->
                dialog.dismiss()
            }
        builder.create().show()

    }
}