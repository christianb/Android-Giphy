package com.bunk.common

import io.reactivex.Scheduler
import io.reactivex.schedulers.Schedulers

class TestSchedulerProvider {
    private val trampoline = Schedulers.trampoline()

    val subscribeOnScheduler = object : SubscribeOnScheduler {
        override val trampoline: Scheduler = this@TestSchedulerProvider.trampoline
        override val single: Scheduler = trampoline
        override val newThread: Scheduler = trampoline
        override val computation: Scheduler = trampoline
        override val io: Scheduler = trampoline
    }

    val observeOnScheduler = object : ObserveOnScheduler {
        override val androidMainThreadScheduler: Scheduler = trampoline
    }
}