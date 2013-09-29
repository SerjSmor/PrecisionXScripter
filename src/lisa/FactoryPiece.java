package lisa;

import java.util.HashMap;
import lisa.Piece.Type;

public class FactoryPiece {
	
	private HashMap <String, PartFactory> map;
	
	private interface PartFactory {
		Piece create();
	}
	
	public FactoryPiece() {
		map = new HashMap<String, PartFactory>();
//		map.put("LINE", new PartFactory() { public Piece create() { return new Piece(Type.LINE); }});
		map.put("SQR", new PartFactory() { public Piece create() { return new Piece(Type.SQR); }});
		map.put("SQR_H", new PartFactory() { public Piece create() { return new Piece(Type.SQR_H); }});
//		map.put("CIR", new PartFactory() { public Piece create() { return new Piece(Type.CIR); }});
//		map.put("CIR_H", new PartFactory() { public Piece create() { return new Piece(Type.CIR_H); }});
//		map.put("X", new PartFactory() { public Piece create() { return new Piece(Type.X); }});
	}
	
	public Piece createPart(String type) throws NoStringToMatchPart {
		PartFactory factory = this.map.get(type);
		
		if (factory == null) {
			throw new NoStringToMatchPart(type);
		}
		
		return factory.create();
	}
}
