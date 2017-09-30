import java.awt.Label;
import java.util.LinkedList;
import java.util.PriorityQueue;

public class Servers implements Runnable{

	
	PriorityQueue<Job> jobQueue;
	LinkedList<Job> jobList;
	Label l, server_count, total_job,jobProcessed_once,jobRequeued_once,jobRequeued_twice;
	
	Job task;
	static int total_count,jobProcessedOnce_count,jobRequeuedOnce_count,jobRequeuedTwice_count = 0 ;
	int serverCount = 0;
	
	public Servers(PriorityQueue<Job> q, LinkedList<Job> j, Label l, Label server_count,  Label total_job, Label jobProcessed_once, Label jobRequeued_once, Label jobRequeued_twice){
		this.jobQueue = q;
		this.jobList = j;
		this.l = l;
		this.server_count = server_count;
		this.total_job = total_job;
		this.jobProcessed_once = jobProcessed_once;
		this.jobRequeued_once = jobRequeued_once;
		this.jobRequeued_twice = jobRequeued_twice;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		while(Time.T <= 300){
		//while (!end){
			//puts the top priority queue to task
			synchronized(jobQueue){
				task = (Job)jobQueue.poll();
				jobQueue.notifyAll();
			}
			
			if(task != null){
				for(int count = 0; count < 8; count++){
					
					l.setText("Job " + task.getJob() + " " + task.getTime() + "s");
					
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}				
					
					int reducedTime = task.getTime();
					
					reducedTime -= 1; 
					
					//to prevent null exception the time remaining must be more than 0 for its time to reduce
					if (reducedTime != 0){
						task.setTime(reducedTime);
						l.setText("Job " + task.getJob() + " " + task.getTime() + "s");
					}
					//indicates that job is done
					else{
						//Total no. of job processed
						total_count++;
						//How many jobs each server has processed
						serverCount++;

						//Evaluates if the completed job has been requeued before to indicate that it finished after processing once
						if (task.requeues == 0){
							jobProcessedOnce_count++;
							jobProcessed_once.setText(String.valueOf(jobProcessedOnce_count));
						}
						
						total_job.setText(String.valueOf(total_count));
						server_count.setText(String.valueOf(serverCount));
						
						//time is set to 0 and exits the loop
						task.setTime(reducedTime);
						break;
					}
				}
				
				l.setText(null);
				
				//Uncompleted jobs
				//Add requeues to indicate how many times they have been requeued
				if (task.getTime() > 0){
					int requeues = task.getRequeues();
					requeues++;
					task.setRequeues(requeues);
					
					switch(requeues){
					case 1:
						jobRequeuedOnce_count++;
						jobRequeued_once.setText(String.valueOf(jobRequeuedOnce_count));
						break;
					case 2:
						jobRequeuedTwice_count++;
						jobRequeued_twice.setText(String.valueOf(jobRequeuedTwice_count));
						break;
					}
					
					//added back to the priority queue to re-sort
					
					synchronized(jobQueue){
						jobQueue.add(task);
						jobQueue.notifyAll();
					}
					
					
				}
				try {
					Thread.sleep(1000);
				} catch (Exception e) {
					// TODO Auto-generated catch block
				}
						
			}
		}
	}
}