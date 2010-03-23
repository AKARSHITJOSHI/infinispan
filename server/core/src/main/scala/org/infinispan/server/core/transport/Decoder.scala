package org.infinispan.server.core.transport

/**
 * // TODO: Document this
 * @author Galder Zamarreño
 * @since
 */

abstract class Decoder {

   def decode(ctx: ChannelHandlerContext, buffer: ChannelBuffer): AnyRef
   
   def exceptionCaught(ctx: ChannelHandlerContext, e: ExceptionEvent)

   def start

   def stop
   
}