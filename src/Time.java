import java.awt.Label;
import java.awt.TextField;
import java.text.DecimalFormat;

public class Time implements Runnable {

	TextField time;
	static int T = 0;
	boolean end;
	Label avg_jobProcessed, arrival_rate;
	
	public Time(TextField time,Label avg_jobProcessed, Label arrival_rate){
		this.time = time;
		this.avg_jobProcessed = avg_jobProcessed;
		this.arrival_rate = arrival_rate;
	}
	
	@Override
	public void run() {
		DecimalFormat df = new DecimalFormat("#.00");
		
		while(T <= 300){
		// TODO Auto-generated method stub
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			//sets the timer
			time.setText(String.valueOf(T++) + " s");
			
			//if the count is 0 it return a weird result of .00 so we should only perform it when it has a value of at least 1
			if (Servers.total_count > 0){
				//Total amount of jobs processed divide by the timer and *60 to determine the jobs completed / minute
				avg_jobProcessed.setText(df.format((double)Servers.total_count/T * 60));
			}
			if (Creator.count > 0){
				//Total amount of jobs created divide by the timer and *60 to determine the jobs arrived / minutes
				arrival_rate.setText(df.format((double)Creator.count/T*60));
			}
		}
		
	}

}
