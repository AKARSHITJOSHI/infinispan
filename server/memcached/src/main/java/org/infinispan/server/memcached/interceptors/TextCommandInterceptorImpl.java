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
package org.infinispan.server.memcached.interceptors;

import org.infinispan.server.core.transport.ChannelHandlerContext;
import org.infinispan.server.memcached.CommandInterceptor;
import org.infinispan.server.memcached.commands.TextCommand;

/**
 * TextCommandInterceptorImpl.
 * 
 * @author Galder Zamarreño
 * @since 4.1
 */
public class TextCommandInterceptorImpl extends AbstractVisitor implements TextCommandInterceptor {
   private final TextCommandInterceptor next;

   TextCommandInterceptorImpl(TextCommandInterceptor next) {
      this.next = next;
   }

   @Override
   public Object invokeNextInterceptor(ChannelHandlerContext ctx, TextCommand command) throws Throwable {
      return command.acceptVisitor(ctx, next);
   }

   @Override
   public CommandInterceptor getNext() {
      return next;
   }

   @Override
   protected Object handleDefault(ChannelHandlerContext ctx, TextCommand command) throws Throwable {
      return invokeNextInterceptor(ctx, command);
   }

}
