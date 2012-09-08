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

public class GraphMonth extends JPanel {

	private DefaultCategoryDataset data = new DefaultCategoryDataset();
	private String[] series = {"2010", "2011"};
	
	public GraphMonth() {
		ChartFactory.setChartTheme(StandardChartTheme.createLegacyTheme());
	    
		readFromCSV();

	    JFreeChart chart = ChartFactory.createBarChart3D("hoge",
	                                    "foo",
	                                    "nami[h]",
	                                    data,
	                                    PlotOrientation.VERTICAL,
	                                    true,
	                                    false,
	                                    false);

	    ChartPanel cpanel = new ChartPanel(chart);
	    add(cpanel, BorderLayout.CENTER);
	    
	    setVisible(true);
	    
	    File file = new File("img/GraphMouth.jpg");
	    try {
	    	ChartUtilities.saveChartAsJPEG(file, chart, 400, 300);
	    } catch (IOException e) {
	    	e.printStackTrace();
	    }
	}
	
	
	private void readFromCSV() {
		for (String str : series) {
			try {
				File csv = new File("data/" + str + ".csv");
				BufferedReader br = new BufferedReader(new FileReader(csv));

				String line = "";
				while ((line = br.readLine()) != null) {
					StringTokenizer st = new StringTokenizer(line, ",");

					List<String> tmpdate = new ArrayList<String>();
					while (st.hasMoreTokens()) {
						tmpdate.add(st.nextToken());
					}
				
					int sum = 0;
					for (int i = 1; i < tmpdate.size(); i++) {
						sum += Integer.parseInt(tmpdate.get(i));
					}
				
					data.addValue(sum / 60.0, str, tmpdate.get(0));
				}
				br.close();
			} catch (FileNotFoundException e) {
				// e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	  }
}

