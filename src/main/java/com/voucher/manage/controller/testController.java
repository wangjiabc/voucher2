package com.voucher.manage.controller;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.activemq.filter.function.splitFunction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;


import com.rmi.server.AssetsImpl;
import com.rmi.server.Server;
import com.rmi.server.entity.ImageData;
import com.voucher.manage.dao.HiddenDAO;
import com.voucher.manage.dao.RoomInfoDao;
import com.voucher.manage.daoImpl.HiddenDAOImpl;
import com.voucher.manage.daoModel.Assets.Assets_Check_Date;
import com.voucher.manage.daoModel.Assets.Hidden_Check_Date;
import com.voucher.manage.daoModel.Assets.Hidden_Neaten_Date;
import com.voucher.manage.daoModel.TTT.FileSelfBelong;
import com.voucher.manage.file.ImageFileFactory;
import com.voucher.manage.service.AffairService;
import com.voucher.manage.singleton.Singleton;
import com.voucher.manage.tools.FileConvect;
import com.voucher.manage.tools.FileTypeTest;
import com.voucher.manage.tools.MyTestUtil;
import com.voucher.sqlserver.context.Connect;
import com.voucher.sqlserver.context.ConnectRMI;


@Controller
@RequestMapping("/test")
public class testController {
	
	ApplicationContext applicationContext=new Connect().get();
	
     private AffairService testService;
	
     private HiddenDAO hiddenDAO=(HiddenDAO) applicationContext.getBean("hiddenDao");
     
     RoomInfoDao roomInfoDao=(RoomInfoDao) applicationContext.getBean("roomInfodao");
     
     Server server=new ConnectRMI().get();
     
	/*
     private RedisDao orderDao;
 	
 	@Autowired
 	public void setOrderDao(RedisDao orderDao) {
 		this.orderDao = orderDao;
 	}
 	*/
     
    //com.rmi.server.Server server=new ConnectRMI().get();
     
	@Transactional(rollbackFor = { Exception.class })
	@Autowired
	public void setTestService(AffairService testService) {
		this.testService=testService;
	}
	
	
	@RequestMapping("/aaa")
	public @ResponseBody
	String aaa() {
		
		return "aaa";
		 
	}
	
	
	
	@RequestMapping("affair1")
	public @ResponseBody
	Integer affair1() throws Exception{
	
		
		
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("id", 1);
		paramMap.put("val", "dddddd");
		
		int i=0;
		/*
		i=testService.insertAll(paramMap);	 
       */
		
		System.out.println(testService);
		
		i=testService.insert1(paramMap);
		
		
		return i;
	}

	
	@RequestMapping("affair2")
	public @ResponseBody
	Integer affair2() throws Exception{
	
		
		
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("id", 2);
		paramMap.put("val", "fffffffff");
		
		int i=0;
		
	    i=testService.insert2(paramMap);
    
		return i;
	}


	/*
	@RequestMapping("save")
	public @ResponseBody String save(@RequestParam String id,@RequestParam 
			String name){
		Orders order=new Orders();
		order.setId(id);
		order.setName(name);
		
		orderDao.save(order);
		
		return "1";
		
	}
	
	
	@RequestMapping("read")
	public @ResponseBody String read(@RequestParam String id){
		Orders order= (Orders) orderDao.read(id);
		
		return order.getName();
	}
	
	@RequestMapping("getAll")
	public @ResponseBody Map getAll(){
		Map<String, Object> map=new HashMap();
		Set set= orderDao.getAll();
		Iterator<String> iterator=set.iterator();
		while (iterator.hasNext()) {
			String key=iterator.next();
			Orders order=(Orders) orderDao.read(key);
			map.put(key, order);			
		}
		return map;
	}
	
	@RequestMapping("del")
	public @ResponseBody Integer del(@RequestParam String id){
		orderDao.delete(id);
		return 1;
	}
	
	@RequestMapping("delAll")
	public @ResponseBody Set delAll(){
		Set set= orderDao.getAll();
		orderDao.deleteAll();
		return set;
	}
	*/
	
	@RequestMapping("getAllCheck")
	public @ResponseBody Map getAllCheck(@RequestParam Integer limit,@RequestParam
			Integer offset){
		
		return hiddenDAO.selectAllHiddenCheck(limit, offset, null, null, null, new HashMap<>());
		
	}
	
	@RequestMapping("getAllChartInfo")
	public @ResponseBody List getAllChartInfo(){
		
		return roomInfoDao.getAllChartInfo();
		
	}
	
	
	@RequestMapping("getAllVerify")
	public @ResponseBody String getAllVerify(){
		
		LinkedHashMap<String, Map<String, Object>> map = Singleton.getInstance().getRegisterMap();

		System.out.println("1:");

		System.out.println("size:" + map.size());

		String s = "";

		for (Map.Entry<String, Map<String, Object>> m : map.entrySet()) {

			map.containsKey(m.getKey());
			System.out.println("key:" + m.getKey());
			for (Map.Entry<String, Object> m2 : m.getValue().entrySet()) {
				System.out.println("     currentSize : " + map.size() + "     key:  " + m2.getKey() + "     value:  "
						+ m2.getValue());
				s = s + "     currentSize : " + map.size() + "     key:  " + m2.getKey() + "     value:  "
						+ m2.getValue();
			}

		}

		return s;
		
	}
	
	@RequestMapping(value="/findAllHistory")
	public @ResponseBody Map findAllHistory(@RequestParam Integer limit,@RequestParam Integer offset){
		
		return server.findAllHistory(limit, offset);
		
	}
	

	@RequestMapping(value="/findimagedata")
	public @ResponseBody List findImageData(@RequestParam String id){
		
		LinkedHashMap<String, List<ImageData>> imageDataMap=Singleton.getInstance().getImageDataMap();
    	List<ImageData> imageDataList=new ArrayList<>();
    	try{
    		imageDataList=imageDataMap.get(id);
    	}catch (Exception e) {
			// TODO: handle exception
    		e.printStackTrace();
    		
		}
		
    	MyTestUtil.print(imageDataList);
    	
		return imageDataList;
		
	}
	
	@RequestMapping(value="inputImage")
	public @ResponseBody Map uploadFilesSpecifyPath(@RequestParam("file") MultipartFile[] file,HttpServletRequest request,HttpServletResponse response) throws Exception {  

		long startTime=System.currentTimeMillis();   //获取开始时间  
		 
		Map map=new HashMap<>(); 
		
		 if(file!=null&&file.length>0){
	            //组合image名称，“;隔开”
	            List<String> fileName =new ArrayList<String>();
	            String upimage = null;
                System.out.println("length="+file.length);
	            try {
	                for (int i = 0; i < file.length; i++) {
	                    if (!file[i].isEmpty()) {

	                        //上传文件，随机名称，";"分号隔开
	                       // fileName.add(FileUtil.uploadImage(request, "/upload/"+"/", file[i], true));
	                    	upimage=uploadImage(request, "/Desktop/pasoft/photo/", file[i],true);
	                    	fileName.add(upimage);
	                    }
	                }

	                //上传成功
	                if(fileName!=null&&fileName.size()>0&&!upimage.equals("error")){
	                	System.out.println(fileName);
	                    System.out.println("上传成功！");
	                    map.put("img", upimage);
	                    return map;
	                 //   Constants.printJson(response, fileName);;
	                }else {
	                  //  Constants.printJson(response, "上传失败！文件格式错误！");
	                }
	            } catch (Exception e) {
	                e.printStackTrace();
	              //  Constants.printJson(response, "上传出现异常！异常出现在：class.UploadController.insert()");
	            }
	        }else {
	         //   Constants.printJson(response, "没有检测到文件！");
	        }
	    
		 
		 
	        long endTime=System.currentTimeMillis(); //获取结束时间  
	        System.out.println("上传文件共使用时间："+(endTime-startTime));  
	        
	        map.put("img", "error");
	        
	        return map;
 
	} 
	
	
	/**
     * 上传图片
     *  原名称
     * @param request 请求
     * @param path_deposit 存放位置(路径)
     * @param file 文件
     * @param isRandomName 是否随机名称
     * @return 完整文件路径
     */
    public  String uploadImage(HttpServletRequest request,String path_deposit,MultipartFile file,boolean isRandomName) {
        
    	String filePath=System.getProperty("user.home")+"\\Desktop\\pasoft\\photo\\";
    	
    	//上传
        try {
            String[] typeImg={"gif","png","jpg","jpeg","ico"};

            if(file!=null){
                String origName=file.getOriginalFilename();// 文件原名称
                System.out.println("上传的文件原名称:"+origName);
                // 判断文件类型
                String type=origName.indexOf(".")!=-1?origName.substring(origName.lastIndexOf(".")+1, origName.length()):null;
                    boolean booIsType=false;
                    for (int i = 0; i < typeImg.length; i++) {
                        if (typeImg[i].equals(type.toLowerCase())) {
                            booIsType=true;
                        }
                    }
                    //类型正确
                    if (booIsType) {
                        //存放图片文件的路径
                        String path=request.getSession().getServletContext().getRealPath(path_deposit);
                       
                    	//组合名称
                        String fileSrc=""; 
                        //是否随机名称
                        String uuid=UUID.randomUUID().toString();
                        if(isRandomName){
                            origName=uuid+origName.substring(origName.lastIndexOf("."));
                        }
                        //判断是否存在目录
                        File targetFile=new File(path,origName);
                        System.out.println("path="+path);
                        if(!targetFile.exists()){
                            targetFile.mkdirs();//创建目录
                        }
                        //上传
                        try{
                            file.transferTo(targetFile);

                          }catch (Exception e) {
  							// TODO: handle exception
                          	e.printStackTrace();
  						}

                        //完整路径
                        fileSrc=request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+path_deposit+origName;
                        
                        copyFile(path+origName, filePath+origName);
                        
                        System.out.println("图片上传成功:"+path+origName);                       
                        System.out.println("图片上传成功2:"+filePath+origName);
                        System.out.println("图片上传成功:"+fileSrc);
                        return origName;
                    }
                }
            return "error";
        }catch (Exception e) {
            e.printStackTrace();
            return "error";
        }
    }
	
    public void copyFile(String oldPath, String newPath) { 
        try { 
            int bytesum = 0; 
            int byteread = 0; 
            File oldfile = new File(oldPath); 
            if (oldfile.exists()) { //文件存在时 
                InputStream inStream = new FileInputStream(oldPath); //读入原文件 
                FileOutputStream fs = new FileOutputStream(newPath); 
                byte[] buffer = new byte[1444]; 
                int length; 
                while ( (byteread = inStream.read(buffer)) != -1) { 
                    bytesum += byteread; //字节数 文件大小 
                    System.out.println(bytesum); 
                    fs.write(buffer, 0, byteread); 
                } 
                inStream.close(); 
            } 
        } 
        catch (Exception e) { 
            System.out.println("复制单个文件操作出错"); 
            e.printStackTrace(); 
        } 
    } 
       
    
}
