package com.asad.noteapp.core.data.dataSource.note.localDataSource.util

object NoteConstant {

    const val TABLE_NAME = "tbl_note"
    const val COLUMN_ID = "id"
    const val COLUMN_TITLE = "title"
    const val COLUMN_DESCRIPTION = "desc"
    const val COLUMN_TAG = "tag"
    const val COLUMN_CREATE_DATE = "create_date"
    const val COLUMN_REMINDER = "reminder"
    const val COLUMN_COLOR = "color"
    const val COLUMN_IS_PINNED = "is_pinned"
}

object TagConstant {
    const val TABLE_NAME = "tbl_tag"
    const val COLUMN_ID = "id"
    const val COLUMN_NAME = "name"
}