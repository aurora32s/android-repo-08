package com.example.reposearchapp.presentation.main

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.commit
import androidx.lifecycle.lifecycleScope
import com.example.reposearchapp.R
import com.example.reposearchapp.data.Token
import com.example.reposearchapp.databinding.ActivityMainBinding
import com.example.reposearchapp.presentation.home.HomeFragment
import com.example.reposearchapp.presentation.login.LoginFragment
import com.example.reposearchapp.presentation.search.SearchFragment
import com.example.reposearchapp.util.Event
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportFragmentManager.commit {
            replace(R.id.fragment_container_main, LoginFragment())
        }

        lifecycleScope.launch {
            viewModel.accessToken.collect {
                Token.token = it
                supportFragmentManager.commit {
                    replace(R.id.fragment_container_main, SearchFragment(), SearchFragment.TAG)
                }
            }
        }

        lifecycleScope.launch {
            viewModel.event.collect {
                when (it) {
                    is Event.Success -> {
                        Toast.makeText(
                            this@MainActivity,
                            getString(R.string.login_success),
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                    is Event.Error -> {
                        showSnackBar(String.format(getString(R.string.login_fail, it.message)))
                    }
                }
            }
        }
    }

    private fun navigateToHomeFragment() {
        supportFragmentManager.commit {
            replace(R.id.fragment_container_main, HomeFragment())
        }
    }

    private fun showSnackBar(message: String) {
        Snackbar.make(binding.root, message, Snackbar.LENGTH_SHORT).show()
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)

        intent?.data?.getQueryParameters("code")?.let {
            viewModel.requestAccessToken(it[0])
        }
    }
}