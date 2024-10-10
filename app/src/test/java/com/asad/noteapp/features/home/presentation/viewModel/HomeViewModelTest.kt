package com.asad.noteapp.features.home.presentation.viewModel

import app.cash.turbine.test
import com.asad.noteapp.core.data.dataSource.calendar.repository.CalendarRepositoryImpl
import com.asad.noteapp.core.domain.note.model.NoteModel
import com.asad.noteapp.core.util.MainDispatcherRule
import com.asad.noteapp.features.home.domain.usecase.FetchNotesUseCase
import com.asad.noteapp.features.home.presentation.model.HomeUiState
import com.asad.noteapp.features.home.presentation.model.Note
import com.google.common.truth.Truth.assertThat
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class HomeViewModelTest {


    private lateinit var sut: HomeViewModel

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private val fetchNotesUseCase = mockk<FetchNotesUseCase>()

    private val calendarRepository = mockk<CalendarRepositoryImpl>()

    @Before
    fun setup() {


    }


    @Test
    fun checkShowingIsLoadingState() = runTest {
        //arrange
        val homeUiState = HomeUiState()
        sut = HomeViewModel(
            fetchNotesUseCase = fetchNotesUseCase,
            calendarRepository = calendarRepository
        )

        //Act
        sut.uiState.test {
            val firstEmission = awaitItem()

            //Assert
            assertThat(firstEmission).isEqualTo(homeUiState)

            cancelAndConsumeRemainingEvents()
        }
    }


    @Test
    fun checkShowingList() = runTest(UnconfinedTestDispatcher()) {
        //arrange
        val homeUiState = HomeUiState()

        val mockNotes = listOf<NoteModel>(
            NoteModel(id = 1, title = "title", note = "description"),
            NoteModel(id = 2, title = "title", note = "description"),
            NoteModel(id = 3, title = "title", note = "description")
        )
        val expectedNotes = listOf<Note>(
            Note(id = 1, title = "title", note = "description"),
            Note(id = 2, title = "title", note = "description"),
            Note(id = 3, title = "title", note = "description")
        )

        coEvery { fetchNotesUseCase.invoke() } returns flow { emit(mockNotes) }

        sut = HomeViewModel(
            fetchNotesUseCase = fetchNotesUseCase,
            calendarRepository = calendarRepository
        )

        //Act
        sut.uiState.test {
            advanceUntilIdle()
            val firstEmission = awaitItem()
            advanceUntilIdle()

            //Assert
            assertThat(firstEmission.notes).isEqualTo(expectedNotes)

            cancelAndConsumeRemainingEvents()
        }
    }


}