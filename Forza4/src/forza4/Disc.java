/*
 * enum Disc that represents a group of constants
 * (the disc values: RED, YELLOW and NONE)
 */
package forza4;

/**
 *
 * @author Branc
 */
public enum Disc {
	RED,
	YELLOW,
	NONE;
	
        //return the string represention of the enum
        @Override
	public String toString() {
		switch(this) {
		case RED:
			return " "+"\33[31mO\33[0m"+" ";
		case YELLOW:
			return " "+"\33[33mO\33[0m"+" ";
		case NONE:
			return "   ";
		default:
			return this.toString();
		}
	}
	
}
