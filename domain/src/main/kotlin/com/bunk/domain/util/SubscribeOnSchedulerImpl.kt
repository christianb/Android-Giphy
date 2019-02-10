package com.bunk.domain.util

import io.reactivex.schedulers.Schedulers

class SubscribeOnSchedulerImpl : SubscribeOnScheduler {
    override val io = Schedulers.io()
    override val computation = Schedulers.computation()
    override val newThread = Schedulers.newThread()
    override val single = Schedulers.single()
    override val trampoline = Schedulers.trampoline()
}