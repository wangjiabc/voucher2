package KMeans;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.CopyOnWriteArrayList;

import com.voucher.manage.tools.MyTestUtil;

import KMeans.Main.MyFrame;


public class Run {
	
	private static int dimension=2;
	
	public static void main(String[] args) throws FileNotFoundException {
		
		PrintWriter out=new PrintWriter("C:\\Users\\WangJing\\Desktop\\tp\\points.txt");
		
		 SqlServerTest t  = new SqlServerTest();  
		 CopyOnWriteArrayList<ArrayList<Double>> names = t.getAllTables("mssql", "");  
	        for (int i = 0; i < names.size(); i++) {  
	           // System.out.println(names.get(i));  
	          //  System.out.println(t.getTableStruct(names.get(i),"mssql"));  
	        	//out.write(names.get(i).toString()+",");
	        	//out.write("\n");
	        }  
	        out.close();
		// TODO Auto-generated method stub
		String fileName="C:\\Users\\WangJing\\Desktop\\python\\machinelearninginaction\\Ch10\\testSet.txt";
		
		//CopyOnWriteArrayList<ArrayList<Double>> data =readData(fileName);
		CopyOnWriteArrayList<ArrayList<Double>> data =names;		
		//new BisectingKMeans(data);
		BisectingKMeans2 bisectingKMeans2=new BisectingKMeans2();
		Map<Integer, Object> map=bisectingKMeans2.get(data);
		new DrawMath(map,data.size());
		DrawMath.MyFrame();
		
		for (Map.Entry<Integer, Object> entry : map.entrySet()) {
			Map<String, Object> map2 = (Map<String, Object>) entry.getValue();
			ArrayList<Double> bestCentroid = (ArrayList<Double>) map2.get("centroid");
			int size=(int) map2.get("size");
			MyTestUtil.print(bestCentroid);
			System.out.print("size="+size);
		}
		
	}
	
	private static CopyOnWriteArrayList<ArrayList<Double>> readData(String fileName) throws FileNotFoundException{
		int i;
        Scanner in = new Scanner(new FileReader(new File(fileName)));
        
        CopyOnWriteArrayList<ArrayList<Double>> data = new CopyOnWriteArrayList<ArrayList<Double>>();
        
        //dimension=in.nextInt();
        //classCount=in.nextInt();
        //dataCount=in.nextInt();
        //int j=0;
        
        
        while(in.hasNext()){
        	ArrayList<Double> temp=new ArrayList<Double>();
        	//System.out.println("Line: "+j);
             for(i=0;i<dimension;i++){
            	 //System.out.println("Line elem: "+i);
            	 temp.add(in.nextDouble());
             }
             //classList.add(in.nextInt());  //if no class is given, comment this out
             data.add(temp);
             //j++;
        }
        
        in.close();
        
        return data;
        //System.out.println("input taken");
	}
}

class SqlServerTest {  
	  
    private static String JDBCURL= "jdbc:jtds:sqlserver://localhost:1433;DatabaseName=TTT";  
    private static String USER = "sa";  
    private static String PWD = "123";  
    private static Connection con = null;  
    private static Map<String,String> type= new HashMap<String, String>();  
      
    static{  
        try {  
            if(type != null){  
                type.put("int", "number");  
                type.put("text", "blob");  
                type.put("nvarchar", "varchar2");  
            }  
            Class.forName("net.sourceforge.jtds.jdbc.Driver");  
        } catch (ClassNotFoundException e) {  
            // TODO Auto-generated catch block  
            e.printStackTrace();  
        }  
    }  
      
           
    /** 
     * 获取连接 
     * @return 
     */  
    public static Connection getConnection(){  
          
        if(con == null){  
            try {  
                con = DriverManager.getConnection(JDBCURL,USER,PWD);  
            } catch (SQLException e) {  
                // TODO Auto-generated catch block  
                e.printStackTrace();  
            }  
        }  
        return con;  
    }  
    /** 
     * 
     * @param type 
     * @param database 
     * @return 
     */  
    public CopyOnWriteArrayList<ArrayList<Double>> getAllTables(String type,String database){  
          
    	CopyOnWriteArrayList<ArrayList<Double>> list = null;  
        if("mssql".equals(type)){  
            //查询所有用户表  
            StringBuffer sql = new StringBuffer("Select lng,lat FROM ")  
                               .append(database)  
                               .append("Position");  
            Statement sta = null;  
            ResultSet rs = null;  
            try {  
                sta = SqlServerTest.getConnection().createStatement();  
                rs = sta.executeQuery(sql.toString());  
                MyTestUtil.print(rs);
                while(rs.next()){  
                    if(list == null){  
                        list = new CopyOnWriteArrayList<ArrayList<Double>>();  
                    }  
                    ArrayList<Double> a=new ArrayList<>();
                    a.add(rs.getDouble("lng"));  
                    a.add(rs.getDouble("lat"));
                    list.add(a);
                }  
            } catch (SQLException e) {  
                e.printStackTrace();  
            }finally{  
                try {  
                    if(rs != null)  
                        rs.close();  
                    if(sta != null)  
                        sta.close();  
                } catch (SQLException e) {  
                    e.printStackTrace();  
                }  
            }  
        }  
        return list;  
    }  

}