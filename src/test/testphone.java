import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class testphone {
	 public static boolean isPhone(String str) { 
	        Pattern p1 = null,p2 = null;
	        Matcher m = null;
	        boolean b = false;  
	        p1 = Pattern.compile("^((13[0-9])|(15[^4])|(18[0,2,3,5-9])|(17[0-8])|(147))\\d{8}$");

	         m = p1.matcher(str);
	         b = m.matches();  
	        return b;
	     }
	 
	 public static void main(String[] args) {
	    //Scanner scanner=new Scanner(System.in);
	    //String a=scanner.nextLine();
	    //System.out.println(isPhone(a));
		 
		 System.out.print(Math.sqrt(Math.pow(5-2, 2)));
  	}
}
