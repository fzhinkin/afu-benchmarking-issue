package org.example

import kotlinx.atomicfu.AtomicInt
import kotlinx.atomicfu.atomic

public class AtomicIntHolder {
    private val fld: AtomicInt = atomic(42)

    val value: Int
        get() = fld.value

    fun incAndGet(): Int = fld.incrementAndGet()

    fun decAndGet(): Int = fld.decrementAndGet()

    fun getAndInc(): Int = fld.getAndIncrement()

    fun getAndDec(): Int = fld.getAndDecrement()

    fun getAndSet(value: Int): Int = fld.getAndSet(value)

    fun getAndAdd(value: Int): Int = fld.getAndAdd(value)

    fun addAndGet(value: Int): Int = fld.addAndGet(value)

    fun cas(oldValue: Int, newValue: Int): Boolean = fld.compareAndSet(oldValue, newValue)
}
