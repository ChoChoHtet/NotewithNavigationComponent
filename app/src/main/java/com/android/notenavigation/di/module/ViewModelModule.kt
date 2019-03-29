package com.android.notenavigation.di.module

import android.arch.lifecycle.ViewModel
import com.android.notenavigation.di.ViewModelKey
import com.android.notenavigation.viewmodel.AddNoteViewModel
import com.android.notenavigation.viewmodel.EditNoteViewModel
import com.android.notenavigation.viewmodel.NoteDetailViewModel
import com.android.notenavigation.viewmodel.NoteListViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

/**
 * Step 4
 * inject this object into a Map using @IntoMap with ViewModel::class as a key
 * and then Provider that will build a NoteListViewModel (example...) object
 */
@Module
abstract class ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(NoteListViewModel::class)
    abstract fun bindNoteListViewModel(noteListViewModel: NoteListViewModel):ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(NoteDetailViewModel::class)
    abstract fun bindNoteDetailViewModel(noteDetailViewModel: NoteDetailViewModel):ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(EditNoteViewModel::class)
    abstract fun binEditNoteViewModel(editNoteViewModel: EditNoteViewModel):ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(AddNoteViewModel::class)
    abstract fun bindAddNoteViewModel(addNoteViewModel: AddNoteViewModel):ViewModel


}