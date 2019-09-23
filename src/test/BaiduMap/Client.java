package BaiduMap;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.voucher.manage.tools.MyTestUtil;

public class Client {
	  public static void main(String[] args){
	        String path = "C:\\Users\\WangJing\\Desktop\\python\\machinelearninginaction\\Ch10\\testSet.txt";	       
	        KMeans kMeans = new KMeans(path, 5);
	        Map map=kMeans.doKMeans();
	        
	        List<Point> centerPoints = (List<Point>) map.get("centerPoints");
	        HashMap<Integer, List<Point>> clusters = (HashMap<Integer, List<Point>>) map.get("clusters");
	        
	        kMeans.show(centerPoints, clusters);
	        
	    }
}
