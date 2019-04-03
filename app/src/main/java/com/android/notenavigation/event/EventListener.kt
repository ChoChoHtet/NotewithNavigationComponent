package com.android.notenavigation.event

interface EventListener<T> {
    fun onEvent(t :T?)
}