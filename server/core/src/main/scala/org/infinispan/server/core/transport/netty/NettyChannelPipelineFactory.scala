package org.infinispan.server.core.transport.netty

import org.jboss.netty.channel._

/**
 * // TODO: Document this
 * @author Galder Zamarreño
 * @since 4.1
 */
class NettyChannelPipelineFactory(decoder: ChannelUpstreamHandler, encoder: ChannelDownstreamHandler)
      extends ChannelPipelineFactory {

   override def getPipeline: ChannelPipeline = {
      val pipeline = Channels.pipeline
      if (decoder != null) {
         pipeline.addLast("decoder", decoder);
      }
      if (encoder != null) {
         pipeline.addLast("encoder", encoder);
      }
      return pipeline;
   }

}