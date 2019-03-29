package com.android.notenavigation

import com.android.notenavigation.db.Note1

class Response (val status:NoteStatus,val note: Note1?){
    companion object {
        fun delete()=Response(NoteStatus.DELETE,null)
        fun viewNote(note: Note1?)=Response(NoteStatus.VIEW,note)
        fun editNote()=Response(NoteStatus.EDIT,null)
    }

}