package Semantic;

import Type.*;
import AST.*;
import java.util.*;

/**
K : Name of the function/variable
v : AST node to be added to the environment
**/
public class Environment<K, V> {
	public HashMap<K, V> envNodeList = new HashMap<K, V>();

	public Environment(){

	}

	public void beginScope(){
		// add a marker to the 
	}

	public void endScope() {

	}

	public boolean inCurrentScope(K key) {
		return false;
		
	}

	public void add(K key, V value) {
		envNodeList.put(key, value);
	}

	public void lookup(K key) {
		
	}

	public V getValue(K key){
		return envNodeList.get(key);
	}

	public boolean containsKey(K key){
		return envNodeList.containsKey(key);
	}
}