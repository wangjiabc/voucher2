package com.voucher.manage.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.voucher.manage.model.MessageList;
import com.voucher.manage.model.WeiXin;

public interface WeiXinService {

	WeiXin getAccessTokenById(Integer campusId);
	
	List<WeiXin> getAllCampusById(Integer cityId);
	
	WeiXin getCampusById(Integer campusId);
	
	Integer insertIntoCampus(Map<String, Object> paramMap);
	
	WeiXin getByCampusIds(Integer campusId);
	
	Integer updateCampusById(Map<String, Object> paramMap);
	
	Integer updateHomePageByCampusId(Map<String, Object> paramMap);
	
	Integer getCampusIdByUserName(String userName);
	
	Integer insertMessageList(MessageList messageList);
	
	List<MessageList> getAllMessageList(Integer campusId,Integer limit, Integer offset,String sort, String order,String search);
    
    Integer getAllMessageCount(Integer campusId,String search);

	String getCompanyName(Integer campusId);

	WeiXin getWeiXinByCampusId(Integer campusId);
<<<<<<< HEAD
    
=======

>>>>>>> 1bcb55cc73926c8ca8de173170efc028e8df4faa
}
