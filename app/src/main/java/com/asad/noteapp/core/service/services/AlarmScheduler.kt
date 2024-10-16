package com.asad.noteapp.core.service.services

import com.asad.noteapp.core.service.model.AlarmModel

interface AlarmScheduler {
    fun schedule(alarmModel: AlarmModel)
    fun cancel(alarmModel: AlarmModel)
}
