package com.android.notenavigation.event

import android.arch.lifecycle.Observer

class EventObserver<T>(private val eventListener: EventListener<T>) : Observer<Event<T>> {
    override fun onChanged(event: Event<T>?) {
       val consumedValue=event?.consume()
        eventListener.onEvent(consumedValue)
    }
}
