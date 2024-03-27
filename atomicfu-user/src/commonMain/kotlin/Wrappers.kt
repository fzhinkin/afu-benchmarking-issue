package org.example

import kotlinx.atomicfu.atomic

public class LongWrapper(value: Long) {
    private val v = atomic(value)
}

public class IntWrapper(value: Int) {
    private val v = atomic(value)
}
