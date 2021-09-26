package com.pranavkonduru.nasaapod.ui.apod

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.Gravity
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target
import com.bumptech.glide.signature.ObjectKey
import com.pranavkonduru.nasaapod.NasaNewsApplication
import com.pranavkonduru.nasaapod.R
import com.pranavkonduru.nasaapod.data.models.APODItem
import com.pranavkonduru.nasaapod.di.qualifiers.APIKey
import com.pranavkonduru.nasaapod.ui.apod.viewmodel.APODViewModel
import com.pranavkonduru.nasaapod.ui.apod.viewmodel.APODViewModelFactory
import com.pranavkonduru.nasaapod.util.getDateTextWithFormat
import com.pranavkonduru.nasaapod.util.hide
import com.pranavkonduru.nasaapod.util.show
import com.pranavkonduru.nasaapod.util.showToast
import kotlinx.android.synthetic.main.activity_apod.*
import javax.inject.Inject

class ApodActivity : AppCompatActivity() {
    @Inject
    lateinit var factory: APODViewModelFactory

    @Inject
    @APIKey
    lateinit var apiKey: String

    private lateinit var viewModel: APODViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_apod)
        injectActivity()
        prepareViewModel()
    }

    override fun onResume() {
        super.onResume()
        progressBar.show()
        viewModel.getAstronomyPicOfTheDay(apiKey)
    }

    private fun injectActivity() {
        getApp().appComponent.inject(this)
    }
    private fun getApp(): NasaNewsApplication {
        return applicationContext as NasaNewsApplication
    }
    private fun prepareViewModel() {
        viewModel = ViewModelProvider(this, factory).get(APODViewModel::class.java)
        viewModel.apodItem.observe(this, Observer { apodItem ->
            if(apodItem != null){
                setAPODItem(apodItem)
            } else {
                setEmptyView()
            }
        })
        viewModel.error.observe(this, Observer {
            showToast(it)
        })
    }

    private fun setEmptyView() {
        Glide.with(this).load(R.mipmap.nasa_logo)
            .listener(object : RequestListener<Drawable> {
                override fun onLoadFailed(
                    e: GlideException?, model: Any?, target: Target<Drawable>?,
                    isFirstResource: Boolean
                ): Boolean {
                    progressBar.hide()
                    descText.text = getString(R.string.empty_apod_text)
                    return false
                }
                override fun onResourceReady(
                    resource: Drawable?, model: Any?, target: Target<Drawable>?,
                    dataSource: DataSource?, isFirstResource: Boolean
                ): Boolean {
                    progressBar.hide()
                    descText.text = getString(R.string.empty_apod_text)
                    return false
                }
            }).into(apodImage)
    }

    private fun setAPODItem(apodItem: APODItem) {
        val requestOptions = RequestOptions()
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .signature(ObjectKey(apodItem.date))
        Glide.with(this).load(apodItem.url)
            .listener(object : RequestListener<Drawable>{
                override fun onLoadFailed(e: GlideException?, model: Any?, target: Target<Drawable>?,
                                          isFirstResource: Boolean): Boolean {
                    setEmptyView()
                    return false
                }
                override fun onResourceReady(resource: Drawable?, model: Any?, target: Target<Drawable>?,
                                             dataSource: DataSource?, isFirstResource: Boolean): Boolean {

                    titleText.text = apodItem.title
                    dateText.text = getDateTextWithFormat(apodItem.date)
                    descText.text = apodItem.explanation
                    descText.gravity = Gravity.START
                    progressBar.hide()
                    return false
                }
            }).apply(requestOptions).into(apodImage)
    }
}