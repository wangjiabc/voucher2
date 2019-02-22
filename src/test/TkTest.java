import com.voucher.manage.tkmapper.entity.Usersinfo;
import com.voucher.manage.tkmapper.mapper.UsersMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/spring.xml", "classpath:/spring-mybatis.xml"})
public class TkTest {

    @Autowired
    UsersMapper usersMapper;
    @Autowired
    BeanFactory beanFactory;

    @Test
    public void test1() {
        Usersinfo where = new Usersinfo();
        where.setOpenId("oKAqL1gdrEPGWzcZkka-UhjkQgXo");
        Usersinfo usersinfo = usersMapper.select(where).get(0);
        System.out.println(usersinfo);
    }
}
