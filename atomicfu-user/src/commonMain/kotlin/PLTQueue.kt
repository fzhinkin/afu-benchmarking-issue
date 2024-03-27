package org.example

import kotlinx.atomicfu.AtomicRef
import kotlinx.atomicfu.atomic

// MPMC bounded queue based on a blog post by David Dice:
// https://blogs.oracle.com/dave/entry/ptlqueue_a_scalable_bounded_capacity
//
// It's only created to benchmark AFU atomics on JS, so there are no guarantees that
// the implementation works correctly in concurrent environment.
class PLTQueue<T>(capacity: Int) {
    init {
        require(capacity > 1) {
            "Capacity has to be positive integer greater than 1, was: $capacity"
        }
        require(capacity.countOneBits() == 1) {
            "Only power-of-two capacities are supported, was: $capacity"
        }
    }
    private val mask = capacity - 1
    private val slots = Array<Slot<T>>(capacity) { Slot(it) }
    private val putCursor = atomic(0)
    private val takeCursor = atomic(0)

    fun put(value: T) {
        val ticket = putCursor.getAndIncrement()
        val slot = slots[ticket and mask]
        while (slot.turn.value != ticket) {
            // burn the cpu!!!!11
        }
        slot.payload.value = value
    }

    fun take(): T {
        val ticket = takeCursor.getAndIncrement()
        val slot = slots[ticket and mask]
        while (slot.turn.value != ticket) {
            // burn the cpu!!!!11
        }
        while (true) {
            val retval = slot.payload.value
            if (retval != null) {
                slot.payload.value = null
                slot.turn.value = ticket + mask + 1
                return retval
            }
        }
    }
}

private class Slot<T>(initial: Int) {
    val turn = atomic(initial)
    val payload: AtomicRef<T?> = atomic(null)
}

class PLTQueueL<T>(capacity: Int) {
    init {
        require(capacity > 1) {
            "Capacity has to be positive integer greater than 1, was: $capacity"
        }
        require(capacity.countOneBits() == 1) {
            "Only power-of-two capacities are supported, was: $capacity"
        }
    }
    private val mask = capacity.toLong() - 1L
    private val slots = Array<SlotL<T>>(capacity) { SlotL(it.toLong()) }
    private val putCursor = atomic(0L)
    private val takeCursor = atomic(0L)

    fun put(value: T) {
        val ticket = putCursor.getAndIncrement()
        val slot = slots[(ticket and mask).toInt()]
        while (slot.turn.value != ticket) {
            // burn the cpu!!!!11
        }
        slot.payload.value = value
    }

    fun take(): T {
        val ticket = takeCursor.getAndIncrement()
        val slot = slots[(ticket and mask).toInt()]
        while (slot.turn.value != ticket) {
            // burn the cpu!!!!11
        }
        while (true) {
            val retval = slot.payload.value
            if (retval != null) {
                slot.payload.value = null
                slot.turn.value = ticket + mask + 1
                return retval
            }
        }
    }
}

private class SlotL<T>(initial: Long) {
    val turn = atomic(initial)
    val payload: AtomicRef<T?> = atomic(null)
}
