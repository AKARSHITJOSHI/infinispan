/*
 * JBoss, Home of Professional Open Source.
 * Copyright 2009, Red Hat, Inc. and/or its affiliates, and
 * individual contributors as indicated by the @author tags. See the
 * copyright.txt file in the distribution for a full listing of
 * individual contributors.
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation; either version 2.1 of
 * the License, or (at your option) any later version.
 *
 * This software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this software; if not, write to the Free
 * Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
 * 02110-1301 USA, or see the FSF site: http://www.fsf.org.
 */
package org.infinispan.server.core.netty;

import static org.jboss.netty.channel.Channels.pipeline;

import org.jboss.netty.channel.ChannelHandler;
import org.jboss.netty.channel.ChannelPipeline;
import org.jboss.netty.channel.ChannelPipelineFactory;

/**
 * NettyChannelPipelineFactory.
 * 
 * @author Galder Zamarreño
 * @since 4.0
 */
public class NettyChannelPipelineFactory implements ChannelPipelineFactory {
   private final ChannelHandler decoder;
   private final ChannelHandler handler;

   public NettyChannelPipelineFactory(ChannelHandler decoder, ChannelHandler handler) {
      this.decoder = decoder;
      this.handler = handler;
   }

   @Override
   public ChannelPipeline getPipeline() throws Exception {
      // Create a default pipeline implementation.
      ChannelPipeline pipeline = pipeline();
      pipeline.addLast("decoder", decoder);
      pipeline.addLast("handler", handler);
      return pipeline;
   }

}
