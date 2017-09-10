package com.eprint.cache;

import java.util.Collection;

public interface Listener<V> {
	 public void publish(Collection<V> publish);
	 
	 public void update(Collection<V> prev, Collection<V> next);
	 
	 public void remove(Collection<V> remove);
}
