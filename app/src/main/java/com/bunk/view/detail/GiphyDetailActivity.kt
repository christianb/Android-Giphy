package com.bunk.view.detail

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bunk.util.ImageProvider
import com.bunk.view.R
import kotlinx.android.synthetic.main.activity_giphy_detail.*

private const val GIF_URL = "gifUrl"

class GiphyDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_giphy_detail)

        intent.getStringExtra(GIF_URL)?.let {
            ImageProvider().url(it).into(detailGifImageView)
        }
    }

    companion object {
        fun createIntent(context: Context, gifUrl: String) =
            Intent(context, GiphyDetailActivity::class.java).apply {
                putExtra(GIF_URL, gifUrl)
            }
    }
}