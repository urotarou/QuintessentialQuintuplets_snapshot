package lab.uro.kitori.quintessentialquintuplets.presentation.screen.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import lab.uro.kitori.quintessentialquintuplets.presentation.screen.select.SelectActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        startActivity(SelectActivity.createIntent(this))
        finish()
    }
}
