import java.io.*;

class CodeJam1 {
    public static void main(String args[]) throws Exception {
        BufferedReader br=new BufferedReader(new FileReader("C:\\Users\\somnath\\Desktop\\testing.txt"));
        PrintWriter writer=new PrintWriter("C:\\Users\\somnath\\Desktop\\codejam1.out", "UTF-8");

        String[] num={"ZERO", "ONE", "TWO", "THREE", "FOUR", "FIVE", "SIX", "SEVEN", "EIGHT", "NINE"};
        int t=Integer.parseInt(br.readLine().trim());
        for (int t_i=1; t_i <= t; t_i++) {
            String string=br.readLine().trim(), number="";
            for (int i=0; i < 10; i++) {
                boolean exist=true;
                while (exist) {
                    char arr[]=num[i].toCharArray();
                    for (char c : arr) {
                        if (count(string, c) < count(num[i], c)) {
                            exist=false;
                            break;
                        }
                    }
                    if (exist) {
                        number=number + i;
                        for (int j=0; j < num[i].length(); j++) {
                            char c=num[i].charAt(j);
                            string=string.replaceFirst("" + c, " ");

                        }
                    }
                }
                writer.println(number);
            }
            writer.println(number);
            writer.close();
        }
    }

    public static int count(String haystack, char needle) {
        int count=0;
        for (int i=0; i < haystack.length(); i++) {
            if (haystack.charAt(i) == needle) {
                count++;
            }
        }
        return count;
    }
}