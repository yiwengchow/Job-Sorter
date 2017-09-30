import java.util.ArrayList;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Collections;

public class Creator implements Runnable{

	int randomJob;
	LinkedList<Job> jobList;
	PriorityQueue<Job> jobQueue;
	ArrayList<Integer> jobID = new ArrayList<Integer>();
	static int count = 0;
	
	public Creator(LinkedList<Job> jobList, PriorityQueue<Job> jobQueue) {
		// TODO Auto-generated constructor stub
		this.jobList = jobList;
		this.jobQueue = jobQueue;
		
		for (int i = 0; i < 100; i++){
			jobID.add(i);
		}
	}
	
	@Override
	public void run() {
		
		while(Time.T <= 300){
			
			jobID.add(count+100);
			Collections.shuffle(jobID);
			randomJob = jobID.get(0);
				
			jobList.add(new Job(randomJob));
			
			synchronized(jobQueue){
				jobQueue.add(jobList.get(count));
				jobQueue.notifyAll();
			}
			count++;
			
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
			}
		}
	}

}
