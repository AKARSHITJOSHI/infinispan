package org.infinispan.server.core.transport.netty

import org.infinispan.server.core.transport.ExceptionEvent
import org.jboss.netty.channel.{ExceptionEvent => NettyExceptionEvent}

/**
 * // TODO: Document this
 * @author Galder Zamarreño
 * @since 4.1
 */
class ExceptionEventAdapter(event: NettyExceptionEvent) extends ExceptionEvent {

   override def getCause: Throwable = return event.getCause

}