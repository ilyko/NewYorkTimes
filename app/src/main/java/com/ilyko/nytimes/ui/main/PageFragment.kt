package com.ilyko.nytimes.ui.main

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import com.ilyko.nytimes.R
import com.ilyko.nytimes.databinding.FragmentPageBinding
import com.ilyko.nytimes.model.NetworkState
import com.ilyko.nytimes.model.Status
import com.ilyko.nytimes.ui.common.BaseFragment
import kotlinx.android.synthetic.main.fragment_page.*

class PageFragment : BaseFragment<PageViewModel, FragmentPageBinding>(PageViewModel::class.java, R.layout.fragment_page) {

    private lateinit var adapter: MostPopularAdapter

    companion object {
        private const val ARG_ARTICLE_TYPE = "article_type"

        @JvmStatic
        fun newInstance(articleType: String): PageFragment {
            return PageFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_ARTICLE_TYPE, articleType)
                }
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = MostPopularAdapter { article ->
            Navigation.findNavController(binding.root).navigate(MainFragmentDirections.actionDetail().setArticleDto(article))
        }
        articleRecyclerView.adapter = adapter
        initRecyclerView()
        arguments?.getString(ARG_ARTICLE_TYPE)?.let { viewModel.loadArticles(it) }
        viewModel.networkState.observe(this, Observer {
            setInitialLoadingState(it)
        })
        //setInitialLoadingState()
    }

    private fun initRecyclerView() {
        viewModel.articles.observe(viewLifecycleOwner, Observer { result ->
            adapter.submitList(result)
        })
    }

    private fun setInitialLoadingState(networkState: NetworkState?) {
        //error message
        if (adapter.itemCount == 0) {
            errorMessageTextView.visibility = if (networkState?.message != null) View.VISIBLE else View.GONE
            if (networkState?.message != null) {
                errorMessageTextView.text = networkState.message
            }

            //loading and retry
            retryLoadingButton.visibility = if (networkState?.status == Status.FAILED) View.VISIBLE else View.GONE
            loadingProgressBar.visibility = if (networkState?.status == Status.RUNNING) View.VISIBLE else View.GONE
            retryLoadingButton.setOnClickListener { viewModel.retry() }
        }
    }


}