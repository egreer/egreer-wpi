package lpf.model.core;




public class Value {
	public final char value;
	
	public Value(char v) {
		this.value = v;
	}
	
	public boolean equals(Object o) {
		if (o instanceof Value){
			Value v = (Value)o;
			if (v.value == this.value) 
				return true;
			}
		return false;
	}
}
