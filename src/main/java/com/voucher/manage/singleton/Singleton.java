package com.voucher.manage.singleton;

import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.rmi.server.entity.ImageData;
import com.voucher.manage.tools.MyTestUtil;

public class Singleton {
	private static Singleton instance = new Singleton();
		
	public final static String ROOMDATABASE="[test]";
	
	//本地文件目录
	public final static String filePath="\\Desktop\\pasoft\\photo";
	
	//资产管理系统图片目录
	public static final String ROOMINFOIMGPATH	="E:\\GTJTSJ\\";
	
	//资产管理系统图片目录2
	public static final String ROOMINFOIMGPATH2	="E:\\GTJTSJ\\pasoft";
	
	//短信帐号
	public static final String UID="泸州市国有公房经营管理有限公司";
	
	//短信密钥
	public static final String KEY="44d75966a2a94d79bb38";
	
	private LinkedHashMap<String,Map<String, Object>> registerMap;
	
	private LinkedHashMap<String, List<ImageData>> imageDataMap;
	
    private Singleton (){    	
    }  
    
    public static Singleton getInstance() {  
    	return instance;  
    }

	public LinkedHashMap<String, Map<String, Object>> getRegisterMap() {
		if (registerMap == null) {
			this.registerMap = new LinkedHashMap<String, Map<String, Object>>() {
				/**
				* 
				*/
				private static final long serialVersionUID = 1L;

				protected boolean removeEldestEntry(Map.Entry<String, Map<String, Object>> eldest) {
					long diff = 0;
					try {
						MyTestUtil.print(eldest);
						Date startDate = (Date) eldest.getValue().get("startTime");
						Date nowDate = new Date();
						diff = nowDate.getTime() - startDate.getTime();
					} catch (Exception e) {
						// TODO: handle exception
						e.printStackTrace();
						return true;
					}

					/*
					 * System.out.println("endDate=   "+endDate.getTime());
					 * System.out.println("noeDate=   "+nowDate.getTime());
					 * System.out.println("diff="+diff/1000);
					 */
					return diff / 1000 > 300;
				}
			};
			return registerMap;
		} else {
			return registerMap;
		}
	}
	
	public LinkedHashMap<String, List<ImageData>> getImageDataMap() {
		if (imageDataMap == null) {
			this.imageDataMap = new LinkedHashMap<String, List<ImageData>>() {
				/**
				* 
				*/
				private static final long serialVersionUID = 1L;

				protected boolean removeEldestEntry(Map.Entry<String, List<ImageData>> eldest) {
					long diff = 0;
					try {
						MyTestUtil.print(eldest);
						Date startDate = (Date) eldest.getValue().get(0).getDate();
						Date nowDate = new Date();
						diff = nowDate.getTime() - startDate.getTime();
					} catch (Exception e) {
						// TODO: handle exception
						e.printStackTrace();
						return true;
					}

					/*
					 * System.out.println("endDate=   "+endDate.getTime());
					 * System.out.println("noeDate=   "+nowDate.getTime());
					 * System.out.println("diff="+diff/1000);
					 */
					return diff / 1000 > 300;
				}
			};
			return imageDataMap;
		} else {
			return imageDataMap;
		}
	}
  
}
