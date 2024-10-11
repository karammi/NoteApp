package com.asad.noteapp.features.note.data.reminderManager

interface ReminderLauncher {
    fun setReminder(noteId: Int, noteTitle: String, noteBody: String?)
}
