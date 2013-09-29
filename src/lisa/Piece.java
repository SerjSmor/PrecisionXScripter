package lisa;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

public class Piece {
	
	private String id;
	private Type type;
	protected HashMap<String, String> connectors = new LinkedHashMap<String, String>();
	
 	public enum Type {
		LINE,
		CIR,
		CIR_H,
		SQR,
		SQR_H,
		X
	}

	
	@SuppressWarnings("unchecked")
	public String toString() {
		String connections = this.type.toString() + " # " + this.id + "\n"; 
		connections += "Connectors: \n";
		
		Iterator it = this.connectors.entrySet().iterator();
    while (it.hasNext()) {
        Map.Entry<String, String> pairs = (Map.Entry<String, String>)it.next();
        connections += (pairs.getKey() + " = " + pairs.getValue() + "\n");
        it.remove(); // multi threading issue avoids a ConcurrentModificationException
    }
    
    return connections;
	}
 	
 	public Piece(String id, Type type) {
 		this.connectors = new HashMap<String, String>();
 		this.id = id;
 		this.type = type;
 	}

	public Piece(Type type) {
		this.type = type;
	}
	
	public Map<String, String> getConnection() {
		return this.connectors;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
}
