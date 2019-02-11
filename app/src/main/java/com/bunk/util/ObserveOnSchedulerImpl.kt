package com.bunk.util

import com.bunk.common.ObserveOnScheduler
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers

class ObserveOnSchedulerImpl : ObserveOnScheduler {
    override val androidMainThreadScheduler: Scheduler = AndroidSchedulers.mainThread()
}