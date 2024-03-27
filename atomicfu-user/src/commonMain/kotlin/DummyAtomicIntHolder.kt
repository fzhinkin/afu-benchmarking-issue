package org.example

import kotlinx.atomicfu.AtomicInt
import kotlinx.atomicfu.atomic

public class DummyAtomicIntHolder {
    private var fld: Int = 42

    val value: Int
        get() = fld

    fun incAndGet(): Int = ++fld

    fun decAndGet(): Int = --fld

    fun getAndInc(): Int = fld++

    fun getAndDec(): Int = fld--

    fun getAndSet(value: Int): Int {
        val old = fld
        fld = value
        return old
    }

    fun getAndAdd(value: Int): Int {
        val old = fld
        fld += value
        return fld
    }

    fun addAndGet(value: Int): Int {
        fld += value
        return fld
    }

    fun cas(oldValue: Int, newValue: Int): Boolean {
        if (oldValue == fld) {
            fld = newValue
            return true
        } else {
            return false
        }
    }
}
