package org.esicad.btssio2aslam.caristsi.caristsi.ui.home

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import org.esicad.btssio2aslam.caristsi.caristsi.R
import org.esicad.btssio2aslam.caristsi.caristsi.scan.QRcodeScannerActivity
import org.esicad.btssio2aslam.caristsi.caristsi.ui.login.LoginActivity
import org.esicad.btssio2aslam.caristsi.caristsi.ui.warehouse.WareHouseComposableActivity

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_home)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val storePackageIntentLauncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
                if (result.resultCode == Activity.RESULT_OK) {
                    Log.i("I", result.data?.data.toString())
                }
            }
        val buttonCamera = findViewById<Button>(R.id.button_store_package)
        buttonCamera.setOnClickListener { _ ->
            // start new activity
            storePackageIntentLauncher.launch(Intent(this, QRcodeScannerActivity::class.java))
        }

        val buttonShowPackages = findViewById<Button>(R.id.button_show_packages_list)
        buttonShowPackages.setOnClickListener { _ ->
            startActivity(Intent(this, WareHouseComposableActivity::class.java))
        }
        val buttonLogout = findViewById<Button>(R.id.button_logout)
        buttonLogout.setOnClickListener { _ ->
            startActivity(Intent(this,LoginActivity::class.java))
            Toast.makeText(
                applicationContext,
                "Vous avez été déconnecté",
                Toast.LENGTH_LONG
            ).show()
            finish()
        }
    }
}