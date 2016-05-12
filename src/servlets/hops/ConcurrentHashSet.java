package servlets.hops;

import java.io.Serializable;
import java.util.AbstractSet;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class ConcurrentHashSet<E> 
	extends AbstractSet<E> 
	implements Serializable,Set<E> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2715534153789047428L;
	
	private ConcurrentHashMap<E, Object> map;
	
	private Object object = new Object();
	
	public ConcurrentHashSet(){
		map = new ConcurrentHashMap<>();
	}
	public ConcurrentHashSet(int op){
		map = new ConcurrentHashMap<>(op);
	}
	
	
	@Override
	public Iterator<E> iterator() {
		
		return map.keySet().iterator();
	}

	@Override
	public int size() {
		
		return map.size();
	}
	
	@Override
	public boolean isEmpty() {
		
		return map.isEmpty();
	}
	
	@Override
	public boolean add(E e) {
		return map.put(e, object)!=null;
	}
	
	
	@Override
	public boolean contains(Object o) {
	
		return map.containsKey(o);
	}
	
	@Override
	public boolean remove(Object o) {
		return map.remove(o)!=null;
	}
	
	@Override
	public void clear() {
		map.clear();
	}

}
