package com.bunk.view.list

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.bunk.domain.model.Gif
import com.bunk.view.R
import com.bunk.view.detail.GiphyDetailActivity
import kotlinx.android.synthetic.main.activity_giphy_list.*
import org.koin.androidx.viewmodel.ext.android.viewModel

private val TAG = GiphyListActivity::class.java.simpleName

private const val SPAN_COUNT = 3

class GiphyListActivity : AppCompatActivity(), GiphyListViewModel.View {

    private val giphyListViewModel: GiphyListViewModel by viewModel()

    private val giphyListAdapter = GiphyListAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_giphy_list)

        with(recyclerView) {
            adapter = giphyListAdapter
            layoutManager = GridLayoutManager(context, SPAN_COUNT)
        }

        giphyListAdapter.gifItemClickListener = giphyListViewModel

        val activity = this
        with(giphyListViewModel) {
            view = activity

            gifLiveData().observe(activity,
                Observer<List<Gif>> {
                    giphyListAdapter.submitList(it)
                }
            )

            errorLiveData().observe(activity,
                Observer<Int> {
                    Toast.makeText(activity, it, Toast.LENGTH_SHORT).show()
                }
            )

            onCreate()
        }
    }

    override fun openDetail(url: String) {
        startActivity(GiphyDetailActivity.createIntent(this, url))
    }

    override fun onDestroy() {
        super.onDestroy()

        giphyListAdapter.gifItemClickListener = null
    }
}
