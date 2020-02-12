package src;

import java.util.ArrayList;
import java.util.List;

public class Node<T> {
	
	private T data = null;
	private List<Node<T>> children = new ArrayList<>();
	private Node<T> parent = null;
	
	public Node(T data) {
		this.data = data;
	}
	
	public Node<T> addChild(Node<T> child){
		child.setParent(this);
		children.add(child);
		return child;
	}
	
	public List<Node<T>> getChildren(){
		return children;
	}	
	
	public List<Node<T>> getParents(Node<T> node){
		List<Node<T>> list = new ArrayList<>();
		while(node.getParent() != null) {
			node = node.getParent();
			list.add(node);
		}
		return list;
	}
	
	public void setData(T data) {
		this.data = data;
	}
	
	public T getData() {
		return data;
	}
	
	public void setParent(Node<T> parent) {
		this.parent = parent;
	}
	
	public Node<T> getParent(){
		return parent;
	}
}
