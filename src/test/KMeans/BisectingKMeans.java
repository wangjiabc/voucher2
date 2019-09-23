package KMeans;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;

import com.voucher.manage.tools.MyTestUtil;

import KMeans.Cluster;

public class BisectingKMeans {
	private final int NIteration=10;	
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
	
	public BisectingKMeans(CopyOnWriteArrayList<ArrayList<Double>> data) throws IOException {
		long startTime = System.currentTimeMillis();
		new CopyOnWriteArrayList<Integer>();
		initialCentroidForKmeans = new ArrayList<ArrayList<Double>>();
		new ArrayList<ArrayList<Double>>();
		random=new Random();
		allInitialCentroid=new HashMap<Integer, ArrayList<ArrayList<Double>>>();
		BiKMeans(data);
		long endTime = System.currentTimeMillis();
        long excTime = endTime - startTime;
        System.out.println("执行时间:" + excTime);
		System.out.println("End");
	}
	

	
	private void BiKMeans(CopyOnWriteArrayList<ArrayList<Double>> data) throws FileNotFoundException, IOException{
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
			System.out.println(i+"cls=========");
			MyTestUtil.print(cls);
			twoMeans(cls);
			meandistortions[i]=SSError;
			ArrayList<ArrayList<Double>> c=new ArrayList<ArrayList<Double>>();
			for (Cluster cl : PQ) {
				c.add(cl.getCentroid());
			}
			allInitialCentroid.put(i, c);
			//System.out.println("SSE-"+i+": "+SSError);
		}
		
		PrintWriter out=new PrintWriter("C:\\Users\\WangJing\\Desktop\\tp\\cen.txt");
		
		int i=0;
		for (Cluster cl : PQ) {
			System.out.println("Cluster: "+i);
			System.out.println("\tC: "+cl.getCentroid());
			//System.out.println("\tSE: "+cl.getSE());
			i++;
			initialCentroidForKmeans.add(cl.getCentroid());//set initial centroids for kmean
			ArrayList<Double> cent=cl.getCentroid();
			for(int j=0;j<dimension;j++){
				out.write(cent.get(j).toString()+" ");
			}
			out.write("\n");
			out.write(cl.getData().toString()+" ");			
			out.write("\r\n");
		}
		out.close();
		//System.out.println("Bisection SSE: "+SSError);
				
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
		
		ArrayList<ArrayList<Double>> bestCentroid=allInitialCentroid.get(best-1);
		
		MyTestUtil.print(bestCentroid);
		System.out.println(data.size());
		//run kmeans
		kMeans(data, bestCentroid, best);
		//MyTestUtil.print(data);
		//run kmeans with random
		//kMeans(data, initialCentroidRandom, K);
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
		/*
		System.out.println("min+max");
		System.out.println(min[0]+"   "+min[1]);
		System.out.println(max[0]+"   "+max[1]);
		*/
		
		int itr;
		for(itr=0;itr<NIteration;itr++){
			//System.out.println("itr======"+itr);
			ArrayList<Double> cen1=new ArrayList<Double>();
			ArrayList<Double> cen2=new ArrayList<Double>();
			int i,j;
			//initialization of 2 centroids of bisection
			for(i=0;i<dimension;i++){

				cen1.add(min[i]+(max[i]-min[i])*random.nextDouble());
				//System.out.println("B*random=="+(min[i]+(max[i]-min[i])*random.nextDouble()));
				//cen2.add(B*random.nextDouble());
			}
			ArrayList<Double> clCen=cluster.getCentroid();
			for(i=0;i<dimension;i++){
				cen2.add(2*clCen.get(i)-cen1.get(i));
			}
			
			
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
	
	private void kMeans(CopyOnWriteArrayList<ArrayList<Double>> data, ArrayList<ArrayList<Double>> initialCentroid ,int k) throws FileNotFoundException{		
		int i,j;
		ArrayList<Cluster> clusters=new ArrayList<Cluster>();
		for(i=0;i<k;i++){
			clusters.add(new Cluster());
			clusters.get(i).setCentroid(initialCentroid.get(i));
		}
		MyTestUtil.print(clusters);
		for (Cluster cl : clusters) {
			System.out.println("\t0Points: "+cl.getData());
		}
		int size=data.size();
		System.out.println("size="+size);
		double minDis=99999999.0;
		int minCluster=0;
		double dis;
		double sse=0;
		//int loop=0;

		// add data to clusters
		for (i = 0; i < size; i++) {
			minDis = 99999999.0;
			minCluster = 0;
			ArrayList<Double> temp = data.get(i);
			for (int c = 0; c < k; c++) { // for each cluster
				dis = 0;
				ArrayList<Double> cen = clusters.get(c).getCentroid();// 得到集群的质心
				for (j = 0; j < dimension; j++) {
					dis += ((temp.get(j) - cen.get(j)) * (temp.get(j) - cen.get(j)));
				}
				if (dis < minDis) { // 检查是否找到较少的距离
					minDis = dis;
					minCluster = c;
				}
			}
			clusters.get(minCluster).getData().add(temp);
		}
	

		PrintWriter out=new PrintWriter("C:\\Users\\WangJing\\Desktop\\tp\\kmeanCen.txt");
		System.out.println("K-Means result:");
		i=0;
		for (Cluster cl : clusters) {
			System.out.println("Cluster: "+i);
			System.out.println("\tC: "+cl.getCentroid());
			System.out.println("\tPoints: "+cl.getData().size());
			System.out.println("\tPoints: "+cl.getData());
			//System.out.println("\tSE: "+cl.getSE());
			i++;
			ArrayList<Double> cent=cl.getCentroid();
			for(j=0;j<dimension;j++){
				out.write(cent.get(j).toString()+" ");
			}
			out.write("\n");
			out.write(cl.getData().toString()+" ");			
			out.write("\r\n");
		}
		out.close();
		
		System.out.println("K-means SSE: "+sse );
		
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


