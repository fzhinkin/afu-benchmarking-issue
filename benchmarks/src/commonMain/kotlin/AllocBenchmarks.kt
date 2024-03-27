package org.example

import kotlinx.benchmark.*

@State(Scope.Benchmark)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(BenchmarkTimeUnit.NANOSECONDS)
@Warmup(time = 1)
@Measurement(time = 1)
open class AllocBenchmarks {
    var ctr = 0

    @Benchmark
    fun allocInt(blackhole: Blackhole) {
        blackhole.consume(IntWrapper(ctr++))
    }

    @Benchmark
    fun allocLong(blackhole: Blackhole) {
        blackhole.consume(LongWrapper((ctr++).toLong()))
    }
}
