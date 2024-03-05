package org.example

import kotlinx.atomicfu.atomic

class AtomicCounterIrTransformationDisabled {
    private val counter = atomic(0L)

    val value: Long = counter.value

    fun inc(): Long = counter.incrementAndGet()

    fun cas(old: Long, new: Long): Boolean = counter.compareAndSet(old, new)
}
