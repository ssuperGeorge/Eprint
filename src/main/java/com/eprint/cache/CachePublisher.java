package com.eprint.cache;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import com.eprint.util.Consumer;
import com.eprint.util.Extractor;

public class CachePublisher<K, V> implements Listener<V> {
	
	private ConcurrentHashMap<K, V> m_internalMap;

	private Extractor<V, K> m_keyExtractor;

	private List<Listener<V>> m_subscriber;
	
	private CacheTable<K, V> m_cacheTable;
	
	public static <K, V> CachePublisher<K, V> create(Extractor<V, K> uniqueKey){
		return new CachePublisher<K, V>(uniqueKey);
	}
	
	private  CachePublisher(Extractor<V, K> keyExtractor) {
		m_cacheTable = new LocalCacheTable();
		m_keyExtractor = keyExtractor;
	}

	@Override
	public void publish(final Collection<V> val) {
		if(val == null || val.isEmpty()) {
			return;
		}
		
		for(V v : val) {
			if(v != null) {
				m_internalMap.put(m_keyExtractor.get(v), v);
			}
		}
		publishToSub(new Consumer<Listener<V>>() {
			@Override
			public void run(Listener<V> v) {
				v.publish(val);
			}
		});
	}

	@Override
	public  void update(final Collection<V> prev, final Collection<V> next) {
		if(prev==null || next == null || prev.size() != next.size()) {
			return ;
		}

		for(V v : next) {
			K key = m_keyExtractor.get(v);
			if(m_internalMap.contains(key)) {
				m_internalMap.put(key, v);
			}
		}
		
		publishToSub(new Consumer<Listener<V>>() {
			@Override
			public void run(Listener<V> v) {
				v.update(prev, next);
			}
		});
	}

	@Override
	public void remove(final Collection<V> val) {
		if(val == null || val.isEmpty()) {
			return;
		}
		
		for( V v : val) {
			if(v!=null) {
				m_internalMap.remove(m_keyExtractor.get(v));
			}
		}
		publishToSub(new Consumer<Listener<V>>() {
			@Override
			public void run(Listener<V> v) {
				v.remove(val);
			}
		});
	}
	
	private void publishToSub(Consumer<Listener<V>> consumer) {
		for(Listener<V> l: m_subscriber) {
			consumer.run(l);
		}
	}
	
	public CacheTable<K, V> getTable(){
		return m_cacheTable;
	}
	

	private class LocalCacheTable  implements CacheTable<K, V>{

		protected LocalCacheTable(){
			m_internalMap = new ConcurrentHashMap<>();
			m_subscriber = new ArrayList<>();
		}

		@Override
		public Collection<V> get(){
			return Collections.unmodifiableCollection(m_internalMap.values());
		}

		@Override
		public V get(K key) {
			return m_internalMap.get(key);
		}

		@Override
		public void subscribe(Listener<V> listener) {
			m_subscriber.add(listener);
		}

		@Override
		public void unsubscribe(Listener<V> listener) {
			m_subscriber.remove(listener);
		}

	}


}
