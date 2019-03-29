package com.android.notenavigation
import com.android.notenavigation.di.AppComponent
import com.android.notenavigation.di.DaggerAppComponent
import dagger.android.HasActivityInjector
import dagger.android.support.DaggerApplication
class NoteApp: DaggerApplication(),HasActivityInjector {
    override fun applicationInjector(): AppComponent {
        return DaggerAppComponent.builder().noteApp(this).build()
    }

    override fun onCreate() {
        super.onCreate()
        applicationInjector().inject(this)
    }
}