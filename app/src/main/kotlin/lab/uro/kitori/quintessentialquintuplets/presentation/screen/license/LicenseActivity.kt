package lab.uro.kitori.quintessentialquintuplets.presentation.screen.license

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import lab.uro.kitori.quintessentialquintuplets.R
import lab.uro.kitori.quintessentialquintuplets.databinding.ActivityLicenseBinding

class LicenseActivity : AppCompatActivity() {
    companion object {
        fun createIntent(context: Context) = Intent(context, LicenseActivity::class.java)
    }

    private lateinit var binding: ActivityLicenseBinding
    private lateinit var viewModel: LicenseViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_license)
        viewModel = ViewModelProviders.of(this).get(LicenseViewModel::class.java)

        initLayout()
        setupDateStream()

        viewModel.getLicense()
    }

    override fun onOptionsItemSelected(item: MenuItem?) = when (item?.itemId) {
        android.R.id.home -> {
            finish()
            true
        }
        else -> super.onOptionsItemSelected(item)
    }

    private fun initLayout() {
        setSupportActionBar(binding.toolBar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = LicenseAdapter()
        binding.recyclerView.addItemDecoration(
            DividerItemDecoration(
                this, LinearLayoutManager.VERTICAL
            )
        )
    }

    private fun setupDateStream() {
        viewModel.licenses.observe(this, Observer {
            (binding.recyclerView.adapter as LicenseAdapter).setItems(it)
        })
    }
}
