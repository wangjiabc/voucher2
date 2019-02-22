package com.voucher.manage.daoImpl;

import com.rmi.server.entity.ImageData;
import com.voucher.manage.dao.MobileDAO;
import com.voucher.manage.daoModel.Assets.Hidden_Check_Date;
import com.voucher.manage.daoModel.Assets.Hidden_Neaten_Date;
import com.voucher.manage.daoModel.Assets.WeiXin_User;
import com.voucher.manage.daoModel.RoomInfo;
import com.voucher.manage.daoModel.TTT.FileSelfBelong;
import com.voucher.manage.daoModelJoin.Assets.Hidden_Check_Join;
import com.voucher.manage.daoModelJoin.Assets.Hidden_Neaten_Join;
import com.voucher.manage.daoModelJoin.RoomInfo_Position;
import com.voucher.manage.daoSQL.DeleteExe;
import com.voucher.manage.daoSQL.InsertExe;
import com.voucher.manage.daoSQL.SelectExe;
import com.voucher.manage.daoSQL.UpdateExe;
import com.voucher.manage.singleton.Singleton;
import com.voucher.manage.tools.CopyFile;
import com.voucher.manage.tools.MyTestUtil;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import javax.servlet.http.HttpServletRequest;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class MobileDAOImpl extends JdbcDaoSupport implements MobileDAO{
	
	@Override
	public Map<String, Object> checkImageQuery(HttpServletRequest request,List guidLits) {
		// TODO Auto-generated method stub
	
		String pathRoot = System.getProperty("user.home");
	
		String filePath=pathRoot+Singleton.filePath;
        
		String imgPath=request.getSession().getServletContext().getRealPath(Singleton.filePath);
		
		//List<byte[]> fileBytes=new ArrayList<byte[]>();
		
		Map fileBytes=new HashMap<>();
		
		Iterator<Hidden_Check_Join> iterator=guidLits.iterator();
	
		while(iterator.hasNext()){			
		
			Hidden_Check_Join hidden_Check_Join = iterator.next();
		
			String Check_id=hidden_Check_Join.getCheck_id();
		
			String sql="SELECT top 1 "+    
				"[Hidden_Check_Date].Check_id, "+
			    "[Hidden_Check_Date].URI, "+
				"[Hidden_Check_Date].date, "+
			    "[Hidden_Check_Date].NAME "+
				"FROM "+
				"[Hidden_Check_Date] left join [Hidden_Check] on [Hidden_Check_Date].Check_id=[Hidden_Check].Check_id "+  
				"where [Hidden_Check_Date].Check_id='"+Check_id+"'  "+
				"AND ([Hidden_Check_Date].TYPE ='png ' OR [Hidden_Check_Date].TYPE ='jpg ' OR [Hidden_Check_Date].TYPE ='jpeg ' OR [Hidden_Check_Date].TYPE ='gif ' ) "+
				"order by [Hidden_Check_Date].date desc ";
		
			List hidden_Data_Joins=this.getJdbcTemplate().query(sql,new checkImageQueryRowMapper());
		
			try{
				Hidden_Check_Date hidden_Check_Date=(Hidden_Check_Date) hidden_Data_Joins.get(0);
							
				//String fileByte=Base64Test.getImageStr(filePath+"\\"+hidden_Data_Join.getURI());
				
				String oldFile=filePath+"\\"+hidden_Check_Date.getURI();
				
				CopyFile.set(imgPath, oldFile, hidden_Check_Date.getURI());
				
				fileBytes.put(Check_id, Singleton.filePath+"\\compressFile\\"+hidden_Check_Date.getURI());
		
			}catch (Exception e) {
			// TODO: handle exception
				//e.printStackTrace();
			}
		
		}
	

		MyTestUtil.print(fileBytes);
		
		return fileBytes;
	}

	
	@Override
	public Map<String, Object> assetCheckImageQuery(HttpServletRequest request,List guidLits) {
		// TODO Auto-generated method stub
	
		String pathRoot = System.getProperty("user.home");
	
		String filePath=pathRoot+Singleton.filePath;
        
		String imgPath=request.getSession().getServletContext().getRealPath(Singleton.filePath);
		
		//List<byte[]> fileBytes=new ArrayList<byte[]>();
		
		Map fileBytes=new HashMap<>();
		
		Iterator<Hidden_Check_Join> iterator=guidLits.iterator();
	
		while(iterator.hasNext()){			
		
			Hidden_Check_Join hidden_Check_Join = iterator.next();
		
			String Check_id=hidden_Check_Join.getCheck_id();
		
			String sql="SELECT top 1 "+    
				"[Assets_Check_Date].Check_id, "+
			    "[Assets_Check_Date].URI, "+
				"[Assets_Check_Date].date, "+
			    "[Assets_Check_Date].NAME "+
				"FROM "+
				"[Assets_Check_Date] left join [Assets_Check] on [Assets_Check_Date].Check_id=[Assets_Check].Check_id "+  
				"where [Assets_Check_Date].Check_id='"+Check_id+"'  "+
				"AND ([Assets_Check_Date].TYPE ='png ' OR [Assets_Check_Date].TYPE ='jpg ' OR [Assets_Check_Date].TYPE ='jpeg ' OR [Assets_Check_Date].TYPE ='gif ' ) "+
				"order by [Assets_Check_Date].date desc ";
		
			List hidden_Data_Joins=this.getJdbcTemplate().query(sql,new checkImageQueryRowMapper());
		
			try{
				Hidden_Check_Date hidden_Check_Date=(Hidden_Check_Date) hidden_Data_Joins.get(0);
							
				//String fileByte=Base64Test.getImageStr(filePath+"\\"+hidden_Data_Join.getURI());
				
				String oldFile=filePath+"\\"+hidden_Check_Date.getURI();
				
				CopyFile.set(imgPath, oldFile, hidden_Check_Date.getURI());
				
				fileBytes.put(Check_id, Singleton.filePath+"\\compressFile\\"+hidden_Check_Date.getURI());
		
			}catch (Exception e) {
			// TODO: handle exception
				//e.printStackTrace();
			}
		
		}
	

		MyTestUtil.print(fileBytes);
		
		return fileBytes;
	}
	
	
	@Override
	public List allCheckImageByGUID(HttpServletRequest request,Hidden_Check_Join hidden_Check_Join) {
		// TODO Auto-generated method stub
	
		String pathRoot = System.getProperty("user.home");
	
		String filePath=pathRoot+Singleton.filePath;
        
		String imgPath=request.getSession().getServletContext().getRealPath(Singleton.filePath);
		
		String check_id=hidden_Check_Join.getCheck_id();
		
		String sql="SELECT "+    
				"[Hidden_Check_Date].check_id, "+
			    "[Hidden_Check_Date].URI, "+
				"[Hidden_Check_Date].date, "+
			    "[Hidden_Check_Date].NAME "+
				"FROM "+
				"[Hidden_Check_Date] left join [Hidden_Check] on [Hidden_Check_Date].check_id=[Hidden_Check].check_id "+  
				"where [Hidden_Check_Date].check_id='"+check_id+"'  "+
				"AND ([Hidden_Check_Date].TYPE ='png ' OR [Hidden_Check_Date].TYPE ='jpg ' OR [Hidden_Check_Date].TYPE ='jpeg ' OR [Hidden_Check_Date].TYPE ='gif ' ) "+
				"order by [Hidden_Check_Date].date desc ";
		
		List hidden_Data_Joins=this.getJdbcTemplate().query(sql,new checkImageQueryRowMapper());
		
		List fileBytes=new ArrayList<>();
		
		Iterator<Hidden_Check_Date> iterator=hidden_Data_Joins.iterator();
		
		while (iterator.hasNext()) {
			
			Hidden_Check_Date hidden_Check_Date=iterator.next();
			
			try{			
				String oldFile=filePath+"\\"+hidden_Check_Date.getURI();
			
				CopyFile.set(imgPath, oldFile, hidden_Check_Date.getURI());
			
				Map<String,String> map=new HashMap<>();
				
				map.put("name", hidden_Check_Date.getNAME());
				map.put("uri", Singleton.filePath+"\\"+hidden_Check_Date.getURI());
				map.put("compressUri", Singleton.filePath+"\\compressFile\\"+hidden_Check_Date.getURI());
				map.put("date", hidden_Check_Date.getDate().toString());
				
				fileBytes.add(map);
			}catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
		
		return fileBytes;
	
	}
	
	
	@Override
	public List allRoomHiddenCheckImageByGUID(HttpServletRequest request, String guid) {
		// TODO Auto-generated method stub
		String pathRoot = System.getProperty("user.home");
		
		String filePath=pathRoot+Singleton.filePath;
        
		String imgPath=request.getSession().getServletContext().getRealPath(Singleton.filePath);
				
		String sql="SELECT "+    
				"[Hidden_Check_Date].check_id, "+
			    "[Hidden_Check_Date].URI, "+
				"[Hidden_Check_Date].date, "+
			    "[Hidden_Check_Date].NAME "+
				"FROM "+
				"[Hidden_Check_Date] left join [Hidden_Check] on [Hidden_Check_Date].check_id=[Hidden_Check].check_id "+  
				"where [Hidden_Check].guid='"+guid+"'  "+
				"AND ([Hidden_Check].state is null or [Hidden_Check].state ! = '整改完成')"+
				"AND ([Hidden_Check_Date].TYPE ='png ' OR [Hidden_Check_Date].TYPE ='jpg ' OR [Hidden_Check_Date].TYPE ='jpeg ' OR [Hidden_Check_Date].TYPE ='gif ' ) "+
				"order by [Hidden_Check_Date].date desc ";
		
		List hidden_Data_Joins=this.getJdbcTemplate().query(sql,new checkImageQueryRowMapper());
		
		List fileBytes=new ArrayList<>();
		
		Iterator<Hidden_Check_Date> iterator=hidden_Data_Joins.iterator();
		
		while (iterator.hasNext()) {
			
			Hidden_Check_Date hidden_Check_Date=iterator.next();
			
			try{			
				String oldFile=filePath+"\\"+hidden_Check_Date.getURI();
			
				CopyFile.set(imgPath, oldFile, hidden_Check_Date.getURI());
			
				Map<String,String> map=new HashMap<>();
				
				map.put("name", hidden_Check_Date.getNAME());
				map.put("uri", Singleton.filePath+"\\"+hidden_Check_Date.getURI());
				map.put("compressUri", Singleton.filePath+"\\compressFile\\"+hidden_Check_Date.getURI());
				map.put("date", hidden_Check_Date.getDate().toString());
				
				fileBytes.add(map);
			}catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
		
		return fileBytes;
	}
	
	@Override
	public List allAssetCheckImageByGUID(HttpServletRequest request,Hidden_Check_Join hidden_Check_Join) {
		// TODO Auto-generated method stub
	
		String pathRoot = System.getProperty("user.home");
	
		String filePath=pathRoot+Singleton.filePath;
        
		String imgPath=request.getSession().getServletContext().getRealPath(Singleton.filePath);
		
		String check_id=hidden_Check_Join.getCheck_id();
		
		String sql="SELECT "+    
				"[Assets_Check_Date].check_id, "+
			    "[Assets_Check_Date].URI, "+
				"[Assets_Check_Date].date, "+
			    "[Assets_Check_Date].NAME "+
				"FROM "+
				"[Assets_Check_Date] left join [Assets_Check] on [Assets_Check_Date].check_id=[Assets_Check].check_id "+  
				"where [Assets_Check_Date].check_id='"+check_id+"'  "+
				"AND ([Assets_Check_Date].TYPE ='png ' OR [Assets_Check_Date].TYPE ='jpg ' OR [Assets_Check_Date].TYPE ='jpeg ' OR [Assets_Check_Date].TYPE ='gif ' ) "+
				"order by [Assets_Check_Date].date desc ";
		
		List hidden_Data_Joins=this.getJdbcTemplate().query(sql,new checkImageQueryRowMapper());
		
		List fileBytes=new ArrayList<>();
		
		Iterator<Hidden_Check_Date> iterator=hidden_Data_Joins.iterator();
		
		while (iterator.hasNext()) {
			
			Hidden_Check_Date hidden_Check_Date=iterator.next();
			
			try{			
				String oldFile=filePath+"\\"+hidden_Check_Date.getURI();
			
				CopyFile.set(imgPath, oldFile, hidden_Check_Date.getURI());
			
				Map<String,String> map=new HashMap<>();
				
				map.put("name", hidden_Check_Date.getNAME());
				map.put("uri", Singleton.filePath+"\\"+hidden_Check_Date.getURI());
				map.put("compressUri", Singleton.filePath+"\\compressFile\\"+hidden_Check_Date.getURI());
				map.put("date", hidden_Check_Date.getDate().toString());
				
				fileBytes.add(map);
			}catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
		
		return fileBytes;
	
	}
	
	class checkImageQueryRowMapper implements RowMapper<Hidden_Check_Date> {
        //rs为返回结果集，以每行为单位封装着
        public Hidden_Check_Date mapRow(ResultSet rs, int rowNum) throws SQLException {    
            Hidden_Check_Date hidden_Check_Date=new Hidden_Check_Date();
        	hidden_Check_Date.setCheck_id(rs.getString("check_id"));
        	hidden_Check_Date.setURI(rs.getString("URI"));
        	hidden_Check_Date.setNAME(rs.getString("NAME"));
        	hidden_Check_Date.setDate(rs.getDate("date"));
            return hidden_Check_Date;
        }
    }
	
	
	@Override
	public Map<String, Object> neatenImageQuery(HttpServletRequest request,List guidLits) {
		// TODO Auto-generated method stub
	
		String pathRoot = System.getProperty("user.home");
	
		String filePath=pathRoot+Singleton.filePath;
        
		String imgPath=request.getSession().getServletContext().getRealPath(Singleton.filePath);
		
		//List<byte[]> fileBytes=new ArrayList<byte[]>();
		
		Map fileBytes=new HashMap<>();
		
		Iterator<Hidden_Neaten_Join> iterator=guidLits.iterator();
	
		while(iterator.hasNext()){			
		
			Hidden_Neaten_Join hidden_Neaten_Join = iterator.next();
		
			String neaten_id=hidden_Neaten_Join.getNeaten_id();
		
			String sql="SELECT top 1 "+    
				"[Hidden_Neaten_Date].neaten_id, "+
			    "[Hidden_Neaten_Date].URI, "+
				"[Hidden_Neaten_Date].date, "+
			    "[Hidden_Neaten_Date].NAME "+
				"FROM "+
				"[Hidden_Neaten_Date] left join [Hidden_Neaten] on [Hidden_Neaten_Date].neaten_id=[Hidden_Neaten].neaten_id "+  
				"where [Hidden_Neaten_Date].neaten_id='"+neaten_id+"'  "+
				"AND ([Hidden_Neaten_Date].TYPE ='png ' OR [Hidden_Neaten_Date].TYPE ='jpg ' OR [Hidden_Neaten_Date].TYPE ='jpeg ' OR [Hidden_Neaten_Date].TYPE ='gif ' ) "+
				"order by [Hidden_Neaten_Date].date desc ";
		
			List hidden_Data_Joins=this.getJdbcTemplate().query(sql,new neatenImageQueryRowMapper());
		
			try{
				Hidden_Neaten_Date hidden_Neaten_Date= (Hidden_Neaten_Date) hidden_Data_Joins.get(0);
							
				//String fileByte=Base64Test.getImageStr(filePath+"\\"+hidden_Data_Join.getURI());
				
				String oldFile=filePath+"\\"+hidden_Neaten_Date.getURI();
				
				CopyFile.set(imgPath, oldFile, hidden_Neaten_Date.getURI());
				
				fileBytes.put(neaten_id, Singleton.filePath+"\\compressFile\\"+hidden_Neaten_Date.getURI());
		
			}catch (Exception e) {
			// TODO: handle exception
				e.printStackTrace();
			}
		
		}
	

		MyTestUtil.print(fileBytes);
		
		return fileBytes;
	}

	
	@Override
	public List allNeatenImageByGUID(HttpServletRequest request,Hidden_Neaten_Join hidden_Neaten_Join) {
		// TODO Auto-generated method stub
	
		String pathRoot = System.getProperty("user.home");
	
		String filePath=pathRoot+Singleton.filePath;
        
		String imgPath=request.getSession().getServletContext().getRealPath(Singleton.filePath);
		
		String neaten_id=hidden_Neaten_Join.getNeaten_id();
		
		String sql="SELECT "+    
			"[Hidden_Neaten_Date].neaten_id, "+
		    "[Hidden_Neaten_Date].URI, "+
			"[Hidden_Neaten_Date].date, "+
		    "[Hidden_Neaten_Date].NAME "+
			"FROM "+
			"[Hidden_Neaten_Date] left join [Hidden_Neaten] on [Hidden_Neaten_Date].neaten_id=[Hidden_Neaten].neaten_id "+  
			"where [Hidden_Neaten_Date].neaten_id='"+neaten_id+"'  "+
			"AND ([Hidden_Neaten_Date].TYPE ='png ' OR [Hidden_Neaten_Date].TYPE ='jpg ' OR [Hidden_Neaten_Date].TYPE ='jpeg ' OR [Hidden_Neaten_Date].TYPE ='gif ' ) "+
			"order by [Hidden_Neaten_Date].date desc ";
		
		List hidden_Data_Joins=this.getJdbcTemplate().query(sql,new neatenImageQueryRowMapper());
		
		List fileBytes=new ArrayList<>();
		
		Iterator<Hidden_Neaten_Date> iterator=hidden_Data_Joins.iterator();
		
		while (iterator.hasNext()) {
			
			Hidden_Neaten_Date hidden_Neaten_Date=iterator.next();
			
			try{			
				String oldFile=filePath+"\\"+hidden_Neaten_Date.getURI();
			
				CopyFile.set(imgPath, oldFile, hidden_Neaten_Date.getURI());
			
				Map<String,String> map=new HashMap<>();
				
				map.put("name", hidden_Neaten_Date.getNAME());
				map.put("uri", Singleton.filePath+"\\"+hidden_Neaten_Date.getURI());
				map.put("compressUri", Singleton.filePath+"\\compressFile\\"+hidden_Neaten_Date.getURI());
				map.put("date", hidden_Neaten_Date.getDate().toString());
				
				fileBytes.add(map);
			}catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
		
		return fileBytes;
	
	}
	
	
	class neatenImageQueryRowMapper implements RowMapper<Hidden_Neaten_Date> {
        //rs为返回结果集，以每行为单位封装着
        public Hidden_Neaten_Date mapRow(ResultSet rs, int rowNum) throws SQLException {    
            Hidden_Neaten_Date hidden_Neaten_Date=new Hidden_Neaten_Date();
        	hidden_Neaten_Date.setNeaten_id(rs.getString("neaten_id"));
        	hidden_Neaten_Date.setURI(rs.getString("URI"));
        	hidden_Neaten_Date.setNAME(rs.getString("NAME"));
        	hidden_Neaten_Date.setDate(rs.getDate("date"));
            return hidden_Neaten_Date;
        }
    }


	@Override
	public Map<String, Object> roomInfoImageQuery(HttpServletRequest request, List guidLits) {
		// TODO Auto-generated method stub
		String pathRoot = System.getProperty("user.home");
		
		String filePath=pathRoot+Singleton.filePath;
        
		String imgPath=request.getSession().getServletContext().getRealPath(Singleton.filePath);
		
		Map fileBytes=new HashMap<>();
		
		Iterator<RoomInfo> iterator=guidLits.iterator();
	
		while(iterator.hasNext()){			
		
			RoomInfo roomInfo = iterator.next();
		
			String roomGUID=roomInfo.getGUID();
		
			String sql="SELECT top 1 "+    
					"[GUID] "+
					",[RoomGUID] "+
					",[UpFileFullName] "+
					",[FileType] "+
					",[FileBelong] "+
					",[FileIndex] "+
					",[ViewFileName] "+
				"FROM "+
				Singleton.ROOMDATABASE+".[dbo].[FileSelfBelong] "+  
				"where RoomGUID='"+roomGUID+"'";
		
			List fileSelfBelongs=this.getJdbcTemplate().query(sql,new fileSelfBelongRowMapper());
		
			try{
				FileSelfBelong fileSelfBelong=(FileSelfBelong) fileSelfBelongs.get(0);
							
				//String fileByte=Base64Test.getImageStr(filePath+"\\"+hidden_Data_Join.getURI());
				
				String oldFile=Singleton.ROOMINFOIMGPATH+fileSelfBelong.getUpFileFullName();
				
				CopyFile.set(imgPath, oldFile, fileSelfBelong.getUpFileFullName());
				
				fileBytes.put(roomGUID, Singleton.filePath+"\\compressFile\\"+fileSelfBelong.getUpFileFullName());
		
			}catch (Exception e) {
			// TODO: handle exception
			//	e.printStackTrace();
			}
		
		}
	

		MyTestUtil.print(fileBytes);
		
		return fileBytes;
	}
	
	@Override
	public Map<String, Object> roomInfo_PositionImageQuery(HttpServletRequest request, List guidLits) {
		// TODO Auto-generated method stub
		String pathRoot = System.getProperty("user.home");
		
		String filePath=pathRoot+Singleton.filePath;
        
		String imgPath=request.getSession().getServletContext().getRealPath(Singleton.filePath);
		
		Map fileBytes=new HashMap<>();
		
		Iterator<RoomInfo_Position> iterator=guidLits.iterator();
	
		while(iterator.hasNext()){			
		
			RoomInfo_Position roomInfo_Position = iterator.next();
		
			String roomGUID=roomInfo_Position.getGUID();
		
			String sql="SELECT top 1 "+    
					"[GUID] "+
					",[RoomGUID] "+
					",[UpFileFullName] "+
					",[FileType] "+
					",[FileBelong] "+
					",[FileIndex] "+
					",[ViewFileName] "+
				"FROM "+
				Singleton.ROOMDATABASE+".[dbo].[FileSelfBelong] "+  
				"where RoomGUID='"+roomGUID+"'"+
				"AND FileBelong='房屋图片'";
		
			List fileSelfBelongs=this.getJdbcTemplate().query(sql,new fileSelfBelongRowMapper());
		
			try{
				FileSelfBelong fileSelfBelong=(FileSelfBelong) fileSelfBelongs.get(0);
							
				//String fileByte=Base64Test.getImageStr(filePath+"\\"+hidden_Data_Join.getURI());
				
				String oldFile=Singleton.ROOMINFOIMGPATH+fileSelfBelong.getUpFileFullName();
				
				System.out.println("oldFile="+oldFile);
				
				System.out.println("imgPath="+imgPath);
				
				CopyFile.set(imgPath, oldFile, fileSelfBelong.getUpFileFullName());
				
				fileBytes.put(roomGUID, Singleton.filePath+"\\compressFile\\"+fileSelfBelong.getUpFileFullName());
		
			}catch (Exception e) {
			// TODO: handle exception
			//	e.printStackTrace();
			}
		
		}
	

		MyTestUtil.print(fileBytes);
		
		return fileBytes;
	}
	
	@Override
	public List allRoomInfoImageByGUID(HttpServletRequest request, RoomInfo roomInfo) {
		// TODO Auto-generated method stub
		String pathRoot = System.getProperty("user.home");
		
		String filePath=pathRoot+Singleton.filePath;
        
		String imgPath=request.getSession().getServletContext().getRealPath(Singleton.filePath);
		
		List fileBytes=new ArrayList<>();
				
		String roomGUID=roomInfo.getGUID();
	
		String sql="SELECT "+    
				"[GUID] "+
				",[RoomGUID] "+
				",[UpFileFullName] "+
				",[FileType] "+
				",[FileBelong] "+
				",[FileIndex] "+
				",[ViewFileName] "+
			"FROM "+
			Singleton.ROOMDATABASE+".[dbo].[FileSelfBelong] "+  
			"where RoomGUID='"+roomGUID+"'"+
			"AND FileBelong='房屋图片'";
		
		List fileSelfBelongs=this.getJdbcTemplate().query(sql,new fileSelfBelongRowMapper());
	
		Iterator<FileSelfBelong> iterator=fileSelfBelongs.iterator();
		
		while(iterator.hasNext()){			

			FileSelfBelong fileSelfBelong=iterator.next();
			
			try{
			
				//String fileByte=Base64Test.getImageStr(filePath+"\\"+hidden_Data_Join.getURI());
				
				String oldFile=Singleton.ROOMINFOIMGPATH+fileSelfBelong.getUpFileFullName();
				
				CopyFile.set(imgPath, oldFile, fileSelfBelong.getUpFileFullName());
				
				Map<String,String> map=new HashMap<>();
				
				map.put("uri", Singleton.filePath+"\\"+fileSelfBelong.getUpFileFullName());
				map.put("compressUri", Singleton.filePath+"\\compressFile\\"+fileSelfBelong.getUpFileFullName());
				map.put("fileBelong", fileSelfBelong.getFileBelong());

				fileBytes.add(map);
		
			}catch (Exception e) {
			// TODO: handle exception
				e.printStackTrace();
			}
		
		}
	
		MyTestUtil.print(fileBytes);
		
		return fileBytes;
	}
	
	
	class fileSelfBelongRowMapper implements RowMapper<FileSelfBelong>{

		@Override
		public FileSelfBelong mapRow(ResultSet rs, int rowNum) throws SQLException {
			// TODO Auto-generated method stub
			FileSelfBelong fileSelfBelong=new FileSelfBelong();
			fileSelfBelong.setGUID(rs.getString("GUID"));
			fileSelfBelong.setRoomGUID(rs.getString("RoomGUID"));
			fileSelfBelong.setUpFileFullName(rs.getString("UpFileFullName"));
			fileSelfBelong.setFileType(rs.getString("FileType"));
			fileSelfBelong.setFileBelong(rs.getString("FileBelong"));
			fileSelfBelong.setFileIndex(rs.getInt("FileIndex"));
			fileSelfBelong.setViewFileName(rs.getString("ViewFileName"));
			return fileSelfBelong;
		}
		
	}


	@Override
	public Integer insertWeiXinUser(WeiXin_User weiXin_User) {
		// TODO Auto-generated method stub
		return InsertExe.get(this.getJdbcTemplate(), weiXin_User);
	}


	@Override
	public Integer deleteWeiXinUser(WeiXin_User weiXin_User) {
		// TODO Auto-generated method stub
		
		String campusAdmin=weiXin_User.getCampusAdmin();
		
		String[] where={"[WeiXin_User].campusAdmin=",campusAdmin};
		
		weiXin_User.setWhere(where);
		
		return DeleteExe.get(this.getJdbcTemplate(), weiXin_User);
		
	}


	@Override
	public Integer updateWeiXinUser(WeiXin_User weiXin_User) {
		// TODO Auto-generated method stub
		
		String campusAdmin=weiXin_User.getCampusAdmin();
		
		String[] where={"[WeiXin_User].campusAdmin=",campusAdmin};
		
		weiXin_User.setWhere(where);
		
		return UpdateExe.get(this.getJdbcTemplate(), weiXin_User);
		
	}


	@Override
	public Integer selectCountWeiXinUser(WeiXin_User weiXin_User) {
		// TODO Auto-generated method stub
		
		String campusAdmin=weiXin_User.getCampusAdmin();
		
		String[] where={"[WeiXin_User].campusAdmin=",campusAdmin};
		
		weiXin_User.setWhere(where);
		
		int count=(int) SelectExe.getCount(this.getJdbcTemplate(), weiXin_User).get("");
		
		return count;
	}


	@Override
	public List flowImageData(HttpServletRequest request,List list) {
		// TODO Auto-generated method stub
		String pathRoot = System.getProperty("user.home");
		
		String filePath=pathRoot+Singleton.filePath;
        
		String imgPath=request.getSession().getServletContext().getRealPath(Singleton.filePath);
		
		Iterator<ImageData> iterator=list.iterator();
		
		List fileBytes=new ArrayList<>();
		
		while (iterator.hasNext()) {
			
			ImageData imageData=iterator.next();
			
			try{			
				String oldFile=filePath+"\\"+imageData.getURI();
			
				CopyFile.set(imgPath, oldFile, imageData.getURI());
			
				Map<String,String> map=new HashMap<>();
				
				map.put("name", imageData.getName());
				map.put("uri", Singleton.filePath+"\\"+imageData.getURI());
				map.put("compressUri", Singleton.filePath+"\\compressFile\\"+imageData.getURI());
				map.put("date", imageData.getDate().toString());
				
				fileBytes.add(map);
			}catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
		
		return fileBytes;
		
	}

}
