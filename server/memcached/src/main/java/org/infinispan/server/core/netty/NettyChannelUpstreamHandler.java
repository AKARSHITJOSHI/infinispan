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

import org.infinispan.CacheException;
import org.infinispan.server.core.CommandHandler;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.ChannelPipelineCoverage;
import org.jboss.netty.channel.ChannelStateEvent;
import org.jboss.netty.channel.MessageEvent;
import org.jboss.netty.channel.SimpleChannelUpstreamHandler;
import org.jboss.netty.channel.group.ChannelGroup;

/**
 * NettyUpstreamHandler.
 * 
 * @author Galder Zamarreño
 * @since 4.0
 */
@ChannelPipelineCoverage("one")
public class NettyChannelUpstreamHandler extends SimpleChannelUpstreamHandler {
   final CommandHandler handler;
   final ChannelGroup group;

   public NettyChannelUpstreamHandler(CommandHandler handler, ChannelGroup group) {
      this.handler = handler;
      this.group = group;
   }

   @Override
   public void channelOpen(ChannelHandlerContext ctx, ChannelStateEvent e) throws Exception {
      group.add(e.getChannel());
      super.channelOpen(ctx, e);
   }

   @Override
   public void messageReceived(ChannelHandlerContext ctx, MessageEvent e) throws Exception {
      try {
         handler.messageReceived(new NettyChannelHandlerContext(ctx), new NettyMessageEvent(e));
      } catch (Exception ee) {
         throw ee;
      } catch (Throwable t) {
         throw new CacheException(t);
      }
   }



}
