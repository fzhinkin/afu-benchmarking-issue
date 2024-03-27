package org.example

import kotlinx.benchmark.*

@State(Scope.Benchmark)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(BenchmarkTimeUnit.NANOSECONDS)
@Warmup(time = 1)
@Measurement(time = 1)
open class QBench {
    val q = PLTQueue<Int>(1024)
    val ql = PLTQueueL<Int>(1024)
    var idx: Int = 0

    @Setup
    fun preFillQ() {
        for (i in 0 until 100) {
            q.put(i)
            ql.put(i)
        }
    }

    @Benchmark
    fun qWithIntCursors(blackhole: Blackhole) {
        blackhole.consume(q.take())
        q.put(idx++)
    }

    @Benchmark
    fun qWithLongCursors(blackhole: Blackhole) {
        blackhole.consume(ql.take())
        ql.put(idx++)
    }
}
