package com.voucher.manage.tools.KMeans;


import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;

import com.voucher.manage.tools.MyTestUtil;

public class BisectingKMeans2 {	
	private int dimension=2;
	private int K=9;
	private double SSError=0.0;
	
	private Random random;
	
	private ArrayList<ArrayList<Double>> initialCentroidForKmeans;
	private Map<Integer,ArrayList<ArrayList<Double>>> allInitialCentroid;

	private PriorityQueue<Cluster> PQ=new PriorityQueue<Cluster>(20, new Comparator<Cluster>(){
		public int compare(Cluster c1, Cluster c2){
			if((c2.getSE()-c1.getSE())>0)
				return 1;
			if((c2.getSE()-c1.getSE())<0)
				return -1;
			else
				return 0;
		}
	});
	
	public BisectingKMeans2(){
		
		new CopyOnWriteArrayList<Integer>();
		initialCentroidForKmeans = new ArrayList<ArrayList<Double>>();
		new ArrayList<ArrayList<Double>>();
		random=new Random();
		allInitialCentroid=new HashMap<Integer, ArrayList<ArrayList<Double>>>();
		
	}
	
	public Map get(CopyOnWriteArrayList<ArrayList<Double>> data) {
		if(data.size()<20){
			return new HashMap<String, Object>();
		}
		long startTime = System.currentTimeMillis();
		Map map = null;
		try {
			map = BiKMeans(data);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		long endTime = System.currentTimeMillis();
        long excTime = endTime - startTime;
        System.out.println("执行时间:" + excTime);
		System.out.println("End");
		return map;
	}
	
	private Map BiKMeans(CopyOnWriteArrayList<ArrayList<Double>> data) throws FileNotFoundException, IOException{
		ArrayList<Double> initialCen=calculateCentroid(data);
		Cluster initialCluster=new Cluster(data,initialCen);
		//double initialSE=calculateSE(initialCluster);
		double initialSE=0;
		for(int i=0;i<data.size();i++){
			initialSE=initialSE+distEclud(initialCen, data.get(i));
		}
		
		initialCluster.setSE(initialSE);
		//double initialSSE=initialCluster.getSE();
		
		SSError=initialCluster.getSE()*2;
		
		//System.out.println("Initial centroid: "+initialCluster.getCentroid());
		//System.out.println("Initial SE: "+initialCluster.getSE());
		
		//twoMeans(initialCluster,initialSSE);
		PQ.add(initialCluster);
		double[] meandistortions = new double[K];
		for(int i=0;i<K;i++){
			Cluster cls=PQ.poll();
			//System.out.println(i+"cls=========");
			//MyTestUtil.print(cls);
			twoMeans2(cls);
			meandistortions[i]=SSError;
			ArrayList<ArrayList<Double>> c=new ArrayList<ArrayList<Double>>();
			for (Cluster cl : PQ) {
				c.add(cl.getCentroid());
			}
			allInitialCentroid.put(i, c);
			//System.out.println("SSE-"+i+": "+SSError);
		}
		
		//PrintWriter out=new PrintWriter("C:\\Users\\WangJing\\Desktop\\tp\\cen.txt");
		
		for (Cluster cl : PQ) {
			initialCentroidForKmeans.add(cl.getCentroid());//set initial centroids for kmean
		}
				
		double sum=0.0;
		double avgs=0.0;
		
		for(int n=0;n<meandistortions.length;n++){
			sum+=meandistortions[n];
		}
		
		avgs=sum/meandistortions.length;
		
		int best=1;
		double bestSSE=0.0;
		for(int n=0;n<meandistortions.length;n++){
			double m=meandistortions[n];
			System.out.println("n="+n+"   avgs="+avgs+"   m="+m);
			if(m>avgs){
				best++;
				if(n<meandistortions.length)
					bestSSE=meandistortions[n];
			}
		}
		
		//System.out.println("initialCentroid===");
		//MyTestUtil.print(initialCentroidForKmeans);
		
		System.out.println("best===="+best);
		System.out.println("bestSSE===="+bestSSE);
		
		ArrayList<ArrayList<Double>> bestCentroid=allInitialCentroid.get(best);
		
		MyTestUtil.print(bestCentroid);
		System.out.println(data.size());
		//run kmeans
		Map map=kMeans(data, bestCentroid, best+1);
		//MyTestUtil.print(data);
		//run kmeans with random
		//kMeans(data, initialCentroidRandom, K);
		return map;
	}
			
	private double distEclud(ArrayList<Double> vecA,ArrayList<Double> vecB){
		double sum=0.0;
		for(int i=0;i<vecA.size();i++){
			sum=sum+Math.sqrt(Math.pow(vecA.get(i)-vecB.get(i), 2));
		}
		return sum;
	}
	
	private void twoMeans2(Cluster cluster) {
		Cluster cluster1 = null;
		Cluster cluster2 = null;
		System.out.println("sseerror="+SSError);
		CopyOnWriteArrayList<ArrayList<Double>> data=cluster.getData();
		double bClusterSE = 0.0;		
		bClusterSE=SSError-cluster.getSE();
		System.out.println("cluster.getSE()="+cluster.getSE()+"  bClusterSE="+bClusterSE);
		System.out.println();
		double prevSSE = bClusterSE;

		int i;
		ArrayList<Double> cen1 = new ArrayList<Double>();
		ArrayList<Double> cen2 = new ArrayList<Double>();
		
		ArrayList<Double> a1 = new ArrayList<Double>();
		ArrayList<Double> a2 = new ArrayList<Double>();
		
		// System.out.println("cen1=="+cen1);
		// System.out.println("cen2=="+cen2);

		CopyOnWriteArrayList<ArrayList<Double>> tempData = new CopyOnWriteArrayList<ArrayList<Double>>(
				cluster.getData());

		double[] min={Double.POSITIVE_INFINITY,Double.POSITIVE_INFINITY};
		double[] max={Double.NEGATIVE_INFINITY,Double.NEGATIVE_INFINITY};
		
		for (int n = 0; n < data.size(); n++) {
			ArrayList<Double> temp = data.get(n);
			for (int m = 0; m < dimension; m++) {

				if (temp.get(m) < min[m])
					min[m] = temp.get(m);

				if (temp.get(m) > max[m])
					max[m] = temp.get(m);

			}
		}
		
		for (i = 0; i < dimension; i++) {
			double rand = min[i] + (max[i] - min[i]) * random.nextDouble();

			a1.add(rand);

		}
		ArrayList<Double> clCen = cluster.getCentroid();

		for (i = 0; i < dimension; i++) {
			a2.add(2 * clCen.get(i) - a1.get(i));
		}

		ArrayList<ArrayList<Double>> ptsInCurrCluster = new ArrayList();
		
		ptsInCurrCluster.add(a1);
		ptsInCurrCluster.add(a2);
		
		boolean isChange=true;
		
		while (isChange) {
			isChange=false;

			try {
				Map<Integer, Object> map = kMeans(tempData, ptsInCurrCluster, 2);
				MyTestUtil.print(map);

				//double se1 = 0.0;
				//double se2 = 0.0;

				//ArrayList<ArrayList> clusterAssment = null;

				CopyOnWriteArrayList<ArrayList<Double>> c1 = new CopyOnWriteArrayList<>();
				CopyOnWriteArrayList<ArrayList<Double>> c2 = new CopyOnWriteArrayList<>();

				double sse1 = 0,sse2 = 0;
				
				for (Map.Entry<Integer, Object> entry : map.entrySet()) {
					Map<String, Object> map2 = (Map<String, Object>) entry.getValue();
					ArrayList<Double> bestCentroid = (ArrayList<Double>) map2.get("centroid");
					CopyOnWriteArrayList<ArrayList<Double>> bestData = (CopyOnWriteArrayList<ArrayList<Double>>) map2
							.get("points");
					//clusterAssment = (ArrayList<ArrayList>) map2.get("clusterAssment");
					double sse=(double) map2.get("sse");
					if (entry.getKey() == 0) {
						cen1.add(bestCentroid.get(0));
						cen1.add(bestCentroid.get(1));
						c1 = bestData;
						sse1=sse;
					} else if (entry.getKey() == 1) {
						c2 = bestData;
						cen2.add(bestCentroid.get(0));
						cen2.add(bestCentroid.get(1));
						sse2=sse;
					}

				}
			
				double se1=0;
				double se2=0;

				//System.out.println("sse1="+sse1);
				//System.out.println("sse2="+sse2);
				
				Cluster cl1 = new Cluster(c1, cen1);
				Cluster cl2 = new Cluster(c2, cen2);

				cl1.setSE(sse1);
				cl2.setSE(sse2);
				
				//System.out.println("se1====" + se1);
				//System.out.println("se2====" + se2);
				//System.out.println("prevSSE====" + prevSSE);
				//System.out.println("(se1 + se2) < prevSSE====" + ((sse1 + sse2) < prevSSE));
				if ((cl1.getSE()+cl2.getSE()) < prevSSE) {
					cluster1 = new Cluster(cl1);
					cluster2 = new Cluster(cl2);
					prevSSE = bClusterSE+sse1 + sse2;
				}else{
					isChange=true;
				}
				//System.out.println("isChange====" + isChange);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		// clusters.add(cluster1);
		// clusters.add(cluster2);
		PQ.add(cluster1);
		PQ.add(cluster2);
		SSError = prevSSE;

	}
	
	private Map kMeans(CopyOnWriteArrayList<ArrayList<Double>> data, ArrayList<ArrayList<Double>> initialCentroid ,int k) throws FileNotFoundException{		
		int i,j;
		ArrayList<ArrayList<Double>> prevCen=new ArrayList<ArrayList<Double>>(initialCentroid);
		ArrayList<Cluster> clusters=new ArrayList<Cluster>();
		for(i=0;i<k;i++){
			clusters.add(new Cluster());
			clusters.get(i).setCentroid(initialCentroid.get(i));
		}
		int size=data.size();
		ArrayList<ArrayList> clusterAssment=new ArrayList<ArrayList>();
		for(i=0;i<size;i++){
			ArrayList arrayList=new ArrayList<>();
			for(j=0;j<dimension;j++){				
				arrayList.add(0);
			}
			clusterAssment.add(arrayList);
		}
		double minDis=99999999.0;
		int minIndex=0;
		double dis;

		// add data to clusters
		boolean clusterChanged=true;
		while(clusterChanged){
			clusterChanged=false;
			//add data to clusters
			for (int c = 0; c < k; c++) {
				clusters.get(c).getData().clear();
				clusters.get(c).setSE(0);
			}
			for (i = 0; i < size; i++) {
				minDis = 99999999.0;
				minIndex = -1;
				ArrayList<Double> temp = data.get(i);
				for (int c = 0; c < k; c++) { // for each cluster
					dis = 0;
					ArrayList<Double> cen = clusters.get(c).getCentroid();
					dis = distEclud(temp, cen);
						if (dis < minDis) {
							minDis = dis;
							minIndex = c;	
						}
					}
				int current = (int) clusterAssment.get(i).get(0);
				if (current != minIndex)
					clusterChanged = true;
				clusterAssment.get(i).set(0, minIndex);
				clusterAssment.get(i).set(1, minDis);
			}

			for(int c=0;c<k;c++){
				double a=0.0;
				double b=0.0;
				int m=0;
				for(int n=0;n<size;n++){
					int current=(int) clusterAssment.get(n).get(0);
					if(current==c){
						a=a+data.get(n).get(0);
						b=b+data.get(n).get(1);
						clusters.get(c).getData().add(data.get(n));
						m++;
					}
				}
				double c1=a/m;
				double c2=b/m;
				clusters.get(c).getCentroid().set(0,c1);
				clusters.get(c).getCentroid().set(1,c2);	
			
				double d=0;
				for(int r=0;r<size;r++){
					int current = (int) clusterAssment.get(r).get(0);
					if (current == c) {
						ArrayList<Double> arrayList = new ArrayList<>();
						arrayList.add(c1);
						arrayList.add(c2);
						d = d+distEclud(data.get(r), arrayList);
					}
				}
				clusters.get(c).setSE(d);
			}
			
		}

		//PrintWriter out=new PrintWriter("C:\\Users\\WangJing\\Desktop\\tp\\kmeanCen.txt");
		//System.out.println("K-Means result:");
		Map<Integer,Map<String,Object>> map=new HashMap<>();
		i=0;
		for (Cluster cl : clusters) {
			Map<String,Object> map2=new HashMap<String, Object>();
			//System.out.println("Cluster: "+i);
			//System.out.println("\tC: "+cl.getCentroid());
			//System.out.println("\tPoints: "+cl.getData().size());
			//System.out.println("\tPoints: "+cl.getData());
			map2.put("centroid", cl.getCentroid());
			map2.put("size", cl.getData().size());
			map2.put("points", cl.getData());
			//map2.put("clusterAssment", clusterAssment);
			map2.put("sse", cl.getSE());
			if(cl.getData().size()>20)
				map2.put("isPlot", true);
			else
				map2.put("isPolt", false);
			map.put(i, map2);
			//System.out.println("\tSEE: "+cl.getSE());
			i++;
		}
		//out.close();
		
		return map;
	}
	
	
	private ArrayList<Double> calculateCentroid(CopyOnWriteArrayList<ArrayList<Double>> tData){
		ArrayList<Double> cen=new ArrayList<Double>();
		int i,j;
		for(i=0;i<dimension;i++){
			cen.add(0.0);
		}
		
		int size=tData.size();
		for(i=0;i<size;i++){
			ArrayList<Double> temp=tData.get(i);
			for(j=0;j<dimension;j++){
				cen.set(j, (cen.get(j)+temp.get(j)));
			}
		}
		
		for(j=0;j<dimension;j++){
			cen.set(j, (cen.get(j)/size));
		}
		
		return cen;
	}

}


