package com.voucher.manage.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.voucher.crab2died.ExcelUtils;
import com.voucher.manage.dao.CurrentDao;
import com.voucher.manage.dao.ExcelDAO;
import com.voucher.manage.daoModel.RoomInfo;
import com.voucher.manage.singleton.Singleton;
import com.voucher.manage.tools.CopyFile;
import com.voucher.manage.tools.MyTestUtil;
import com.voucher.sqlserver.context.Connect;
import com.voucher.manage.singleton.*;

class DBUtils {
    private static Properties prop = new Properties();
    //static String url = "jdbc:jtds:sqlserver://223.86.150.188:1433/";
    //static String dataBase = "manage";
    static String url;
    static String dataBase;

    static {
        ClassLoader loader = DBUtils.class.getClassLoader();
        InputStream in = loader.getResourceAsStream("jdbc.properties");
        try {
            prop.load(in);
            Class.forName(prop.getProperty("driverClassName"));
            url = prop.getProperty("url");
            dataBase = url.substring(url.lastIndexOf("/") + 1);
            System.out.println("url======"+url+"           dataBase="+dataBase);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public static Connection getConnection(String url,String username,String password) throws Exception {
    	if(username==null||username.equals(""))
    		username=prop.getProperty("username");
    	if(password==null||password.equals(""))
    		password=prop.getProperty("password");
        return DriverManager.getConnection(url, username,password);
    }

    public static void close(Connection conn) {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

}

@Controller
@RequestMapping("/excel")
public class ExcelController {
    
	static String pathRoot = System.getProperty("user.home");
	
	public final static String filePath=pathRoot+"\\Desktop\\pasoft\\excel\\";

	ApplicationContext applicationContext=new Connect().get();

    private ExcelDAO excelDAO=(ExcelDAO) applicationContext.getBean("excelDao");
	
    private CurrentDao currentDao=(CurrentDao) applicationContext.getBean("currentDao");
	
    @RequestMapping("/listToExcel")
    public @ResponseBody Map listToExcel(@RequestParam Integer limit,
    		@RequestParam Integer offset,HttpServletRequest request) throws Exception {
    	
    	  List list = new ArrayList<>();
    	  RoomInfo room=new RoomInfo();
    	  room.setLimit(limit);
    	  room.setOffset(offset);
    	  room.setNotIn("id");
          Map map=currentDao.selectTable("RoomInfo");
          MyTestUtil.print(map);
          
          list=(List) map.get("rows");         
    	
          List<Map<String, Object>> fixedTitle=(List<Map<String, Object>>) map.get("fixedTitle");
          
          List<String> headers = new ArrayList<>();
          List<String> fields=new ArrayList<>();
          
          for (int i = 0; i < fixedTitle.size(); i++) {
        	  Map<String, Object> dynTitleMap=fixedTitle.get(i);
              headers.add((String) dynTitleMap.get("title"));
              fields.add((String) dynTitleMap.get("field"));
          }

          JSONArray jsonArray=new JSONArray();
          
          Iterator iterator=list.iterator();
          
          List list2=new ArrayList<>();

          while (iterator.hasNext()) {
			Map map2=(Map) iterator.next();
			Iterator<String> fieldsIterator=fields.iterator();
			List list3=new ArrayList<>();
			while (fieldsIterator.hasNext()) {
				String field=fieldsIterator.next();
				list3.add(map2.get(field));
			}
			list2.add(list3);
			
          }
          
          Class className = room.getClass();
          String tableName = className.getName().replace("com.voucher.manage.daoModel.", "");   
          
          File targetFile=new File(filePath);
          
          if(!targetFile.exists()){
              targetFile.mkdirs();//创建目录
              System.out.println("目录不存在");
          }
          
          ExcelUtils.getInstance().exportObjects2Excel(list2, headers, filePath+tableName+".xlsx");
          
          String imgPath=request.getSession().getServletContext().getRealPath(Singleton.filePath);
          
          CopyFile.setFile(imgPath, filePath+tableName+".xlsx", tableName+".xlsx");
          
          Map map2=new HashMap<>();
          
          map2.put("url=", Singleton.filePath+"\\"+tableName+".xlsx");
          
          return map2;
          
    }
	
    @RequestMapping("/getConnection")
    public @ResponseBody Map getConnection(String ip,String database,String userName,String password
    		,HttpServletRequest request) {
    	
    	 Connection conn = null;
    	 
    	 Map map=new HashMap<>();
    	 
    	 ip="jdbc:jtds:sqlserver://"+ip+"/"+database;
    	 
    	try {
             conn = DBUtils.getConnection(ip,userName,password);
             map.put("result", "连接成功");
             
             String cookie=request.getSession().getId();
             
             Map connectMap=new HashMap<>();
             
             connectMap.put("ip", ip);
             connectMap.put("userName", userName);
             connectMap.put("password", password);
     		
     		LinkedHashMap<String, Map<String,Object>> linkedHashMap=Singleton.getInstance().getRegisterMapLong();
     		
     		Map<String,Object> linkedMap=linkedHashMap.get(cookie);
    		
     		if(linkedMap==null)
     			linkedMap=new HashMap<>();
    		
    		linkedMap.put("startTime", new Date());
    		linkedMap.put("connectMap", connectMap);
     		
         	linkedHashMap.put(cookie, linkedMap);
             
         } catch (Exception var11) {
             var11.printStackTrace();
             map.put("result", "连接失败");
         } finally {
             DBUtils.close(conn);
         }

    	return map;
    }
    
    @RequestMapping("/getTableNames")
    public @ResponseBody List<String> getTableNames(String database,String userName,String password,HttpServletRequest request) {
        Connection conn = null;
        
        String sql ="";
        
        if(database==null||database.equals("")){
        	sql="SELECT Name FROM " + DBUtils.dataBase + "..SysObjects Where XType='U' ORDER BY Name";
        }else {
        	sql="SELECT Name FROM " + database + "..SysObjects Where XType='U' ORDER BY Name";
		}
        
        ArrayList tabNames = null;

        try {
        	if(userName!=null&&password!=null){
        		String cookie=request.getSession().getId();
        		
        		LinkedHashMap<String, Map<String,Object>> linkedHashMap=Singleton.getInstance().getRegisterMapLong();
         		
         		Map<String,Object> linkedMap=linkedHashMap.get(cookie);
         		
         		Map connectMap=(Map) linkedMap.get("connectMap");
         		
         		conn = DBUtils.getConnection((String) connectMap.get("ip"),userName,password);
         		MyTestUtil.print(connectMap);
        	}else{
        		conn = DBUtils.getConnection(DBUtils.url,userName,password);
        		System.out.println("userName="+userName+"   pa="+password);
        	}
        	
            PreparedStatement prep = conn.prepareStatement(sql);
            ResultSet rs = prep.executeQuery();
            tabNames = new ArrayList();

			String REGEX = "item_";
			Pattern pattern = Pattern.compile(REGEX);
			
			String REGEX2 = "sys_";
			Pattern pattern2 = Pattern.compile(REGEX2);
			
            for (int var7 = 0; rs.next(); ++var7) {
            	
            	Matcher matcher = pattern.matcher(rs.getString("NAME"));
  				if(matcher.find()){
  					continue;
  				}
  				Matcher matcher2 = pattern2.matcher(rs.getString("NAME"));
  				if(matcher2.find()){
  					continue;
  				}
  				
                tabNames.add(rs.getString("NAME"));
            }
        } catch (Exception var11) {
            var11.printStackTrace();
        } finally {
            DBUtils.close(conn);
        }

        return tabNames;
    }
   
    
    @RequestMapping("/getTableColumns")
    public @ResponseBody List<Map> getTableColumns(@RequestParam String tableName,
    		String database,String userName,String password,HttpServletRequest request) {
        Connection conn = null;
        String sql = "select top 1 * from " + tableName;
        ArrayList tabNames = null;

        List<Map> colNames = new ArrayList<>();
        String[] colType;
        int[] colSize;
        
        try {
        	if(database!=null&&userName!=null&&password!=null){
        		String cookie=request.getSession().getId();
        		
        		LinkedHashMap<String, Map<String,Object>> linkedHashMap=Singleton.getInstance().getRegisterMapLong();
         		
         		Map<String,Object> linkedMap=linkedHashMap.get(cookie);
         		
         		Map connectMap=(Map) linkedMap.get("connectMap");
         		
         		conn = DBUtils.getConnection((String) connectMap.get("ip"),userName,password);
        	}else{
        		conn = DBUtils.getConnection(DBUtils.url,userName,password);
        	}
            PreparedStatement prep = conn.prepareStatement(sql);
            ResultSetMetaData rsmd = prep.getMetaData();
            System.out.println("rsmd=" + rsmd);
            int size = rsmd.getColumnCount();           
            colType = new String[size];
            colSize = new int[size];
            
            for (int i = 0; i < rsmd.getColumnCount(); ++i) {
            	
            	Map map=new HashMap<>();
                map.put("colName",rsmd.getColumnName(i + 1));
                colType[i] = rsmd.getColumnTypeName(i + 1);

                map.put("type", colType[i]);

                colNames.add(map);
                
                colSize[i] = rsmd.getColumnDisplaySize(i + 1);
            }

        } catch (Exception var16) {
            var16.printStackTrace();
        } finally {
            DBUtils.close(conn);
        }

        return colNames;
    }
    
    @RequestMapping("/getExcelLabel")
    public @ResponseBody List getExcelLabel(@RequestParam("file") MultipartFile file,
    		HttpServletRequest request) {

    	String cookie=request.getSession().getId();
    	   	
    	List<String> list=new ArrayList<>();

    	
    	
    	File targetFile=new File(filePath+file.getName());
        
        if(!targetFile.exists()){
            targetFile.mkdirs();//创建目录
            System.out.println("目录不存在");
        }
        
        try {
        	file.transferTo(targetFile);
        }catch (Exception e) {
			// TODO: handle exception
        	e.printStackTrace();
		} 

		LinkedHashMap<String, Map<String,Object>> linkedHashMap=Singleton.getInstance().getRegisterMapLong();
		
		Map<String,Object> linkedMap=linkedHashMap.get(cookie);
		
		Map<String,Object> map=new HashMap<>();
		
		if(linkedMap!=null)
			map=linkedMap;
		
		map.put("excelPath", filePath+file.getName());
		map.put("startTime", new Date());
		
    	linkedHashMap.put(cookie, map);
    	
        try {
        	 // @RequestParam("file") MultipartFile file 是用来接收前端传递过来的文件
            // 1.创建workbook对象，读取整个文档
            InputStream inputStream = new FileInputStream(new File(filePath+file.getName()));
            Workbook wb = WorkbookFactory.create(inputStream);
            // 不基于注解,将Excel内容读至List<List<String>>对象内
            List<List<String>> lists = ExcelUtils.getInstance().readExcel2(wb, 0, 0, 0);
            System.out.println("读取Excel至String数组：");
            for (List<String> l : lists) {
                for(String n:l){
                	list.add(n);
                }
            }

            // 2)
            // 基于注解,将Excel内容读至List<Student2>对象内
            // 验证读取转换函数Student2ExpelConverter
            // 注解 `@ExcelField(title = "是否开除", order = 5, readConverter =  Student2ExpelConverter.class)`
            /*List<Student1> students = ExcelUtils.getInstance().readExcel2Objects(path, Student1.class, 0, 0);
            SystemConstant.out.println("读取Excel至对象数组(支持类型转换)：");
            for (Student1 st : students) {
                SystemConstant.out.println(st);
            }*/
        } catch (Exception e) {
            e.printStackTrace();
        }
        MyTestUtil.print(list);
        return list;
    }
    
    @RequestMapping("/intoList")
    public @ResponseBody Map intoList(@RequestParam String json,String limitLine,
            String offsetLine,String database,String userName,String password,HttpServletRequest request) {
    	    	
    	Map<String, String> jsonMap = 
    			JSONObject.parseObject(json, new TypeReference<Map<String, String>>(){});
    	
    	String cookie=request.getSession().getId();
	   	
    	LinkedHashMap<String, Map<String,Object>> linkedHashMap=Singleton.getInstance().getRegisterMapLong();
    	
    	Map<String,Object> linkedMap=linkedHashMap.get(cookie);
    	
    	MyTestUtil.print(linkedMap);
    	
    	String excelPath=(String) linkedMap.get("excelPath");
    	
    	Map map=new HashMap<>();
    	
    	map.put("result", "输入值为空");
    	
    	int start=1;
    	int end=1;
    	
    	if(limitLine!=null||limitLine.equals("")){
    		
    	}else{
    		try{
    			start=Integer.parseInt(limitLine);
    		}catch (Exception e) {
				// TODO: handle exception
    			e.printStackTrace();
			}
    	}
    	
        try {
        	 // @RequestParam("file") MultipartFile file 是用来接收前端传递过来的文件
            // 1.创建workbook对象，读取整个文档
            InputStream inputStream = new FileInputStream(new File(excelPath));
            Workbook wb = WorkbookFactory.create(inputStream);
            // 不基于注解,将Excel内容读至List<List<String>>对象内
            Sheet sheet = wb.getSheetAt(0);
            if(offsetLine==null||offsetLine.equals("")){
            	end=sheet.getLastRowNum();
            }else{
            	try{
            		end=Integer.parseInt(offsetLine);
            	}catch (Exception e) {
					// TODO: handle exception
            		e.printStackTrace();
            		end=sheet.getLastRowNum();
				}
            }
            System.out.println("limitLine="+limitLine+"   offsetLine="+offsetLine);
            List<List<String>> lists = ExcelUtils.getInstance().readExcel2(wb, start, end, 0);

            // 2)
            // 基于注解,将Excel内容读至List<Student2>对象内
            // 验证读取转换函数Student2ExpelConverter
            // 注解 `@ExcelField(title = "是否开除", order = 5, readConverter =  Student2ExpelConverter.class)`
            /*List<Student1> students = ExcelUtils.getInstance().readExcel2Objects(path, Student1.class, 0, 0);
            SystemConstant.out.println("读取Excel至对象数组(支持类型转换)：");
            for (Student1 st : students) {
                SystemConstant.out.println(st);
            }*/
            
            String s="";
            if(database!=null&&userName!=null&&password!=null){
            	System.out.println("insert1");
            	Map connectMap=(Map) linkedMap.get("connectMap");
         		
         		Connection connection= DBUtils.getConnection((String) connectMap.get("ip"),userName,password);
         		
            	s=excelDAO.insertTable(connection,jsonMap, lists);
            }else{
            	System.out.println("insert2");
            	s=excelDAO.insertTable(jsonMap, lists);
            }
            
            map.put("result", s);
        } catch (Exception e) {
            e.printStackTrace();
            map.put("result", "导入失败");
        }
        
        return map;
    }
    
}
