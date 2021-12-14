/**
 * Frequency object used to store the frequency size and character.
 * 
 * @author Skully (https://github.com/ImSkully)
 * @email contact@skully.tech
 */

public class Frequency {
	private int freq; // The frequency of the character.
	private char c; // The character itself.

	// Argument constructor.
	Frequency(int newFreq, char newCharacter) {
		this.freq = newFreq;
		this.c = newCharacter;
	}

	// Getters.
	public int getFreq() {
		return this.freq;
	}

	public char getC() {
		return this.c;
	}

	// Setters.
	public void setFreq(int newFreq) {
		freq = newFreq;
	}

	public void setC(char newCharacter) {
		c = newCharacter;
	}
}