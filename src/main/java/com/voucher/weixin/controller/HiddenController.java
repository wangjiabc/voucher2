package com.voucher.weixin.controller;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Array;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.http.message.BasicNameValuePair;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.defaults.DefaultSqlSessionFactory;
import org.aspectj.weaver.ast.Var;
import org.bouncycastle.jce.provider.asymmetric.ec.Signature.ecCVCDSA;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.rmi.server.Server;
import com.rmi.server.entity.FlowData;
import com.rmi.server.entity.ImageData;
import com.rmi.server.entity.Neaten;
import com.rmi.server.entity.RoomInfoFlowIdEntity;
import com.voucher.manage.dao.AssetsDAO;
import com.voucher.manage.dao.HiddenDAO;
import com.voucher.manage.dao.MobileDAO;
import com.voucher.manage.dao.RoomInfoDao;
import com.voucher.manage.daoModel.RoomInfo;
import com.voucher.manage.daoModel.Assets.Hidden_Check;
import com.voucher.manage.daoModel.Assets.Hidden_Check_Date;
import com.voucher.manage.daoModel.Assets.Hidden_Check_Item;
import com.voucher.manage.daoModel.Assets.Hidden_Neaten;
import com.voucher.manage.daoModel.Assets.Hidden_Neaten_Date;
import com.voucher.manage.daoModel.Assets.Position;
import com.voucher.manage.daoModel.Assets.RoomInfo_Hidden_Item;
import com.voucher.manage.daoModel.TTT.ChartInfo;
import com.voucher.manage.daoModelJoin.Assets.Hidden_Check_Join;
import com.voucher.manage.daoModelJoin.Assets.Hidden_Neaten_Join;
import com.voucher.manage.mapper.WeiXinMapper;
import com.voucher.manage.model.Users;
import com.voucher.manage.model.WeiXin;
import com.voucher.manage.service.UserService;
import com.voucher.manage.service.WeiXinService;
import com.voucher.manage.singleton.Singleton;
import com.voucher.manage.tools.MyTestUtil;
import com.voucher.manage.tools.TestDistance;
import com.voucher.sqlserver.context.Connect;
import com.voucher.sqlserver.context.ConnectRMI;

@Controller
@RequestMapping("/mobile/hidden")
public class HiddenController {

	ApplicationContext applicationContext = new Connect().get();

	HiddenDAO hiddenDAO = (HiddenDAO) applicationContext.getBean("hiddenDao");

	AssetsDAO assetsDAO = (AssetsDAO) applicationContext.getBean("assetsdao");

	MobileDAO mobileDao = (MobileDAO) applicationContext.getBean("mobileDao");

	RoomInfoDao roomInfoDao = (RoomInfoDao) applicationContext.getBean("roomInfodao");

	private UserService userService;

	@Autowired
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	@Autowired
	private WeiXinService weixinService;

	Server server = new ConnectRMI().get();

	@RequestMapping("/selectAllCheck")
	public @ResponseBody Map selectAllCheck(@RequestParam Integer limit, @RequestParam Integer offset, String sort,
			String order, @RequestParam String search, String search2, String search3, String search4, String search5,

			HttpServletRequest request) {
		Map searchMap = new HashMap<>();

		Map map;

		System.out.println("search5=" + search5);

		if (search == null || search.equals("") && search5 != null && !search5.equals("")) {

			Calendar cal = Calendar.getInstance();
			cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONDAY) - 1, cal.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
			cal.set(Calendar.DAY_OF_MONTH, cal.getActualMinimum(Calendar.DAY_OF_MONTH));
			cal.roll(Calendar.DATE, -1);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

			String startTime = null;

			String endTime = null;

			startTime = sdf.format(cal.getTime());

			System.out.println("startTime=" + startTime);

			cal = Calendar.getInstance();
			cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONDAY), cal.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
			cal.set(Calendar.DAY_OF_MONTH, cal.getActualMinimum(Calendar.DAY_OF_MONTH));
			cal.roll(Calendar.DATE, -1);
			endTime = sdf.format(cal.getTime());

			System.out.println("endTime=" + endTime);

			map = hiddenDAO.selectNotHiddenCheckAssetByDate(limit, offset, "", startTime, endTime);

			List list = (List) map.get("rows");

			int total = (int) map.get("total");

			Map fileBytes = mobileDao.roomInfo_PositionImageQuery(request, list);

			Map result = new HashMap<>();

			result.put("hidden_Check", list);
			result.put("total", total);
			result.put("fileBytes", fileBytes);

			return result;

		}

		if (search != null && !search.equals("")) {

			int d = (int) TestDistance.get(search);

			System.out.println("d=" + d);

			if (d > 0) {

				Calendar cal = Calendar.getInstance();
				cal.set(cal.get(Calendar.YEAR), d - 1, 0, 0, 0, 0);
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

				String startTime = null;

				String endTime = null;

				startTime = sdf.format(cal.getTime());

				cal.set(cal.get(Calendar.YEAR), d, 0, 0, 0, 0);

				endTime = sdf.format(cal.getTime());

				System.out.println("searchMap=" + searchMap);

				searchMap.put("convert(varchar(11),[Hidden_Check].date,120 ) >", startTime);
				searchMap.put("convert(varchar(11),[Hidden_Check].date,120 ) <", endTime);

				System.out.println("cal.getActualMinimum(Calendar.DAY_OF_MONTH=" + startTime);

				System.out.println("searchMap=" + searchMap);

				if (search5 != null && !search5.equals("")) {
					map = hiddenDAO.selectNotHiddenCheckAssetByDate(limit, offset, "", startTime, endTime);
				} else {
					map = hiddenDAO.selectAllHiddenCheck(limit, offset, sort, order, null, searchMap);
				}
				List list = (List) map.get("rows");

				int total = (int) map.get("total");

				Map fileBytes;

				if (search5 != null && !search5.equals("")) {
					fileBytes = mobileDao.roomInfo_PositionImageQuery(request, list);
				} else {
					fileBytes = mobileDao.checkImageQuery(request, list);
				}

				Map result = new HashMap<>();

				result.put("hidden_Check", list);
				result.put("total", total);
				result.put("fileBytes", fileBytes);

				return result;

			} else {
				searchMap.put(Singleton.ROOMDATABASE + ".[dbo].[RoomInfo].[Address] like", "%" + search + "%");
			}

		}

		if (search2 != null && !search2.equals("")) {

			Calendar cal = Calendar.getInstance();
			cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONDAY), cal.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
			cal.set(Calendar.DAY_OF_MONTH, cal.getActualMinimum(Calendar.DAY_OF_MONTH));
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

			String startTime = null;

			startTime = sdf.format(cal.getTime());

			searchMap.put("convert(varchar(11),[Hidden_Check].date,120 ) >", startTime);

			System.out.println("startTime=" + startTime);
		}

		if (search3 != null && !search3.equals("")) {
			searchMap.put("[Hidden_Check].guid = ", search3);
		}

		if (search4 != null && !search4.equals("")) {
			searchMap.put("[Hidden_Check].check_name = ", search4);
		}

		map = hiddenDAO.selectAllHiddenCheck(limit, offset, sort, order, search, searchMap);

		List list = (List) map.get("rows");

		int total = (int) map.get("total");

		Map fileBytes = mobileDao.checkImageQuery(request, list);

		Map result = new HashMap<>();

		result.put("hidden_Check", list);
		result.put("total", total);
		result.put("fileBytes", fileBytes);

		return result;
	}

	@RequestMapping("/selectCheckByCheckId")
	public @ResponseBody Map selectCheckByCheckId(@RequestParam String check_id, HttpServletRequest request) {
		Map searchMap = new HashMap<>();

		searchMap.put("[Hidden_Check].check_id = ", check_id);
		String[] where = { "check_id = ", check_id };

		Map map = hiddenDAO.selectAllHiddenCheck(1, 0, null, null, null, searchMap);

		Hidden_Check_Item hidden_Check_Item = new Hidden_Check_Item();
		hidden_Check_Item.setLimit(1);
		hidden_Check_Item.setOffset(0);
		hidden_Check_Item.setNotIn("check_id");
		hidden_Check_Item.setWhere(where);

		List list = (List) map.get("rows");

		Map result = new HashMap<>();

		Hidden_Check_Join hidden_Check_Join = (Hidden_Check_Join) list.get(0);

		hidden_Check_Item = hiddenDAO.selectHidden_Check_ItemById(hidden_Check_Item);

		List fileBytes = mobileDao.allCheckImageByGUID(request, hidden_Check_Join);

		result.put("hidden_Check", hidden_Check_Join);
		result.put("hidden_Check_Item", hidden_Check_Item);
		result.put("fileBytes", fileBytes);
		MyTestUtil.print(fileBytes);

		return result;
	}

	@RequestMapping("/selectAllNeaten")
	public @ResponseBody Map selectAllNeaten(@RequestParam Integer limit, @RequestParam Integer offset, String sort,
			String order, @RequestParam String search, HttpServletRequest request) {
		Map searchMap = new HashMap<>();

		if (!search.equals("")) {
			searchMap.put("[Hidden_Neaten].neaten_name like", "%" + search + "%");
		}

		Map map = hiddenDAO.selectAllHiddenNeaten(limit, offset, sort, order, searchMap);

		List list = (List) map.get("rows");

		int total = (int) map.get("total");

		Map fileBytes = mobileDao.neatenImageQuery(request, list);

		Map result = new HashMap<>();

		result.put("hidden_Neaten", list);
		result.put("total", total);
		result.put("fileBytes", fileBytes);

		return result;
	}

	@RequestMapping("/selectNeatenByNeatenId")
	public @ResponseBody Map selectNeatenByNeatenId(@RequestParam String neaten_id, HttpServletRequest request) {
		Map searchMap = new HashMap<>();

		searchMap.put("[Hidden_Neaten].neaten_id = ", neaten_id);

		Map map = hiddenDAO.selectAllHiddenNeaten(1, 0, null, null, searchMap);

		List list = (List) map.get("rows");

		Map result = new HashMap<>();

		Hidden_Neaten_Join hidden_Neaten_Join = (Hidden_Neaten_Join) list.get(0);

		List fileBytes = mobileDao.allNeatenImageByGUID(request, hidden_Neaten_Join);

		result.put("hidden_Neaten", hidden_Neaten_Join);
		result.put("fileBytes", fileBytes);
		MyTestUtil.print(fileBytes);

		return result;
	}

	@RequestMapping("/insertHiddenCheck")
	public @ResponseBody Map insertHiddenCheck(@RequestParam String guid, @RequestParam String checkItemDate,
			String name, // 资产名称
			@RequestParam String happenTime, @RequestParam String check_name, @RequestParam String check_circs,
			@RequestParam String addComp, @RequestParam String remark, @RequestParam Double lng,
			@RequestParam Double lat, @RequestParam String id, HttpServletRequest request) {
		Integer campusId = 1;

		WeiXin weixin = weixinService.getWeiXinByCampusId(campusId);

		Hidden_Check hidden_Check = new Hidden_Check();

		UUID uuid = UUID.randomUUID();// 安全巡查id check_id

		String openId = (String) request.getSession().getAttribute("openId");

		JSONObject jsonObject1;

		Hidden_Check_Item hidden_Check_Item = new Hidden_Check_Item();

		RoomInfo_Hidden_Item roomInfo_Hidden_Item = new RoomInfo_Hidden_Item();

		String checkItem = null;

		boolean isNull = false;

		Integer item = null;

		LinkedHashMap<String, List<ImageData>> imageDataMap = Singleton.getInstance().getImageDataMap();
		List<ImageData> imageDataList = new ArrayList<>();
		try {
			imageDataList = imageDataMap.get(id);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();

		}

		if (imageDataList == null) {
			imageDataList = new ArrayList<>();
		}

		MyTestUtil.print(imageDataList);

		try {
			jsonObject1 = JSONObject.parseObject(checkItemDate);
			hidden_Check_Item.setFire_extinguisher(jsonObject1.getInteger("fire_extinguisher"));
			hidden_Check_Item.setHigh_power(jsonObject1.getInteger("high_power"));
			hidden_Check_Item.setBlow(jsonObject1.getInteger("blow"));
			hidden_Check_Item.setLine_aging(jsonObject1.getInteger("line_aging"));
			hidden_Check_Item.setIncline(jsonObject1.getInteger("incline"));
			hidden_Check_Item.setSplit(jsonObject1.getInteger("split"));
			hidden_Check_Item.setDown(jsonObject1.getInteger("down"));
			hidden_Check_Item.setBreak_off(jsonObject1.getInteger("break_off"));
			hidden_Check_Item.setDestroy(jsonObject1.getInteger("destroy"));
			hidden_Check_Item.setInvalidation(jsonObject1.getInteger("invalidation"));
			hidden_Check_Item.setFlaw(jsonObject1.getInteger("flaw"));
			hidden_Check_Item.setCesspool(jsonObject1.getInteger("cesspool"));
			hidden_Check_Item.setCoast(jsonObject1.getInteger("coast"));
			hidden_Check_Item.setWall_up(jsonObject1.getInteger("wall_up"));
			hidden_Check_Item.setOther(jsonObject1.getString("other"));

			roomInfo_Hidden_Item.setGuid(guid);
			roomInfo_Hidden_Item.setFire_extinguisher(jsonObject1.getInteger("fire_extinguisher"));
			roomInfo_Hidden_Item.setHigh_power(jsonObject1.getInteger("high_power"));
			roomInfo_Hidden_Item.setBlow(jsonObject1.getInteger("blow"));
			roomInfo_Hidden_Item.setLine_aging(jsonObject1.getInteger("line_aging"));
			roomInfo_Hidden_Item.setIncline(jsonObject1.getInteger("incline"));
			roomInfo_Hidden_Item.setSplit(jsonObject1.getInteger("split"));
			roomInfo_Hidden_Item.setDown(jsonObject1.getInteger("down"));
			roomInfo_Hidden_Item.setBreak_off(jsonObject1.getInteger("break_off"));
			roomInfo_Hidden_Item.setDestroy(jsonObject1.getInteger("destroy"));
			roomInfo_Hidden_Item.setInvalidation(jsonObject1.getInteger("invalidation"));
			roomInfo_Hidden_Item.setFlaw(jsonObject1.getInteger("flaw"));
			roomInfo_Hidden_Item.setCesspool(jsonObject1.getInteger("cesspool"));
			roomInfo_Hidden_Item.setCoast(jsonObject1.getInteger("coast"));
			roomInfo_Hidden_Item.setWall_up(jsonObject1.getInteger("wall_up"));
			roomInfo_Hidden_Item.setOther(jsonObject1.getString("other"));

			System.out.println("other=" + jsonObject1.getString("other"));

			if (jsonObject1.getString("other") != null && !jsonObject1.getString("other").equals("")) {
				hidden_Check_Item.setIs_other(1);
				roomInfo_Hidden_Item.setIs_other(1);
			}

			boolean isItemNull = jsonObject1.getInteger("fire_extinguisher") == null
					&& jsonObject1.getInteger("high_power") == null && jsonObject1.getInteger("blow") == null
					&& jsonObject1.getInteger("line_aging") == null && jsonObject1.getInteger("incline") == null
					&& jsonObject1.getInteger("split") == null && jsonObject1.getInteger("down") == null
					&& jsonObject1.getInteger("break_off") == null && jsonObject1.getInteger("destroy") == null
					&& jsonObject1.getInteger("invalidation") == null && jsonObject1.getInteger("flaw") == null
					&& jsonObject1.getInteger("cesspool") == null && jsonObject1.getInteger("coast") == null
					&& jsonObject1.getInteger("wall_up") == null;

			isNull = isItemNull
					&& (jsonObject1.getString("other") == null && jsonObject1.getString("other").equals(""));

			item = getInt(jsonObject1.getInteger("fire_extinguisher")) + getInt(jsonObject1.getInteger("high_power"))
					+ getInt(jsonObject1.getInteger("blow")) + getInt(jsonObject1.getInteger("line_aging"))
					+ getInt(jsonObject1.getInteger("incline")) + getInt(jsonObject1.getInteger("split"))
					+ getInt(jsonObject1.getInteger("down")) + getInt(jsonObject1.getInteger("break_off"))
					+ getInt(jsonObject1.getInteger("destroy")) + getInt(jsonObject1.getInteger("invalidation"))
					+ getInt(jsonObject1.getInteger("flaw")) + getInt(jsonObject1.getInteger("cesspool"))
					+ getInt(jsonObject1.getInteger("coast")) + getInt(jsonObject1.getInteger("wall_up"));

			checkItem = getItem("灭火器", hidden_Check_Item.getFire_extinguisher())
					+ getItem("大功率用电器", hidden_Check_Item.getHigh_power())
					+ getItem("易燃易爆物品", hidden_Check_Item.getBlow())
					+ getItem("线路老化", hidden_Check_Item.getLine_aging()) + getItem("倾斜", hidden_Check_Item.getIncline())
					+ getItem("开裂", hidden_Check_Item.getSplit()) + getItem("地基下沉", hidden_Check_Item.getDown())
					+ getItem("屋面脱落", hidden_Check_Item.getBreak_off())
					+ getItem("结构破坏", hidden_Check_Item.getDestroy())
					+ getItem("承重失效", hidden_Check_Item.getInvalidation()) + getItem("漏雨", hidden_Check_Item.getFlaw())
					+ getItem("化粪池问题", hidden_Check_Item.getCesspool()) + getItem("山体滑坡", hidden_Check_Item.getCoast())
					+ getItem("管道堵塞", hidden_Check_Item.getWall_up());

			if (checkItem.length() > 2)
				checkItem = checkItem.substring(0, checkItem.length() - 2);

			if (check_circs != null && !check_circs.equals("") && !isItemNull) {
				check_circs = check_circs + " , " + checkItem;
			} else if ((check_circs == null || check_circs.equals("")) && !isItemNull) {
				check_circs = checkItem;
			} else {
				check_circs = check_circs.substring(0, check_circs.length());
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println("hidden_Check_Item=" + hidden_Check_Item == null);

		System.out.println("check_circs=" + check_circs);

		System.out.println("isNull=" + isNull);

		Map map = new HashMap<>();

		if (check_name.equals("异常") && isNull) {
			map.put("status", 2);
			return map;
		}

		System.out.println("item=" + item);

		hidden_Check.setCampusAdmin(openId);

		hidden_Check.setCheck_id(uuid.toString());

		hidden_Check.setGUID(guid);

		hidden_Check.setCheck_name(check_name);

		hidden_Check.setRemark(remark);

		hidden_Check.setCheck_circs(check_circs);

		if (check_name != null && check_name.equals("异常")) {

			hidden_Check.setState("未整改");

		}
		if (happenTime != null && !happenTime.equals("")) {
			try {
				DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
				Date date;
				date = fmt.parse(happenTime);
				hidden_Check.setHappen_time(date);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		Date date = new Date();
		hidden_Check.setUpdate_time(date);
		hidden_Check.setDate(date);
		hidden_Check.setTerminal("Wechat");

		hidden_Check_Item.setCheck_id(uuid.toString());

		int i = hiddenDAO.insertHiddenCheck(hidden_Check, hidden_Check_Item, roomInfo_Hidden_Item, roomInfoDao);

		MyTestUtil.print(i);
		
		if (i > 0) { // 执行插入成功的方法

			Iterator iterator = imageDataList.iterator();
			int n = 0;
			while (iterator.hasNext()) {
				ImageData imageData = (ImageData) iterator.next();
				Hidden_Check_Date hidden_Check_Date = new Hidden_Check_Date();
				hidden_Check_Date.setCheck_id(uuid.toString());
				hidden_Check_Date.setNAME(imageData.getName());
				hidden_Check_Date.setFileBelong("检查图片");
				hidden_Check_Date.setURI(imageData.getURI());
				hidden_Check_Date.setTYPE(imageData.getType());
				hidden_Check_Date.setFileIndex(n);
				hidden_Check_Date.setDate(imageData.getDate());
				Integer in = hiddenDAO.insertHidden_Check_Date(hidden_Check_Date);
				MyTestUtil.print(in);
				n++;
			}
		}

		map.put("status", i);
		map.put("check_id", uuid.toString());

		final String checkCircs = hidden_Check.getCheck_circs();

		if (check_name != null && check_name.equals("异常")) {
				
				//发送隐患通知
				try{						
					Runnable r = new Runnable() {
						@Override
						public void run() {
							String thisguid = null;
							try {
								thisguid = URLEncoder.encode(guid, "utf-8");
								System.out.println("thisguid="+thisguid);
							} catch (UnsupportedEncodingException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							Users users=userService.getUserByOnlyOpenId(openId);
							
							String url=weixin.getUrl()+"/voucher/mobile/1/guidance/addNeatenInfoItem.html?guid="+thisguid;
							
							SimpleDateFormat sdf  =   new  SimpleDateFormat( " yyyy-MM-dd HH:mm:ss " ); 
							String time = sdf.format(new Date());
							
							String currentOpenId=( String ) request.getSession().getAttribute("openId");
							
							WechatSendMessageController wechatSendMessageController=new WechatSendMessageController();
							
							wechatSendMessageController.sendMessage(2, "隐患通知",
									"整改通知", url, "隐患资产:"+name, users.getName(), time, "安全巡查", checkCircs,
									"限期整改","", currentOpenId);
							
							wechatSendMessageController.send(guid, uuid.toString(), users.getName(), openId, request);

						}
				};

				Thread t = new Thread(r);
				t.start();
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}

		}

		JSONObject jsonObject = JSONObject.parseObject(addComp);

		String province = jsonObject.getString("province");
		String city = jsonObject.getString("city");
		String district = jsonObject.getString("district");
		String street = jsonObject.getString("street");
		String streetNumber = jsonObject.getString("streetNumber");

		Position position = new Position();

		position.setCheck_id(uuid.toString());
		position.setLat(lat);
		position.setLng(lng);

		position.setProvince(province);
		position.setCity(city);
		position.setDistrict(district);
		position.setStreet(streetNumber);
		position.setStreet_number(streetNumber);
		position.setDate(date);

		assetsDAO.updatePosition(position);

		position.setCheck_id(null);
		position.setGUID(guid);

		boolean isUpdate = false; // 如果有位置就不更新

		assetsDAO.updatePositionByRoomInfo(position, isUpdate); // 更新资产位置

		return map;

	}

	String getItem(String name, Integer value) {
		if (value != null) {
			if (value == 0) {
				return name + "正常" + " , ";
			} else if (value == 1) {
				return name + "异常" + " , ";
			} else {
				return "";
			}
		} else {
			return "";
		}
	}

	int getInt(Integer value) {
		if (value != null) {
			return value;
		} else {
			return 0;
		}
	}

	@RequestMapping("/updateHiddenCheck")
	public @ResponseBody Map updateHiddenCheck(@RequestParam String check_id, @RequestParam String check_name,
			@RequestParam String happenTime, @RequestParam String remark, @RequestParam String check_circs,
			@RequestParam String addComp, @RequestParam Double lng, @RequestParam Double lat,
			HttpServletRequest request) {

		Hidden_Check hidden_Check = new Hidden_Check();

		hidden_Check.setCheck_name(check_name);

		hidden_Check.setRemark(remark);

		hidden_Check.setCheck_circs(check_circs);

		String[] where = { "[Hidden_Check].check_id=", check_id };

		hidden_Check.setWhere(where);

		if (happenTime != null && !happenTime.equals("")) {
			try {
				DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
				Date date;
				date = fmt.parse(happenTime);
				hidden_Check.setHappen_time(date);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		Date date = new Date();
		hidden_Check.setUpdate_time(date);
		hidden_Check.setDate(date);

		int i = hiddenDAO.updateHiddenCheck(hidden_Check);

		JSONObject jsonObject = JSONObject.parseObject(addComp);

		String province = jsonObject.getString("province");
		String city = jsonObject.getString("city");
		String district = jsonObject.getString("district");
		String street = jsonObject.getString("street");
		String streetNumber = jsonObject.getString("streetNumber");

		Position position = new Position();

		position.setCheck_id(check_id);
		position.setLat(lat);
		position.setLng(lng);

		position.setProvince(province);
		position.setCity(city);
		position.setDistrict(district);
		position.setStreet(streetNumber);
		position.setStreet_number(streetNumber);

		// assetsDAO.updatePosition(position);

		Map map = new HashMap<>();

		map.put("status", i);
		map.put("check_id", check_id);

		position.setCheck_id(null);

		// assetsDAO.updatePositionByRoomInfo(position); //更新资产位置

		return map;

	}

	@RequestMapping("/selectNameBycampusAdmin")
	public @ResponseBody Map selectNameBycampusAdmin(@RequestParam String campusAdmin) {

		Map map = new HashMap<>();

		Users users = userService.getUserByOnlyOpenId(campusAdmin);

		map.put("name", users.getName());

		return map;

	}

	@RequestMapping("/insertHiddenRepair")
	public @ResponseBody Map insertHiddenRepair(@RequestParam String id, @RequestParam String progress,
			HttpServletRequest request) throws JSONException {

		Map map = server.findHistoryById(id);

		Neaten neaten = (Neaten) map.get("neaten");

		org.json.JSONObject jsonObject;

		jsonObject = new org.json.JSONObject(neaten);
		/*
		 * String guid=jsonObject.getString("guid"); String address =
		 * jsonObject.getString("address"); String
		 * progress=jsonObject.getString("progress"); String
		 * neaten_instance=jsonObject.getString("neaten_instance"); String
		 * happenTime=jsonObject.getString("happenTime"); String
		 * principal=jsonObject.getString("principal"); String
		 * remark=jsonObject.getString("remark"); String
		 * addComp=jsonObject.getString("addComp"); Double
		 * lng=jsonObject.getDouble("lng"); Double lat=jsonObject.getDouble("lat");
		 * String type=jsonObject.getString("type"); Float area=(float)
		 * jsonObject.getDouble("area"); Float amount=(float)
		 * jsonObject.getDouble("amount"); Float amountTotal=(float)
		 * jsonObject.getDouble("amountTotal"); Float auditingAmount=(float)
		 * jsonObject.getDouble("auditingAmount"); String
		 * availabeLength=jsonObject.getString("availabeLength"); String
		 * workUnit=jsonObject.getString("workUnit"); String
		 * checkItemDate=jsonObject.getString("checkItemDate");
		 */

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		
		Integer neaten_type=0;
		
		//是否不需要安全巡查直接建立维修记录标识
		try {
			neaten_type=neaten.getNeaten_type();
		}catch(Exception e) {
			
		}
		
		String guid=neaten.getGUID();
		String address = neaten.getAddress();
		String neaten_instance=neaten.getNeaten_instance();
		String happenTime=sdf.format(neaten.getHappen_time());
		String principal=neaten.getPrincipal();
		String remark=neaten.getRemark();
		String addComp=neaten.getAddComp();
		Double lng=neaten.getLng();
		Double lat=neaten.getLat();
		String type=neaten.getType();
		Float area=neaten.getArea();
		Float amount=neaten.getAmount();
		Float amountTotal=neaten.getAmountTotal();
		Float auditingAmount=neaten.getAuditingAmount();
		String availabeLength=neaten.getAvailabeLength();
		String workUnit=neaten.getWorkUnit();
		String checkItemDate=neaten.getCheckItemDate();
		
		FlowData flowData=(FlowData) map.get("flowData");
		
		List imageDataList=flowData.getImageDataList();
		
		UUID uuid=UUID.randomUUID();
		
		if(imageDataList!=null){
			Iterator iterator=imageDataList.iterator();
			int n=0;

			while (iterator.hasNext()) {
				ImageData imageData = (ImageData) iterator.next();
				Hidden_Neaten_Date hidden_Neaten_Date = new Hidden_Neaten_Date();
				hidden_Neaten_Date.setNeaten_id(uuid.toString());
				hidden_Neaten_Date.setNAME(imageData.getName());
				hidden_Neaten_Date.setFileBelong("整改图片");
				hidden_Neaten_Date.setURI(imageData.getURI());
				hidden_Neaten_Date.setTYPE(imageData.getType());
				hidden_Neaten_Date.setFileIndex(n);
				hidden_Neaten_Date.setDate(imageData.getDate());
				hiddenDAO.insertHidden_Neaten_Date(hidden_Neaten_Date);
				n++;
			}
		}


		if (neaten_type != null && neaten_type == 1) {

			String processInstanceId = "";

			Map mapEntity = server.selectById(guid, 1, 1, 0);

			List<RoomInfoFlowIdEntity> list = (List<RoomInfoFlowIdEntity>) mapEntity.get("rows");

			try {
				RoomInfoFlowIdEntity roomInfoFlowIdEntity = list.get(0);
				processInstanceId = roomInfoFlowIdEntity.getProcessInstanceId();
				if (roomInfoFlowIdEntity.getResult() != 1) {
					mapEntity.put("status", "failure");
					return mapEntity;
				}
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				mapEntity.put("status", "failure");
				return mapEntity;
			}


			String openId = (String) request.getSession().getAttribute("openId");

			Hidden_Neaten hidden_Neaten = new Hidden_Neaten();

			hidden_Neaten.setProcessInstance_id(processInstanceId);

			hidden_Neaten.setGUID(guid);

			hidden_Neaten.setNeaten_name(address);

			System.out.println("address=" + address);

			hidden_Neaten.setNeaten_id(uuid.toString());

			hidden_Neaten.setPrincipal(principal);

			hidden_Neaten.setRemark(remark);

			hidden_Neaten.setNeaten_instance(neaten_instance);

			hidden_Neaten.setAmount(amount);

			hidden_Neaten.setAmountTotal(amountTotal);

			hidden_Neaten.setAuditingAmount(auditingAmount);

			hidden_Neaten.setAvailabeLength(availabeLength);

			hidden_Neaten.setWorkUnit(workUnit);

			hidden_Neaten.setCampusAdmin(openId);

			Users users = userService.getUserByOnlyOpenId(openId);

			String userName = users.getName();

			hidden_Neaten.setUserName(userName);

			hidden_Neaten.setProgress(progress);

			hidden_Neaten.setIs_repair(1);

			hidden_Neaten.setArea(area);

			hidden_Neaten.setType(type);

			DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
			Date date;

			try {
				date = fmt.parse(happenTime);
				hidden_Neaten.setHappen_time(date);
				hidden_Neaten.setDate(date);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
	        

			String check_circs = getRoomInfoHiddenItemDataByGUID(guid);

			hidden_Neaten.setCheck_circs(check_circs);

			System.out.println("=========================");
			System.out.println(hidden_Neaten.toString());

			return hiddenDAO.insertHiddenNeaten(hidden_Neaten);

		}

		return insertHiddenNeaten(guid, progress, 1, address, happenTime, principal, remark, neaten_instance, addComp,
				checkItemDate, lng, lat, type, area, amount, amountTotal, auditingAmount, availabeLength, workUnit,
				uuid.toString(), request);

	}

	@RequestMapping("/insertHiddenNeaten")
	public @ResponseBody Map insertHiddenNeaten(@RequestParam String guid, @RequestParam String progress,
			@RequestParam Integer is_repair, @RequestParam String address, @RequestParam String happenTime,
			@RequestParam String principal, @RequestParam String remark, @RequestParam String neaten_instance,
			@RequestParam String addComp, @RequestParam String checkItemDate, @RequestParam Double lng,
			@RequestParam Double lat, String type, Float area, Float amount, Float amountTotal, Float auditingAmount,
			String availabeLength, String workUnit, String imgId, HttpServletRequest request) {

		String processInstanceId = "";

		if (is_repair == 1) {

			Map mapEntity = server.selectById(guid, 1, 1, 0);

			List<RoomInfoFlowIdEntity> list = (List<RoomInfoFlowIdEntity>) mapEntity.get("rows");

			try {
				RoomInfoFlowIdEntity roomInfoFlowIdEntity = list.get(0);
				processInstanceId = roomInfoFlowIdEntity.getProcessInstanceId();
				if (roomInfoFlowIdEntity.getResult() != 1) {
					mapEntity.put("status", "failure");
					return mapEntity;
				}
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				mapEntity.put("status", "failure");
				return mapEntity;
			}
		}

		Hidden_Neaten hidden_Neaten = new Hidden_Neaten();

		UUID uuid = UUID.randomUUID();

		String openId = (String) request.getSession().getAttribute("openId");

		JSONObject jsonObject1 = null;

		Hidden_Check_Item hidden_Check_Item = new Hidden_Check_Item();

		RoomInfo_Hidden_Item roomInfo_Hidden_Item = new RoomInfo_Hidden_Item();

		String checkItem = null;

		boolean isNull = false;

		Integer item = 0;

		Map map = new HashMap<>();

		hidden_Neaten.setProcessInstance_id(processInstanceId);

		hidden_Neaten.setGUID(guid);

		hidden_Neaten.setNeaten_name(address);

		System.out.println("address=" + address);

		hidden_Neaten.setNeaten_id(uuid.toString());

		hidden_Neaten.setPrincipal(principal);

		hidden_Neaten.setRemark(remark);

		hidden_Neaten.setNeaten_instance(neaten_instance);

		hidden_Neaten.setCampusAdmin(openId);

		Users users = userService.getUserByOnlyOpenId(openId);

		String userName = users.getName();

		hidden_Neaten.setUserName(userName);

		hidden_Neaten.setProgress(progress);

		hidden_Neaten.setIs_repair(0);

		String check_circs = getRoomInfoHiddenItemDataByGUID(guid);

		hidden_Neaten.setCheck_circs(check_circs);

		try {
			jsonObject1 = JSONObject.parseObject(checkItemDate);
			hidden_Check_Item.setFire_extinguisher(jsonObject1.getInteger("fire_extinguisher"));
			hidden_Check_Item.setHigh_power(jsonObject1.getInteger("high_power"));
			hidden_Check_Item.setBlow(jsonObject1.getInteger("blow"));
			hidden_Check_Item.setLine_aging(jsonObject1.getInteger("line_aging"));
			hidden_Check_Item.setIncline(jsonObject1.getInteger("incline"));
			hidden_Check_Item.setSplit(jsonObject1.getInteger("split"));
			hidden_Check_Item.setDown(jsonObject1.getInteger("down"));
			hidden_Check_Item.setBreak_off(jsonObject1.getInteger("break_off"));
			hidden_Check_Item.setDestroy(jsonObject1.getInteger("destroy"));
			hidden_Check_Item.setInvalidation(jsonObject1.getInteger("invalidation"));
			hidden_Check_Item.setFlaw(jsonObject1.getInteger("flaw"));
			hidden_Check_Item.setCesspool(jsonObject1.getInteger("cesspool"));
			hidden_Check_Item.setCoast(jsonObject1.getInteger("coast"));
			hidden_Check_Item.setWall_up(jsonObject1.getInteger("wall_up"));
			hidden_Check_Item.setOther(jsonObject1.getString("other"));

			roomInfo_Hidden_Item.setGuid(guid);
			if (getInt(jsonObject1.getInteger("fire_extinguisher")) > 0)
				roomInfo_Hidden_Item.setFire_extinguisher(0);
			if (getInt(jsonObject1.getInteger("high_power")) > 0)
				roomInfo_Hidden_Item.setHigh_power(0);
			if (getInt(jsonObject1.getInteger("blow")) > 0)
				roomInfo_Hidden_Item.setBlow(0);
			if (getInt(jsonObject1.getInteger("line_aging")) > 0)
				roomInfo_Hidden_Item.setLine_aging(0);
			if (getInt(jsonObject1.getInteger("incline")) > 0)
				roomInfo_Hidden_Item.setIncline(0);
			if (getInt(jsonObject1.getInteger("split")) > 0)
				roomInfo_Hidden_Item.setSplit(0);
			if (getInt(jsonObject1.getInteger("down")) > 0)
				roomInfo_Hidden_Item.setDown(0);
			if (getInt(jsonObject1.getInteger("break_off")) > 0)
				roomInfo_Hidden_Item.setBreak_off(0);
			if (getInt(jsonObject1.getInteger("destroy")) > 0)
				roomInfo_Hidden_Item.setDestroy(0);
			if (getInt(jsonObject1.getInteger("invalidation")) > 0)
				roomInfo_Hidden_Item.setInvalidation(0);
			if (getInt(jsonObject1.getInteger("flaw")) > 0)
				roomInfo_Hidden_Item.setFlaw(0);
			if (getInt(jsonObject1.getInteger("cesspool")) > 0)
				roomInfo_Hidden_Item.setCesspool(0);
			if (getInt(jsonObject1.getInteger("coast")) > 0)
				roomInfo_Hidden_Item.setCoast(0);
			if (getInt(jsonObject1.getInteger("wall_up")) > 0)
				roomInfo_Hidden_Item.setWall_up(0);
			if (jsonObject1.getString("other") != null && !jsonObject1.getString("other").equals("")) {
				roomInfo_Hidden_Item.setOther("");
				roomInfo_Hidden_Item.setIs_other(0);
				hidden_Check_Item.setIs_other(1);
				roomInfo_Hidden_Item.setIs_other(0); // roomInfo_Hidden_Item要更新的整改项目应为0
			}

			isNull = jsonObject1.getInteger("fire_extinguisher") == null && jsonObject1.getInteger("high_power") == null
					&& jsonObject1.getInteger("blow") == null && jsonObject1.getInteger("line_aging") == null
					&& jsonObject1.getInteger("incline") == null && jsonObject1.getInteger("split") == null
					&& jsonObject1.getInteger("down") == null && jsonObject1.getInteger("break_off") == null
					&& jsonObject1.getInteger("destroy") == null && jsonObject1.getInteger("invalidation") == null
					&& jsonObject1.getInteger("flaw") == null && jsonObject1.getInteger("cesspool") == null
					&& jsonObject1.getInteger("coast") == null && jsonObject1.getInteger("wall_up") == null
					&& (jsonObject1.getString("other") == null || jsonObject1.getString("other").equals(""));

			item = getInt(jsonObject1.getInteger("fire_extinguisher")) + getInt(jsonObject1.getInteger("high_power"))
					+ getInt(jsonObject1.getInteger("blow")) + getInt(jsonObject1.getInteger("line_aging"))
					+ getInt(jsonObject1.getInteger("incline")) + getInt(jsonObject1.getInteger("split"))
					+ getInt(jsonObject1.getInteger("down")) + getInt(jsonObject1.getInteger("break_off"))
					+ getInt(jsonObject1.getInteger("destroy")) + getInt(jsonObject1.getInteger("invalidation"))
					+ getInt(jsonObject1.getInteger("flaw")) + getInt(jsonObject1.getInteger("cesspool"))
					+ getInt(jsonObject1.getInteger("coast")) + getInt(jsonObject1.getInteger("wall_up"));

			if (jsonObject1.getString("other") != null && !jsonObject1.getString("other").equals(""))
				item = item + 1; // other项加1

			checkItem = getItem("灭火器", hidden_Check_Item.getFire_extinguisher())
					+ getItem("大功率用电器", hidden_Check_Item.getHigh_power())
					+ getItem("易燃易爆物品", hidden_Check_Item.getBlow())
					+ getItem("线路老化", hidden_Check_Item.getLine_aging()) + getItem("倾斜", hidden_Check_Item.getIncline())
					+ getItem("开裂", hidden_Check_Item.getSplit()) + getItem("地基下沉", hidden_Check_Item.getDown())
					+ getItem("屋面脱落", hidden_Check_Item.getBreak_off())
					+ getItem("结构破坏", hidden_Check_Item.getDestroy())
					+ getItem("承重失效", hidden_Check_Item.getInvalidation()) + getItem("漏雨", hidden_Check_Item.getFlaw())
					+ getItem("化粪池问题", hidden_Check_Item.getCesspool()) + getItem("山体滑坡", hidden_Check_Item.getCoast())
					+ getItem("管道堵塞", hidden_Check_Item.getWall_up());

			if (jsonObject1.getString("other") != null && !jsonObject1.getString("other").equals(""))
				checkItem = jsonObject1.getString("other") + " , " + checkItem; // other项加1

			if (checkItem.length() > 2) {
				checkItem = checkItem.substring(0, checkItem.length() - 2);
				hidden_Neaten.setNeaten_item(checkItem);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		System.out.println("other=" + jsonObject1.getString("other"));

		System.out.println("isNull=" + isNull);

		System.out.println("item=" + item);

		System.out.println("checkitme=" + checkItem);

		if (progress != null && is_repair == 1 && progress.equals("整改完成")) {

			if (auditingAmount == null || type == null || type.equals("") || amount == null || amountTotal == null
					|| area == null || availabeLength == null || availabeLength.equals("") || workUnit == null
					|| workUnit.equals("")) {

				System.out.println("auditingAmount=" + auditingAmount);
				System.out.println("type=" + type);
				System.out.println("amount=" + amount);
				System.out.println("amountTotal=" + amountTotal);
				System.out.println("area=" + area);
				System.out.println("availabeLength=" + availabeLength);
				System.out.println("workUnit=" + workUnit);

				map.put("status", "failure");
				map.put("neaten_id", uuid.toString());

				return map;
			}

			hidden_Neaten.setIs_repair(is_repair);
			hidden_Neaten.setRoomGUID(guid);
			hidden_Neaten.setType(type);
			hidden_Neaten.setAmount(amount);
			hidden_Neaten.setAmountTotal(amountTotal);
			hidden_Neaten.setAuditingAmount(auditingAmount);
			hidden_Neaten.setArea(area);
			hidden_Neaten.setAvailabeLength(availabeLength);
			hidden_Neaten.setWorkUnit(workUnit);
		}

		if (happenTime != null && !happenTime.equals("")) {
			try {
				DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
				Date date;
				date = fmt.parse(happenTime);
				hidden_Neaten.setHappen_time(date);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		Date date = new Date();
		hidden_Neaten.setUpdate_time(date);
		hidden_Neaten.setDate(date);
		hidden_Neaten.setTerminal("Wechat");

		int i = hiddenDAO.insertHiddenNeaten(hidden_Neaten, roomInfo_Hidden_Item, hidden_Check_Item, item);

		System.out.println("iii=" + i);

		if (i > 0) {
			JSONObject jsonObject = JSONObject.parseObject(addComp);

			String province = jsonObject.getString("province");
			String city = jsonObject.getString("city");
			String district = jsonObject.getString("district");
			String street = jsonObject.getString("street");
			String streetNumber = jsonObject.getString("streetNumber");

			Position position = new Position();

			position.setNeaten_id(uuid.toString());
			position.setLat(lat);
			position.setLng(lng);

			position.setProvince(province);
			position.setCity(city);
			position.setDistrict(district);
			position.setStreet(streetNumber);
			position.setStreet_number(streetNumber);

			assetsDAO.updatePositionByNeaten(position);
		}

		if (i > 0) {
			map.put("status", "succeed");
		} else {
			map.put("status", "failure");
		}

		if (imgId != null && !imgId.equals("")) {
			Hidden_Neaten_Date hidden_Neaten_Date = new Hidden_Neaten_Date();
			hidden_Neaten_Date.setNeaten_id(uuid.toString());
			String[] where = { "neaten_id=", imgId };
			hidden_Neaten_Date.setWhere(where);
			hiddenDAO.updateHidden_Neaten_Date(hidden_Neaten_Date);
		}

		map.put("neaten_id", uuid.toString());

		return map;

	}

	@RequestMapping("/updateHiddenNeaten")
	public @ResponseBody Map updateHiddenNeaten(@RequestParam String guid, @RequestParam String neaten_id,
			@RequestParam String progress, @RequestParam String happenTime, @RequestParam String principal,
			@RequestParam String remark, @RequestParam String neaten_instance, @RequestParam String addComp,
			@RequestParam Double lng, @RequestParam Double lat, HttpServletRequest request) {

		Hidden_Neaten hidden_Neaten = new Hidden_Neaten();

		String openId = (String) request.getSession().getAttribute("openId");

		hidden_Neaten.setNeaten_id(neaten_id);

		hidden_Neaten.setPrincipal(principal);

		hidden_Neaten.setRemark(remark);

		hidden_Neaten.setNeaten_instance(neaten_instance);

		hidden_Neaten.setCampusAdmin(openId);

		if (happenTime != null && !happenTime.equals("")) {
			try {
				DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
				Date date;
				date = fmt.parse(happenTime);
				hidden_Neaten.setHappen_time(date);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		Date date = new Date();
		hidden_Neaten.setUpdate_time(date);
		hidden_Neaten.setDate(date);
		hidden_Neaten.setTerminal("Wechat");

		String[] where = { "[Hidden_Neaten].neaten_id=", neaten_id };

		hidden_Neaten.setWhere(where);

		int i = hiddenDAO.updateHiddenNeaten(hidden_Neaten);

		JSONObject jsonObject = JSONObject.parseObject(addComp);

		String province = jsonObject.getString("province");
		String city = jsonObject.getString("city");
		String district = jsonObject.getString("district");
		String street = jsonObject.getString("street");
		String streetNumber = jsonObject.getString("streetNumber");

		Position position = new Position();

		position.setNeaten_id(neaten_id);
		position.setLat(lat);
		position.setLng(lng);

		position.setProvince(province);
		position.setCity(city);
		position.setDistrict(district);
		position.setStreet(streetNumber);
		position.setStreet_number(streetNumber);

		assetsDAO.updatePositionByNeaten(position);

		Map map = new HashMap<>();

		map.put("status", i);
		map.put("neaten_id", neaten_id);

		return map;

	}

	@RequestMapping("/hiddenStat")
	public @ResponseBody Map hiddenStat() {

		int inHidden = assetsDAO.findInHidden();

		int notHidden = assetsDAO.findNotHidden();

		int successHidden = assetsDAO.findSuccessHidden();

		Map search = new HashMap<>();

		search.put(Singleton.ROOMDATABASE + ".[dbo].[RoomInfo].State=", "已出租");

		int alreadyHire = roomInfoDao.getRoomInfoCount(search);

		search = new HashMap<>();

		search.put(Singleton.ROOMDATABASE + ".[dbo].[RoomInfo].State=", "空置");

		int notHire = roomInfoDao.getRoomInfoCount(search);

		search = new HashMap<>();

		search.put(Singleton.ROOMDATABASE + ".[dbo].[RoomInfo].State=", "不可出租");

		int catnotHire = roomInfoDao.getRoomInfoCount(search);

		int allAsset = alreadyHire + notHire + catnotHire;

		int allAssetsHidden = assetsDAO.findAllAssetsHidden();

		Map map = new HashMap<>();

		map.put("inHidden", inHidden);

		map.put("notHidden", notHidden);

		map.put("successHidden", successHidden);

		map.put("allAsset", allAsset);

		map.put("allAssetsHidden", allAssetsHidden);

		return map;

	}

	@RequestMapping("/findHiddenByYear")
	public @ResponseBody List findHiddenByYear() {

		List list = assetsDAO.findHiddenByYear();

		return list;

	}

	@RequestMapping("/findHiddenByMonthOfYear")
	public @ResponseBody List findHiddenByMonthOfYear(@RequestParam String year) {

		List list = assetsDAO.findHiddenByMonthOfYear(year);

		return list;

	}

	@RequestMapping("/getAllAssetByHidden_GUID")
	public @ResponseBody Integer getAllAssetByHidden_GUID(@RequestParam String guid) {

		int count = hiddenDAO.getAllAssetByHidden_GUID(guid);

		return count;

	}

	@RequestMapping("/getRoomInfoHiddenItemByGUID")
	public @ResponseBody RoomInfo_Hidden_Item getRoomInfoHiddenItemByGUID(@RequestParam String guid) {

		Map search = new HashMap<>();

		search.put("[RoomInfo_Hidden_Item].guid = ", guid);

		RoomInfo_Hidden_Item roomInfo_Hidden_Item = null;

		try {
			roomInfo_Hidden_Item = (RoomInfo_Hidden_Item) hiddenDAO.selectRoomInfo_Hidden_Item(1, 0, "", "", search)
					.get(0);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		System.out.println("roomInfo_Hidden_Item==================" + roomInfo_Hidden_Item.toString());
		return roomInfo_Hidden_Item;
	}

	@RequestMapping("/getRoomInfoHiddenItemDataByGUID")
	public @ResponseBody String getRoomInfoHiddenItemDataByGUID(@RequestParam String guid) {

		Map search = new HashMap<>();

		search.put("[RoomInfo_Hidden_Item].guid = ", guid);

		String hiddenCircs = "";

		try {
			RoomInfo_Hidden_Item hidden_Check_Item = (RoomInfo_Hidden_Item) hiddenDAO
					.selectRoomInfo_Hidden_Item(1, 0, "", "", search).get(0);

			hiddenCircs = getItem2("灭火器", hidden_Check_Item.getFire_extinguisher())
					+ getItem2("大功率用电器", hidden_Check_Item.getHigh_power())
					+ getItem2("易燃易爆物品", hidden_Check_Item.getBlow())
					+ getItem2("线路老化", hidden_Check_Item.getLine_aging())
					+ getItem2("倾斜", hidden_Check_Item.getIncline()) + getItem2("开裂", hidden_Check_Item.getSplit())
					+ getItem2("地基下沉", hidden_Check_Item.getDown()) + getItem2("屋面脱落", hidden_Check_Item.getBreak_off())
					+ getItem2("结构破坏", hidden_Check_Item.getDestroy())
					+ getItem2("承重失效", hidden_Check_Item.getInvalidation())
					+ getItem2("漏雨", hidden_Check_Item.getFlaw()) + getItem2("化粪池问题", hidden_Check_Item.getCesspool())
					+ getItem2("山体滑坡", hidden_Check_Item.getCoast()) + getItem2("管道堵塞", hidden_Check_Item.getWall_up());

			String other = hidden_Check_Item.getOther();

			if (other != null && !other.equals("")) {
				hiddenCircs = other + " , " + hiddenCircs;
			}

			if (hiddenCircs.length() > 2)
				hiddenCircs = hiddenCircs.substring(0, hiddenCircs.length() - 2);

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		return hiddenCircs;
	}

	String getItem2(String name, Integer value) {
		if (value != null) {
			if (value == 0) {
				return "";
			} else if (value == 1) {
				return name + "异常" + " , ";
			} else {
				return "";
			}
		} else {
			return "";
		}
	}

}
