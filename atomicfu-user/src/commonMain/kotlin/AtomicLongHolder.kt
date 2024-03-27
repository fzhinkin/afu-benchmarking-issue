package org.example

import kotlinx.atomicfu.AtomicLong
import kotlinx.atomicfu.atomic

public class AtomicLongHolder {
    private val fld: AtomicLong = atomic(42L)

    val value: Long
        get() = fld.value

    fun incAndGet(): Long = fld.incrementAndGet()

    fun decAndGet(): Long = fld.decrementAndGet()

    fun getAndInc(): Long = fld.getAndIncrement()

    fun getAndDec(): Long = fld.getAndDecrement()

    fun getAndSet(value: Long): Long = fld.getAndSet(value)

    fun getAndAdd(value: Long): Long = fld.getAndAdd(value)

    fun addAndGet(value: Long): Long = fld.addAndGet(value)

    fun cas(oldValue: Long, newValue: Long): Boolean = fld.compareAndSet(oldValue, newValue)
}
