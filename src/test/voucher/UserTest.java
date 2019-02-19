package voucher;

import java.util.Iterator;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.voucher.manage.dao.IUserDAO;
import com.voucher.manage.daoModel.Users;


public class UserTest {
    
	ApplicationContext applicationContext=new ClassPathXmlApplicationContext("spring-sqlservers.xml");
	
    @Test//增
    public void demo1(){
        Users users=new Users();
        users.setId(3);
        users.setUsername("admin");
        users.setPassword("123456");
      
        IUserDAO dao=(IUserDAO) applicationContext.getBean("dao");
        dao.addUser(users);
        
    }
    
    @Test//改
    public void demo2(){
        Users users=new Users();
        users.setId(1);
        users.setUsername("admin");
        users.setPassword("admin");
       
        IUserDAO dao=(IUserDAO) applicationContext.getBean("dao");
        dao.updateUser(users);
    }
    
    @Test//删
    public void demo3(){
      
          IUserDAO dao=(IUserDAO) applicationContext.getBean("dao");
   //     dao.deleteUser(3);
    }
    
    @Test//查（简单查询，返回字符串）
    public void demo4(){
    
    	IUserDAO dao=(IUserDAO) applicationContext.getBean("dao");
    //    String name=dao.searchUserName(3);
     //   System.out.println(name);
    }
    
    @Test//查（简单查询，返回对象）
    public void demo5(){
    
    	IUserDAO dao=(IUserDAO) applicationContext.getBean("dao");
   //     Users user=dao.searchUser(3);
   //    System.out.println(user.getUsername());
    }
    
    @Test//查（复杂查询，返回对象集合）
    public void demo6(){
  
    	IUserDAO dao=(IUserDAO) applicationContext.getBean("dao");
        List<Users> users=dao.findAll();
        Iterator<Users> iterator=users.iterator();
        while(iterator.hasNext()){
          System.out.println(iterator.next().getPassword());
        }
    }
    
    

}
