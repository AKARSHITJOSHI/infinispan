package org.infinispan.server.hotrod

import org.infinispan.server.core.transport.ChannelBuffer

/**
 * // TODO: Document this
 * @author Galder Zamarreño
 * @since 4.0
 */

object VInt {

   def write(out: ChannelBuffer, i: Int) {
      if ((i & ~0x7F) == 0) out.writeByte(i.toByte)
      else {
         out.writeByte(((i & 0x7f) | 0x80).toByte)
         write(out, i >>> 7)
      }
   }

   def read(in: ChannelBuffer): Int = {
      val b = in.readByte
      read(in, b, 7, b & 0x7F)
   }

   private def read(in: ChannelBuffer, b: Byte, shift: Int, i: Int): Int = {
      if ((b & 0x80) == 0) i
      else {
         val bb = in.readByte
         read(in, bb, shift + 7, i | ((bb & 0x7FL) << shift).toInt)
      }
   }
}