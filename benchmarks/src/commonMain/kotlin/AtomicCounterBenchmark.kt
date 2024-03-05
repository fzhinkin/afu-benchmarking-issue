package org.example

import kotlinx.benchmark.Benchmark
import kotlinx.benchmark.Scope
import kotlinx.benchmark.State

@State(Scope.Benchmark)
class AtomicCounterBenchmark {
    private val withIrTransformation = AtomicCounterIrTransformationEnabled()
    private val withoutIrTransformation = AtomicCounterIrTransformationDisabled()

    @Benchmark
    fun withIrTransformation(): Long = withIrTransformation.inc()

    @Benchmark
    fun withoutIrTransformation(): Long = withoutIrTransformation.inc()
}
