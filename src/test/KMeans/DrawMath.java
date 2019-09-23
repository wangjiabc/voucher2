package KMeans;

import java.awt.BasicStroke;
import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

import javax.swing.JFrame;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.DefaultXYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import com.voucher.manage.tools.MyTestUtil;
 
public class DrawMath {
 
	private static Map<Integer, Object> map=new HashMap();
	
	private static int size;
	
	public DrawMath(Map<Integer, Object> map,int size) {
		// TODO Auto-generated constructor stub
		this.map=map;
		this.size=size;
	}
	
	public static void MyFrame() {
 
		DefaultXYDataset xydataset = new DefaultXYDataset(); 
		
		double [][]datas = new double[2][size];
		int n=0;
		int j=0;
		for (Map.Entry<Integer, Object> entry : map.entrySet()) {
			Map<String, Object> map2 = (Map<String, Object>) entry.getValue();
			CopyOnWriteArrayList<ArrayList<Double>> bestData = (CopyOnWriteArrayList<ArrayList<Double>>) map2
					.get("points");
			
			for(int i=0;i<bestData.size();i++){
				ArrayList<Double> a=bestData.get(i);
				Double a1=a.get(0);
				Double a2=a.get(1);
				datas[0][n]=a1;
				datas[1][n]=a2;
				n++;
				
			}			
			j++;
    	}
		
		ArrayList<double[]> arrayList=new ArrayList<>();
		
		int m=0;
		
		for (Map.Entry<Integer, Object> entry : map.entrySet()) {
			Map<String, Object> map2 = (Map<String, Object>) entry.getValue();
			ArrayList<Double> bestCentroid = (ArrayList<Double>) map2.get("centroid");
			Double a1=bestCentroid.get(0);
			Double a2=bestCentroid.get(1);
			double[] ds={a1,a2};
			arrayList.add(ds);
			System.out.println(m);
			m++;			
    	}
		
		double[][] ds=new double[2][m];;
		
		for(int i=0;i<arrayList.size();i++){
			ds[0][i]=arrayList.get(i)[0];
			ds[1][i]=arrayList.get(i)[1];
			System.out.print(i);
		}
		
		xydataset.addSeries(0,datas);
		xydataset.addSeries(1, ds);

		
		 
		

		JFreeChart chart = ChartFactory.createScatterPlot("Driving record", "longitude", "latitude", xydataset,
				PlotOrientation.VERTICAL, true, true, true);
		ChartFrame frame = new ChartFrame("散点图", chart, true);
		chart.setBackgroundPaint(Color.white);
		chart.setBorderPaint(Color.GREEN);
		chart.setBorderStroke(new BasicStroke(1.5f));
		XYPlot xyplot = (XYPlot) chart.getPlot();

		xyplot.setBackgroundPaint(new Color(255, 253, 246));
		ValueAxis vaaxis = xyplot.getDomainAxis();
		vaaxis.setAxisLineStroke(new BasicStroke(1.5f));

		ValueAxis va = xyplot.getDomainAxis(0);
		va.setAxisLineStroke(new BasicStroke(1.5f));

		va.setAxisLineStroke(new BasicStroke(1.5f)); // 坐标轴粗细
		va.setAxisLinePaint(new Color(215, 215, 215)); // 坐标轴颜色
		xyplot.setOutlineStroke(new BasicStroke(1.5f)); // 边框粗细
		va.setLabelPaint(new Color(10, 10, 10)); // 坐标轴标题颜色
		va.setTickLabelPaint(new Color(102, 102, 102)); // 坐标轴标尺值颜色
		ValueAxis axis = xyplot.getRangeAxis();
		axis.setAxisLineStroke(new BasicStroke(1.5f));

		XYLineAndShapeRenderer xylineandshaperenderer = (XYLineAndShapeRenderer) xyplot.getRenderer();
		xylineandshaperenderer.setSeriesOutlinePaint(0, Color.WHITE);
		xylineandshaperenderer.setUseOutlinePaint(true);
		NumberAxis numberaxis = (NumberAxis) xyplot.getDomainAxis();
		numberaxis.setAutoRangeIncludesZero(false);
		numberaxis.setTickMarkInsideLength(2.0F);
		numberaxis.setTickMarkOutsideLength(0.0F);
		numberaxis.setAxisLineStroke(new BasicStroke(1.5f));

		frame.pack();
		frame.setVisible(true);

	}
	
	
	
}
