package com.asad.noteapp.features.note.domain.usecase

import com.asad.noteapp.features.note.domain.model.NoteModel
import javax.inject.Inject

class DeleteNoteUseCase @Inject constructor() {
    suspend operator fun invoke(note: NoteModel) {
    }
}