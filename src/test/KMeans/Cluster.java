package KMeans;

import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;

import KMeans.Cluster;

public class Cluster{
	private double se;
	private ArrayList<Double> centroid;
	private CopyOnWriteArrayList<ArrayList<Double>> data;
	
	public Cluster(){
		se=0;
		centroid=new ArrayList<Double>();
		data=new CopyOnWriteArrayList<ArrayList<Double>>();
	}
	public Cluster(CopyOnWriteArrayList<ArrayList<Double>> pData, ArrayList<Double> cen){
		centroid=new ArrayList<Double>(cen);
		data=new CopyOnWriteArrayList<ArrayList<Double>>(pData);
	}
	public Cluster(Cluster cl){
		centroid=new ArrayList<Double>(cl.getCentroid());
		data=new CopyOnWriteArrayList<ArrayList<Double>>(cl.getData());
		se=cl.getSE();
	}
	public CopyOnWriteArrayList<ArrayList<Double>> getData(){
		return data;
	}
	public ArrayList<Double> getCentroid(){
		return centroid;
	}
	public void setCentroid(ArrayList<Double> cen){
		centroid=new ArrayList<Double>(cen);
	}
	public double getSE(){
		return se;
	}
	public void setSE(double serror){
		se=serror;
	}
	public void updateCentroid(int dim){
		if(data.size()==0){
			return;
		}
		int i,j;
		ArrayList<Double> newCen=new ArrayList<Double>();
		for(i=0;i<dim;i++){
			newCen.add(0.0);
		}
		int size=data.size();
		
		for(i=0;i<size;i++){
			ArrayList<Double> temp=data.get(i);
			for(j=0;j<dim;j++){
				newCen.set(j, (newCen.get(j)+temp.get(j)));
			}
		}
		for(j=0;j<dim;j++){
			newCen.set(j, (newCen.get(j)/size));
		}
		centroid=new ArrayList<Double>(newCen);
	}
}