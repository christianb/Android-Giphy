package com.bunk.domain.util

import io.reactivex.Scheduler

interface SubscribeOnScheduler {
    val io: Scheduler
    val computation: Scheduler
    val newThread: Scheduler
    val single: Scheduler
    val trampoline: Scheduler
}