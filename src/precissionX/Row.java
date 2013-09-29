package precissionX;

public enum Row {
	A,
	B,
	C,
	D,
	E,
	F,
	G,
	H,
	I,
	J,
	K,
	L;

	public Row getNext() {
		return values()[(ordinal()+1) % values().length];
	}
}
