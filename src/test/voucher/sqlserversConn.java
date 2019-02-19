package voucher;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;

@ContextConfiguration(locations = { "classpath:/spring-sqlservers.xml" })
public class sqlserversConn{

    private final String SQL_INSERT = "insert into users values('zhangsan3','223456')";
    
    @Test
    public void testVoid() {
       System.out.println("test jdbcTemplate...");
        @SuppressWarnings("resource")
        ApplicationContext ctx = new ClassPathXmlApplicationContext("spring-sqlservers.xml");
        JdbcTemplate jdbct = (JdbcTemplate) ctx.getBean("jdbcTemplate");
        jdbct.execute(SQL_INSERT);
    }
}
