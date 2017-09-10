package com.eprint.cache;

import java.util.Collection;

public interface CacheTable<K, V>{
	public void subscribe(Listener<V> l);
	
	public void unsubscribe(Listener<V> l);
	
	public V get(K key);
	
	public Collection<V> get();
}