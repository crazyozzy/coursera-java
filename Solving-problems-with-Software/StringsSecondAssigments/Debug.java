
/**
 * Write a description of Part2 here.
 * 
 * @crazyozzy aka KDA
 * @0.0.0
 */
public class Debug {
    public void findAbc (String input) {
        int index = input.indexOf("abc");
	while (true) {
	    if (index == -1 || index >= input.length() - 3) {
		break;
	    }
	    //System.out.println("index before: " + index);
	    String found = input.substring(index + 1, index + 4);
	    System.out.println(found);
	    index = input.indexOf("abc", index + 3);
	    //System.out.println("index after: " + index);
	}
    }

    public void test() {
        //findAbc("abcdkfjsksioehgjfhsdjfhksdfhuwabcabcajfieowj");
	findAbc("abcabcabcabca");
    }

    public static void main (String[] args) {
	Debug deb1 = new Debug();
	deb1.test();
    }
}
