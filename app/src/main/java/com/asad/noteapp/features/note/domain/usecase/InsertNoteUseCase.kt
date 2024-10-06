package com.asad.noteapp.features.note.domain.usecase

import com.asad.noteapp.features.note.domain.model.NoteModel
import com.asad.noteapp.features.note.domain.repository.NoteRepository
import javax.inject.Inject

class InsertNoteUseCase @Inject constructor(
    private val noteRepository: NoteRepository
) {
    suspend operator fun invoke(note: NoteModel) = noteRepository.insertNote(note)
}