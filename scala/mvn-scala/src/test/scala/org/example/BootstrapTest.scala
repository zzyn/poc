package org.example

import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.{Tag, Test}

@Test
class BootstrapTest {

    @Test
    @Tag("example")
    def testOK() = assertTrue(true)

//    @Test
//    def testKO() = assertTrue(false)

}


