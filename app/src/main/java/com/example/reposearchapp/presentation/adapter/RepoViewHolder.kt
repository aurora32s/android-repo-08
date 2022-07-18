package com.example.reposearchapp.presentation.adapter

import androidx.recyclerview.widget.RecyclerView
import com.example.reposearchapp.data.RepoItemModel
import com.example.reposearchapp.databinding.ItemRepoBinding

class RepoViewHolder(private val binding: ItemRepoBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(item: RepoItemModel) {
        binding.item = item
    }
}