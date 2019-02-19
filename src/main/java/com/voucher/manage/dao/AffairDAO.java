package com.voucher.manage.dao;

import java.util.Map;

import com.voucher.manage.daoModel.Aaa;
import com.voucher.manage.model.Affair;

public interface AffairDAO {

int insertTest(Map<String, Object> paramMap);
	
	int insertTest2(Map<String, Object> paramMap);
	
	int insertTest3(Map<String, Object> paramMap);
	
	int insertAll(Map<String, Object> paramMap);
	
	int insert1(Map<String, Object> paramMap);
	
	int insert2(Map<String, Object> paramMap);
	
	Affair selectTest3();

	int insertA(Aaa aaa);

	int update(Aaa aaa);

}
