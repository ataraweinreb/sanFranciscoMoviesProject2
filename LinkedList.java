

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * This class is a generic implementation of singly linked list which implements the Collection interface. 
 * It serves as the parent class of MovieList.
 * It has the nested classes of Node and linkedListIterator.
 * 
 * @author atara
 */
public class LinkedList<E> implements Collection<E> {
	
	/**
	 * This is a private, static, generic nested class which represents a node of a singly linked list.
	 * Each node contains its data, as well as a reference to the next node in the list. 
	 * Only the LinkedList class has acess to the fields of this Node class!
	 */
	private static class Node<E> {
		
		//instance variables
		E data = null; //generic type field to store the node's data
		Node<E> next = null; //reference to the next generic node of the list

		/**
		 * Constructs a new singly linked list node with the specified data and reference 
		 * to the next node as its parameters.
		 * @param data the data to be stored in this node. Generic.
		 * @param next the reference to the next node of the singly linked list
		 */
		public Node(E data, Node<E> next) {
			this.data = data;
			this.next = next;
		}
		
	} //end of nested node class
	
	/**
	 * This class implements Iterator<E>, and is instantiated when the iterator() is called
	 * @author atara
	 */
	private class linkedListIterator implements Iterator<E> {
		
		//instance variable
		Node<E> index;
		
		/**
		 * Constructs a new instance of the linkedListIterator class, 
		 * and sets the index to start at the head node of the list.
		 */
		public linkedListIterator() {
			index = head;
		}
		
		/**
		 * This method checks if there is another node in the list to iterate.
		 * It returns true when the index is not null
		 */
		public boolean hasNext() {
			return (index != null);
		}
		
		/**
		 * This method advances the iterator to the next node in the list. 
		 * @throws NoSuchElementException if there is no "next" node to iterate
		 * @return answer the data inside the next node
		 */
		public E next() {
			if (!hasNext()) {
				throw new NoSuchElementException("There is no next node.");
			}
			E answer = index.data;
			index = index.next;
			return answer;
		}
		
		/**
		 * This is an unsupported remove() method of the iterator
		 * @throws UnsupportedOperationException when this method is called because it is unsupported by this program
		 */
		public void remove() {
			throw new UnsupportedOperationException("Invalid operation.");
		}
	}
	
	//instance variables of the singly linked list
	private Node<E> head = null;
	private Node<E> tail = null;
	private int size = 0; //the number of nodes in the list
	
	/**
	 * Constructs a new empty singly LinkedList.
	 */
	public LinkedList() {
		
	}
	
	/**
	 * This method gets the size of the list, the number of nodes in it.
	 * @return size the number of nodes in the list
	 */
	public int size() {
		return size;
	}
	
	/**
	 * This method checks if the list is empty, meaning if it has no nodes.
	 * @return true when the size of the list is 0, meaning that it has no nodes
	 */
	public boolean isEmpty() {
		return size == 0;
	}
	
	/**
	 * This method returns the data stored in the head node of the list. 
	 * If the list is empty, it returns null because then there is no head node.
	 * @return the data stored in the head node
	 */
	public E first() {
		if (isEmpty()) { //if the list is empty, there is no head
			return null;
		}
		return head.data;
	}
	
	/**
	 * This method returns the data stored in the tail node of the list.
	 * If the list is empty, it returns null because then there is no tail node.
	 * @return the data stored in the tail node
	 */
	public E last() {
		if (isEmpty()) { //if the list is empty, there is no tail
			return null;
		}
		return tail.data;
	}
	
	/**
	 * This method removes the current head node of the list and returns the data stored inside of it.
	 * The size is then decremented because we are removing from the list.
	 * @return the data stored in the head node that is removed
	 */
	public E removeFirst() {
		if (isEmpty()) {
			return null;
		}
		E answer = head.data;
		head = head.next;
		size--;
		if(isEmpty()) {
			tail = null;
		}
		return answer;
	}
	
	/**
	 * This method removes the tail node of the list and returns the data stored inside of it.
	 * The size is decremented because we are removing from the list.
	 * @return the data stored in the tail node that is removed
	 */
	public E removeLast() {
		if (isEmpty()) {
			return null;
		}
		E answer = tail.data;
		Node<E> beforeTail = head;
		while(beforeTail.next != tail) {
			beforeTail = beforeTail.next;
		}
		tail = beforeTail;
		tail.next = null;
		size--;
		return answer;
	}
	
	/**
	 * This boolean method removes the node with the specified data from the list. 
	 * @param theData the specified data of the node to search for
	 * @return true if the node is successfully able to be removed
	 */
	public boolean remove(Object theData) {
		if (indexOf(theData) == -1) { //not in the list
			return false;
		}
		else if (head.data.equals(theData)) { //if the match is in the head, then call removeFirst()
			removeFirst();
			return true;
		} 
		else if (tail.data.equals(theData)) { //if the match is in the tail, then call removeLast()
			removeLast();
			return true;
		} 
		else { //it's somewhere else in the list
			Node<E> beforeRemove = head;
			while (beforeRemove.next.data != theData) {
				beforeRemove = beforeRemove.next;
			}
			Node<E> toRemove = beforeRemove.next;
			beforeRemove.next = beforeRemove.next.next;
			toRemove.data = null;
			toRemove.next = null;
			size--;
			return true;
		}
	}
	
	/**
	 * This method removes the node with the specified data from the list and returns the data in it.
	 * @param theData the specified data of the node to search for
	 * @return the data stored in the node that is removed 
	 */
	public E removeAndReturnData(Object theData) {
		if (indexOf(theData) == -1) {
			return null;
		}
		else if (head.data.equals(theData)) {
			return removeFirst();
		} 
		else if (tail.data.equals(theData)) {
			return removeLast();
		} 
		else {
			Node<E> beforeRemove = head;
			while (beforeRemove.next.data != theData) {
				beforeRemove = beforeRemove.next;
			}
			Node<E> toRemove = beforeRemove.next;
			E answer = toRemove.data;
			beforeRemove.next = beforeRemove.next.next;
			toRemove.data = null;
			toRemove.next = null;
			size--;
			return answer;
		}
	}
	
	/**
	 * This method returns the index of the matching data, and if it is not found in the list then it returns -1
	 * @param o the data to search for
	 * @return the index of the node where the data is, or -1 if it is not found
	 */
	public int indexOf(Object o) {
		int index = -1;
		if (isEmpty()) { //if the list is empty, it obviously does not contain this element
			return -1;
		}
		Node<E> current = head;
		while (current != null) {
			index++;
			if (current.data == o) {
				return index;
			}
			current = current.next;
		}
		return -1;	
	}
	
	/**
	 * This method gets the data of the node at the specified index
	 * @param index the index of the node to find and get its data
	 * @return the data at that index
	 * @throws IndexOutOfBoundsException when the index is out of range
	 * (less than 0 or greater than or equal to the size of the list)
	 */
	public E get(int index) throws IndexOutOfBoundsException {
		if (index < 0 || index >= size()) {
			throw new IndexOutOfBoundsException("Error: the index is out of range.");
		}
		Node<E> current = head;
		
		int counter = 0;
		while (counter != index) {
			counter++;
			current = current.next;
		}
		return current.data;	
	}
	
	/**
	 * This method adds to the head of the list
	 * @param theData the data to add to the head of the list
	 */
	public void addFirst(E theData) {
		Node<E> newest = new Node<>(theData, head); //create a new node with the current head as it's "next"
		if (isEmpty()) { //if this is the first (and therefore only) node in the list
			tail = newest; //it's technically also the last in the list
		}
		head = newest; //set the newest node as the new head
		size++; 
	}

	/**
	 * This method adds a node to the end of the list
	 * @param theData the data to add to the back of the list
	 */
	public void addLast(E theData) {
		Node<E> newest = new Node<>(theData, null);
		if (isEmpty()) {
			head = newest;
		}
		else {
			tail.next = newest;
		}
		tail = newest;
		size++;
	}

	/**
	 * This method should override the toString method.
	 * @return the String representation of this LinkedList
	 */
	public String toString() {
		String result = "";
		if (isEmpty()) {
			result += "Empty list.";
		}
		else {
			Node<E> current = head;
			while (current != null) {
				if (current.next != null) {
					result += current.data+"\n";
					current = current.next;
				}
				else {
					result += current.data;
					current = current.next;
				}
			}
		} 
		return result;
	}

	/**
	 * This boolean method adds a node to the end of the list by calling the addLast method
	 * Returns true after the node is successfully added
	 */
	public boolean add(E theData) {
		addLast(theData);
		return true;
	}

	//leave unimplemented (optional operation)
	public boolean addAll(Collection<? extends E> arg0) {
		throw new UnsupportedOperationException("Invalid operation.");
	}
	
	/**
	 * This method removes all of the elements from this collection.
	 * It creates a reference called "deleter," which goes through each node and sets the data to null.
	 * Head and tail are then set to null
	 * The size is also set to 0, to reflect that the list is now empty
	 */
	public void clear() {
		Node<E> deleter = head;
		while (deleter != null) {
			Node<E> nextNode = deleter.next;
			deleter.data = null;
			deleter.next = null;
			deleter = nextNode;
		}
		head = tail = null;
		size = 0;
	}
	
	/**
	 * This boolean method returns true if this collection contains the specified element, and returns false otherwise.
	 */
	public boolean contains(Object o) {
		return indexOf(o) >= 0;
	}

	/**
	 * This boolean method returns true if this collection contains all of the elements in the specified collection, and returns false otherwise.
	 */
	public boolean containsAll(Collection<?> theCollection) {
		for (Object o : theCollection) { //for each object in the collection
			if (!contains(o)) { //if o is not in it, return false
				return false; 
			}
		}
		return true; //it contains everything
	}
	
	/**
	 * @override This method overrides the equals method
	 * It checks if 2 linked lists are equal, 
	 * meaning that they are the same size and type, and have the same nodes, in the same order
	 * @return true if the linked lists are found to be equal
	 * @return false if the linked lists are found to be unequal
	 */
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		LinkedList other = (LinkedList) obj; //safe cast, no generic types!
		
		if (size != other.size) {
			return false;
		}
		
		//create two reference nodes to walk through both lists and compare the nodes
		Node<E> walkA = this.head;
		Node<E> walkB = other.head;
		while (walkA != null) {
			if (!walkA.data.equals(walkB.data)) {
				return false;
			}
			walkA = walkA.next;
			walkB = walkB.next;
		}
		return true; //the lists match
	}
	
	/**
	 * This method returns the hash code value for this collection by invoking its super class
	 */
	public int hashCode() {
		return super.hashCode();
	}

	/**
	 * This method returns an iterator over the elements in this collection by returning a new linkedListIterator() 
	 */
	public Iterator<E> iterator() {
		return new linkedListIterator();
	}
	
	//leave unimplemented
	public boolean removeAll(Collection<?> arg0) {
		throw new UnsupportedOperationException("Invalid operation.");
	}

	//leave unimplemented
	public boolean retainAll(Collection<?> arg0) {
		throw new UnsupportedOperationException("Invalid operation.");
	}

	/**
	 * This method returns an array of objects containing all of the elements in this collection.
	 * @return arr array of objects containing all of the elements in this collection
	 */
	public Object[] toArray() {
		int size = size();
		Object[] arr = new Object[size];
		for (int i = 0; i < this.size(); i++) {
			arr[i] = get(i);
		}
		return arr;
	}

	/**
	 * This method returns an array containing all of the elements in this collection; 
	 * the runtime type of the returned array is that of the specified array
	 * @return a the array of the specified type which contains all of the elements in this collection
	 */
	public <T> T[] toArray(T[] a) {
		Object[] array = toArray();
		if (a.length < size) {
			return (T[]) Arrays.copyOf(array, size, a.getClass());
		}
		System.arraycopy(array, 0, a, 0, size);
		if (a.length > size) {
			a[size] = null;
		}
		return a;
	}
			
	/**
	 * @author Professor Klukowska
	 * This method sorts the list by converting it to an array, sorting the array and then converting it back to a list
	 */
	public void sort () {
		Object [] array = toArray();
		Arrays.sort(array); 
		this.clear(); 
		for (Object o : array ) { 
			this.add((E)o); 
		} 
	}
}




