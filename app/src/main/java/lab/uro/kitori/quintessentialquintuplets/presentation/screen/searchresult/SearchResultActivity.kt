package lab.uro.kitori.quintessentialquintuplets.presentation.screen.searchresult

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import lab.uro.kitori.quintessentialquintuplets.R
import lab.uro.kitori.quintessentialquintuplets.databinding.ActivitySearchResultBinding
import lab.uro.kitori.quintessentialquintuplets.domain.value.Heroine
import lab.uro.kitori.quintessentialquintuplets.presentation.screen.imageviewer.ImageViewerFragment
import lab.uro.kitori.quintessentialquintuplets.presentation.view.bottomsheet.TweetContextMenuBottomSheet

class SearchResultActivity : AppCompatActivity() {
    companion object {
        private const val KEY_EXTRA_HEROINE = "key_extra_heroine"

        fun createIntent(context: Context, heroine: Heroine) = Intent(context, SearchResultActivity::class.java).apply {
            putExtra(KEY_EXTRA_HEROINE, heroine)
        }
    }

    private lateinit var binding: ActivitySearchResultBinding
    private lateinit var viewModel: SearchResultViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_search_result)
        viewModel = ViewModelProviders.of(this, SearchResultViewModelFactory(application, getSelectedHeroine()))
                .get(SearchResultViewModel::class.java)

        initLayout()
        setupDateStream()
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
        supportActionBar?.run {
            title = getSelectedHeroineName()
            setDisplayHomeAsUpEnabled(true)
        }

        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = SearchResultAdapter(getSelectedHeroine(), object : SearchResultAdapter.TweetActionListener {
            override fun onUserClick(userName: String) {
                startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(userName)))
            }

            override fun onTextClick(tweetUrl: String) {
                startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(tweetUrl)))
            }

            override fun onImageClick(imageUrl: String) {
                supportFragmentManager.beginTransaction()
                        .replace(R.id.fragment_container_layout, ImageViewerFragment.newInstance(imageUrl))
                        .addToBackStack(ImageViewerFragment.TAG)
                        .commit()
            }

            override fun onImageLongClick(tweetUrl: String, imageUrl: String): Boolean {
                TweetContextMenuBottomSheet.newInstance(tweetUrl, imageUrl)
                        .show(supportFragmentManager, TweetContextMenuBottomSheet.TAG)
                return true
            }
        })
    }

    private fun setupDateStream() {
        viewModel.tweets.observe(this, Observer {
            (binding.recyclerView.adapter as SearchResultAdapter).submitList(it)
        })

        viewModel.loadingState.observe(this, Observer {
            binding.progressBar.visibility = View.GONE
            (binding.recyclerView.adapter as SearchResultAdapter).changeLoadingState(it)
        })
    }

    private fun getSelectedHeroine() = intent.getSerializableExtra(KEY_EXTRA_HEROINE) as Heroine

    private fun getSelectedHeroineName() = getString(getSelectedHeroine().nameResId)
}
