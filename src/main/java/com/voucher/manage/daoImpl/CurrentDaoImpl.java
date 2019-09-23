package com.voucher.manage.daoImpl;


import com.alibaba.fastjson.JSONArray;
import com.voucher.manage.dao.CurrentDao;

import com.voucher.manage.daoModel.TTT.ChartInfo;
import com.voucher.manage.daoRowMapper.RowMappers;
import com.voucher.manage.daoRowMapper.RowMappersTableJoin;
import com.voucher.manage.daoRowMapper.RowMappersTableJoin2;
import com.voucher.manage.daoSQL.*;
import com.voucher.manage.daoSQL.annotations.DBTable;
import com.voucher.manage.tools.Md5;
import com.voucher.manage.tools.MyTestUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CurrentDaoImpl extends JdbcDaoSupport implements CurrentDao {

    @Override
    public Map selectTable(Object object) throws ClassNotFoundException, SQLException {
        // TODO Auto-generated method stub

        Map hMap = NewSelectSqlJoin.get(object);

        String sql = (String) hMap.get("sql");
        System.out.print("sql========"+sql);
        List params = (List) hMap.get("params");

        Map hMap2 = NewSelectSqlJoin.getCount(object);

        String sql2 = (String) hMap2.get("sql");
        List params2 = (List) hMap2.get("params");

        Class className = object.getClass();
        String name = className.getName();                                    //从控制台输入一个类名，我们输入User即可
        Class<?> cl = Class.forName(name);                         //加载类，如果该类不在默认路径底下，会报 java.lang.ClassNotFoundException
        DBTable dbTable = cl.getAnnotation(DBTable.class);

        String tableName = (dbTable.name().length() < 1) ? cl.getName() : dbTable.name();//获取表的名字，如果没有在DBTable中定义，则获取类名作为Table的名字

        List<String> columnList=new ArrayList<String>();
        
        
        System.out.print("implparams=======");
        
        MyTestUtil.print(params);
        System.out.print("=========================");
        //表格数据
        List<Map> list = this.getJdbcTemplate().query(sql, params.toArray(), new RowMappersTableJoin(columnList, className, tableName));

        int total = (int) this.getJdbcTemplate().queryForMap(sql2, params2.toArray()).get("");


        Map map = new HashMap<>(8);
        map.put("rows", list);
        map.put("total", total);

        return map;
    }


    @Override
    public Map selectTable(Object[] objects, String[][] joinParameters) throws ClassNotFoundException, SQLException {
        // TODO Auto-generated method stub

        Map hMap = NewSelectSqlJoin2.get(objects, joinParameters);

        String sql = (String) hMap.get("sql");
        List params = (List) hMap.get("params");

        Map hMap2 = NewSelectSqlJoin2.getCount(objects);

        String sql2 = (String) hMap2.get("sql");
        List params2 = (List) hMap2.get("params");

        List<Map<String, Object>> fixedTitleList = new ArrayList<>();
        List<Map<String, Object>> dynTitleList = new ArrayList<>();

        List<Class> classNames = new ArrayList<>();

        List<String> tableNames = new ArrayList<>();

        boolean isCache=true;
        
        int s = 0;
        for (Object object : objects) {

            Class className = object.getClass();
            String name = className.getName(); // 从控制台输入一个类名，我们输入User即可
            Class<?> cl = Class.forName(name); // 加载类，如果该类不在默认路径底下，会报
            // java.lang.ClassNotFoundException
            DBTable dbTable = cl.getAnnotation(DBTable.class);

            String tableName = (dbTable.name().length() < 1) ? cl.getName() : dbTable.name();// 获取表的名字，如果没有在DBTable中定义，则获取类名作为Table的名字

            classNames.add(className);
            tableNames.add(tableName);


			tableName = tableName.substring(1, tableName.length() - 1);


			}
            

            

        Class[] classNames_ = new Class[classNames.size()];
        String[] tableName_ = new String[tableNames.size()];

        List<String> columnList=new ArrayList<String>();
        
		System.out.print("params========"); 
		 MyTestUtil.print(params);
	        System.out.print("=========================");  
        // 表格数据
        List<Map> list = this.getJdbcTemplate().query(sql, params.toArray(),
                new RowMappersTableJoin2(columnList, classNames.toArray(classNames_), tableNames.toArray(tableName_)));
        	//	new RowMappers(ChartInfo.class));
        int total = (int) this.getJdbcTemplate().queryForMap(sql2, params2.toArray()).get("");

        Map map = new HashMap<>();
        map.put("rows", list);
        map.put("total", total);

        return map;
    }

    @Override
    public Integer insertTable(Object object, String val) throws ClassNotFoundException {
        // TODO Auto-generated method stub

        String fields = "";

        String values = "";

        List params = new ArrayList<>();

        Class className = object.getClass();
        String name = className.getName();                                    //从控制台输入一个类名，我们输入User即可
        Class<?> cl = Class.forName(name);                         //加载类，如果该类不在默认路径底下，会报 java.lang.ClassNotFoundException
        DBTable dbTable = cl.getAnnotation(DBTable.class);

        String tableName = (dbTable.name().length() < 1) ? cl.getName() : dbTable.name();//获取表的名字，如果没有在DBTable中定义，则获取类名作为Table的名字

        val = val.substring(1, val.length() - 1);

        String[] valStrings = val.split(",");

        int i = 0;

        if (val != null && !val.equals("")) {
            for (String valString : valStrings) {

                valString = valString.substring(1, valString.length() - 1);

                if (i % 2 == 0) {
                    fields = fields + "," + valString;
                } else if (i != 0 && i % 2 != 0) {
                    values = values + ",?";
                    params.add(valString);
                }

                i++;
            }
        } else {
            return -1;
        }

        fields = fields.substring(1, fields.length());
        values = values.substring(1, values.length());

        int result = 0;

        String sql = " insert into " + tableName + " (" + fields + ") " +
                " values (" + values + ")";

        result = this.getJdbcTemplate().update(sql, params.toArray());

     //   result = InsertExe.get(this.getJdbcTemplate(), object);
              
        return result;

    }

	@Override
	public Integer updateTable(String tableName, String line_uuid, String value) {
		// TODO Auto-generated method stub
		return null;
	}

  
}
