package com.example.retrofitexample

import android.os.Bundle
import android.widget.ProgressBar
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.isVisible
import com.example.retrofitexample.databinding.ActivityMainBinding
import com.example.retrofitexample.retrofit.MainViewModel
import com.example.retrofitexample.retrofit.model.MyPost

class MainActivity : AppCompatActivity() {

    private val mainViewModel: MainViewModel by viewModels()
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets

        }
        mainViewModel.post.observe(this) { myPost ->
            binding.txtPost.text = myPost.title
        }

        mainViewModel.isLoading.observe(this) { isLoading ->
           binding.progress.isVisible = isLoading
        }

        mainViewModel.hasError.observe(this) { hasError ->
            binding.txtError.isVisible = hasError
        }

    }
}