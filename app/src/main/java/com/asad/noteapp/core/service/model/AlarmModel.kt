package com.asad.noteapp.core.service.model

data class AlarmModel(
    val alarmTime: Long,
    val id: Int,
    val title: String,
    val note: String?
)
