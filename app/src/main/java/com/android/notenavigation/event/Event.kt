package com.android.notenavigation.event

open class Event<out T>(private val content:T) {
   /* var hasBeenHandled=false
    private set

    fun getContentIfNotHandled():T?{
        return if (hasBeenHandled){
            null
        }else{
            hasBeenHandled=true
            content
        }
    }
    fun peekContent():T=content*/
    private var consumed=false

    fun consume():T?{
        return if (consumed){
            null
        }else{
            consumed=true
            content
        }
    }
    fun peekContent():T=content

    @Override
    override fun equals(obj: Any?): Boolean {
        if (this === obj) return true
        if (javaClass != obj!!.javaClass) return false
        val other = obj as Event<*>?
        return if (content !== other!!.content) false else consumed === other!!.consumed
    }

    override fun hashCode(): Int {
        var hash = content.hashCode()
        hash = 31 * hash * consumed.hashCode()
        return hash
    }
}