package com.asad.noteapp

import android.content.Context
import androidx.activity.compose.setContent
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.test.assertAny
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.hasContentDescription
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onAllNodesWithContentDescription
import androidx.compose.ui.test.onAllNodesWithTag
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.asad.noteapp.app.MainActivity
import com.asad.noteapp.app.navigation.NoteApp
import com.asad.noteapp.core.di.module.DatabaseModule
import com.asad.noteapp.theme.util.Emoji
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

    val context = InstrumentationRegistry.getInstrumentation().targetContext

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
            onNodeWithContentDescription(context.getString(R.string.add_note)).assertExists()

            onNodeWithText(
                context.getString(
                    R.string.create_note_app,
                    Emoji.eye
                )
            ).assertIsDisplayed()

            onNodeWithText(
                context.getString(
                    R.string.create_note_app,
                    Emoji.eye
                )
            ).performClick()


            waitUntil {
                onAllNodesWithTag(context.getString(R.string.title_label))
                    .fetchSemanticsNodes()
                    .size == 1
            }

            waitUntil(2000) {
                onAllNodesWithTag(context.getString(R.string.title_label))
                    .fetchSemanticsNodes()
                    .size == 1
            }

            onNodeWithText(text = context.getString(R.string.title_label))
                .assertIsDisplayed()

            onNodeWithText(text = context.getString(R.string.title_label))
                .performClick()

            onNodeWithText(text = context.getString(R.string.title_label))
                .performTextInput(context.getString(R.string.note_title_test_example))

            waitUntil(2000) {
                onAllNodesWithTag(context.getString(R.string.title_label))
                    .assertAny(matcher = hasText(context.getString(R.string.note_title_test_example)))
                    .fetchSemanticsNodes()
                    .size == 1
            }

            onNodeWithTag(testTag = context.getString(R.string.note_test_tag))
                .assertIsDisplayed()

            onNodeWithText(text = context.getString(R.string.note_title))
                .performClick()

            onNodeWithTag(testTag = context.getString(R.string.note_test_tag))
                .performTextInput(context.getString(R.string.this_is_note_body_test_example))

            waitUntil(2000) {
                onAllNodesWithTag(context.getString(R.string.note_test_tag))
                    .assertAny(matcher = hasText(context.getString(R.string.this_is_note_body_test_example)))
                    .fetchSemanticsNodes()
                    .size == 1
            }
        }
    }

}