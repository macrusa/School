
public class SLinkedList {
	protected Node head = null;
	protected Node tail = null;
	protected int size = 0;
	public SLinkedList(){
		this.head = null;
		this.tail = null;
		this.size = 0;
	}
	public void addFirst(Node node){
		node.setNext(null);
		if(this.size == 0){
			this.head = node;
			this.tail = this.head;
		}
		else{
			node.setNext(this.head);
			this.head = node;			
		}
		this.size++;	
	}
	public void addLast(Node node){
		node.setNext(null);
		if(this.size == 0){
			this.tail = node;
			this.head = this.tail;
		}
		else{
			this.tail.setNext(node);
			this.tail = node;
		}
		this.size++;
	}
	public Node removeFirst(){
		if(this.head == null){
			return null; // empty list
		}
		else{
			Node retNode = this.head;
			this.head = this.head.getNext();
			this.size--;
			return retNode;
		}
	}
	public Node getHead(){
		return this.head;
	}
	public int size(){
		return this.size;
	}
	public String toString(){
		String str = "";
		//
		// Start from the head node 
		//
		Node u = this.head;
		//
		while(u != null){ // if current node is null, stop looping
			str += u.getElement().toString();
			//
			// Lets try the next node.
			//
			u = u.getNext();
		}
		//
		// done
		//
		return str;
	}
	public Node getNodeAt(int targetIndex) throws Exception{
		if( targetIndex < 0 || targetIndex >= this.size ){
			throw new IndexOutOfBoundsException("Requested index " + targetIndex +
					" is incompatible with list size " + this.size);
		}
		int currentIndex = 0;
		Node u = this.head;
		while(u != null){
			if( currentIndex == targetIndex){
				// found the node
				break;
			}
			//
			u = u.getNext();
			currentIndex++;
		}
		//
		return u;
	}
	public int indexOf(Object element) throws Exception{
		if( this.size == 0){
			return -1;
		}
		Node u = this.head;
		int currentIndex = 0;
		while( u != null){
			if(u.getElement().equals(element)){
				return currentIndex;
			}
			//
			currentIndex++;
			u = u.getNext();
		}
		//
		// element not found
		//
		return -1;
	}
}
