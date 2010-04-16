package org.infinispan.server.memcached

import java.util.concurrent.TimeUnit
import java.lang.reflect.Method
import org.testng.annotations.Test
import org.testng.Assert._

/**
 * // TODO: Document this
 * @author Galder Zamarreño
 * @since // TODO
 */
@Test(groups = Array("functional"), testName = "server.memcached.MemcachedShutdownTest")
class MemcachedShutdownTest extends MemcachedSingleNodeTest {

   override protected def shutdownClient {}

   def testAny(m: Method) {
      val f = client.set(k(m), 0, v(m))
      assertTrue(f.get(timeout, TimeUnit.SECONDS).booleanValue)
      assertEquals(client.get(k(m)), v(m))
   }

}