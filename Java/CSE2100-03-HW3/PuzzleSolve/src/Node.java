
public class Node {
	private Node next = null;
	private Object element = null;
	public Node(Object elem, Node next){
		this.element = elem;
		this.next = next;
	}
	public Node(Object elem){
		this.element = elem;
		this.next = null;
	}
	public boolean hasNext(){
		return this.next != null;
	}
	public Node getNext(){
		return this.next;
	}
	public Object getElement(){
		return this.element;
	}
	public void setElement(Object newElem){
		this.element = newElem;
	}
	public void setNext(Node newNext){
		this.next = newNext;
	}
}
