package com.android.notenavigation

class Response (val status:NoteStatus,val note: Note?){
    companion object {
        fun delete()=Response(NoteStatus.DELETE,null)
        fun viewNote(note: Note?)=Response(NoteStatus.VIEW,note)
        fun editNote()=Response(NoteStatus.EDIT,null)
    }

}