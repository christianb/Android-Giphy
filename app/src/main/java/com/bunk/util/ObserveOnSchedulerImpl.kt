package com.bunk.util

import com.bunk.domain.util.ObserveOnScheduler
import io.reactivex.android.schedulers.AndroidSchedulers

class ObserveOnSchedulerImpl(): ObserveOnScheduler {
    override val androidMainThreadScheduler = AndroidSchedulers.mainThread()
}