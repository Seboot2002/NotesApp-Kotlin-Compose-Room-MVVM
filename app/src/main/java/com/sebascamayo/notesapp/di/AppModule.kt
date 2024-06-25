package com.sebascamayo.notesapp.di

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.sebascamayo.notesapp.features.feature_notes.data.datasource.local.NoteDatabase
import com.sebascamayo.notesapp.features.feature_notes.data.repository.NotesRepositoryImpl
import com.sebascamayo.notesapp.features.feature_notes.domain.repository.NotesRepository
import com.sebascamayo.notesapp.features.feature_notes.domain.use_case.AddNote
import com.sebascamayo.notesapp.features.feature_notes.domain.use_case.DeleteNote
import com.sebascamayo.notesapp.features.feature_notes.domain.use_case.GetNote
import com.sebascamayo.notesapp.features.feature_notes.domain.use_case.GetNotes
import com.sebascamayo.notesapp.features.feature_notes.domain.use_case.NoteUseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

//La inyeccion de dependencia se usa para usar database, repositorio, uses_cases en cualquier parte del codigo

// La inyeccion de dependecia nos evita el tener que mandar datos de manera secuencial y nos brinda todo desde un solo lugar
// Esto se logra mediante una interfaz y una implementacion, ya que mediante el uso de la interfaz le inyectamos los parametros al iniar la implementacion
// Ahora al entrar en una interfaz nos redirige directamente a a la implementacion con los parametros agregados en el singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideNoteDatabase(app: Application): NoteDatabase {
        return Room.databaseBuilder(
            app,
            NoteDatabase::class.java,
            NoteDatabase.DATABASE_NAME
        ).build()
    }


    @Provides
    @Singleton
    fun provideNoteRepository(db: NoteDatabase): NotesRepository{

        return NotesRepositoryImpl(db.noteDao)
    }

    @Provides
    @Singleton
    fun provideNoteUseCases(repository: NotesRepository): NoteUseCases {

        return NoteUseCases(
            getNotes = GetNotes(repository),
            deleteNote = DeleteNote(repository),
            addNote = AddNote(repository),
            getNote = GetNote(repository)
        )
    }

}