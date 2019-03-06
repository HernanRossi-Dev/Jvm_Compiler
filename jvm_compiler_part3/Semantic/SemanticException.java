package Semantic;
import AST.*;
import Type.*;

public class SemanticException extends Exception {

	String message; 
	int line;
	int offset;

	public SemanticException(String msg, int l, int o){
		message = msg;
		line = l;
		offset = o;
	}

	// public SemanticException(String msg, int l){
	// 	message = msg;
	// 	line = l;
	// 	offset = 0;
	// }

	public String toString() {
		String s = "Error: " + line + " : " + offset + " : " + message;
		return s; 
	}
}