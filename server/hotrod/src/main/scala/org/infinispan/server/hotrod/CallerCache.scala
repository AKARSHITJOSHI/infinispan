package org.infinispan.server.hotrod

import org.infinispan.manager.CacheManager
import org.infinispan.{Cache => InfinispanCache}

/**
 * // TODO: Document this
 * @author Galder Zamarreño
 * @since 4.0
 */

class CallerCache(val manager: CacheManager) extends Cache {

   override def put(c: StorageCommand): Response = {
      val cache = getCache(c.cacheName)
      cache.put(new Key(c.key), new Value(c.value))
      new Response(OpCodes.PutResponse, c.id, Status.Success)
   }

   override def get(c: RetrievalCommand): Response = {
      val cache = getCache(c.cacheName)
      val value = cache.get(new Key(c.key))
      if (value != null)
         new RetrievalResponse(OpCodes.GetResponse, c.id, Status.Success, value.v)
      else
         new RetrievalResponse(OpCodes.GetResponse, c.id, Status.KeyDoesNotExist, null)
   }

   private def getCache(cacheName: String): InfinispanCache[Key, Value] = {
      // TODO: Detect __default cache and call simply getCache()
      manager.getCache(cacheName)
   }
}