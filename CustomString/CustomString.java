package CustomString;

import java.util.Arrays;

/** Custom String class for Java 2 - lab 01
 *  Semester 5 at Western Technical College
 * 
 * @author Hunter Miller
 *
 */
public class CustomString {
	
	private static final int DEFAULT_CAP = 10;
	
	private char[] values;
	private int size;
	private int capacity;
	

	public CustomString() {
		this.values = new char[DEFAULT_CAP];
		this.size = 0;
		this.capacity = DEFAULT_CAP;
	}
	
	public CustomString(char[] values) throws Exception {
		if(values == null || values.length == 0) {
			throw new Exception("Invalid char array given. CustomString object not created.");
		}
		else {
			this.values = new char[values.length + DEFAULT_CAP];
			for(int i = 0; i < values.length; i++) {
				this.values[i] = values[i];
			}
			this.size = values.length;
			this.capacity = DEFAULT_CAP + values.length;
		}
	}
	
	//number of chars in CustomString (use this for actual string length)
	//checks for null value characters in array (the capacity zone after the char array given from user) and returns index of that first null character found (which would be the last character the user gives before the buffer [capacity] zone)
	public int getSize() {
		for(int i = 0; i < length(); i++) {
			if(values[i] == 0) {
				return i;
			}
		}
		return 0;
	}
	
	public int getCapacity() {
		return capacity;
	}
	
	//length of entire CustomString (with capacity)
	public int length() {
		return values.length;
	}
	
	public boolean isEmpty() {
		return values == null || getSize() == 0;
	}
	
	public char charAt(int i) {
		if(i < 0 || i >= getSize()) {
			return 0;
		}
		
		return values[i];
	}
	
	public void getChars(int srcBegin, int srcEnd, char[] dst, int dstBegin) {
		if(srcBegin < 0) {
			System.out.println("srcBegin less than zero. getChars processing aborted.");
		}
		else if(srcEnd > this.getSize()) {
			System.out.println("srcEnd is greater than size of this CustomString. getChars processing aborted.");
		}
		else if (srcBegin > srcEnd) {
			System.out.println("srcBegin is larger than srcEnd. getChars processing aborted.");
		}
		else if((dstBegin+(srcEnd-srcBegin)) > dst.length) {
			System.out.println("dstBegin+(srcEnd-srcBegin) is greater than dst.length. getChars processing aborted.");
		}
		else if(dstBegin < 0) {
			System.out.println("dstBegin is less than zero. getChars processing aborted");
		}
		else {
			System.arraycopy(this.values, srcBegin, dst, srcEnd, srcEnd - srcBegin);
		}
	}
	
	//unable to figure out how to do this given an array of objects (how to cast from Object to Object[] without any casting errors?)
	public boolean equals(Object anObject) {
		if(anObject == null) {
			return false;
		}
		//need to check for numerical objects. If numerical, Character.forDigit works to change value of given digit to ASCII value (correct number)
		char[] anObjAsCharArr = null;
		if(anObject instanceof Number) {
			char singleChar = Character.forDigit((int) anObject, 10);
			anObjAsCharArr = new char[1];
			anObjAsCharArr[0] = singleChar;
		}
		else if(anObject instanceof String) {
			try {
				//unable to cast anObject (if it is string) to char[]. Utilizing String casting.
				String temp = (String)(anObject);
				char[] tempStringArr = temp.toCharArray();
				anObjAsCharArr = new char[tempStringArr.length];
				for(int i = 0; i < tempStringArr.length; i++) {
					anObjAsCharArr[i] = tempStringArr[i];
				}
			} catch (ClassCastException e) {
				return false;
			}
		}
		else if(anObject instanceof CustomString) {
			anObjAsCharArr = ((CustomString) anObject).toCharArray();
		}
		else {
			return false;
		}
		try {
			CustomString anObjAsCString = new CustomString(anObjAsCharArr);
			return this.compareTo(anObjAsCString) == 0 ? true : false;
		} catch (Exception e) {
			e.getMessage();
			return false;
		}
	}
	
	public boolean equalsIgnoreCase(CustomString anotherCString) throws Exception {
		if(anotherCString == null) {
			return false;
		}
		CustomString thisAsUpper = this.toUpperCase();
		CustomString anotherCStringAsUpper = anotherCString.toUpperCase();
		return thisAsUpper.compareTo(anotherCStringAsUpper) == 0 ? true : false;
	}
	
	public boolean equalsIgnoreCase(String anotherString) throws Exception {
		if(anotherString == null) {
			return false;
		}
		CustomString thisAsUpper = this.toUpperCase();
		CustomString anotherStringAsUpper = new CustomString(anotherString.toCharArray()).toUpperCase();
		return thisAsUpper.compareTo(anotherStringAsUpper) == 0 ? true : false;
	}
	
	public int compareTo(CustomString anotherCString) {
		if(anotherCString == null) {
			return 1;
		}
		else {
			char[] thisCharArr = this.toCharArray();
			char[] otherStringCharArr = anotherCString.toCharArray();
			int smallerArrLength = Math.min(thisCharArr.length, otherStringCharArr.length);
			int i = 0;
			//add up lexicographically in while loop, initialize isEqual to zero. If lexicographically this CString is less than otherCString, return -1. If it's higher, return 1.
			//If lexicographically both are the same (isEqual == 0), check length of both arrays. If other array is longer, this array will be lexicographically less than the other one. If the converse is true, this array will be lexicographically more than the other one.
			int isEqual = 0;
			while(i < smallerArrLength) {
				isEqual += thisCharArr[i] - otherStringCharArr[i];
				i++;
			}
			if(isEqual == 0) {
				if(thisCharArr.length < otherStringCharArr.length) {
					return -1;
				}
				else if(thisCharArr.length > otherStringCharArr.length) {
					return 1;
				}
				else {
					return 0;
				}
			}
			else if(isEqual < 0) {
				return -1;
			}
			else {
				return 1;
			}
		}
	}
	
	public char[] toCharArray() {
		char[] newCharArr = new char[getSize()];
		for(int i = 0; i < getSize(); i++) {
			newCharArr[i] = values[i];
		}
		return newCharArr;
	}
	
	public void append(char[] values) {
		if(values == null || values.length == 0) {
			System.out.println("Null or empty char array given in append method. CustomString unchanged.");
		}
		else {
			//if cap is going to be bigger
			if((values.length + this.length()) > this.getCapacity()) {
				this.capacity = values.length + this.length();
				char[] tempThisArrNewCap = new char[this.capacity];
				//copy current values into temp arr with new cap
				for(int i = 0; i < this.getSize(); i++) {
					tempThisArrNewCap[i] = this.values[i];
				}
				//add onto the new temp array with values given from method
				for(int n = this.getSize(); n < this.getSize() + values.length;) {
					for(int j = 0; j < values.length; j++) {
						tempThisArrNewCap[n] = values[j];
						n++;
					}
				}
				//create this values arr as the new one (tempThisArrNewCap)
				this.values = tempThisArrNewCap;
			}
			else {
				//if cap remains the same
				char[] tempArr = new char[this.capacity];
				//copy current values into temp arr
				for(int i = 0; i < this.getSize(); i++) {
					tempArr[i] = this.values[i];
				}
				//add onto the new temp array with values given from method
				for(int n = this.getSize(); n < this.getSize() + values.length;) {
					for(int j = 0; j < values.length; j++) {
						tempArr[n] = values[j];
						n++;
					}
				}
				//this values as temp arr
				this.values = tempArr;
			}
			
		}
		
	}
	
	public void resize(int newCap) throws Exception {
		if(newCap < this.length()) {
			throw new Exception("Unable to resize CustomString array. New capacity given is less than current capacity.");
		}
		char[] temp = new char[getSize()];
		for(int i = 0; i < getSize(); i++) {
			temp[i] = values[i];
		}
		this.values = new char[newCap];
		for(int i = 0; i < temp.length; i++) {
			this.values[i] = temp[i];
		}
		this.capacity = newCap;
	}
	
	public boolean startsWith(CustomString prefix) {
		if(prefix == null || this.getSize() < prefix.getSize()) {
			System.out.println("Invalid prefix CustomString given.");
			return false;
		}
		char[] thisCStringCharArr = this.toCharArray();
		char[] otherCStringCharArr = prefix.toCharArray();
		int matches = 0;
		for(int i = 0; i < thisCStringCharArr.length; i++) {
			if(i >= otherCStringCharArr.length) {
				break;
			}
			if(thisCStringCharArr[i] == otherCStringCharArr[i]) {
				matches++;
			}
		}
		return matches == otherCStringCharArr.length;
		
	}
	
	public boolean endsWith(CustomString suffix) {
		if(suffix == null || this.getSize() < suffix.getSize()) {
			System.out.println("Invalid suffix CustomString given.");
			return false;
		}
		char[] thisCStringCharArr = this.toCharArray();
		char[] thisCStringCharReversed = new char[thisCStringCharArr.length];
		char[] otherCStringCharArr = suffix.toCharArray();
		char[] otherCStringCharReversed = new char[otherCStringCharArr.length];
		//swap both arrays so they are reversed (first doing this array)
		for(int i = 0; i < thisCStringCharArr.length; i++) {
			thisCStringCharReversed[i] = thisCStringCharArr[thisCStringCharArr.length - 1 - i];
		}
		for(int i = 0; i < otherCStringCharArr.length; i++) {
			otherCStringCharReversed[i] = otherCStringCharArr[otherCStringCharArr.length - 1 - i];
		}
		//then go ahead and check as though it's the startsWith
		int matches = 0;
		for(int i = 0; i < thisCStringCharReversed.length; i++) {
			if(i >= otherCStringCharReversed.length) {
				break;
			}
			if(thisCStringCharReversed[i] == otherCStringCharReversed[i]) {
				matches++;
			}
		}
		return matches == otherCStringCharReversed.length;
	}
	
	public int indexOf(char c) {
		for(int i = 0; i < getSize(); i++) {
			if(values[i] == c) {
				return i;
			}
		}
		return -1;
	}
	
	public int indexOf(int c) {
		char a = (char)(c);
		return indexOf(a);
	}
	
	public int indexOf(CustomString anotherCString) {
		if(anotherCString.getSize() > this.getSize()) {
			return -1;
		}
		else {
			char[] thisCStringCharArr = this.toCharArray();
			char[] otherCStringCharArr = anotherCString.toCharArray();
			int matches = 0;
			int neededIdx = 0;
			outerloop:
			for(int i = 0; i < thisCStringCharArr.length; i++) {
				for(int j = 0; j < otherCStringCharArr.length; j++) {
					if(thisCStringCharArr[i] == otherCStringCharArr[j]) {
						matches++;
						if(matches == otherCStringCharArr.length) {
							neededIdx = i - otherCStringCharArr.length + 1;
							break outerloop;
						}
						i++;
					}
					else {
						matches = 0;
					}
				}
			}
			if(matches == 0) {
				return -1;
			}
			else {
				return neededIdx;
			}
		}
	}
	
	public CustomString substring(int beginIndex, int endIndex) throws Exception {
		if(beginIndex < 0 || endIndex > this.getSize()) {
			System.out.println("index out of bounds to find substring");
			return null;
		}
		//need to check that user doesn't make it so beginIdx is higher than endIdx
		int substrLength = endIndex-beginIndex;
		if(substrLength < 0) {
			System.out.println("Begin index given was higher than end index. Unable to substring");
			return null;
		}
		char[] thisCharArr = this.toCharArray();
		char[] substrChar = Arrays.copyOfRange(thisCharArr, beginIndex, endIndex);
		return new CustomString(substrChar);
	}
	
	public CustomString replace(char oldChar, char newChar) throws Exception {
		char[] thisAsChar = this.toCharArray();
		for(int i = 0; i < thisAsChar.length; i++) {
			if(thisAsChar[i] == oldChar) {
				thisAsChar[i] = newChar;
			}
		}
		return new CustomString(thisAsChar);
	}
	
	//utilize recursion to replace multiple occurrences of a target CustomString with a replacement CustomString
	public CustomString replace(CustomString target, CustomString replacement) throws Exception {
		if(replacement.getSize() > this.getSize()) {
			System.out.println("replacement CString is larger than this CString. Returning null CString in replace method");
			return null;
		}
		char[] thisCharArr = this.toCharArray();
		char[] replaceArr = replacement.toCharArray();
		char[] targetArr = target.toCharArray();
		int idxOfFirstOccurrence = this.indexOf(target);
		//if occurrence of target is not in this CustomString, just return this
		if(idxOfFirstOccurrence == -1) {
			return this;
		}
		
		int matches = 0;
		outerloop:
		for(int i = idxOfFirstOccurrence; i < this.getSize();) {
			for(int j = 0; j < targetArr.length; j++) {
				if(thisCharArr[i] != targetArr[j]) {
					i++;
				}
				if(thisCharArr[i] == targetArr[j]) {
					matches++;
					thisCharArr[i] = replaceArr[j];
					if(matches == replaceArr.length) {
						break outerloop;
					}
				}
			}
		}
		//Utilize recursion with adjusted CustomString and call this method upon it.
		if(matches == targetArr.length) {
			CustomString c = new CustomString(thisCharArr);
			return c.replace(target, replacement);
		}
		else {
			return new CustomString(thisCharArr);
		}
		
	}
	
	public boolean contains(CustomString anotherCString) {
		if(this.getSize() < anotherCString.getSize()) {
			System.out.println("This CustomString is smaller than the CustomString inputted. This CustomString does not contain the inputted CustomString");
			return false;
		}
		char[] thisCStringCharArr = this.toCharArray();
		char[] otherCStringCharArr = anotherCString.toCharArray();
		//set matches flag to 0. Loop through this array, then other array. If match found, increment i and loop through j loop again. Continue until all matches are found. Else, set matches flag to zero and loop through i loop again.
		int matches = 0;
		for(int i = 0; i < thisCStringCharArr.length; i++) {
			for(int j = 0; j < otherCStringCharArr.length; j++) {
				if(thisCStringCharArr[i] == otherCStringCharArr[j]) {
					matches++;
					i++;
				}
				else {
					matches = 0;
				}
			}
		}
		return matches == otherCStringCharArr.length;
	}
	
	public CustomString toUpperCase() throws Exception {
		char[] newCharArr = new char[getSize()];
		for(int i = 0; i < getSize(); i++) {
			if(Character.isLetter(values[i])) {
				newCharArr[i] = Character.toUpperCase(values[i]);
			}
			else {
				newCharArr[i] = values[i];
			}
		}
		return new CustomString(newCharArr);
	}
	
	public String toString() {
		char[] temp = new char[getSize()];
		for(int i = 0; i < getSize(); i++) {
			temp[i] = values[i];
		}

		return new String(temp);
	}
	
}
