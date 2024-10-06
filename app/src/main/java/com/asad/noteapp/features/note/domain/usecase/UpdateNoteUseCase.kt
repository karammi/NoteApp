package com.asad.noteapp.features.note.domain.usecase

import com.asad.noteapp.features.note.domain.model.NoteModel
import com.asad.noteapp.features.note.domain.repository.NoteRepository
import javax.inject.Inject

class UpdateNoteUseCase @Inject constructor(
    private val noteRepository: NoteRepository
) {

    suspend operator fun invoke(note: NoteModel) = noteRepository.updateNote(note)

}