package org.example

import kotlinx.benchmark.*
import kotlin.random.Random

@State(Scope.Benchmark)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(BenchmarkTimeUnit.NANOSECONDS)
@Warmup(time = 1)
@Measurement(time = 1)
open class AtomicLongBenchmark {
    private val holder = AtomicLongHolder()

    var delta = 42L
    var newValue: Long = Random.nextLong()
    var idx = 1L

    @Benchmark
    fun getValue(blackhole: Blackhole) {
        blackhole.consume(holder.value)
    }

    @Benchmark
    fun incAndGet(blackhole: Blackhole) {
        blackhole.consume(holder.incAndGet())
    }

    @Benchmark
    fun decAndGet(blackhole: Blackhole) {
        blackhole.consume(holder.decAndGet())
    }

    @Benchmark
    fun getAndInc(blackhole: Blackhole) {
        blackhole.consume(holder.getAndInc())
    }

    @Benchmark
    fun getAndDec(blackhole: Blackhole) {
        blackhole.consume(holder.getAndDec())
    }

    @Benchmark
    fun getAndSet(blackhole: Blackhole) {
        blackhole.consume(holder.getAndSet(delta))
    }

    @Benchmark
    fun getAndAdd(blackhole: Blackhole) {
        blackhole.consume(holder.getAndAdd(delta))
    }

    @Benchmark
    fun addAndGet(blackhole: Blackhole) {
        blackhole.consume(holder.addAndGet(delta))
    }

    @Benchmark
    fun casSuccess(blackhole: Blackhole) {
        val oldValue = holder.value
        blackhole.consume(holder.cas(oldValue, newValue))
        newValue = oldValue
    }

    @Benchmark
    fun casFail(blackhole: Blackhole) {
        blackhole.consume(holder.cas(newValue, newValue))
    }

    @Benchmark
    fun casUnstable(blackhole: Blackhole) {
        val oldValue = holder.value
        val nextValue = idx++
        idx = (idx and Long.MAX_VALUE)
        // cas will fail for all (expect 0) oldValues when idx is even
        // and will succeed when idx is odd
        val mask = nextValue.shl(63).shr(63)
        blackhole.consume(holder.cas(oldValue.and(mask), nextValue))
    }
}
