package com.pranavkonduru.nasaapod.ui.nasanewslist

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.pranavkonduru.nasaapod.R
import com.pranavkonduru.nasaapod.data.models.listeners.NasaNewsItemClickListener
import com.pranavkonduru.nasaapod.ui.apod.ApodActivity
import com.pranavkonduru.nasaapod.ui.nasanewslist.adapters.NasaNewsListAdapter
import com.pranavkonduru.nasaapod.util.DataUtils
import com.pranavkonduru.nasaapod.util.hide
import com.pranavkonduru.nasaapod.util.show
import com.pranavkonduru.nasaapod.util.showToast
import kotlinx.android.synthetic.main.activity_nasa_news_list.*

class NasaNewsListActivity : AppCompatActivity(), NasaNewsItemClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nasa_news_list)
        initViews()
    }
    private fun initViews() {
        nasaNewsItemsListView.also {
            it?.setHasFixedSize(true)
            it?.adapter = NasaNewsListAdapter(
                DataUtils.fetchNasaNewsItemsList(),
                this@NasaNewsListActivity
            )
        }
    }
    override fun onItemClick(position: Int) {
        when (position) {
            1 ->  navigateToApodActivity()
            else -> showToast("Under Construction!!!")
        }
    }

    private fun navigateToApodActivity() {
        progressBar?.show()
        val intent = Intent(this@NasaNewsListActivity, ApodActivity::class.java)
        startActivity(intent)
    }

    override fun onResume() {
        super.onResume()
        progressBar?.hide()
    }
}