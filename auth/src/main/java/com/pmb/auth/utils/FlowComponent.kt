package com.pmb.auth.utils

import android.util.Log
import com.pmb.auth.presentaion.first_login_confirm.viewModel.TimerEvent
import com.pmb.auth.presentaion.first_login_confirm.viewModel.TimerTypeId
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.takeWhile

fun Flow<Pair<TimerTypeId, TimerEvent>>.startCountDown(
    timerId: TimerTypeId,
    initialTime: Long
): Flow<Pair<TimerTypeId, Long>> = flow {
    var remainingTime = initialTime
    generateSequence((remainingTime / 1000) - 1) { it - 1 }
        .asFlow()
        .onEach {
            delay(1000)
            remainingTime -= 1000
            Log.d("second", remainingTime.toString())
            emit(timerId to remainingTime / 1000)
        }.onStart { emit(timerId to remainingTime / 1000) }
        .takeWhile { it >= 0 }
        .collect()
}