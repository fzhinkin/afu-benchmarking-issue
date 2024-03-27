package org.example

import kotlin.test.Test
import kotlin.test.assertEquals

class PltQueueSmokeTest {
    @Test
    fun testQueue() {
        val q = PLTQueue<Int>(32)
        q.put(42)
        assertEquals(42, q.take())
        q.put(1)
        q.put(2)
        q.put(3)
        assertEquals(1, q.take())
        assertEquals(2, q.take())
        assertEquals(3, q.take())
    }
}
