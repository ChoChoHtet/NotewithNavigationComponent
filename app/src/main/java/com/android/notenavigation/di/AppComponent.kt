package com.android.notenavigation.di
import android.app.Application
import com.android.notenavigation.NoteApp
import com.android.notenavigation.di.module.*
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import dagger.android.support.DaggerApplication
import javax.inject.Singleton

/**
 * @Component -> connection between @Module and the class which needs dependency
 * @Component.Builder -> will be called from custom application class
 */
@Singleton
@Component(modules = [
    ActivityModule::class,
    FragmentModule::class,
    AppModule::class,
    DaggerViewModelModule::class,
    ViewModelModule::class,
    DbModule::class,
    AndroidSupportInjectionModule::class])
interface AppComponent : AndroidInjector<DaggerApplication>{
    //custom application class
    fun inject(noteApp: NoteApp)

   @Component.Builder
    interface Builder{
        fun build():AppComponent

        @BindsInstance
        fun noteApp(application: Application):Builder
    }


}