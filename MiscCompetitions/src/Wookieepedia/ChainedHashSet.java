package Wookieepedia;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Set;

@SuppressWarnings("unchecked")
public class ChainedHashSet<E> implements Set<Person> {
	private LinkedList<Person>[] buckets;
	private int size;

	// constructor
	public ChainedHashSet() {
		buckets = new LinkedList[10];
		for (int i = 0; i < buckets.length; i++) {
			buckets[i] = new LinkedList<Person>();
		}
	}

	public int hashFunction(int hc) {
		return Math.abs(hc) % buckets.length;
	}

	public double loadFactor() {
		return (double) size / (double) buckets.length;
	}

	public void uglyPrint() {
		for (int i = 0; i < buckets.length; i++) {
			System.out.print("Bucket " + i);
			for (int j = 0; j < buckets[i].size(); j++) {
				System.out.print(" *");
			}
			System.out.println();
		}
	}

	public void resize() {
		LinkedList<Person>[] old = buckets;
		buckets = new LinkedList[buckets.length * 2];
		size = 0;
		for (int i = 0; i < buckets.length; i++) {
			buckets[i] = new LinkedList<Person>();
		}
		for (int i = 0; i < old.length; i++) {
			for (int j = 0; j < old[i].size(); j++) {
				add(old[i].get(j));
			}
		}
	}

	@Override
	public boolean add(Person e) {
		// TODO Auto-generated method stub
		if (this.contains(e)) {
			return false;
		}
		size++;
		int hash = hashFunction(e.hashCode());
		buckets[hash].add(e);
		if (loadFactor() > 1) {
			uglyPrint();
			System.out.println(loadFactor());
			resize();
			System.out.println(loadFactor());
			uglyPrint();
		}
		return true;
	}

	@Override
	public boolean addAll(Collection<? extends Person> c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub
	}

	@Override
	public boolean contains(Object o) {
		int hash = hashFunction(o.hashCode());
		if (buckets[hash].contains(o)) {
			return true;
		}
		return false;
	}

	@Override
	public boolean containsAll(Collection<?> c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	@Override
	public Iterator<Person> iterator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean remove(Object o) {
		// TODO Auto-generated method stub
		size--;
		return false;
	}

	@Override
	public boolean removeAll(Collection<?> c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean retainAll(Collection<?> c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public Object[] toArray() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> T[] toArray(T[] a) {
		// TODO Auto-generated method stub
		return null;
	}
}
