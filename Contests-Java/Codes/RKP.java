public class RK {

    public static void main(String[] abc) {
        testHash("abc", 0, 3, 294, 0);   // abc
        testHash("abc", 0, 2, 195, 0);   // ab
        testHash("abc", 1, 2, 197, 195); // bc
        testHash("rompy", 1, 3, 332, 334); // omp

        System.out.println("Testing RK");
        test("abc", "a", 0);
        test("abc", "b", 1);
        test("abc", "c", 2);
        test("abc", "d", -1);
        test("catdog", "tdo", 2);
        test("ratatat", "at", 1);
        test("abcdefghijklmnopqrs", "nop", 13);
        test("foo", "", 0);
        test("", "bar", -1);
    }
    
    public static int rk(char[] s, char[] w) {
        int wordhash = hash(w, 0, w.length, 0);
        int shash = 0;
        for(int i=0; i<=s.length-w.length; i++) {
            shash = hash(s, i, w.length, shash);
            if(shash == wordhash) {
                // compare actual characters to be sure
                boolean ok = true;
                int k, j;
                for(k=i,j=0; k<w.length && ok; k++, j++)
                    if(s[i] != w[j]) {
                        ok = false;
                        break;
                    }
                if(ok)
                    return i;
            }
        }
        return -1;
    }

    // rolling hash function (sum of ASCII values)
    public static int hash(char[]s, int i, int len, int hval) {
        if(i==0) {// first run, compute full hash
            hval = 0;
            for(int k=0; k<len; k++)
                hval += s[k];
            return hval;
        }
        if(hval!=0 && i!=0)
            hval -= s[i-1];
        if(i+len <= s.length)
            hval += s[i+len-1];
        else // out of bounds
            return -1;
        return hval;
    }

    public static void test(String text, String word, int exp) {
        char[] textC = text.toCharArray();
        char[] wordC = word.toCharArray();
        int result = rk(textC, wordC);
        if(result == exp)
            System.out.println("PASSED");
        else {
            System.out.println("FAILED");
            System.out.println("\ttext: " + text);
            System.out.println("\tword: " + word);
            System.out.println("\texp: " + exp + ", res: " + result);
        }

    }

    public static void testHash(String a, int i, int len, int exp, int hval) {
        int result = hash(a.toCharArray(), i, len, hval);
        if(result == exp)
            System.out.println("th PASS");
        else {
            System.out.println("th FAIL");
            System.out.println("\tword:  " + a);
            System.out.println("\tstart: " + i);
            System.out.println("\tlen:   " + len);
            System.out.println("\texp:   " + exp);
            System.out.println("\thval:  " + hval);
            System.out.println("\tres:   " + result);
        }
    }
}
