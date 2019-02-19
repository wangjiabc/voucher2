package com.voucher.manage.daoImpl;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.URLEncoder;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.context.ApplicationContext;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.alibaba.fastjson.JSONObject;
import com.rmi.server.Server;
import com.voucher.manage.dao.HiddenDAO;
import com.voucher.manage.dao.RoomInfoDao;
import com.voucher.manage.daoModel.RoomInfo;
import com.voucher.manage.daoModel.Assets.Hidden_Check;
import com.voucher.manage.daoModel.Assets.Hidden_Check_Date;
import com.voucher.manage.daoModel.Assets.Hidden_Check_Item;
import com.voucher.manage.daoModel.Assets.Hidden_Neaten;
import com.voucher.manage.daoModel.Assets.Hidden_Neaten_Date;
import com.voucher.manage.daoModel.Assets.Hidden_User;
import com.voucher.manage.daoModel.Assets.Neaten_Check;
import com.voucher.manage.daoModel.Assets.Position;
import com.voucher.manage.daoModel.Assets.RoomInfo_Hidden_Item;
import com.voucher.manage.daoModel.Assets.WeiXin_User;
import com.voucher.manage.daoModelJoin.RoomInfo_Position;
import com.voucher.manage.daoModelJoin.Assets.Hidden_Check_Item_Join;
import com.voucher.manage.daoModelJoin.Assets.Hidden_Check_Join;
import com.voucher.manage.daoModelJoin.Assets.Hidden_Neaten_Join;
import com.voucher.manage.daoModelJoin.Assets.Position_Check_Join;
import com.voucher.manage.daoRowMapper.RowMappers;
import com.voucher.manage.daoRowMapper.RowMappersJoin;
import com.voucher.manage.daoSQL.DeleteExe;
import com.voucher.manage.daoSQL.InsertExe;
import com.voucher.manage.daoSQL.SelectExe;
import com.voucher.manage.daoSQL.SelectJoinExe;
import com.voucher.manage.daoSQL.SelectSQL;
import com.voucher.manage.daoSQL.SelectSqlJoinExe;
import com.voucher.manage.daoSQL.UpdateExe;
import com.voucher.manage.file.AbstractFileUpload;
import com.voucher.manage.model.Users;
import com.voucher.manage.singleton.Singleton;
import com.voucher.manage.tools.FileConvect;
import com.voucher.manage.tools.MyTestUtil;
import com.voucher.manage.tools.TransMapToString;
import com.voucher.sqlserver.context.Connect;
import com.voucher.sqlserver.context.ConnectRMI;
import com.voucher.weixin.controller.WechatSendMessageController;

public class HiddenDAOImpl extends JdbcDaoSupport implements HiddenDAO{

	Server server=new ConnectRMI().get();
	
	@Override
	public Integer InsertIntoHiddenData(String GUID,String NAME,String TYPE,String uri) {
		// TODO Auto-generated method stub
		Hidden_Check_Date hidden_Data=new Hidden_Check_Date();
		Date date=new Date();
		
		hidden_Data.setCheck_id(GUID);
		hidden_Data.setNAME(NAME);
		hidden_Data.setTYPE(TYPE);
		hidden_Data.setURI(uri);
        hidden_Data.setDate(date);
        hidden_Data.setFileBelong("隐患图片");
        
        String[] where={"[GUID]=",GUID,
				"[FileBelong]=","隐患图片"};
		
        hidden_Data.setWhere(where);
        
        int count=(int) SelectExe.getCount(this.getJdbcTemplate(), hidden_Data).get("");
		
		hidden_Data.setFileIndex(count+1);
        
		return InsertExe.get(this.getJdbcTemplate(), hidden_Data);
	}
	
	
	@Override
	public Integer InsertIntoHiddenCheckData(String Check_id, String NAME, String TYPE, String uri) {
		// TODO Auto-generated method stub
		Hidden_Check_Date hidden_Check_Date=new Hidden_Check_Date();
		Date date=new Date();
		
		hidden_Check_Date.setCheck_id(Check_id);
		hidden_Check_Date.setNAME(NAME);
		hidden_Check_Date.setTYPE(TYPE);
		hidden_Check_Date.setURI(uri);
		hidden_Check_Date.setDate(date);
		
		hidden_Check_Date.setFileBelong("检查图片");
        
        String[] where={"[check_id]=",Check_id,
				"[FileBelong]=","检查图片"};
		
        hidden_Check_Date.setWhere(where);
        
        int count=(int) SelectExe.getCount(this.getJdbcTemplate(), hidden_Check_Date).get("");
		
		hidden_Check_Date.setFileIndex(count+1);
		
		return InsertExe.get(this.getJdbcTemplate(), hidden_Check_Date);
	}


	@Override
	public Integer InsertIntoHiddenNeatenData(String Neaten_id, String NAME, String TYPE, String uri) {
		// TODO Auto-generated method stub
		Hidden_Neaten_Date hidden_Neaten_Date=new Hidden_Neaten_Date();
		Date date=new Date();
		
		hidden_Neaten_Date.setNeaten_id(Neaten_id);
		hidden_Neaten_Date.setNAME(NAME);
		hidden_Neaten_Date.setTYPE(TYPE);
		hidden_Neaten_Date.setURI(uri);
		hidden_Neaten_Date.setDate(date);
		
		hidden_Neaten_Date.setFileBelong("整改图片");
        
        String[] where={"[neaten_id]=",Neaten_id,
				"[FileBelong]=","整改图片"};
		
        hidden_Neaten_Date.setWhere(where);
        
        int count=(int) SelectExe.getCount(this.getJdbcTemplate(), hidden_Neaten_Date).get("");
		
        hidden_Neaten_Date.setFileIndex(count+1);
		
		return InsertExe.get(this.getJdbcTemplate(), hidden_Neaten_Date);
	}
	
	@Override
	public Map<String, Object> selectAllHiddenDate(String GUID) {
		
		String pathRoot = System.getProperty("user.home");
		
		String filePath=pathRoot+Singleton.filePath;
		
		List<String> names=new ArrayList<String>();
		List<String> types=new ArrayList<String>();
		List<byte[]> fileBytes=new ArrayList<byte[]>();
		
		// TODO Auto-generated method stub
		Hidden_Check_Date hidden_Data=new Hidden_Check_Date();
		hidden_Data.setLimit(1000);
		hidden_Data.setOffset(0);
		hidden_Data.setNotIn("GUID");
		String[] where={"GUID =",GUID};
		
		hidden_Data.setWhere(where);
		
		List<Hidden_Check_Date> hidden_Datas=SelectExe.get(this.getJdbcTemplate(), hidden_Data);
		
		Iterator<Hidden_Check_Date> iterator=hidden_Datas.iterator();
		
		while(iterator.hasNext()){
			
			Hidden_Check_Date hidden_Data2=iterator.next();
			
			File file=new File(filePath+"\\"+hidden_Data2.getURI());
			
			byte[] fileByte=FileConvect.fileToByte(file);
			
			names.add(hidden_Data2.getNAME());
			types.add(hidden_Data2.getTYPE());
			fileBytes.add(fileByte);
			
		}
		
		Map<String, Object> map=new HashMap<>();
		
		map.put("names", names);
		map.put("types", types);
		map.put("fileBytes", fileBytes);
		
		return map;
	}
	
	
	@Override
	public Map<String, Object> selectAllHiddenCheckDate(String check_id) {
		// TODO Auto-generated method stub
        String pathRoot = System.getProperty("user.home");
		
		String filePath=pathRoot+Singleton.filePath;
		
		List<String> names=new ArrayList<String>();
		List<String> types=new ArrayList<String>();
		List<byte[]> fileBytes=new ArrayList<byte[]>();
		
		// TODO Auto-generated method stub
		Hidden_Check_Date hidden_Check_Date=new Hidden_Check_Date();
		hidden_Check_Date.setLimit(1000);
		hidden_Check_Date.setOffset(0);
		hidden_Check_Date.setNotIn("id");
		String[] where={"check_id =",check_id};
		
		hidden_Check_Date.setWhere(where);
		
		List<Hidden_Check_Date> hidden_Check_Dates=SelectExe.get(this.getJdbcTemplate(), hidden_Check_Date);
		
		Iterator<Hidden_Check_Date> iterator=hidden_Check_Dates.iterator();
		
		while(iterator.hasNext()){
			
			Hidden_Check_Date hidden_Check_Date2=iterator.next();
			
			File file=new File(filePath+"\\"+hidden_Check_Date2.getURI());
			
			byte[] fileByte=FileConvect.fileToByte(file);
			
			names.add(hidden_Check_Date2.getNAME());
			types.add(hidden_Check_Date2.getTYPE());
			fileBytes.add(fileByte);
			
		}
		
		Map<String, Object> map=new HashMap<>();
		
		map.put("names", names);
		map.put("types", types);
		map.put("fileBytes", fileBytes);
		
		return map;
	}


	@Override
	public Map<String, Object> selectAllHiddenNeatenDate(String neaten_id) {
		// TODO Auto-generated method stub
        String pathRoot = System.getProperty("user.home");
		
		String filePath=pathRoot+Singleton.filePath;
		
		List<String> names=new ArrayList<String>();
		List<String> types=new ArrayList<String>();
		List<byte[]> fileBytes=new ArrayList<byte[]>();
		
		// TODO Auto-generated method stub
		Hidden_Neaten_Date hidden_Neaten_Date=new Hidden_Neaten_Date();
		hidden_Neaten_Date.setLimit(1000);
		hidden_Neaten_Date.setOffset(0);
		hidden_Neaten_Date.setNotIn("id");
		String[] where={"neaten_id =",neaten_id};
		
		hidden_Neaten_Date.setWhere(where);
		
		List<Hidden_Neaten_Date> hidden_Neaten_Dates=SelectExe.get(this.getJdbcTemplate(), hidden_Neaten_Date);
		
		Iterator<Hidden_Neaten_Date> iterator=hidden_Neaten_Dates.iterator();
		
		while(iterator.hasNext()){
			
			Hidden_Neaten_Date hidden_Neaten_Date2=iterator.next();
			
			File file=new File(filePath+"\\"+hidden_Neaten_Date2.getURI());
			
			byte[] fileByte=FileConvect.fileToByte(file);
			
			names.add(hidden_Neaten_Date2.getNAME());
			types.add(hidden_Neaten_Date2.getTYPE());
			fileBytes.add(fileByte);
			
		}
		
		Map<String, Object> map=new HashMap<>();
		
		map.put("names", names);
		map.put("types", types);
		map.put("fileBytes", fileBytes);
		
		return map;
	}
	
	@Override
	public Map<String, Object> selectAllHidden(Integer limit, Integer offset, String sort, String order, Map<String, String> search) {
		// TODO Auto-generated method stub
        Hidden_Check hidden=new Hidden_Check();
		
		hidden.setLimit(limit);
		hidden.setOffset(offset);
		hidden.setSort(sort);
		hidden.setOrder(order);
		hidden.setNotIn("id");
		
		if(!search.isEmpty()){
			search.put("[Hidden_Check].exist =", "1");
		    String[] where=TransMapToString.get(search);
		    hidden.setWhere(where);
		}else{
			String[] where={"[Hidden_Check].exist =", "1"};
			hidden.setWhere(where);
		}
		
		Map map=new HashMap<String, Object>();
		
		List list=SelectExe.get(this.getJdbcTemplate(), hidden);
		MyTestUtil.print(list);
		map.put("rows", list);
		
		Map countMap=SelectExe.getCount(this.getJdbcTemplate(), hidden);
		
		map.put("total", countMap.get(""));
		
		return map;
	}


	@Override
	public Map<String, Object> selectHiddenUser(String campusAdmin) {
		// TODO Auto-generated method stub
		Hidden_User hidden_User=new Hidden_User();
		hidden_User.setOffset(0);
		hidden_User.setLimit(10);
		hidden_User.setNotIn("id");
		
		String[] where={"[Hidden_User].campusAdmin =",campusAdmin};
		hidden_User.setWhere(where);
		
		List list=SelectExe.get(this.getJdbcTemplate(), hidden_User);
		
		Map map=new HashMap<>();
		
		try{
		  Hidden_User hidden_User2=(Hidden_User) list.get(0);
		  map.put("state", 1);
		  map.put("row", hidden_User2);
		  return map;
		}catch (Exception e) {
			// TODO: handle exception
		  e.printStackTrace();	
		  map.put("state", 0);
		  return map;
		}
	}
	
	@Override
	public Map<String, Object> selectAllHiddenUser(Integer limit, Integer offset, String sort,
			String order,Map<String, String> search) {
		// TODO Auto-generated method stub
		Hidden_User hidden_User=new Hidden_User();
		hidden_User.setOffset(offset);
		hidden_User.setLimit(limit);
		hidden_User.setNotIn("id");
		
		try{
			if(!search.isEmpty()){
				search.put("[Hidden_User].purview !=", "0");
				String[] where=TransMapToString.get(search);
				hidden_User.setWhere(where);
			}else{
				String[] where={"[Hidden_User].purview !=", "0"};
				hidden_User.setWhere(where);
			}
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		Map<String, Object> map=new HashMap<>();
		
		List list=SelectExe.get(this.getJdbcTemplate(), hidden_User);
		
		MyTestUtil.print(list);
		
		map.put("rows", list);
		
		Map countMap=SelectExe.getCount(this.getJdbcTemplate(), hidden_User);
		
		map.put("total", countMap.get(""));
		
		return map;
		
	}

	
	@Override
	public Integer updateUserPassword(Hidden_User hidden_User,String OldPw) {
		// TODO Auto-generated method stub
		hidden_User.setNotIn("id");
		hidden_User.setLimit(10);
		hidden_User.setOffset(0);
		
		String[] where={"[Hidden_User].campusAdmin=",hidden_User.getCampusAdmin()};
		hidden_User.setWhere(where);
		List list=SelectExe.get(this.getJdbcTemplate(), hidden_User);
		
		try{
			Hidden_User hidden_User2=(Hidden_User) list.get(0);
			if(!OldPw.equals(hidden_User2.getPassword())){
				System.out.println("npw="+hidden_User.getPassword());
				System.out.println("opw="+hidden_User2.getPassword());
				return 3;
			}else{
				return UpdateExe.get(this.getJdbcTemplate(), hidden_User);
			}
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return 2;
		}
	}
	
	@Override
	public Map<String, Object> selectAllHiddenCheck(Integer limit, Integer offset, String sort,
			String order,String address,Map<String, String> search) {
		// TODO Auto-generated method stub
		
		Map map=new HashMap<String, Object>();
		List<Hidden_Check_Join> list;
		Map countMap;
		
		Hidden_Check hidden_Check=new Hidden_Check();
		search.put("[Hidden_Check].exist =", "1");
		
		hidden_Check.setOffset(offset);
		hidden_Check.setLimit(limit);
		hidden_Check.setSort(sort);
		hidden_Check.setOrder(order);
		hidden_Check.setNotIn("id");
		
		RoomInfo roomInfo=new RoomInfo();
		
		roomInfo.setOffset(offset);
		roomInfo.setLimit(limit);
		roomInfo.setSort(sort);
		roomInfo.setOrder(order);
		roomInfo.setNotIn("id");
		
		Position position=new Position();
		
		position.setLimit(limit);
		position.setOffset(offset);
		position.setSort(sort);
		position.setOrder(order);
		position.setNotIn("id");
		
		WeiXin_User weiXin_User=new WeiXin_User();
		
		weiXin_User.setLimit(limit);
		weiXin_User.setOffset(offset);
		weiXin_User.setSort(sort);
		weiXin_User.setOrder(order);
		weiXin_User.setNotIn("id");
		
		if(!search.isEmpty()){
		    String[] where=TransMapToString.get(search);
		    hidden_Check.setWhere(where);
		    roomInfo.setWhere(where);
		    position.setWhere(where);
		    weiXin_User.setWhere(where);
		}

		Object[] objects={hidden_Check,roomInfo,position,weiXin_User};
		
		String[] join={"guid","check_id","campusAdmin"};
		
		Hidden_Check_Join hidden_Check_Join=new Hidden_Check_Join();
		
		list=SelectJoinExe.get(this.getJdbcTemplate(), objects, hidden_Check_Join, join);
		countMap=SelectJoinExe.getCount(this.getJdbcTemplate(), objects, join);

		
		map.put("rows", list);
		System.out.println("checkjoinlist=");
		//MyTestUtil.print(list);
		

		map.put("total", countMap.get(""));
		
		return map;
	}

	//按时间查询没有巡查的资产
	@Override
	public Map selectNotHiddenCheckAssetByDate(int limit, int offset,String search,
			String startTime,String endTime) {
		
		// TODO Auto-generated method stub
			String sql0="SELECT TOP "+limit+"* from "+
							"(select ROW_NUMBER() OVER (ORDER BY "+Singleton.ROOMDATABASE+".[dbo].[RoomInfo].num) AS rows,"+
							"[Position].province,"+
							"[Position].city,"+
							"[Position].district,"+
							"[Position].street,"+
							"[Position].street_number,"+
							"[Position].lng,"+
							"[Position].lat,"+
							"[Position].date,"+
							Singleton.ROOMDATABASE+".[dbo].[RoomInfo].GUID,"+
						    Singleton.ROOMDATABASE+".[dbo].[RoomInfo].Num,"+
						    Singleton.ROOMDATABASE+".[dbo].[RoomInfo].OriginalNum,"+
						    Singleton.ROOMDATABASE+".[dbo].[RoomInfo].Address,"+
						    Singleton.ROOMDATABASE+".[dbo].[RoomInfo].OriginalAddress,"+
						    Singleton.ROOMDATABASE+".[dbo].[RoomInfo].Region,"+
						    Singleton.ROOMDATABASE+".[dbo].[RoomInfo].Segment,"+
						    Singleton.ROOMDATABASE+".[dbo].[RoomInfo].ManageRegion,"+
						    Singleton.ROOMDATABASE+".[dbo].[RoomInfo].RoomProperty,"+
						    Singleton.ROOMDATABASE+".[dbo].[RoomInfo].Useful,"+
						    Singleton.ROOMDATABASE+".[dbo].[RoomInfo].Floor,"+
						    Singleton.ROOMDATABASE+".[dbo].[RoomInfo].State,"+
						    Singleton.ROOMDATABASE+".[dbo].[RoomInfo].Structure,"+
						    Singleton.ROOMDATABASE+".[dbo].[RoomInfo].BuildArea,"+
						    Singleton.ROOMDATABASE+".[dbo].[RoomInfo].RoomType,"+
						    Singleton.ROOMDATABASE+".[dbo].[RoomInfo].IsCity,"+
						    Singleton.ROOMDATABASE+".[dbo].[RoomInfo].Manager,"+
						    Singleton.ROOMDATABASE+".[dbo].[RoomInfo].ManagerPhone,"+
						    Singleton.ROOMDATABASE+".[dbo].[RoomInfo].IsStreet,"+
						    Singleton.ROOMDATABASE+".[dbo].[RoomInfo].FitMent,"+
						    Singleton.ROOMDATABASE+".[dbo].[RoomInfo].BeFrom,"+
						    Singleton.ROOMDATABASE+".[dbo].[RoomInfo].InDate,"+
						    Singleton.ROOMDATABASE+".[dbo].[RoomInfo].PropertyRightNo,"+
						    Singleton.ROOMDATABASE+".[dbo].[RoomInfo].PropertyRightArea,"+
						    Singleton.ROOMDATABASE+".[dbo].[RoomInfo].DesignUseful,"+
						    Singleton.ROOMDATABASE+".[dbo].[RoomInfo].BuildYear,"+
						    Singleton.ROOMDATABASE+".[dbo].[RoomInfo].PropertyRightUnit,"+
						    Singleton.ROOMDATABASE+".[dbo].[RoomInfo].RealPropertyRightUnit,"+
						    Singleton.ROOMDATABASE+".[dbo].[RoomInfo].PropertyCardUnit "+				   
							"FROM "+Singleton.ROOMDATABASE+".[dbo].[RoomInfo] "+
						    "left join (SELECT "+Singleton.ROOMDATABASE+".[dbo].[RoomInfo].GUID,COUNT(*) as c "+
						    "FROM "+Singleton.ROOMDATABASE+".[dbo].[RoomInfo] left join Hidden_Check on RoomInfo.GUID=Hidden_Check.guid "+ 
						    "where convert(varchar(11),"+"[Hidden_Check].date ,120 )>'"+startTime+"' "+
							"and convert(varchar(11),"+"[Hidden_Check].date ,120 )<='"+endTime+"' "+
						    "and [Hidden_Check].exist=1 "+
						    "group by "+Singleton.ROOMDATABASE+".[dbo].[RoomInfo].GUID ) t1 "+
						    "on t1.GUID="+Singleton.ROOMDATABASE+".[dbo].[RoomInfo].GUID " +
							"left join  [Position] "+
							"on "+Singleton.ROOMDATABASE+".[dbo].[RoomInfo].GUID = [Position].GUID "+
							"WHERE c is null "+
							"AND ([Position].lng is not null AND [Position].lat is not null "+
							"AND ([RoomInfo].State = '已出租' or [RoomInfo].State = '不可出租' or [RoomInfo].State = '空置' ) ";
							
				
				String sql;
				
				String sql2="SELECT count(*) "+				   
						"FROM "+Singleton.ROOMDATABASE+".[dbo].[RoomInfo] "+						
						"left join (SELECT TOP 1000 "+Singleton.ROOMDATABASE+".[dbo].[RoomInfo].GUID,COUNT(*) as c "+
						"FROM "+Singleton.ROOMDATABASE+".[dbo].[RoomInfo] left join Hidden_Check on RoomInfo.GUID=Hidden_Check.guid "+ 
						"where convert(varchar(11),"+"[dbo].[Hidden_Check].date ,120 )>'"+startTime+"' "+
					    "and convert(varchar(11),"+"[dbo].[Hidden_Check].date ,120 )<='"+endTime+"' "+
					    "and [Hidden_Check].exist=1 "+
						"group by "+Singleton.ROOMDATABASE+".[dbo].[RoomInfo].GUID ) t1 "+
						"on t1.GUID="+Singleton.ROOMDATABASE+".[dbo].[RoomInfo].GUID " +
						"left join  [Position] "+
						"on "+Singleton.ROOMDATABASE+".[dbo].[RoomInfo].GUID = [Position].GUID "+
						"WHERE c is null "+
						"AND ([Position].lng is not null AND [Position].lat is not null "+
						"AND ([RoomInfo].State = '已出租' or [RoomInfo].State = '不可出租' or [RoomInfo].State = '空置' ) ";
				
				if(search.equals("")){
					sql=sql0+")) as w1 where rows>"+offset;
					sql2=sql2+")";
				}else{
					sql=sql0+" AND ("+Singleton.ROOMDATABASE+".[dbo].[RoomInfo].Address like '%"+search+"%' "
							+" OR "+Singleton.ROOMDATABASE+".[dbo].[RoomInfo].Num like '%"+search+"%' )"+") "+
							"as w1 where rows>"+offset;
					sql2=sql2+") AND ("+Singleton.ROOMDATABASE+".[dbo].[RoomInfo].Address like '%"+search+"%' "
							+" OR "+Singleton.ROOMDATABASE+".[dbo].[RoomInfo].Num like '%"+search+"%' )";
				}
				
				System.out.println("sql="+sql);
				
				RoomInfo_Position roomInfo_Position=new RoomInfo_Position();
				
				Position position=new Position();		
				
				RoomInfo roomInfo=new RoomInfo();
				
				Object[] objects={roomInfo,position};
				
				Map map=new HashMap<>();
				
				try{
					List list=SelectSqlJoinExe.get(this.getJdbcTemplate(), sql, objects,roomInfo_Position);
					int total=(int) SelectSqlJoinExe.getCount(this.getJdbcTemplate(), sql2, objects).get("");
					map.put("rows", list);
					map.put("total", total);
				}catch (Exception e) {
					// TODO: handle exception
				}

				return map;
	}
	
	//安全巡查位置
	@Override
	public Map<String, Object> selectAllHiddenCheckPosition(Integer limit, Integer offset, String sort,
			String order,Map<String, String> search){
		Map map=new HashMap<String, Object>();
		List<Hidden_Check_Join> list;
		Map countMap;
		
		Hidden_Check hidden_Check=new Hidden_Check();
		search.put("[Hidden_Check].exist =", "1");
		
		hidden_Check.setOffset(offset);
		hidden_Check.setLimit(limit);
		hidden_Check.setSort(sort);
		hidden_Check.setOrder(order);
		hidden_Check.setNotIn("id");
		
		Position position=new Position();
		
		position.setLimit(limit);
		position.setOffset(offset);
		position.setSort(sort);
		position.setOrder(order);
		position.setNotIn("id");
		
		WeiXin_User weiXin_User=new WeiXin_User();
		
		weiXin_User.setLimit(limit);
		weiXin_User.setOffset(offset);
		weiXin_User.setSort(sort);
		weiXin_User.setOrder(order);
		weiXin_User.setNotIn("id");
		
		if(!search.isEmpty()){
		    String[] where=TransMapToString.get(search);
		    hidden_Check.setWhere(where);
		    position.setWhere(where);
		    weiXin_User.setWhere(where);
		}
		
		Object[] objects={hidden_Check,position,weiXin_User};
		
		String[] join={"check_id","campusAdmin"};
	
		Hidden_Check_Join hidden_Check_Join=new Hidden_Check_Join();
	
		list=SelectJoinExe.get(this.getJdbcTemplate(), objects, position, join);
	
		countMap=SelectJoinExe.getCount(this.getJdbcTemplate(), objects, join);
		
		map.put("rows", list);
		System.out.println("checkjoinlist=");
		//MyTestUtil.print(list);
		

		map.put("total", countMap.get(""));
		
		return map;
	}
	

	@Override
	public Integer insertHiddenCheck(Hidden_Check hidden_Check, Hidden_Check_Item hidden_Check_Item,
			RoomInfo_Hidden_Item roomInfo_Hidden_Item, RoomInfoDao roomInfoDao) {
		// TODO Auto-generated method stub
		String check_name=hidden_Check.getCheck_name();
		
		int i=InsertExe.get(this.getJdbcTemplate(), hidden_Check);
		
		if (i > 0) {
			i = insertIntoHidden_Check_Item(hidden_Check_Item);
		} else {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		}

		if (i > 0) {
			i = updateRoomInfo_Hidden_Item(roomInfo_Hidden_Item);
		} else {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		}
		
		if(i > 0){

			RoomInfo roomInfo = new RoomInfo();
			
			if (check_name != null && check_name.equals("异常")) {
								
				// 更新资产隐患字段
				Map search = new HashMap<>();

				search.put("[RoomInfo_Hidden_Item].guid = ", hidden_Check.getGUID());
				
				try{
					
					RoomInfo_Hidden_Item roomInfo_Hidden_Item2=(RoomInfo_Hidden_Item)selectRoomInfo_Hidden_Item(1, 0, "", "", search).get(0);
					
					int isHidden=getInt(roomInfo_Hidden_Item2.getFire_extinguisher())+
								getInt(roomInfo_Hidden_Item2.getHigh_power())+
								getInt(roomInfo_Hidden_Item2.getBlow())+
								getInt(roomInfo_Hidden_Item2.getLine_aging())+
								getInt(roomInfo_Hidden_Item2.getIncline())+
								getInt(roomInfo_Hidden_Item2.getSplit())+
								getInt(roomInfo_Hidden_Item2.getDown())+
								getInt(roomInfo_Hidden_Item2.getBreak_off())+
								getInt(roomInfo_Hidden_Item2.getDestroy())+
								getInt(roomInfo_Hidden_Item2.getInvalidation())+
								getInt(roomInfo_Hidden_Item2.getFlaw())+
								getInt(roomInfo_Hidden_Item2.getCesspool())+
								getInt(roomInfo_Hidden_Item2.getCoast())+
								getInt(roomInfo_Hidden_Item2.getWall_up())+
								getInt(roomInfo_Hidden_Item2.getIs_other());
					
					System.out.println("isHidden="+isHidden);
					
					roomInfo.setIsHidden(isHidden);
					

					
				}catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
				
			}

			// 更新安全巡查时间
			
			roomInfo.setHidden_check_date(hidden_Check.getDate());

			String[] where = { Singleton.ROOMDATABASE + ".[dbo].[RoomInfo].GUID = ", hidden_Check.getGUID()};

			roomInfo.setWhere(where);
			
			i=roomInfoDao.updateRoomInfo(roomInfo);
			
			if(i<1){
				TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			}
		}
		
		return i;
		
	}


	@Override
	public Integer deleteHiddenCheck(Hidden_Check hidden_Check) {
		// TODO Auto-generated method stub
		Hidden_Check hidden_Check2=new Hidden_Check();
		hidden_Check2.setExist(0);
		hidden_Check2.setWhere(hidden_Check.getWhere());
		//return DeleteExe.get(this.getJdbcTemplate(), hidden_Check);
		
		int i=UpdateExe.get(this.getJdbcTemplate(), hidden_Check2);
		
		if(i==1){
			String[] where2={"check_id=",hidden_Check.getCheck_id()};
			Hidden_Check_Date hidden_Check_Date=new Hidden_Check_Date();
			hidden_Check_Date.setWhere(where2);
			DeleteExe.get(this.getJdbcTemplate(), hidden_Check_Date);
		}
		
		return i;
	}


	@Override
	public Map<String, Object> selectAllHiddenNeaten(Integer limit, Integer offset, String sort,
			String order,Map<String, String> search) {
		// TODO Auto-generated method stub
		Hidden_Neaten hidden_Neaten=new Hidden_Neaten();
		search.put("[Hidden_Neaten].exist =", "1");
		
		hidden_Neaten.setOffset(offset);
		hidden_Neaten.setLimit(limit);
		hidden_Neaten.setSort(sort);
		hidden_Neaten.setOrder(order);
		hidden_Neaten.setNotIn("id");
		
		
		Position position=new Position();
		
		position.setLimit(limit);
		position.setOffset(offset);
		position.setSort(sort);
		position.setOrder(order);
		position.setNotIn("id");
		
		WeiXin_User weiXin_User=new WeiXin_User();
		
		weiXin_User.setLimit(limit);
		weiXin_User.setOffset(offset);
		weiXin_User.setSort(sort);
		weiXin_User.setOrder(order);
		weiXin_User.setNotIn("id");
		
		if(!search.isEmpty()){
		    String[] where=TransMapToString.get(search);
		    hidden_Neaten.setWhere(where);
		    position.setWhere(where);
		    weiXin_User.setWhere(where);
		}
		
		Map map=new HashMap<String, Object>();
		
		Object[] objects={hidden_Neaten,position,weiXin_User};
		
		Hidden_Neaten_Join hidden_Neaten_Join=new Hidden_Neaten_Join();
		
		String[] join={"neaten_id","campusAdmin"};
		
		List<Hidden_Neaten> list=SelectJoinExe.get(this.getJdbcTemplate(), objects, hidden_Neaten_Join, join);
		
		map.put("rows", list);
		
		Map countMap=SelectJoinExe.getCount(this.getJdbcTemplate(), objects, join);
			
		map.put("total", countMap.get(""));
			
		return map;
	}


	@Override
	public Integer insertHiddenNeaten(Hidden_Neaten hidden_Neaten,RoomInfo_Hidden_Item roomInfo_Hidden_Item,
			Hidden_Check_Item hidden_Check_Item,Integer item){
		// TODO Auto-generated method stub
		// 写入整改记录
		int i = InsertExe.get(this.getJdbcTemplate(), hidden_Neaten);

		String guid=hidden_Neaten.getGUID();
		
		System.out.println("i="+i);
		
		if (i > 0) {
			Hidden_Check hidden_check = new Hidden_Check();

			hidden_check.setState(hidden_Neaten.getProgress());

			hidden_check.setUpdate_time(hidden_Neaten.getDate());

			String[] where = { "[Hidden_Check].GUID=", hidden_Neaten.getGUID(), "[Hidden_Check].state !=", "整改完成",
								"[Hidden_Check].check_name = ","异常"};

			hidden_check.setWhere(where);

			SimpleDateFormat sdf =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
			
			String time=sdf.format(hidden_Neaten.getDate());
			

			String[] where2 = { "[RoomInfo_Hidden_Item].guid=", hidden_Neaten.getGUID() };

			roomInfo_Hidden_Item.setWhere(where2);

			String sql = "select *from [Hidden_Check] left join [Hidden_Check_Item] "
					+ "ON [Hidden_Check].check_id=[Hidden_Check_Item].check_id" + " WHERE [Hidden_Check].GUID = '"
					+ hidden_Neaten.getGUID() + "' " + "AND [Hidden_Check].check_name = '异常' "
					+ "AND ([Hidden_Check].state != '整改完成' " + "OR [Hidden_Check].state is null) "
					+"AND exist=1";

			Class[] object = { Hidden_Check.class, Hidden_Check_Item.class };

			List<Hidden_Check_Item_Join> list2 = this.getJdbcTemplate().query(sql,
					new RowMappersJoin(object, Hidden_Check_Item_Join.class));

			Iterator<Hidden_Check_Item_Join> iterator2 = list2.iterator();

			System.out.println("sql="+sql);
			
			MyTestUtil.print(list2);
			
			while (iterator2.hasNext()) {

				Hidden_Check_Item_Join hidden_Check_Item_Join = iterator2.next();

				String[] where4 = { "check_id=", hidden_Check_Item_Join.getCheck_id() };
				
				hidden_Check_Item.setWhere(where4);
				
				hidden_Check_Item.setLimit(1);
				hidden_Check_Item.setOffset(0);
				hidden_Check_Item.setNotIn("check_id");
				
				MyTestUtil.print(hidden_Check_Item);
				
				Hidden_Check_Item hidden_Check_Item2=(Hidden_Check_Item) SelectExe.get(this.getJdbcTemplate(), hidden_Check_Item).get(0);
				
				Hidden_Check_Item hidden_Check_Item3 = new Hidden_Check_Item();
								
				try {
					hidden_Check_Item3=compare(hidden_Check_Item, hidden_Check_Item2);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				hidden_Check_Item3.setWhere(where4);
								
				MyTestUtil.print(hidden_Check_Item3);
				
				try{
					i=UpdateExe.get(this.getJdbcTemplate(), hidden_Check_Item3);
				}catch (Exception e) {
					// TODO: handle exception
				}
						
				Hidden_Check hidden_Check2 = new Hidden_Check();

				Hidden_Check_Item hidden_Check_Item5=(Hidden_Check_Item) SelectExe.get(this.getJdbcTemplate(), hidden_Check_Item).get(0);
				
				System.out.println("hidden_check_item5=");
				
				MyTestUtil.print(hidden_Check_Item);
				
				int isNeate = getInt(hidden_Check_Item5.getFire_extinguisher())
						+ getInt(hidden_Check_Item5.getHigh_power()) + getInt(hidden_Check_Item5.getBlow())
						+ getInt(hidden_Check_Item5.getLine_aging()) + getInt(hidden_Check_Item5.getIncline())
						+ getInt(hidden_Check_Item5.getSplit()) + getInt(hidden_Check_Item5.getDown())
						+ getInt(hidden_Check_Item5.getBreak_off()) + getInt(hidden_Check_Item5.getDestroy())
						+ getInt(hidden_Check_Item5.getInvalidation()) + getInt(hidden_Check_Item5.getFlaw())
						+ getInt(hidden_Check_Item5.getCesspool()) + getInt(hidden_Check_Item5.getCoast())
						+ getInt(hidden_Check_Item5.getWall_up())+getInt(hidden_Check_Item5.getIs_other());
				
				System.out.println("isNeate ="+isNeate);
				
				if (hidden_Neaten.getProgress().equals("整改完成")&&isNeate ==0) {
					hidden_Check2.setState("整改完成");
				} else {
					hidden_Check2.setState("整改中");
				}
				
				hidden_Check2.setWhere(where4);

				i = UpdateExe.get(this.getJdbcTemplate(), hidden_Check2);

				if (i >0) {
	
				}else{
					TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
				}

			}
			
				if (i > 0) {
					
					hidden_check.setLimit(1000);
					hidden_check.setOffset(0);
					hidden_check.setNotIn("id");
					
					List<Hidden_Check> list=SelectExe.get(this.getJdbcTemplate(), hidden_check);
					
					Iterator<Hidden_Check> iterator=list.iterator();
					
					while (iterator.hasNext()) {
						
						Hidden_Check hidden_Check2=iterator.next();
						
						Neaten_Check neaten_Check=new Neaten_Check();
						
						neaten_Check.setGuid(hidden_Neaten.getGUID());
						neaten_Check.setNeaten_id(hidden_Neaten.getNeaten_id());
						neaten_Check.setCheck_id(hidden_Check2.getCheck_id());
						neaten_Check.setPrincipal(hidden_Neaten.getPrincipal());
						neaten_Check.setProgress(hidden_Neaten.getProgress());
						neaten_Check.setUserName(hidden_Neaten.getUserName());
						neaten_Check.setCampusAdmin(hidden_Neaten.getCampusAdmin());
						neaten_Check.setDate(hidden_Neaten.getDate());
						
						i=InsertExe.get(this.getJdbcTemplate(), neaten_Check);
						
						if(i<1){
							TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
						}
					}
					
					if (i > 0) {

						if (hidden_Neaten.getProgress().equals("整改完成")) {

							RoomInfo roomInfo = new RoomInfo();

							roomInfo.setLimit(1);
							roomInfo.setOffset(0);
							roomInfo.setNotIn("GUID");
							
							System.out.print("[RoomInfo].guid="+hidden_Neaten.getGUID());
							
							String[] where3 = {"[RoomInfo].guid=", hidden_Neaten.getGUID()};

							roomInfo.setWhere(where3);
							
							roomInfo=(RoomInfo) SelectExe.get(this.getJdbcTemplate(), roomInfo).get(0);
							
							RoomInfo roomInfo2=new RoomInfo();
							roomInfo2.setWhere(where3);
							
							MyTestUtil.print(roomInfo);
							
							System.out.println("item="+item);
							
							int isHidden=roomInfo.getIsHidden()-item;
							
							roomInfo2.setIsHidden(isHidden);
							
							// 更新资产隐患字段
							i = UpdateExe.get(this.getJdbcTemplate(), roomInfo2);

							if (i > 0) {

								if(isHidden==0){
									// 删除资产对应隐患表记录
									DeleteExe.get(this.getJdbcTemplate(), roomInfo_Hidden_Item);
									
								   }else{
									
									// 更新资产对应隐患表记录
									i=UpdateExe.get(this.getJdbcTemplate(), roomInfo_Hidden_Item);
									
									if(i>0){

										}else {
											TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
										}
									
								   }
								
							} else {
								TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
							}
						}

					} else {
						TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
					}

				} else {
					TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
				}
			}else{
				TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			}

		RoomInfo roomInfo=new RoomInfo();
		
		roomInfo.setNeaten_flow(0);
		
		String[] where={"[GUID]=",guid};
		
		roomInfo.setWhere(where);
		
		i=UpdateExe.get(this.getJdbcTemplate(), roomInfo);
		
		if(i<1){
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		}
		
		if (hidden_Neaten.getIs_repair() > 0) {
			try {
				i = server.del(guid);
				if (i < 1) {
					TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			}
		}
		
		return i;
	}

	int getInt(Integer value){
		if(value!=null){
			return value;
		}else{
			return 0;
		}
	}
	
	 Hidden_Check_Item compare(Hidden_Check_Item obj1, Hidden_Check_Item Obj2)
            throws Exception {

        Hidden_Check_Item hidden_Check_Item=new Hidden_Check_Item();

        Field[] fs = obj1.getClass().getDeclaredFields();//获取所有属性
        for (Field f : fs) {
            f.setAccessible(true);//设置访问性，反射类的方法，设置为true就可以访问private修饰的东西，否则无法访问
            Object v1 = f.get(obj1);
            Object v2 = f.get(Obj2);
            if(equals(v1, v2)){
                 System.out.println(f.getName());
				if (!f.getName().equals("serialVersionUID") && !f.getName().equals("check_id")
						&& !f.getName().equals("other") && !f.getName().equals("notIn") && !f.getName().equals("sort")
						&& !f.getName().equals("order") && !f.getName().equals("where")
						&& !f.getName().equals("whereTerm")) {
					String getMethodName = "set" + f.getName().substring(0, 1).toUpperCase() + f.getName().substring(1);
					Method getMethod = hidden_Check_Item.getClass().getDeclaredMethod(getMethodName, Integer.class);
					getMethod.invoke(hidden_Check_Item, 0);
                 }else if(f.getName().equals("order")){
                	 Method getMethod = hidden_Check_Item.getClass().getDeclaredMethod("setOther", String.class);
 					 getMethod.invoke(hidden_Check_Item, "");
 					 getMethod = hidden_Check_Item.getClass().getDeclaredMethod("setIsOther", Integer.class);
 					 getMethod.invoke(hidden_Check_Item, 0);
                 }
            }
        }
        return hidden_Check_Item;
    }
	
	  boolean equals(Object obj1, Object obj2) {

		   System.out.println("obj1="+obj1);
		   System.out.println("obj2="+obj2);

	        if (obj1 == null || obj2 == null) {
	            return false;
	        }
	        return obj1.equals(obj2);
	    }
	
	@Override
	public Integer deleteHiddenNeaten(Hidden_Neaten hidden_Neaten) {
		// TODO Auto-generated method stub
		Hidden_Neaten hidden_Neaten2=new Hidden_Neaten();
		hidden_Neaten2.setExist(0);
		hidden_Neaten2.setWhere(hidden_Neaten.getWhere());
		//return DeleteExe.get(this.getJdbcTemplate(), hidden_Neaten);
		
		int i=UpdateExe.get(this.getJdbcTemplate(), hidden_Neaten2);
		
		if(i==1){
			String[] where2={"[Hidden_Neaten_Date].neaten_id=",hidden_Neaten.getNeaten_id()};
			Hidden_Neaten_Date hidden_Neaten_Date=new Hidden_Neaten_Date();
			hidden_Neaten_Date.setWhere(where2);
			DeleteExe.get(this.getJdbcTemplate(), hidden_Neaten_Date);		
		}
		
		return i;
	}


	@Override
	public Integer updateHiddenCheck(Hidden_Check hidden_Check) {
		// TODO Auto-generated method stub
		return UpdateExe.get(this.getJdbcTemplate(), hidden_Check);
	}


	@Override
	public Integer updateHiddenNeaten(Hidden_Neaten hidden_Neaten) {
		// TODO Auto-generated method stub
		return UpdateExe.get(this.getJdbcTemplate(), hidden_Neaten);
	}


	@Override
	public List<Hidden_Check_Join> selectHiddenOfMap(Map<String, String> search) {
		// TODO Auto-generated method stub
		
		Hidden_Check hidden=new Hidden_Check();
		search.put("[Hidden_Check].exist =", "1");		 
		hidden.setLimit(2);
		hidden.setOffset(0);
		hidden.setSort("date");
		hidden.setOrder("desc");
		hidden.setNotIn("GUID");

		RoomInfo roomInfo=new RoomInfo();		
		roomInfo.setLimit(2);
		roomInfo.setOffset(0);
		roomInfo.setSort("date");
		roomInfo.setOrder("desc");
		roomInfo.setNotIn("GUID");
		
		Position position = new Position();
		position.setLimit(2);
		position.setOffset(0);
		position.setSort("date");
		position.setOrder("desc");
		position.setNotIn("GUID");

		WeiXin_User weiXin_User = new WeiXin_User();
		weiXin_User.setLimit(10);
		weiXin_User.setOffset(0);
		weiXin_User.setSort(null);
		weiXin_User.setOrder(null);
		weiXin_User.setNotIn("check_id");
		
		 search.put("[Hidden_Check].check_name =", "异常");
		 search.put("[Position].GUID !=", "");
		 String[] where=TransMapToString.get(search);
		 hidden.setWhere(where);
		 position.setWhere(where);
		 weiXin_User.setWhere(where);

			 
		Map map=new HashMap<String, Object>();
				
		Object[] objects={hidden,roomInfo,position,weiXin_User};
				
		String[] join={"GUID","GUID","campusAdmin"};
			
		Hidden_Check_Join hidden_Join=new Hidden_Check_Join();
		
		List hidden_joins=SelectJoinExe.get(this.getJdbcTemplate(), objects, hidden_Join, join);		
		
		MyTestUtil.print(hidden_joins);
		
		return hidden_joins;
	}


	@Override
	public Integer getAllAssetByHidden_GUID(String guid) {
		// TODO Auto-generated method stub
		
		RoomInfo roomInfo=new RoomInfo();
		
		String[] where={"[RoomInfo].IsHidden>","0"};

		roomInfo.setWhere(where);
		
		int count=(int) SelectExe.getCount(this.getJdbcTemplate(), roomInfo).get("");

		return count;
	}


	@Override
	public Integer insertIntoHidden_Check_Item(Hidden_Check_Item hidden_Check_Item) {
		// TODO Auto-generated method stub
		return InsertExe.get(this.getJdbcTemplate(), hidden_Check_Item);
	}


	@Override
	public Hidden_Check_Item selectHidden_Check_ItemById(Hidden_Check_Item hidden_Check_Item) {
		// TODO Auto-generated method stub
		Hidden_Check_Item hidden_Check_Item2=new Hidden_Check_Item();
		try{
			hidden_Check_Item2=(Hidden_Check_Item) SelectExe.get(this.getJdbcTemplate(), hidden_Check_Item).get(0);
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return hidden_Check_Item2;
	}


	@Override
	public Map selectAllHidden_Point(Integer limit, Integer offset, String sort, String order,
			Map<String, String> search) {
		// TODO Auto-generated method stub
		RoomInfo roomInfo=new RoomInfo();
		roomInfo.setLimit(limit);
		roomInfo.setOffset(offset);
		roomInfo.setSort(sort);
		roomInfo.setOrder(order);
		roomInfo.setNotIn("GUID");
		
		Position position=new Position();
		position.setLimit(limit);
		position.setOffset(offset);
		position.setSort(sort);
		position.setOrder(order);
		position.setNotIn("GUID");
		
		if(search!=null&&!search.isEmpty()){
			search.put("[RoomInfo].IsHidden > ", "0");
		    String[] where=TransMapToString.get(search);
		    roomInfo.setWhere(where);
		    position.setWhere(where);
		}else{
			String[] where={"[RoomInfo].IsHidden > ", "0"};
			roomInfo.setWhere(where);
		    position.setWhere(where);
		}
		
		RoomInfo_Position roomInfo_Position=new RoomInfo_Position();
		
		Object[] objects={roomInfo,position};
		
		String[] join={"GUID"};
		
		List list=SelectJoinExe.get(this.getJdbcTemplate(), objects, roomInfo_Position, join);
		
		int total=(int) SelectJoinExe.getCount(this.getJdbcTemplate(), objects, join).get("");
		
		Map map=new HashMap<>();
		
		map.put("row", list);
		
		map.put("total", total);
		
		return map;
	}


	@Override
	public Integer updateRoomInfo_Hidden_Item(RoomInfo_Hidden_Item roomInfo_Hidden_Item) {
		// TODO Auto-generated method stub
		String guid=roomInfo_Hidden_Item.getGuid();
		
		String[] where={"[RoomInfo_Hidden_Item].guid=",guid};
		
		roomInfo_Hidden_Item.setWhere(where);
		
		int i;
		
		int count=(int) SelectExe.getCount(this.getJdbcTemplate(), roomInfo_Hidden_Item).get("");
		
		if(count>0){
			i=UpdateExe.get(this.getJdbcTemplate(), roomInfo_Hidden_Item);
		}else{
			i=InsertExe.get(this.getJdbcTemplate(), roomInfo_Hidden_Item);
		}
		
		return i;
	}


	@Override
	public List<RoomInfo_Hidden_Item> selectRoomInfo_Hidden_Item(Integer limit, Integer offset, String sort,
			String order, Map<String, String> search) {
		// TODO Auto-generated method stub
		RoomInfo_Hidden_Item roomInfo_Hidden_Item=new RoomInfo_Hidden_Item();
		roomInfo_Hidden_Item.setLimit(limit);
		roomInfo_Hidden_Item.setOffset(offset);
		roomInfo_Hidden_Item.setNotIn("guid");
		
		if(!search.isEmpty()){
		    String[] where=TransMapToString.get(search);
		    roomInfo_Hidden_Item.setWhere(where);
		}
		
		return SelectExe.get(this.getJdbcTemplate(), roomInfo_Hidden_Item);
	}


	@Override
	public Integer insertIntoNeaten_Check(Neaten_Check neaten_Check) {
		// TODO Auto-generated method stub
		return InsertExe.get(this.getJdbcTemplate(), neaten_Check);
	}


	@Override
	public Integer insertHidden_Neaten_Date(Hidden_Neaten_Date hidden_Neaten_Date) {
		// TODO Auto-generated method stub
		return InsertExe.get(this.getJdbcTemplate(), hidden_Neaten_Date);
	}


	@Override
	public Integer updateHidden_Neaten_Date(Hidden_Neaten_Date hidden_Neaten_Date) {
		// TODO Auto-generated method stub
		return UpdateExe.get(this.getJdbcTemplate(), hidden_Neaten_Date);
	}



}
