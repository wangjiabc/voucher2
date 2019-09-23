package com.voucher.manage.daoImpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.voucher.manage.dao.KMeansDao;
import com.voucher.manage.daoModel.RoomInfo;
import com.voucher.manage.daoModelJoin.RoomInfo_Position;
import com.voucher.manage.daoRowMapper.RowMappers;
import com.voucher.manage.tools.TransMapToString;

public class KMeansDAOImpl extends JdbcDaoSupport implements KMeansDao{

	@Override
	public CopyOnWriteArrayList<ArrayList<Double>> findPosition() {
		// TODO Auto-generated method stub
		
		CopyOnWriteArrayList<ArrayList<Double>> cList = null;
		
		String sql="select lng,lat from Position where is_roomInfo=1";
		try {
			Statement sta =this.getConnection().createStatement();
			ResultSet rs = sta.executeQuery(sql.toString());
			while(rs.next()){  
                if(cList == null){  
                	cList = new CopyOnWriteArrayList<ArrayList<Double>>();  
                }  
                ArrayList<Double> a=new ArrayList<>();
                a.add(rs.getDouble("lng"));  
                a.add(rs.getDouble("lat"));
                cList.add(a);
            }  
		} catch (CannotGetJdbcConnectionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return cList;
	}

	@Override
	public Map findAssetByDistance(int limit, int offset, Double lng, Double lat, String search) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map findAssetByLngLat(CopyOnWriteArrayList<ArrayList<Double>> points, int page, Map searchMap) {
		// TODO Auto-generated method stub
		
		int end=page*10;
		
		int max=points.size();
		
		if(max<(page-1)*10)
			return null;
		
		if(max<end)
			end=max;
		
		String sql=" select RoomInfo.GUID,Address,Num,Region,RoomProperty,State,BuildArea,lng,lat from RoomInfo "
				+ " left join Position on RoomInfo.GUID=Position.GUID ";
		
		String sql2=" select count(*) from RoomInfo "
				+ " left join Position on RoomInfo.GUID=Position.GUID ";
		
		String sql0="";
		
		String sql1="";
		
		for(int start=(page-1)*10;start<end;start++){
			ArrayList<Double> arrayList=points.get(start);
			double lng=arrayList.get(0);
			double lat=arrayList.get(1);
			sql0=sql0+lng+",";
			sql1=sql1+lat+",";
		}
		
		sql0=sql0.substring(0,sql0.length()-1);
		
		sql1=sql1.substring(0,sql1.length()-1);
		
		sql=sql+"where lng in ("+sql0+") and lat in ("+sql1+")";
		
		sql2=sql2+"where lng in ("+sql0+") and lat in ("+sql1+")";
		
		if(searchMap!=null&&!searchMap.isEmpty()){
			StringBuilder sb = new StringBuilder();
			
			String[] where=TransMapToString.get(searchMap);
			
			int i=0;
			for(String str : where){
			    
			    if(i%2==0){
			    	sb.append(str);
			    }else{
			    	sb.append("'"+str+"'");
			    	sb.append(" or ");
			    }
			    i++;
			}
			String s = sb.toString();
			
			String serach=s.substring(0,s.length()-4);
			
			System.out.println("serach="+serach);
			
			sql=sql+" AND ("+serach+")";
			
			sql2=sql2+" AND ("+serach+")";
		}
		
		List<Map> list=this.getJdbcTemplate().query(sql, new roomInfo());
		
		int total = (int) this.getJdbcTemplate().queryForMap(sql2).get("");
		
		Map map=new HashMap();
		
		map.put("rows", list);
		
		map.put("total", total);
		
		return map;
	}

	class roomInfo implements RowMapper<Map>{

		@Override
		public Map mapRow(ResultSet rs, int rowNum) throws SQLException {
			// TODO Auto-generated method stub
			
			Map map=new HashMap<>();

			map.put("GUID", rs.getString("GUID"));
			map.put("Address", rs.getString("Address"));
			map.put("Num", rs.getString("Num"));
			map.put("Region", rs.getString("Region"));
			map.put("RoomProperty", rs.getString("RoomProperty"));
			map.put("State", rs.getString("State"));
			map.put("BuildArea", rs.getFloat("BuildArea"));
			map.put("lng", rs.getDouble("lng"));
			map.put("lat", rs.getDouble("lat"));
			
			return map;
		}
		
	}
	
}
