package src;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
	
	public static int distanceToNode(Node<String> node_destino, Node<String> node_origen) {
		int distancia = 0;
		while(!node_origen.getData().equals(node_destino.getData())) {
			node_origen = node_origen.getParent();
			distancia ++;
		}
		return distancia;
	}

	public static void main(String[] args) throws IOException {
		boolean parent_found = false, child_found = false;
		Node<String> root = null;
		int distancia = 0;
		Node<String> p = null, c = null;
		List<Node<String>> nodelist = new ArrayList<>();
		List<String> datalist = new ArrayList<>();
		
		
		FileReader f = new FileReader("prueba2.txt");
		BufferedReader br = new BufferedReader(f);		
		String line = br.readLine();
		
		String parent_data = line.substring(0, 3);
		String child_data = line.substring(4);		
		
		nodelist.add(new Node<>(parent_data));
		datalist.add(parent_data);
		
		nodelist.add(new Node<>(child_data));
		datalist.add(child_data);
		
		nodelist.get(0).addChild(nodelist.get(1));
		
		
		
		while((line = br.readLine()) != null) {
			
			parent_data = line.substring(0, 3);
			child_data = line.substring(4);
			
			for(int i = 0; i < datalist.size() && (parent_found == false || child_found == false); i++) {
				if(datalist.get(i).equals(parent_data)) {
					p = nodelist.get(i);
					parent_found = true;
				}
				if(datalist.get(i).equals(child_data)) {
					c = nodelist.get(i);
					child_found = true;
				}
			}
			if(parent_found == false) {
				p = new Node<String>(parent_data);
				nodelist.add(p);
				datalist.add(parent_data);
			}
			if(child_found == false) {
				c = new Node<String>(child_data);
				nodelist.add(c);
				datalist.add(child_data);
			}
			p.addChild(c);
			parent_found = false;
			child_found = false;
		}	
		br.close();
		
		for(int i = 0; i < nodelist.size();i++) {
			if(nodelist.get(i).getParent() == null) {
				root = nodelist.get(i);
			}
		}
		
		//PARTE 1
		for(Node<String> node:nodelist) {
			distancia = distancia + distanceToNode(root, node);
		}
		System.out.println(distancia);
		distancia = 0;
		
		//PARTE 2
		Node<String> you = null, san = null, interseccion = null;
		for(Node<String> node:nodelist) {
			if(node.getData().equals("YOU")) {
				you = node;
			}
			if(node.getData().equals("SAN")) {
				san = node;
			}
		}
		
		boolean found = false;
		for(Node<String> node1:you.getParents(you)) {
			for(Node<String> node2:san.getParents(san)) {
				if(node1.getData().equals(node2.getData())) {
					interseccion = node1;
					found = true;
				}
				if(found == true) {
					break;
				}
			}
			if(found == true) {
				break;
			}
		}
		System.out.println(distanceToNode(interseccion, you.getParent()) + distanceToNode(interseccion, san.getParent()));
	}
}

