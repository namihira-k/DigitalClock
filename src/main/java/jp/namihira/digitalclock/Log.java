package jp.namihira.digitalclock;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.StringTokenizer;

public class Log {

	private List<List<Integer>> date = new ArrayList<List<Integer>>();
	
	private String year = "2011";
	
	private int limitHour = 8;
	private int limitMinute = 30;
	
	public Log() {
		
	}
	
	public void save(){
		try {
			File csv = new File("data/" + year + ".csv");
			
			BufferedReader br = new BufferedReader(new FileReader(csv));
			String line = "";
			List<Integer> tmpdate = null;
			while ((line = br.readLine()) != null) {
				tmpdate = new ArrayList<Integer>();
				StringTokenizer st = new StringTokenizer(line, ",");
				
				while (st.hasMoreTokens()) {
					tmpdate.add(Integer.parseInt(st.nextToken()));
				}
				
				date.add(tmpdate);
			}
			br.close();
			
			Calendar nowcal = Calendar.getInstance();
	        int diffHour = nowcal.get(Calendar.HOUR_OF_DAY) - limitHour;
	        int diffMinute = nowcal.get(Calendar.MINUTE) - limitMinute;
	        if (diffMinute < 0) {
	        	diffMinute = 0;
	        }
	        
	        if (tmpdate.get(0) != nowcal.get(Calendar.MONTH) + 1) {
	        	tmpdate = new ArrayList<Integer>();
		    	tmpdate.add(nowcal.get(Calendar.MONTH) + 1);
	        } else {
	        	date.remove(date.size() - 1);
	        }
	        tmpdate.add(diffMinute + diffHour * 60);
	        date.add(tmpdate);
			
			BufferedWriter bw = new BufferedWriter(new FileWriter(csv, false)); 
		      
			for (int i = 0; i < date.size(); i++) {
				StringBuffer sb = new StringBuffer();
				for (Integer v : date.get(i)) {
					sb.append(v);
					sb.append(",");
				}
				bw.write(sb.toString());
				bw.newLine();
			}
			bw.close();
			
		    } catch (FileNotFoundException e) {
		    	e.printStackTrace();
		    } catch (IOException e) {
		      e.printStackTrace();
		    }
		  }
}
