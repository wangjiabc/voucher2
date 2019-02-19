package voucher;

import com.voucher.manage.service.AffairService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Configuration
@ComponentScan
public class MybatisConn {
	
  private AffairService affairService;

  @Autowired
  public void setAffairService(AffairService affairService) {
	this.affairService=affairService;
}
  
    @Test
	public	void affair1(ServletRequest request) throws Exception{
		
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("id", 1);
		paramMap.put("val", "dddddd");
		
	//	AffairMapper affairMapper=(AffairMapper) Mybatis.ctx.getBean(AffairMapper.class);
		
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		WebApplicationContext wac = WebApplicationContextUtils
				.getRequiredWebApplicationContext(httpRequest.getSession()
						.getServletContext());
		
		System.out.println(affairService);
		
		try{

		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	//	int i=affair.getAmount();

	}





}
