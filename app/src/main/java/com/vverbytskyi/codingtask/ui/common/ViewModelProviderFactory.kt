package com.vverbytskyi.codingtask.ui.common

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class ViewModelProviderFactory<VM : ViewModel> (private val viewModel: VM) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(viewModel.javaClass)) {
            return viewModel as T
        }
        throw IllegalArgumentException("Unknown class name")
    }
}