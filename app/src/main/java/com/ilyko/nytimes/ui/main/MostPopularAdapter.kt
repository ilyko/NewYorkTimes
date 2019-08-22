package com.ilyko.nytimes.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import com.ilyko.nytimes.R
import com.ilyko.nytimes.databinding.ArticleItemBinding
import com.ilyko.nytimes.model.ArticleDto

import com.ilyko.nytimes.ui.common.DataBoundListAdapter

class MostPopularAdapter(
        private val OnArticleClick: ((ArticleDto) -> Unit)?
) : DataBoundListAdapter<ArticleDto, ArticleItemBinding>(
        diffCallback = object : DiffUtil.ItemCallback<ArticleDto>() {
            override fun areItemsTheSame(oldItem: ArticleDto, newItem: ArticleDto): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: ArticleDto, newItem: ArticleDto): Boolean {
                return oldItem == newItem
            }
        }
) {

    override fun createBinding(parent: ViewGroup): ArticleItemBinding {
        val binding = DataBindingUtil.inflate<ArticleItemBinding>(
                LayoutInflater.from(parent.context),
                R.layout.article_item,
                parent,
                false
        )
        binding.root.setOnClickListener {
            binding.article?.let {
                OnArticleClick?.invoke(it)
            }

        }
        return binding
    }

    override fun bind(binding: ArticleItemBinding, item: ArticleDto) {
        binding.article = item
    }
}