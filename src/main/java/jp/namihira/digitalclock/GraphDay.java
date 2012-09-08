package jp.namihira.digitalclock;

import java.awt.BorderLayout;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import javax.swing.JDialog;
import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.StandardChartTheme;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

public class GraphDay extends JPanel {

	private List<String> date = new ArrayList<String>();
	private DefaultCategoryDataset data = new DefaultCategoryDataset();
	String year = "2011";
	
	public GraphDay() {
		ChartFactory.setChartTheme(StandardChartTheme.createLegacyTheme());
		draw();
	}
	
	private void draw() {
		
		readFromCSV();
		
		JFreeChart chart = ChartFactory.createBarChart3D("谿区･ｭ譎る俣[譎る俣]",
		                                   "譌･",
		                                   "谿区･ｭ譎る俣[h]",
		                                   data,
		                                   PlotOrientation.VERTICAL,
		                                   false,
		                                   false,
		                                   false);
		ChartPanel cpanel = new ChartPanel(chart);
		add(cpanel, BorderLayout.CENTER);
		
		File file = new File("img/GraphDay.jpg");
	    try {
	    	ChartUtilities.saveChartAsJPEG(file, chart, 400, 300);
	    } catch (IOException e) {
	    	e.printStackTrace();
	    }
	}	
		
	private void readFromCSV() {
		try {
			
			File csv = new File("data/" + year + ".csv");
			BufferedReader br = new BufferedReader(new FileReader(csv));

			String line = "";
			String tmpline = "";
			while ((line = br.readLine()) != null) {
				tmpline = line;
			}
			br.close();
			
			StringTokenizer st = new StringTokenizer(tmpline, ",");
	
			List<String> tmpdate = new ArrayList<String>();
			while (st.hasMoreTokens()) {
				tmpdate.add(st.nextToken());
			}
				
			for (int i = 1; i < tmpdate.size(); i++) {
				data.addValue(Integer.parseInt(tmpdate.get(i)) / 60.0, tmpdate.get(0) + "譌･", i + "譌･");
			}
			
			
	    } catch (FileNotFoundException e) {
	      // File�ｽI�ｽu�ｽW�ｽF�ｽN�ｽg�ｽ�ｽ�ｽ�ｽ�ｽ�ｽ�ｽﾌ暦ｿｽO�ｽﾟ托ｿｽ
	      e.printStackTrace();
	    } catch (IOException e) {
	      // BufferedReader�ｽI�ｽu�ｽW�ｽF�ｽN�ｽg�ｽﾌク�ｽ�ｽ�ｽ[�ｽY�ｽ�ｽ�ｽﾌ暦ｿｽO�ｽﾟ托ｿｽ
	      e.printStackTrace();
	    }
	  }
}
