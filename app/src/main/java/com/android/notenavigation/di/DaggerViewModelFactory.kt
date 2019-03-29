package com.android.notenavigation.di

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import java.lang.Exception
import java.lang.IllegalArgumentException
import javax.inject.Inject
import javax.inject.Provider
import javax.inject.Singleton

/**
 * Step 3
 * view model factory class -> dynamically create view model for activity or fragment
 * just inject viewModelFactory in activity or fragment and then generate your view model class
 */
@Suppress("UNCHECKED_CAST")
@Singleton
class DaggerViewModelFactory
@Inject constructor(private val viewModels:MutableMap<Class<out ViewModel>,@JvmSuppressWildcards Provider<ViewModel>>):ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        val creator=viewModels[modelClass]?:viewModels.asIterable().firstOrNull{modelClass.isAssignableFrom(it.key)}?.value
        ?:throw IllegalArgumentException("Unknown model class $modelClass")
        return try {
            creator.get()as T
        }catch (e:Exception){
            throw RuntimeException(e)
        }
    }
}