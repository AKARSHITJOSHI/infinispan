package org.infinispan.server.hotrod

import org.testng.annotations.Test
import java.lang.reflect.Method
import org.jboss.netty.channel.ChannelFuture

/**
 * // TODO: Document this
 * @author Galder Zamarreño
 * @since 4.1
 */
@Test(groups = Array("functional"), testName = "server.hotrod.HotRodShutdownTest")
class HotRodShutdownTest extends HotRodSingleNodeTest {

   override protected def shutdownClient: ChannelFuture = null

   def testPutBasic(m: Method) {
      client.assertPut(m)
   }

}