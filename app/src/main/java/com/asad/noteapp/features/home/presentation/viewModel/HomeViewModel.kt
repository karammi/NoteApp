package com.asad.noteapp.features.home.presentation.viewModel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.workDataOf
import com.asad.noteapp.core.data.NotificationConstants
import com.asad.noteapp.core.data.ReminderWorker
import com.asad.noteapp.features.home.domain.usecase.FetchNotesUseCase
import com.asad.noteapp.features.note.domain.model.NoteModel
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onEmpty
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.util.concurrent.TimeUnit
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val fetchNotesUseCase: FetchNotesUseCase,
    @ApplicationContext private val context: Context
) : ViewModel() {

    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState = _uiState.asStateFlow()

    init {
        fetchNotes()
    }

    fun fetchNotes() {
        viewModelScope.launch {
            fetchNotesUseCase()
                .onEmpty { }
                .catch { }
                .collect { notes ->
                    updateUiState(notes = notes)
                }
        }
    }

    private fun updateUiState(notes: List<NoteModel>) {
        _uiState.update {
            it.copy(
                isLoading = false,
                notes = notes,
                isError = false,
            )
        }
    }

    fun updateListViewLayout(useGridLayout: Boolean) {
        _uiState.update { currentState -> currentState.copy(isGridLayout = useGridLayout) }
    }

    fun startReminder(noteId: Int, noteTitle: String, noteBody: String?) {
        val data = workDataOf(
            NotificationConstants.ID_KEY to noteId,
            NotificationConstants.TITLE_KEY to noteTitle,
            NotificationConstants.NOTE_KEY to noteBody
        )
        val workRequest = OneTimeWorkRequestBuilder<ReminderWorker>()
            .setInitialDelay(1, TimeUnit.SECONDS)
            .setInputData(data)
            .build()

        WorkManager.getInstance(context).enqueue(workRequest)
    }
}