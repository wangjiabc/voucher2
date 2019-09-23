package voucher;


import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.defaults.DefaultSqlSessionFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.voucher.manage.mapper.WeiXinMapper;
import com.voucher.manage.model.WeiXin;
import java.util.ArrayList;
import java.util.List;

import com.rmi.server.entity.ImageData;
import com.voucher.manage.tools.MyTestUtil;

public class WeiXinTest {

	
	public static void main(String[] args) {

		ClassPathXmlApplicationContext applicationContext=new ClassPathXmlApplicationContext("spring-mybatis2.xml");		
		DefaultSqlSessionFactory defaultSqlSessionFactory= (DefaultSqlSessionFactory) applicationContext.getBean("sqlSessionFactory");				
		SqlSession sqlSession=defaultSqlSessionFactory.openSession();
		WeiXinMapper weiXinMapper=sqlSession.getMapper(WeiXinMapper.class);
		
		WeiXin weixin=weiXinMapper.getWeiXinByCampusId(1);
		
		System.out.println(weixin.getUrl());

		
		List<ImageData> imageDataList=new ArrayList<>();
		ImageData imgdata1 = new ImageData();
		imgdata1.setName("安全巡查照片");
		imgdata1.setURI("59c8708cdc88adf6858fbfb88198d82e1566977895704");
		ImageData imgdata2 = new ImageData();
		imgdata2.setName("安全巡查照片2");
		imgdata2.setURI("59c8708cdc88adf6858fbfb88198d82e1566977895704");
		imageDataList.add(imgdata1);
		imageDataList.add(imgdata2);
		MyTestUtil.print(imageDataList);

	}

}
