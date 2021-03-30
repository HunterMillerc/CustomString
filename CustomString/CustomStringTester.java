package CustomString;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class CustomStringTester {

	//Test default constructor
	@Test
	void testCustomStringConstructor() {
		new CustomString();
		CustomString test = new CustomString();
		assertEquals(0, test.getSize());
		assertEquals(10, test.getCapacity());
		assertEquals("", test.toString());
		assertEquals(true, test.isEmpty());
	}
	
	//Test constructor with giving char array
	//Simple methods also tested 
	@Test 
	void testCustomStringConstructorWithCharGiven() throws Exception {
		//a should create exception and exit
		//TEST FOR EXCEPTION PASSED
		//CustomString a = new CustomString(null);
		String testStr = "Hello what a day";
		char[] testChar = testStr.toCharArray();
		CustomString test = new CustomString(testChar);
		assertEquals("Hello what a day", test.toString());
		assertEquals(16, test.getSize());
		assertEquals(26, test.getCapacity());
		assertEquals(26, test.length());
		assertEquals(false, test.isEmpty());
		assertEquals("e", String.valueOf(test.charAt(1)));
		assertEquals(0, test.charAt(-1));
		//one above array
		assertEquals(0, test.charAt(16));
		boolean whitespace = Character.isWhitespace(test.charAt(5));
		assertEquals(true, whitespace);
	}
	
	//Create Dog class (cannot convert it to CustomString object)
	class Dog{
		private String name;
		
		Dog(String name){
			this.name = name;
		}
	}
	//Test equals Object
	@Test
	void testEqualsObject() throws Exception {
		Object nullObject = null;
		int checkIntToCString = 1;
		String stringA = "A";
		Dog dogObj = new Dog("Filo");
		CustomString numberOne = new CustomString("1".toCharArray());
		CustomString hello = new CustomString("hello".toCharArray());
		CustomString hello2 = new CustomString("hello".toCharArray());
		CustomString HELLO = new CustomString("HELLO".toCharArray());
		CustomString morehello = new CustomString("helloa".toCharArray());
		CustomString helloworld = new CustomString("Hello world".toCharArray());
		CustomString a = new CustomString("a".toCharArray());
		CustomString b = new CustomString("b".toCharArray());
		CustomString A = new CustomString("A".toCharArray());
		
		//Test for null object check and attempt to cast to CustomString
		assertEquals(false, a.equals(nullObject));
		
		//Test for Dog object that cannot be cast
		assertEquals(false, a.equals(dogObj));
		
		//should be able to cast int to CustomString
		assertEquals(true, numberOne.equals(checkIntToCString));
		assertEquals(false, a.equals(checkIntToCString));
		
		//Should be able to cast String to CustomString
		assertEquals(true, A.equals(stringA));
		
		//Check for equals based on compare to (this is after checking for null object or otherObj cannot be cast into CustomString)
		//also testing that upper/lower case is checked here
		assertEquals(false, helloworld.equals(hello));
		assertEquals(false, hello.equals(helloworld));
		assertEquals(true, hello.equals(hello2));
		assertEquals(false, A.equals(a));
		assertEquals(false, HELLO.equals(hello));
		assertEquals(false, hello.equals(morehello));
		assertEquals(false, b.equals(a));
		assertEquals(false, a.equals(A));
		assertEquals(false, a.equals(null));
	}
	
	//Test compareTo
	@Test
	void testCompareTo() throws Exception {
		CustomString hello = new CustomString("hello".toCharArray());
		CustomString hello2 = new CustomString("hello".toCharArray());
		CustomString HELLO = new CustomString("HELLO".toCharArray());
		CustomString morehello = new CustomString("helloa".toCharArray());
		CustomString a = new CustomString("a".toCharArray());
		CustomString b = new CustomString("b".toCharArray());
		CustomString A = new CustomString("A".toCharArray());
		CustomString ABC = new CustomString("ABC".toCharArray());
		CustomString aBC = new CustomString("aBC".toCharArray());
		assertEquals(-1, ABC.compareTo(aBC));
		assertEquals(0, hello.compareTo(hello2));
		assertEquals(-1, A.compareTo(a));
		assertEquals(-1, HELLO.compareTo(hello));
		assertEquals(-1, HELLO.compareTo(morehello));
		assertEquals(-1, hello.compareTo(morehello));
		assertEquals(1, b.compareTo(a));
		assertEquals(1, a.compareTo(A));
		assertEquals(1, a.compareTo(null));
	}
	
	//Test resize
	@Test
	void testResize() throws Exception {
		//CustomString exceptionTest = new CustomString("test".toCharArray());
		//test for exception handling
		//TEST FOR EXCEPTION PASSED
		//exceptionTest.resize(13);
		CustomString a = new CustomString("a".toCharArray());
		//one higher than current size
		a.resize(12);
		assertEquals(12, a.length());
		//significantly higher than current size
		a.resize(50);
		assertEquals(50, a.length());
		
		//test for exception on resize for variable a
		//TEST FOR EXCEPTION PASSED
		//a.resize(25);
		
		//check that chars are kept in resize()
		CustomString helloWorld = new CustomString("Hello World!".toCharArray());
		helloWorld.resize(50);
		assertEquals("Hello World!", helloWorld.toString());
		assertEquals(50, helloWorld.length());
		//check correct size (12 characters in Hello World!)
		assertEquals(12, helloWorld.getSize());
		assertEquals(50, helloWorld.getCapacity());
	}
	
	//Test indexOf(char c)
	@Test
	void testIndexOfChar() throws Exception {
		CustomString Hello = new CustomString("Hello".toCharArray());
		//test for characters not in CustomString
		assertEquals(-1, Hello.indexOf(' '));
		assertEquals(-1, Hello.indexOf('\u0000'));
		assertEquals(-1, Hello.indexOf('a'));
		assertEquals(-1, Hello.indexOf('h'));
		
		//test for characters in CustomString
		assertEquals(0, Hello.indexOf('H'));
		assertEquals(2, Hello.indexOf('l'));
		assertEquals(4, Hello.indexOf('o'));
	}
	
	//Test indexOf(int c)
	@Test
	void testIndexOfInt() throws Exception {
		CustomString HelloWorld = new CustomString("Hello World!".toCharArray());
		//test for characters not in CustomString
		assertEquals(-1, HelloWorld.indexOf(0));
		assertEquals(-1, HelloWorld.indexOf(36));
		assertEquals(-1, HelloWorld.indexOf(104));
		assertEquals(-1, HelloWorld.indexOf(-20));
		
		//test for characters in CustomString
		assertEquals(0, HelloWorld.indexOf(72));
		assertEquals(5, HelloWorld.indexOf(32));
		assertEquals(11, HelloWorld.indexOf(33));
	}
	
	
	//Test indexOf(CustomString anotherCString)
	@Test
	void testIndexOfCString() throws Exception {
		CustomString HelloWorld = new CustomString("Hello World!".toCharArray());
		CustomString smaller = new CustomString("World".toCharArray());
		CustomString nada = new CustomString("nada".toCharArray());
		
		//test for if input is not a part of this CString to return -1
		assertEquals(-1, HelloWorld.indexOf(nada));
		
		//test for input larger than this CString to return -1
		assertEquals(-1, smaller.indexOf(HelloWorld));
		
		//Test that World is at index 6
		assertEquals(6, HelloWorld.indexOf(smaller));
	}
	
	//Test toCharArray()
	@Test
	void testToCharArray() throws Exception {
		CustomString abc = new CustomString("abc".toCharArray());
		char[] abcArr = abc.toCharArray();
		assertEquals("abc", String.valueOf(abcArr));
		assertEquals(3, abcArr.length);
	}
	
	//Test contains()
	@Test
	void testContains() throws Exception {
		CustomString hello = new CustomString("hello".toCharArray());
		CustomString ello = new CustomString("ello".toCharArray());
		CustomString world = new CustomString("world".toCharArray());
		CustomString a = new CustomString("a".toCharArray());
		CustomString ab = new CustomString("ab".toCharArray());
		CustomString ELLO = new CustomString("ELLO".toCharArray());
		
		//test for this CustomString being smaller than the other
		assertEquals(false, ello.contains(hello));
		
		//test for correct implementation
		assertEquals(true, hello.contains(ello));
		assertEquals(true, ab.contains(a));
		
		//test with uppercase/lowercase not matching
		assertEquals(false, hello.contains(ELLO));
		
		//test when nothing matches
		assertEquals(false, world.contains(hello));
	}
	
	//Test toUpperCase()
	@Test
	void testToUpperCase() throws Exception {
		CustomString HelloWorld = new CustomString("Hello World!".toCharArray());
		CustomString numbersAndStuff = new CustomString("Hello 123 World!".toCharArray());
		CustomString HelloWorldUpper = HelloWorld.toUpperCase();
		CustomString numbersAndStuffUpper = numbersAndStuff.toUpperCase();
		
		//check that all letters are upper case
		assertEquals("HELLO WORLD!", HelloWorldUpper.toString());
		assertEquals("HELLO 123 WORLD!", numbersAndStuffUpper.toString());
		
		//check that size is the same after upper case CString created
		assertEquals(12, HelloWorldUpper.getSize());
		assertEquals(12, HelloWorld.getSize());
	}
	
	//Test equalsIgnoreCase(CustomString anotherCString)
	@Test
	void testEqualsIgnoreCaseCustomString() throws Exception {
		CustomString HelloWorld = new CustomString("Hello World!".toCharArray());
		CustomString helloworld = new CustomString("hello world!".toCharArray());
		CustomString notEqual = new CustomString("hello".toCharArray());
		CustomString nullCString = null;
		
		//test for null
		assertEquals(false, HelloWorld.equalsIgnoreCase(nullCString));
		
		//test for not equal CStrings
		assertEquals(false, HelloWorld.equalsIgnoreCase(notEqual));
		
		//test for equal
		assertEquals(true, HelloWorld.equalsIgnoreCase(helloworld));
	}
	
	//Test equalsIgnoreCase(String otherString)
	@Test
	void testEqualsIgnoreCaseString() throws Exception{
		CustomString HelloWorld = new CustomString("Hello World!".toCharArray());
		String helloworld = "hello world!";
		String notEqual = "hello";
		String nullString = null;
		
		//test for null
		assertEquals(false, HelloWorld.equalsIgnoreCase(nullString));
		
		//test for not equal
		assertEquals(false, HelloWorld.equalsIgnoreCase(notEqual));
		
		//test for equal
		assertEquals(true, HelloWorld.equalsIgnoreCase(helloworld));
	}
	
	//Test getChars
	@Test
	void testGetChars() throws Exception {
		CustomString HelloWorld = new CustomString("Hello World!".toCharArray());
		char[] test1 = new char[5];
		char[] test2 = new char[12];
		char[] test3 = new char[12];
		//srcBegin is negative
		//TEST PASSED
		HelloWorld.getChars(-1, 5, test1, 0);
		
		//srcBegin is greater than srcEnd
		//TEST PASSED
		HelloWorld.getChars(5, 1, test1, 2);
		
		//srcEnd is greater than length of string
		//TEST PASSED
		HelloWorld.getChars(0, 13, test1, 0);
		
		//dstBegin is negative
		//TEST PASSED
		HelloWorld.getChars(0, 5, test1, -1);
		
		//dstBegin+(srcEnd-srcBegin) is greater than dst.length
		//TEST PASSED
		HelloWorld.getChars(7, 10, test1, 3);
		
		//Check that both char[] dst are equal after doing same method on both
		//verified that they are not the same if arrays are of different size
		HelloWorld.getChars(0, 5, test2, 3);
		HelloWorld.getChars(0, 5, test3, 3);
		assertEquals(String.valueOf(test2), String.valueOf(test3));
	}
	
	//Test startsWith
	@Test
	void testStartsWith() throws Exception {
		CustomString world = new CustomString("world".toCharArray());
		CustomString worlds = new CustomString("worlds".toCharArray());
		CustomString wor = new CustomString("wor".toCharArray());
		CustomString nothing = new CustomString("nothing".toCharArray());
		CustomString orld = new CustomString("orld".toCharArray());
		CustomString nullCString = null;
		
		//test for no matching and matching but not on first index
		assertEquals(false, world.startsWith(nothing));
		assertEquals(false, world.startsWith(orld));
		
		//test for null CString given
		assertEquals(false, world.startsWith(nullCString));
		
		//test for longer CString given
		assertEquals(false, world.startsWith(worlds));
		
		//test for correct operation
		assertEquals(true, world.startsWith(wor));
	}
	
	//Test endsWith
	@Test
	void testEndsWith() throws Exception {
		CustomString world = new CustomString("world".toCharArray());
		CustomString worlds = new CustomString("worlds".toCharArray());
		CustomString rld = new CustomString("rld".toCharArray());
		CustomString nothing = new CustomString("nothing".toCharArray());
		CustomString worl = new CustomString("worl".toCharArray());
		CustomString nullCString = null;
		
		//test for no matching and matching but not on last index
		assertEquals(false, world.endsWith(nothing));
		assertEquals(false, world.endsWith(worl));
		
		//test for null CString given
		assertEquals(false, world.endsWith(nullCString));
		
		//test for longer CString given
		assertEquals(false, world.endsWith(worlds));
		
		//test for correct operation
		assertEquals(true, world.endsWith(rld));
	}
	
	//Test subString
	@Test
	void testSubString() throws Exception {
		CustomString world = new CustomString("world".toCharArray());
		CustomString nothing = new CustomString("nothing".toCharArray());
		
		//test for beginIdx out of bounds
		assertEquals(null, world.substring(-1, 3));
		
		//test for endIdx out of bounds
		assertEquals(null, world.substring(1, 6));
		
		//test for higher begin index than end index
		assertEquals(null, world.substring(5, 2));
		
		//test for idx right at end
		CustomString idxAtEnd = nothing.substring(2, 7);
		assertEquals("thing", idxAtEnd.toString());
		
		//test for idx in middle
		CustomString middleIdx = nothing.substring(1, 5);
		assertEquals("othi", middleIdx.toString());
		
		//test for both values being right at the end
		CustomString end = nothing.substring(6, 7);
		assertEquals("g", end.toString());
		
		//test for both values being right in beginning
		CustomString start = nothing.substring(0, 1);
		assertEquals("n", start.toString());
	}
	
	//Test append
	@Test
	void testAppend() throws Exception{
		CustomString worldhowdy = new CustomString("world".toCharArray());
		CustomString aAboveCap = new CustomString("a".toCharArray());
		CustomString HelloWorld = new CustomString("Hello World!".toCharArray());
		CustomString testNull = new CustomString("b".toCharArray());
		
		worldhowdy.append(" howdy".toCharArray());
		aAboveCap.append(" I have to make sure I'm above capacity".toCharArray());
		
		//test for null or empty array given
		HelloWorld.append("".toCharArray());
		testNull.append(null);
		assertEquals("Hello World!", HelloWorld.toString());
		assertEquals("b", testNull.toString());
		
		//test for above capacity
		assertEquals("a I have to make sure I'm above capacity", aAboveCap.toString());
		
		//test for below capacity
		assertEquals("world howdy", worldhowdy.toString());
		
		//test for capacity and size
		//same capacity and size
		assertEquals(11, testNull.length());
		assertEquals(1, testNull.getSize());
		assertEquals(11, testNull.getCapacity());
		
		//same capacity, different size
		assertEquals(21, worldhowdy.length());
		assertEquals(11, worldhowdy.getSize());
		assertEquals(21, worldhowdy.getCapacity());
		
		//different capacity and size
		assertEquals(40, aAboveCap.getSize());
		assertEquals(50, aAboveCap.length());
		assertEquals(50, aAboveCap.getCapacity());
		
	}
	
	//Test replace(char, char)
	@Test
	void testReplaceChar() throws Exception{
		CustomString world = new CustomString("world".toCharArray());
		CustomString occurrence = new CustomString("occurrence".toCharArray());
		CustomString nada = new CustomString("nada".toCharArray());
		
		//test for one occurrence
		CustomString wirld = world.replace('o', 'i');
		assertEquals("wirld", wirld.toString());
		
		//test for multiple occurrences
		CustomString okkurrenke = occurrence.replace('c', 'k');
		assertEquals("okkurrenke", okkurrenke.toString());
		
		//test for no occurrences
		CustomString noChange = nada.replace('s', 'd');
		assertEquals("nada", noChange.toString());
		
	}
	
	//Test replace(CString, CString)
	@Test
	void testReplaceCString() throws Exception{
		CustomString bug = new CustomString("bug".toCharArray());
		CustomString i = new CustomString("i".toCharArray());
		CustomString u = new CustomString("u".toCharArray());
		
		//Test for one occurrence
		CustomString big = bug.replace(u, i);
		assertEquals("big", big.toString());
		
		//Test for multiple occurrences
		CustomString chaseCStr = new CustomString("The dog chased the dog".toCharArray());
		CustomString dog = new CustomString("dog".toCharArray());
		CustomString cat = new CustomString("cat".toCharArray());
		CustomString newChase = chaseCStr.replace(dog, cat);
		assertEquals("The cat chased the cat", newChase.toString());
	}
	
}
