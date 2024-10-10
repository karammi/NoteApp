package com.asad.noteapp.features.home.data.dataSource.local

import com.asad.noteapp.core.data.dataSource.note.localDataSource.entity.NoteEntity
import com.asad.noteapp.features.home.data.dataSource.local.dao.HomeDao
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner


@RunWith(MockitoJUnitRunner::class) // Or your preferred runner
class HomeLocalDataSourceImplTest {

    @Mock
    private lateinit var homeDao: HomeDao

    @InjectMocks
    private lateinit var homeLocalDataSource: HomeLocalDataSourceImpl

    @Test
    fun fetchNotes_shouldReturnListOfNotes() = runTest {
        // Arrange
        val expectedNotes = listOf(
            NoteEntity(1, "Title 1", "Content 1"),
            NoteEntity(2, "Title 2", "Content 2")
        )
        val flow = flowOf(expectedNotes)
        `when`(homeDao.fetchNotes()).thenReturn(flow)

        // Act
        val actualNotes = homeLocalDataSource.fetchNotes().first()

        // Assert
        assertThat(actualNotes).isEqualTo(expectedNotes)
    }
}
/*@RunWith(JUnit4::class)
class HomeLocalDataSourceTest {

    @Mock
    private lateinit var fakeHomeNoteDao: FakeHomeNoteDao
    private lateinit var sut: HomeLocalDataSourceImpl

    @Before
    fun setup() {
        fakeHomeNoteDao = FakeHomeNoteDao()
        sut = HomeLocalDataSourceImpl(homeDao = fakeHomeNoteDao)
    }

    @Test
    fun fetch_note_should_return_list_of_notes() = runTest {
        // Arrange


        // Act

        // Assert
        assertThat("a").isEqualTo("a")
    }
}*/

class FakeHomeNoteDao : HomeDao {
    override fun fetchNotes(): Flow<List<NoteEntity>> {
        return flowOf(
            listOf(
                NoteEntity(1, "Title", "Body"),
                NoteEntity(2, "Title2", "Body2"),
                NoteEntity(3, "Title3", "Body3"),
                NoteEntity(4, "Title4", "Body4"),
                NoteEntity(5, "Title5", "Body5"),
            )
        )
    }
}