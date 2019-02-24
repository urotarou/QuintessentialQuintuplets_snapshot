package lab.uro.kitori.quintessentialquintuplets.presentation.screen.select

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import lab.uro.kitori.quintessentialquintuplets.R
import lab.uro.kitori.quintessentialquintuplets.databinding.ActivitySelectBinding
import lab.uro.kitori.quintessentialquintuplets.domain.value.Heroine
import lab.uro.kitori.quintessentialquintuplets.presentation.screen.license.LicenseActivity
import lab.uro.kitori.quintessentialquintuplets.presentation.screen.searchresult.SearchResultActivity

class SelectActivity : AppCompatActivity() {
    companion object {
        fun createIntent(context: Context) = Intent(context, SelectActivity::class.java)
    }

    private lateinit var binding: ActivitySelectBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_select)

        initLayout()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.select_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?) = when (item?.itemId) {
        R.id.menu_license -> {
            startActivity(LicenseActivity.createIntent(this))
            true
        }
        else -> super.onOptionsItemSelected(item)
    }

    private fun initLayout() {
        setSupportActionBar(binding.toolBar)

        binding.ichikaButton.setOnClickListener { startActivity(SearchResultActivity.createIntent(this, Heroine.ICHIKA)) }
        binding.ninoButton.setOnClickListener { startActivity(SearchResultActivity.createIntent(this, Heroine.NINO)) }
        binding.mikuButton.setOnClickListener { startActivity(SearchResultActivity.createIntent(this, Heroine.MIKU)) }
        binding.yotsubaButton.setOnClickListener { startActivity(SearchResultActivity.createIntent(this, Heroine.YOTSUBA)) }
        binding.itsukiButton.setOnClickListener { startActivity(SearchResultActivity.createIntent(this, Heroine.ITSUKI)) }
    }
}
