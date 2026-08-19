package io.zbus.spring.boot.handler.chain.def;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import org.apache.commons.lang3.StringUtils;

import io.zbus.spring.boot.event.ZbusEvent;
import io.zbus.spring.boot.handler.EventHandler;
import io.zbus.spring.boot.handler.NamedHandlerList;
import io.zbus.spring.boot.handler.chain.HandlerChain;
import io.zbus.spring.boot.handler.chain.ProxiedHandlerChain;

/**
 * Default {@link NamedHandlerList} implementation backed by an
 * {@link ArrayList}, producing {@link ProxiedHandlerChain} instances when
 * proxied.
 *
 * @author <a href="https://github.com/loong10k">Loong Wan</a>
 * @since 1.0.0
 */
public class DefaultNamedHandlerList implements NamedHandlerList<ZbusEvent> {

	private String name;
	
	private List<EventHandler<ZbusEvent>> backingList;
	
	public DefaultNamedHandlerList(String name) {
		 this(name, new ArrayList<EventHandler<ZbusEvent>>());
	}

	public DefaultNamedHandlerList(String name, List<EventHandler<ZbusEvent>> backingList) {
		 if (backingList == null) {
	            throw new NullPointerException("backingList constructor argument cannot be null.");
        }
        this.backingList = backingList;
        setName(name);
	}

	/**
	 * Sets the name.
	 *
	 * @param name the name
	 */
	public void setName(String name) {
		 if (StringUtils.isBlank(name)) {
	         throw new IllegalArgumentException("Cannot specify a null or empty name.");
        }
        this.name = name;
	}

	@Override
	/**
	 * Returns the name.
	 *
	 * @return the name
	 */
	public String getName() {
		return this.name;
	}
	
	@Override
	/**
	 * proxy.
	 *
	 * @param handlerChain the handler chain
	 * @return the result
	 */
	public HandlerChain<ZbusEvent> proxy(HandlerChain<ZbusEvent> handlerChain) {
		return new ProxiedHandlerChain((ProxiedHandlerChain) handlerChain, this);
	}
	
	@Override
	/**
	 * size.
	 *
	 * @return the result
	 */
	public int size() {
		return this.backingList.size();
	}

	@Override
	/**
	 * Returns the empty.
	 *
	 * @return the empty
	 */
	public boolean isEmpty() {
		return this.backingList.isEmpty();
	}

	@Override
	/**
	 * contains.
	 *
	 * @param o the o
	 * @return the result
	 */
	public boolean contains(Object o) {
		return this.backingList.contains(o);
	}

	@Override
	/**
	 * iterator.
	 *
	 * @return the result
	 */
	public Iterator<EventHandler<ZbusEvent>> iterator() {
		return this.backingList.iterator();
	}

	@Override
	/**
	 * to Array.
	 *
	 * @return the result
	 */
	public Object[] toArray() {
		return this.backingList.toArray();
	}

	@Override
	public <T> T[] toArray(T[] a) {
		return this.backingList.toArray(a);
	}

	@Override
	/**
	 * add.
	 *
	 * @param e the e
	 * @return the result
	 */
	public boolean add(EventHandler<ZbusEvent> e) {
		return this.backingList.add(e);
	}

	@Override
	/**
	 * remove.
	 *
	 * @param o the o
	 * @return the result
	 */
	public boolean remove(Object o) {
		return this.backingList.remove(o);
	}

	@Override
	/**
	 * contains All.
	 *
	 * @param c the c
	 * @return the result
	 */
	public boolean containsAll(Collection<?> c) {
		return this.backingList.containsAll(c);
	}

	@Override
	/**
	 * add All.
	 *
	 * @param c the c
	 * @return the result
	 */
	public boolean addAll(Collection<? extends EventHandler<ZbusEvent>> c) {
		return this.backingList.addAll(c);
	}

	@Override
	/**
	 * add All.
	 *
	 * @param index the index
	 * @param c the c
	 * @return the result
	 */
	public boolean addAll(int index, Collection<? extends EventHandler<ZbusEvent>> c) {
		return this.backingList.addAll(index, c);
	}

	@Override
	/**
	 * remove All.
	 *
	 * @param c the c
	 * @return the result
	 */
	public boolean removeAll(Collection<?> c) {
		return this.backingList.removeAll(c);
	}

	@Override
	/**
	 * retain All.
	 *
	 * @param c the c
	 * @return the result
	 */
	public boolean retainAll(Collection<?> c) {
		return this.backingList.retainAll(c);
	}

	@Override
	/**
	 * clear.
	 *
	 */
	public void clear() {
		this.backingList.clear();
	}

	@Override
	/**
	 * get.
	 *
	 * @param index the index
	 * @return the result
	 */
	public EventHandler<ZbusEvent> get(int index) {
		return this.backingList.get(index);
	}

	@Override
	/**
	 * set.
	 *
	 * @param index the index
	 * @param element the element
	 * @return the result
	 */
	public EventHandler<ZbusEvent> set(int index, EventHandler<ZbusEvent> element) {
		return this.backingList.set(index, element);
	}

	@Override
	/**
	 * add.
	 *
	 * @param index the index
	 * @param element the element
	 */
	public void add(int index, EventHandler<ZbusEvent> element) {
		this.backingList.add(index, element);
	}

	@Override
	/**
	 * remove.
	 *
	 * @param index the index
	 * @return the result
	 */
	public EventHandler<ZbusEvent> remove(int index) {
		return this.backingList.remove(index);
	}

	@Override
	/**
	 * index Of.
	 *
	 * @param o the o
	 * @return the result
	 */
	public int indexOf(Object o) {
		return this.backingList.indexOf(o);
	}

	@Override
	/**
	 * last Index Of.
	 *
	 * @param o the o
	 * @return the result
	 */
	public int lastIndexOf(Object o) {
		return this.backingList.lastIndexOf(o);
	}

	@Override
	/**
	 * list Iterator.
	 *
	 * @return the result
	 */
	public ListIterator<EventHandler<ZbusEvent>> listIterator() {
		return this.backingList.listIterator();
	}

	@Override
	/**
	 * list Iterator.
	 *
	 * @param index the index
	 * @return the result
	 */
	public ListIterator<EventHandler<ZbusEvent>> listIterator(int index) {
		return this.backingList.listIterator(index);
	}

	@Override
	/**
	 * sub List.
	 *
	 * @param fromIndex the from index
	 * @param toIndex the to index
	 * @return the result
	 */
	public List<EventHandler<ZbusEvent>> subList(int fromIndex, int toIndex) {
		return this.backingList.subList(fromIndex, toIndex);
	}

 }
