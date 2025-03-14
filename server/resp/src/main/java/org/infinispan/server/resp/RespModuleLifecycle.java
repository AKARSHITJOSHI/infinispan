package org.infinispan.server.resp;

import org.infinispan.commons.marshall.AdvancedExternalizer;
import org.infinispan.configuration.global.GlobalConfiguration;
import org.infinispan.factories.GlobalComponentRegistry;
import org.infinispan.factories.annotations.InfinispanModule;
import org.infinispan.lifecycle.ModuleLifecycle;
import org.infinispan.marshall.protostream.impl.SerializationContextRegistry;
import org.infinispan.server.iteration.IterationFilter;
import org.infinispan.server.resp.commands.tx.WATCH;
import org.infinispan.server.resp.filter.ComposedFilterConverter;
import org.infinispan.server.resp.filter.EventListenerConverter;
import org.infinispan.server.resp.filter.EventListenerKeysFilter;
import org.infinispan.server.resp.json.JsonArrayAppendFunction;
import org.infinispan.server.resp.json.JsonArrindexFunction;
import org.infinispan.server.resp.json.JsonArrinsertFunction;
import org.infinispan.server.resp.json.JsonArrpopFunction;
import org.infinispan.server.resp.json.JsonArrtrimFunction;
import org.infinispan.server.resp.json.JsonBucket;
import org.infinispan.server.resp.json.JsonClearFunction;
import org.infinispan.server.resp.json.JsonDelFunction;
import org.infinispan.server.resp.json.JsonGetFunction;
import org.infinispan.server.resp.json.JsonLenArrayFunction;
import org.infinispan.server.resp.json.JsonLenObjFunction;
import org.infinispan.server.resp.json.JsonLenStrFunction;
import org.infinispan.server.resp.json.JsonNumIncrOpFunction;
import org.infinispan.server.resp.json.JsonNumMultOpFunction;
import org.infinispan.server.resp.json.JsonMergeFunction;
import org.infinispan.server.resp.json.JsonObjkeysFunction;
import org.infinispan.server.resp.json.JsonSetFunction;
import org.infinispan.server.resp.json.JsonStringAppendFunction;
import org.infinispan.server.resp.json.JsonToggleFunction;
import org.infinispan.server.resp.json.JsonTypeFunction;

import java.util.Map;

import static org.infinispan.server.core.ExternalizerIds.ITERATION_FILTER;

@InfinispanModule(name = "resp", requiredModules = "core")
public class RespModuleLifecycle implements ModuleLifecycle {

   @Override
   public void cacheManagerStarting(GlobalComponentRegistry gcr, GlobalConfiguration globalConfiguration) {
      final Map<Integer, AdvancedExternalizer<?>> externalizerMap = globalConfiguration.serialization()
            .advancedExternalizers();

      externalizerMap.put(EventListenerKeysFilter.EXTERNALIZER.getId(), EventListenerKeysFilter.EXTERNALIZER);
      externalizerMap.put(WATCH.EXTERNALIZER.getId(), WATCH.EXTERNALIZER);
      externalizerMap.put(EventListenerConverter.EXTERNALIZER.getId(), EventListenerConverter.EXTERNALIZER);
      externalizerMap.put(ComposedFilterConverter.EXTERNALIZER.getId(), ComposedFilterConverter.EXTERNALIZER);
      externalizerMap.put(JsonGetFunction.EXTERNALIZER.getId(), JsonGetFunction.EXTERNALIZER);
      externalizerMap.put(JsonSetFunction.EXTERNALIZER.getId(), JsonSetFunction.EXTERNALIZER);
      externalizerMap.put(JsonLenArrayFunction.EXTERNALIZER.getId(), JsonLenArrayFunction.EXTERNALIZER);
      externalizerMap.put(JsonLenObjFunction.EXTERNALIZER.getId(), JsonLenObjFunction.EXTERNALIZER);
      externalizerMap.put(JsonLenStrFunction.EXTERNALIZER.getId(), JsonLenStrFunction.EXTERNALIZER);
      externalizerMap.put(JsonTypeFunction.EXTERNALIZER.getId(), JsonTypeFunction.EXTERNALIZER);
      externalizerMap.put(JsonDelFunction.EXTERNALIZER.getId(), JsonDelFunction.EXTERNALIZER);
      externalizerMap.put(JsonArrayAppendFunction.EXTERNALIZER.getId(), JsonArrayAppendFunction.EXTERNALIZER);
      externalizerMap.put(JsonStringAppendFunction.EXTERNALIZER.getId(), JsonStringAppendFunction.EXTERNALIZER);
      externalizerMap.put(JsonToggleFunction.EXTERNALIZER.getId(), JsonToggleFunction.EXTERNALIZER);
      externalizerMap.put(JsonClearFunction.EXTERNALIZER.getId(), JsonClearFunction.EXTERNALIZER);
      externalizerMap.put(JsonObjkeysFunction.EXTERNALIZER.getId(), JsonObjkeysFunction.EXTERNALIZER);
      externalizerMap.put(JsonNumIncrOpFunction.EXTERNALIZER.getId(), JsonNumIncrOpFunction.EXTERNALIZER);
      externalizerMap.put(JsonNumMultOpFunction.EXTERNALIZER.getId(), JsonNumMultOpFunction.EXTERNALIZER);
      externalizerMap.put(JsonArrindexFunction.EXTERNALIZER.getId(), JsonArrindexFunction.EXTERNALIZER);
      externalizerMap.put(JsonBucket.EXTERNALIZER.getId(), JsonBucket.EXTERNALIZER);
      externalizerMap.put(JsonArrinsertFunction.EXTERNALIZER.getId(), JsonArrinsertFunction.EXTERNALIZER);
      externalizerMap.put(JsonArrtrimFunction.EXTERNALIZER.getId(), JsonArrtrimFunction.EXTERNALIZER);
      externalizerMap.put(JsonArrpopFunction.EXTERNALIZER.getId(), JsonArrpopFunction.EXTERNALIZER);
      externalizerMap.put(JsonMergeFunction.EXTERNALIZER.getId(), JsonMergeFunction.EXTERNALIZER);

      // Externalizer that could be loaded by other modules.
      externalizerMap.put(ITERATION_FILTER, new IterationFilter.IterationFilterExternalizer());

      SerializationContextRegistry ctxRegistry = gcr.getComponent(SerializationContextRegistry.class);
      ctxRegistry.addContextInitializer(SerializationContextRegistry.MarshallerType.GLOBAL, new PersistenceContextInitializerImpl());
      ctxRegistry.addContextInitializer(SerializationContextRegistry.MarshallerType.PERSISTENCE, new PersistenceContextInitializerImpl());
   }
}
