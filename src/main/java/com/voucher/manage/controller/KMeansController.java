package com.voucher.manage.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

import javax.servlet.http.HttpServletRequest;

import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.voucher.manage.dao.AssetsDAO;
import com.voucher.manage.dao.KMeansDao;
import com.voucher.manage.dao.MobileDAO;
import com.voucher.manage.daoModelJoin.RoomInfo_Position;
import com.voucher.manage.redis.JedisUtil1;
import com.voucher.manage.tools.MyTestUtil;
import com.voucher.manage.tools.KMeans.BisectingKMeans;
import com.voucher.manage.tools.KMeans.BisectingKMeans2;
import com.voucher.sqlserver.context.Connect;

@CrossOrigin
@Controller
@RequestMapping("/baiduMap/kMeans")
public class KMeansController {

	ApplicationContext applicationContext=new Connect().get();
	
	KMeansDao kMeansDao=(KMeansDao) applicationContext.getBean("kMeansDao");
	
	AssetsDAO assetsDAO = (AssetsDAO) applicationContext.getBean("assetsdao");
	
	MobileDAO mobileDao = (MobileDAO) applicationContext.getBean("mobileDao");
	
	@RequestMapping("/getAll")
	public @ResponseBody Map getAll(String id) {		

		BisectingKMeans2 bisectingKMeans2=new BisectingKMeans2();
		
		Map<String, Object> map=new HashMap<String, Object>(); 
		
		if(id==null||id.equals("")){
			id = "assetMap";
			if (JedisUtil1.exist(id)) {
				map = JedisUtil1.getObject(id);
			} else {
				CopyOnWriteArrayList<ArrayList<Double>> cList = kMeansDao.findPosition();
				map = bisectingKMeans2.get(cList);		
				Map<String, Object> mapCopy=new HashMap<String, Object>(); 
				int i = 0;
				for (Map.Entry<String, Object> entry : map.entrySet()) {
					Map<String, Object> map2 = (Map<String, Object>) entry.getValue();
					String lowerCentroid = id + "_" + i;
					map2.put("id", lowerCentroid);
					JedisUtil1.deleteData(lowerCentroid);
					JedisUtil1.setObject(lowerCentroid, map2);
					mapCopy.put(String.valueOf(entry.getKey()), map2);
					i++;
				}
				JedisUtil1.setObject(id, mapCopy);
			}
		}else{
			
			if(JedisUtil1.exist(id)){
				map=JedisUtil1.getObject(id);
			}else{
				if(JedisUtil1.exist("assetMap")){
					map=JedisUtil1.getObject("assetMap");
				}else{
					CopyOnWriteArrayList<ArrayList<Double>> cList=kMeansDao.findPosition();
					map=bisectingKMeans2.get(cList);
					JedisUtil1.setObject("assetMap", map);
				}
			}
			
			CopyOnWriteArrayList<ArrayList<Double>> cList=(CopyOnWriteArrayList<ArrayList<Double>>) map.get("points");
			map=bisectingKMeans2.get(cList);
			int i=0;
			for (Map.Entry<String, Object> entry : map.entrySet()) {
				Map<String, Object> map2 = (Map<String, Object>) entry.getValue();				
				String lowerCentroid=id+"_"+i;
				map2.put("id", lowerCentroid);
				JedisUtil1.deleteData(lowerCentroid);
				JedisUtil1.setObject(lowerCentroid, map2);
				i++;				
			}
		}
				
		MyTestUtil.print(map);
		
		return map;
	}
	
	
	@RequestMapping("/getAllRoom")
	public @ResponseBody Map<String, Object> getAllRoom(@RequestParam Integer page,String id,
			HttpServletRequest request) {
		
		Map<String, Object> map=new HashMap<String, Object>();
		
		Map searchMap=new HashMap<>();
		
		Map resultMap=new HashMap<>();
		
		if(id!=null&&!id.equals("")&&JedisUtil1.exist(id)){
			
			map=JedisUtil1.getObject(id);
			
			CopyOnWriteArrayList<ArrayList<Double>> points=(CopyOnWriteArrayList<ArrayList<Double>>) map.get("points");
						
			resultMap=kMeansDao.findAssetByLngLat(points, page, searchMap);
			
		}else{
		
			int offset=(page-1)*10;
			
			int limit=page*10;
			
			resultMap=assetsDAO.findAllRoomInfo_Position(limit, offset, "Num", "asc", "or", searchMap);
		
		}

		List<Map> roominfos=null;
		
		if(resultMap!=null)
			roominfos= (List) resultMap.get("rows");
		else
			return null;
		
		Iterator iterator=roominfos.iterator();
		
		List<RoomInfo_Position> roomInfo_Positions=new ArrayList<>();
		
		while (iterator.hasNext()) {
			RoomInfo_Position roomInfo_Position=new RoomInfo_Position();
			Map map2=(Map) iterator.next();
			String GUID=(String) map2.get("GUID");
			roomInfo_Position.setGUID(GUID);
			roomInfo_Positions.add(roomInfo_Position);
		}
		
		Map fileBytes = mobileDao.roomInfo_PositionImageQuery(request, roomInfo_Positions);
		
		resultMap.put("fileBytes", fileBytes);
		
		return resultMap;
	}

	
}
