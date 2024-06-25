package com.sebascamayo.notesapp

import android.app.Application
import android.content.Context
import dagger.hilt.android.HiltAndroidApp

//Esta app se refistra en el "manifest"

@HiltAndroidApp
class NoteApp : Application(){
    init {
        instance = this
    }

    companion object {
        private var instance : NoteApp? = null

        fun applicationContext() : Context {
            return instance!!.applicationContext
        }
    }
}