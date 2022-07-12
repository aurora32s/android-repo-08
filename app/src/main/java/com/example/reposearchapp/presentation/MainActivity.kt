package com.example.reposearchapp.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.reposearchapp.data.Result
import com.example.reposearchapp.data.remote.RepoService
import com.example.reposearchapp.data.safeApiCall
import com.example.reposearchapp.databinding.ActivityMainBinding
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        /**
         * 테스트
         */
        lifecycleScope.launch {
            when (val result = safeApiCall { RepoService.api.getUser(1) }) {
                is Result.Success -> {
                    println(result.data)
                }
                is Result.Error -> {
                    println(result.exception)
                }
            }
        }
    }
}