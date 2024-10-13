package com.asad.noteapp.features.home.domain.usecase

import com.asad.noteapp.core.domain.note.model.NoteModel
import com.asad.noteapp.features.home.domain.repository.HomeRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SearchNoteUseCase @Inject constructor(
    private val homeRepository: HomeRepository
) {
    operator fun invoke(): Flow<List<NoteModel>> {
        return homeRepository.fetchNotes()
    }
}