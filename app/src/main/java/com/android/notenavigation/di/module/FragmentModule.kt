package com.android.notenavigation.di.module

import com.android.notenavigation.view.fragment.AddNoteFragment
import com.android.notenavigation.view.fragment.EditNoteFragment
import com.android.notenavigation.view.fragment.NoteDetailFragment
import com.android.notenavigation.view.fragment.NoteListFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface FragmentModule {
    @ContributesAndroidInjector
    fun noteListFragment():NoteListFragment

    @ContributesAndroidInjector
    fun addNoteFragment():AddNoteFragment

    @ContributesAndroidInjector
    fun noteDetailFragment():NoteDetailFragment

    @ContributesAndroidInjector
    fun editNoteFragment():EditNoteFragment

}