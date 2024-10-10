package com.asad.noteapp

import androidx.activity.compose.setContent
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.asad.noteapp.app.MainActivity
import com.asad.noteapp.app.navigation.NoteApp
import com.asad.noteapp.core.di.module.DatabaseModule
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import okhttp3.OkHttpClient
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import javax.inject.Inject

@UninstallModules(
    value = [DatabaseModule::class],
)
@RunWith(AndroidJUnit4::class)
@HiltAndroidTest
class BreedListFavoriteScenarioTest {
    @get:Rule(order = 0)
    val hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @Before
    fun setup() {
        hiltRule.inject()
        composeTestRule.activity.setContent {
            NoteApp()
        }
    }

    @Test
    fun runApp() {
        composeTestRule.apply {
            onNodeWithContentDescription("add").assertExists()

            onNodeWithText("Create New Note").assertIsDisplayed()

            onNodeWithText("Create New Note")
                .performClick()
        }
    }

}