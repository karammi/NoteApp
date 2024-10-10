package com.asad.noteapp

import android.util.Log
import androidx.activity.compose.setContent
import androidx.compose.ui.test.assertAny
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onAllNodesWithTag
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.runner.permission.UiAutomationPermissionGranter
import androidx.work.Configuration
import androidx.work.WorkManager
import com.asad.noteapp.app.MainActivity
import com.asad.noteapp.app.navigation.NoteApp
import com.asad.noteapp.core.di.module.DatabaseModule
import com.asad.noteapp.theme.util.Emoji
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@UninstallModules(
    value = [DatabaseModule::class],
)
@RunWith(AndroidJUnit4::class)
@HiltAndroidTest
class CreateNoteScenarioTest {
    @get:Rule(order = 0)
    val hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    private val permissionGranter =
        UiAutomationPermissionGranter().addPermissions(android.Manifest.permission.POST_NOTIFICATIONS)

    val context = InstrumentationRegistry.getInstrumentation().targetContext


    @Before
    fun setup() {
        hiltRule.inject()
        if (!WorkManager.isInitialized())
            WorkManager.initialize(
                context, Configuration.Builder()
                    .setMinimumLoggingLevel(Log.DEBUG)
                    .build()
            )
        composeTestRule.activity.setContent {
            NoteApp()
        }
    }

    @Test
    fun scenarioOfCreateNoteAndShowNoteOnHomeScreen() {
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

            onNodeWithTag(testTag = context.getString(R.string.note_bottom_component))
                .assertIsDisplayed()


            onNodeWithTag(testTag = context.getString(R.string.fab_test_tag))
                .assertIsDisplayed()

            onNodeWithTag(testTag = context.getString(R.string.fab_test_tag))
                .performClick()

            waitUntil(2000) {
                onAllNodesWithTag(context.getString(R.string.create_note_test_tag))
                    .assertAny(matcher = hasTestTag(context.getString(R.string.create_note_test_tag)))
                    .fetchSemanticsNodes()
                    .size == 1
            }
        }
    }

    @Test
    fun scenarioOfCreateNoteAndShowNoteOnHomeScreenWithSetReminder() {

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

            onNodeWithTag(testTag = context.getString(R.string.note_bottom_component))
                .assertIsDisplayed()

            onNodeWithContentDescription(label = context.getString(R.string.reminder_content_desc))
                .assertIsDisplayed()

            onNodeWithContentDescription(label = context.getString(R.string.reminder_content_desc))
                .performClick()


            /** TODO
             * steps to run scenario test::
            1. asser node
            2. open sheet
            3. choose an item on sheet
            4. or choose pick a date
            5. check the calendar dialog
            6. assert it display
            7. choose a date from date picker
            8. choose a time form time picker
            9. save that time
            10. save that note
            11. register note with default date
             */


//            1. asser node
//            2. open sheet
            waitUntil(3000) {
                onAllNodesWithTag(context.getString(R.string.later_today_test_tag))
                    .assertAny(matcher = hasTestTag(context.getString(R.string.later_today_test_tag)))
                    .fetchSemanticsNodes()
                    .size == 1
            }

            //assert item is displayed
            onNodeWithTag(context.getString(R.string.later_today_test_tag))
                .assertIsDisplayed()

            onNodeWithTag(context.getString(R.string.later_today_test_tag))
                .performClick()


            /** TODO
             * steps to run scenario test::
            3. choose an item on sheet
            9. save that time
            10. save that note
            11. register note with default date
             */

            ///dialog
//            waitUntil(3000) {
//                onAllNodesWithTag(context.getString(R.string.calendar_dialog_test_tag))
//                    .assertAny(matcher = hasTestTag(context.getString(R.string.calendar_dialog_test_tag)))
//                    .fetchSemanticsNodes()
//                    .size == 1
//            }


            onNodeWithTag(testTag = context.getString(R.string.fab_test_tag))
                .assertIsDisplayed()

            onNodeWithTag(testTag = context.getString(R.string.fab_test_tag))
                .performClick()

            waitUntil(2000) {
                onAllNodesWithTag(context.getString(R.string.create_note_test_tag))
                    .assertAny(matcher = hasTestTag(context.getString(R.string.create_note_test_tag)))
                    .fetchSemanticsNodes()
                    .size == 1
            }
        }
    }
}

