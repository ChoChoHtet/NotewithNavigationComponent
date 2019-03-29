package com.android.notenavigation.di

import android.arch.lifecycle.ViewModel
import dagger.MapKey
import kotlin.reflect.KClass

/**
 * Step 4
 * view model key -> map your view model class for view model factory can provide or inject correctly
 */
@Target(AnnotationTarget.FUNCTION,AnnotationTarget.PROPERTY_GETTER,AnnotationTarget.PROPERTY_SETTER)
@MapKey
annotation class ViewModelKey (val value:KClass<out ViewModel>)