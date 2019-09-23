package com.voucher.manage.tools.KMeans;


import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;

import com.voucher.manage.tools.MyTestUtil;

public class BisectingKMeans {
	private final int NIteration=3;	
	private int dimension=2;
	private int K=9;
	private double SSError=0;
	
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
	
	public BisectingKMeans(){
		
		new CopyOnWriteArrayList<Integer>();
		initialCentroidForKmeans = new ArrayList<ArrayList<Double>>();
		new ArrayList<ArrayList<Double>>();
		random=new Random();
		allInitialCentroid=new HashMap<Integer, ArrayList<ArrayList<Double>>>();
		
	}
	
	public Map get(CopyOnWriteArrayList<ArrayList<Double>> data) {
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
		double initialSE=calculateSE(initialCluster);
		initialCluster.setSE(initialSE);
		//double initialSSE=initialCluster.getSE();
		
		SSError=initialCluster.getSE();
		
		//System.out.println("Initial centroid: "+initialCluster.getCentroid());
		//System.out.println("Initial SE: "+initialCluster.getSE());
		
		//twoMeans(initialCluster,initialSSE);
		PQ.add(initialCluster);
		double[] meandistortions = new double[10];
		meandistortions[0]=SSError;
		for(int i=1;i<K;i++){
			Cluster cls=PQ.poll();
			//System.out.println(i+"cls=========");
			//MyTestUtil.print(cls);
			twoMeans(cls);
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
					bestSSE=meandistortions[n+1];
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
	
	private void twoMeans(Cluster cluster){
		Cluster cluster1=null;
		Cluster cluster2=null;
		
		double bClusterSE=cluster.getSE();
		double updatedSSE=SSError-bClusterSE;
		double prevSSE=updatedSSE;
		
		CopyOnWriteArrayList<ArrayList<Double>> data=cluster.getData();
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
		
		System.out.println("min+max");
		System.out.println(min[0]+"   "+min[1]);
		System.out.println(max[0]+"   "+max[1]);
		
		
		int itr;
		for(itr=0;itr<NIteration;itr++){
			//System.out.println("itr======"+itr);
			ArrayList<Double> cen1=new ArrayList<Double>();
			ArrayList<Double> cen2=new ArrayList<Double>();
			int i,j;
			//initialization of 2 centroids of bisection
			for(i=0;i<dimension;i++){
				double rand=min[i]+(max[i]-min[i])*random.nextDouble();
				cen1.add(rand);
			
				//cen2.add(B*random.nextDouble());
			}
			ArrayList<Double> clCen=cluster.getCentroid();
			for(i=0;i<dimension;i++){
				cen2.add(2*clCen.get(i)-cen1.get(i));
			}
			
			//System.out.println("cen1=="+cen1);
			//System.out.println("cen2=="+cen2);
			
			CopyOnWriteArrayList<ArrayList<Double>> tempData=new CopyOnWriteArrayList<ArrayList<Double>>(cluster.getData());
			
			CopyOnWriteArrayList<ArrayList<Double>> c1=new CopyOnWriteArrayList<ArrayList<Double>>();
			CopyOnWriteArrayList<ArrayList<Double>> c2=new CopyOnWriteArrayList<ArrayList<Double>>();

			double dis1,dis2;
			int size=tempData.size();
			while(true){

				//保留以前的质心
				ArrayList<Double> prevCen1=new ArrayList<Double>(cen1);
				ArrayList<Double> prevCen2=new ArrayList<Double>(cen2);
				//clear 2 clusters to start again
				c1.clear();
				c2.clear();
				
				//set points in cluster
				for(i=0;i<size;i++){
					dis1=dis2=0;
					ArrayList<Double> temp=tempData.get(i);
					for(j=0;j<dimension;j++){
						dis1+=(temp.get(j)-cen1.get(j))*(temp.get(j)-cen1.get(j));
						dis2+=(temp.get(j)-cen2.get(j))*(temp.get(j)-cen2.get(j));
					}
					if(dis2>dis1){
						c1.add(temp);
					}
					else{
						c2.add(temp);
					}
				}
				
				//update centroid
				int size1=c1.size();
				int size2=c2.size();
				ArrayList<Double> newCen=new ArrayList<Double>();
				//initial newcentroid all 0
				for(i=0;i<dimension;i++){
					newCen.add(0.0);
				}
				//update centroid for c1
				for(i=0;i<size1;i++){
					ArrayList<Double> temp=c1.get(i);
					for(j=0;j<dimension;j++){
						newCen.set(j, (newCen.get(j)+temp.get(j)));
					}
				}
				for(i=0;i<dimension;i++){
					cen1.set(i, newCen.get(i)/size1);
				}
				
				//update centroid for c2
				//initial newcentroid all 0
				newCen.clear();
				for(i=0;i<dimension;i++){
					newCen.add(0.0);
				}
				
				for(i=0;i<size2;i++){
					ArrayList<Double> temp=c2.get(i);
					for(j=0;j<dimension;j++){
						newCen.set(j, (newCen.get(j)+temp.get(j)));
					}
				}
				for(i=0;i<dimension;i++){
					cen2.set(i, newCen.get(i)/size2);
				}
				
				//check breaking condition
				dis1=dis2=0;
				for(i=0;i<dimension;i++){
					dis1+=(prevCen1.get(i)-cen1.get(i))*(prevCen1.get(i)-cen1.get(i));
					dis2+=(prevCen2.get(i)-cen2.get(i))*(prevCen2.get(i)-cen2.get(i));
				}
				dis1=Math.sqrt(dis1);
				dis2=Math.sqrt(dis2);
				//System.out.println("dis1="+dis1);
				//System.out.println("dis2="+dis2);
				if(dis1<.001 && dis2<.001){//质心的变化非常小
					break;
				}
			}
			
			Cluster cl1=new Cluster(c1,cen1);
			Cluster cl2=new Cluster(c2,cen2);
			
			double se1=calculateSE(cl1);
			double se2=calculateSE(cl2);
			cl1.setSE(se1);
			cl2.setSE(se2);
			
			
			if(itr==0){
				cluster1= new Cluster(cl1);
				cluster2= new Cluster(cl2);
				prevSSE=updatedSSE+se1+se2;
			}
			
			else if((updatedSSE+se1+se2)<prevSSE){
				cluster1= new Cluster(cl1);
				cluster2= new Cluster(cl2);
				prevSSE=updatedSSE+se1+se2;
			}
			
			
			
		}
		//System.out.println("prevSSE===="+prevSSE);
		//clusters.add(cluster1);
		//clusters.add(cluster2);
		PQ.add(cluster1);
		PQ.add(cluster2);
		SSError=prevSSE;
		
	}
	
	private double distEclud(ArrayList<Double> vecA,ArrayList<Double> vecB){
		double sum=0.0;
		for(int i=0;i<vecA.size();i++){
			sum=sum+Math.pow(vecA.get(i)-vecB.get(i), 2);
		}
		return sum;
	}
	
	private void twoMeans2(Cluster cluster) {
		Cluster cluster1 = null;
		Cluster cluster2 = null;

		double bClusterSE = cluster.getSE();
		double updatedSSE = SSError - bClusterSE;
		double prevSSE = updatedSSE;

		CopyOnWriteArrayList<ArrayList<Double>> data = cluster.getData();

		int i, j;
		int size = data.size();

		ArrayList<Double> cen1 = new ArrayList<Double>();
		ArrayList<Double> cen2 = new ArrayList<Double>();

		// System.out.println("cen1=="+cen1);
		// System.out.println("cen2=="+cen2);

		CopyOnWriteArrayList<ArrayList<Double>> tempData = new CopyOnWriteArrayList<ArrayList<Double>>(
				cluster.getData());

		CopyOnWriteArrayList<ArrayList<Double>> c1 = new CopyOnWriteArrayList<ArrayList<Double>>();
		CopyOnWriteArrayList<ArrayList<Double>> c2 = new CopyOnWriteArrayList<ArrayList<Double>>();

		double dis1, dis2;
		size = tempData.size();

		// clear 2 clusters to start again
		c1.clear();
		c2.clear();

		ArrayList<ArrayList<Double>> ptsInCurrCluster = new ArrayList();
		ArrayList<Double> arrayList = new ArrayList<>();
		arrayList.add(cluster.getCentroid().get(0));
		ptsInCurrCluster.add(arrayList);
		
		try {
			Map map = kMeans(tempData, ptsInCurrCluster, 2);

			ArrayList<Double> bestCentroid=(ArrayList<Double>) map.get("centroid");
			cen1.add(bestCentroid.get(0));
			cen2.add(bestCentroid.get(1));
			Cluster cl1 = new Cluster(c1, cen1);
			Cluster cl2 = new Cluster(c2, cen2);
			
			double se1=0.0;
			double se2=0.0;

			ArrayList<ArrayList> clusterAssment=(ArrayList<ArrayList>) map.get("clusterAssment");
			
			for(i=0;i<clusterAssment.size();i++){
				double s1=(double) clusterAssment.get(i).get(0);
				se1=se1+s1;
				double s2=(double) clusterAssment.get(i).get(1);
				se2=se2+s2;
			}
			
			if ((updatedSSE + se1 + se2) < prevSSE) {
				cluster1 = new Cluster(cl1);
				cluster2 = new Cluster(cl2);
				prevSSE = updatedSSE + se1 + se2;
			}

			// System.out.println("prevSSE===="+prevSSE);
			// clusters.add(cluster1);
			// clusters.add(cluster2);
			PQ.add(cluster1);
			PQ.add(cluster2);
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

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
		double sse=0;
		// add data to clusters
		boolean clusterChanged=true;
		while(clusterChanged){
			clusterChanged=false;
			//add data to clusters
			sse=0;
			for(i=0;i<size;i++){
				minDis=99999999.0;
				minIndex=-1;
				ArrayList<Double> temp=data.get(i);
				for(int c=0;c<k;c++){ //for each cluster
					dis=0;
					ArrayList<Double> cen=clusters.get(c).getCentroid();//get centroid of the cluster
					for(j=0;j<dimension;j++){
						dis+=((temp.get(j)-cen.get(j))*(temp.get(j)-cen.get(j)));
						sse=sse+dis;
					}
					if(dis<minDis){ //check if less distance is found or not
						minDis=dis;
						minIndex=c;
					}
				}
				int current=(int) clusterAssment.get(i).get(0);
				if(current!=minIndex)
					clusterChanged=true;
				clusterAssment.get(i).set(0,minIndex);
				clusterAssment.get(i).set(1, Math.pow(minDis,2));
			}
			

			for (int c = 0; c < k; c++) {
				clusters.get(c).getData().clear();
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

				clusters.get(c).getCentroid().set(0,a/m);
				clusters.get(c).getCentroid().set(1,b/m);

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
			map2.put("clusterAssment", clusterAssment);
			if(cl.getData().size()>20)
				map2.put("isPlot", true);
			else
				map2.put("isPolt", false);
			map.put(i, map2);
			//System.out.println("\tSE: "+cl.getSE());
			i++;
		}
		//out.close();
		
		return map;
	}
	
	private double calculateSE(Cluster cluster){
		ArrayList<Double> cen=cluster.getCentroid();
		CopyOnWriteArrayList<ArrayList<Double>> tempData=cluster.getData();
		int size=tempData.size();
		int i,j;
		double error=0;
		for(i=0;i<size;i++){
			ArrayList<Double> temp=tempData.get(i);
			for(j=0;j<dimension;j++){
				error+=(temp.get(j)-cen.get(j))*(temp.get(j)-cen.get(j));
			}
		}
		
		return error;
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


