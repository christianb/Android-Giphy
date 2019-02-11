package com.bunk.common

import io.reactivex.Scheduler

interface ObserveOnScheduler {
    val androidMainThreadScheduler: Scheduler
}