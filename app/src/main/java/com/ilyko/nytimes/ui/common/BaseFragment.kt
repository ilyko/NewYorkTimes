package com.ilyko.nytimes.ui.common

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import dagger.android.support.DaggerFragment
import javax.inject.Inject

abstract class BaseFragment<VM : ViewModel, DB : ViewDataBinding>(private val mViewModelClass: Class<VM>, @LayoutRes val layoutRes: Int) :
    DaggerFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    val viewModel: VM by lazy { ViewModelProviders.of(this, viewModelFactory).get(mViewModelClass) }

    open lateinit var binding: DB
    private fun init(inflater: LayoutInflater, container: ViewGroup) {
        binding = DataBindingUtil.inflate(inflater, layoutRes, container, false)
    }

    open fun onInject() {}

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        binding.lifecycleOwner = this.viewLifecycleOwner

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        init(inflater, container!!)
        super.onCreateView(inflater, container, savedInstanceState)
        return binding.root
    }

    protected fun setTitle(title: String) {
        backButtonHandler.setTitle(title)
    }

    protected fun showBackButton() {
        backButtonHandler.setBackButtonEnabled(true)
    }

    protected fun hideBackButton() {
        backButtonHandler.setBackButtonEnabled(false)
        //(activity as MainActivity).setTitle(getString(R.string.app_name))
    }

    private lateinit var backButtonHandler: BackButtonHandler

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if (context is BackButtonHandler) {
            backButtonHandler = context
        } else {
            throw RuntimeException(context!!.toString() + " must implement FragmentEvent")
        }
    }

}

interface BackButtonHandler {
    fun setBackButtonEnabled(enabled: Boolean)

    fun setTitle(title: String)
}