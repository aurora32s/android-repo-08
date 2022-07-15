package com.example.reposearchapp.presentation.search


import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.activityViewModels
import com.example.reposearchapp.R
import com.example.reposearchapp.databinding.FragmentSearchBinding
import com.example.reposearchapp.presentation.adapter.RepoAdapter
import com.example.reposearchapp.presentation.base.BaseFragment


class SearchFragment : BaseFragment<FragmentSearchBinding>(R.layout.fragment_search) {
    private val searchViewModel by activityViewModels<SearchViewModel>()
    lateinit var adapter: RepoAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.vm = searchViewModel

        initSearchView()
        initRecyclerView()
    }

    private fun initSearchView() {
        binding.searchView.run {
            requestFocus()
            setQuery(searchViewModel.lastQuery, false)
            setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    return true
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    searchViewModel.search(newText ?: "")

                    return true
                }
            })
        }
    }

    private fun initRecyclerView() {
        adapter = RepoAdapter()
        binding.rvRepo.adapter = adapter
        searchViewModel.repoList.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }
    }

    companion object {
        const val TAG = "SearchFragment"
    }
}