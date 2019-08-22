package com.ilyko.nytimes.ui.favorites

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import com.ilyko.nytimes.R
import com.ilyko.nytimes.databinding.FragmentPageBinding
import com.ilyko.nytimes.ui.common.BaseFragment
import com.ilyko.nytimes.ui.main.MainFragmentDirections
import com.ilyko.nytimes.ui.main.MostPopularAdapter
import kotlinx.android.synthetic.main.fragment_page.*

class FavoritesFragment :
    BaseFragment<FavoriteViewModel, FragmentPageBinding>(FavoriteViewModel::class.java, R.layout.fragment_page) {

    private lateinit var adapter: MostPopularAdapter

    companion object {

        @JvmStatic
        fun newInstance(): FavoritesFragment {
            return FavoritesFragment()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = MostPopularAdapter { article ->
            Navigation.findNavController(binding.root)
                .navigate(MainFragmentDirections.actionDetail().setArticleDto(article))
        }
        articleRecyclerView.adapter = adapter
        initRecyclerView()
        viewModel.loadFavoriteArticles()
    }



    private fun initRecyclerView() {
        viewModel.articles.observe(viewLifecycleOwner, Observer { result ->
            adapter.submitList(result)
        })
    }
}