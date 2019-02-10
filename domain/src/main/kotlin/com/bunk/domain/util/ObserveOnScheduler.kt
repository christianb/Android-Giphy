package com.bunk.domain.util

import io.reactivex.Scheduler

interface ObserveOnScheduler {
    val androidMainThreadScheduler: Scheduler
}