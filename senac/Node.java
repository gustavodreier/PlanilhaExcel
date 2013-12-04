package senac.Listas;

public class Node {
	
	private Object data;
	private Node prevNode;
	private Node nextNode;
	private String index;
	
	public Node() {
		data = null;
		prevNode = null;
		nextNode = null;
		index = null;
	}
	
	public Node(Object data, String index) {
		this.data = data;
		prevNode = null;
		nextNode = null;
		this.index = index;
	}

	public Object getData() {
		return data;
	}
	
	public void setData(Object data) {
		this.data = data;
	}
	
	public Node prev() {
		return this.prevNode;
	}
	
	public void setprev(Node prev) {
		this.prevNode = prev;
	}
	
	public Node next() {
		return this.nextNode;
	}
	
	public void setnext(Node next) {
		this.nextNode = next;
	}
	
	public String getIndex() {
		return this.index;
	}
	
	public void setIndex(String index) {
		this.index = index;
	}
}
