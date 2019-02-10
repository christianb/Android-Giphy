package com.bunk.view.list

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.bunk.domain.model.Gif
import com.bunk.view.R
import kotlinx.android.synthetic.main.activity_giphy_list.*
import org.koin.androidx.viewmodel.ext.android.viewModel

private val TAG = GiphyListActivity::class.java.simpleName

private const val SPAN_COUNT = 3

class GiphyListActivity : AppCompatActivity() {

    private val giphyListViewModel: GiphyListViewModel by viewModel()

    private val giphyListAdapter = GiphyListAdapter().apply {
        gifItemClickListener = object : GifItemClickListener {
            override fun onItemClick(gif: Gif) {
                // todo implement me
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_giphy_list)

        with(recyclerView) {
            adapter = giphyListAdapter
            layoutManager = GridLayoutManager(context, SPAN_COUNT)
//            addItemDecoration(VerticalSpaceItemDecoration(VERTICAL_SPACE_HEIGHT))
//            addItemDecoration(
//                androidx.recyclerview.widget.DividerItemDecoration(
//                    context,
//                    androidx.recyclerview.widget.LinearLayoutManager.VERTICAL
//                )
//            )
//            addOnScrollListener(paginationScrollListener)
        }

        giphyListViewModel.gifLiveData().observe(this,
            Observer<List<Gif>> {
                giphyListAdapter.submitList(it)
            }
        )

        giphyListViewModel.onCreate()
    }
}
