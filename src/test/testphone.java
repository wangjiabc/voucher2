import com.voucher.manage.dao.AffairDAO;
import com.voucher.manage.daoModel.Aaa;
import com.voucher.manage.model.Affair;
import com.voucher.manage.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;

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
	 
	 public static void main(String[] args) {/*
	    Scanner scanner=new Scanner(System.in);
	    String a=scanner.nextLine();
	    System.out.println(isPhone(a));
	    */

		 ClassPathXmlApplicationContext applicationContext=new ClassPathXmlApplicationContext("spring-sqlservers.xml");

		 AffairDAO affairDAO= (AffairDAO) applicationContext.getBean("affairdao");

		 Aaa aaa=new Aaa();

		 aaa.setYear(22);
		 aaa.setName("ttttttt");

		 String[] where={"name=","dsgdsg"};

		 aaa.setWhere(where);

		 System.out.println(affairDAO.update(aaa));

  	}
}
