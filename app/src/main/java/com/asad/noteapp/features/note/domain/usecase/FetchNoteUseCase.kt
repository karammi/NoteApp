package com.asad.noteapp.features.note.domain.usecase

import com.asad.noteapp.core.domain.note.model.NoteModel
import com.asad.noteapp.features.note.domain.repository.NoteRepository
import javax.inject.Inject

class FetchNoteUseCase @Inject constructor(
    private val noteRepository: NoteRepository
) {
    suspend operator fun invoke(noteId: Int): NoteModel? = noteRepository.fetchNote(noteId)
}