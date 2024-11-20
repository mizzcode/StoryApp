package com.dicoding.picodiploma.storyapp.view.detail

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.dicoding.picodiploma.storyapp.R
import com.dicoding.picodiploma.storyapp.data.Result
import com.dicoding.picodiploma.storyapp.databinding.ActivityDetailBinding
import com.dicoding.picodiploma.storyapp.view.ViewModelFactory

class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding

    private val viewModel by viewModels<DetailViewModel> {
        ViewModelFactory.getInstance(this)
    }

    companion object {
        const val EXTRA_ID = "extra_id"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        supportActionBar?.apply {
            title = getString(R.string.detail_story)
            setDisplayHomeAsUpEnabled(true)
        }

        val id = intent.getStringExtra(EXTRA_ID)

        if (id != null) {
            viewModel.getDetailStory(id).observe(this) {result ->
                if (result != null) {
                    when (result) {
                        is Result.Error -> {
                            binding.progressBar.visibility = View.GONE
                        }
                        Result.Loading -> {
                            binding.progressBar.visibility = View.VISIBLE
                        }
                        is Result.Success -> {
                            binding.progressBar.visibility = View.GONE

                            val data = result.data

                            Glide.with(this@DetailActivity)
                                .load(data.story?.photoUrl)
                                .diskCacheStrategy(DiskCacheStrategy.ALL) // Menyimpan gambar di disk cache
                                .listener(object : RequestListener<Drawable> {
                                    override fun onLoadFailed(
                                        e: GlideException?,
                                        model: Any?,
                                        target: Target<Drawable>,
                                        isFirstResource: Boolean
                                    ): Boolean {
                                        binding.progressBar.visibility = View.GONE
                                        Toast.makeText(this@DetailActivity, "Tidak ada koneksi internet", Toast.LENGTH_SHORT).show()
                                        return false
                                    }

                                    override fun onResourceReady(
                                        resource: Drawable,
                                        model: Any,
                                        target: Target<Drawable>?,
                                        dataSource: DataSource,
                                        isFirstResource: Boolean
                                    ): Boolean {
                                        binding.progressBar.visibility = View.GONE
                                        return false
                                    }

                                })
                                .into(binding.ivStory)

                            binding.titleStory.text = data.story?.name
                            binding.descStory.text = data.story?.description
                        }
                    }
                }
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                onBackPressedDispatcher.onBackPressed()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}