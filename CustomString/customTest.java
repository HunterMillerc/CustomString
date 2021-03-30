package CustomString;

public class customTest {

	public static void main(String[] args) throws Exception {
		CustomString chaseCStr = new CustomString("The dog chased the dog".toCharArray());
		CustomString dog = new CustomString("dog".toCharArray());
		CustomString cat = new CustomString("cat".toCharArray());
		//CustomString newChase = chaseCStr.replace(dog, cat);
		int i = chaseCStr.indexOf(dog);
		System.out.println(i);
	}

}
