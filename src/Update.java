import java.awt.List;
import java.util.LinkedList;
import java.util.PriorityQueue;

public class Update implements Runnable {
	
	List list;
	PriorityQueue<Job> jobQueue;
	
	
	public Update(PriorityQueue<Job> q,List list){
		this.list = list;
		this.jobQueue = q;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
		int size;
		LinkedList<Job> temp = new LinkedList<Job>();
		
		while(Time.T <= 300){
			//takes the highest priority task and store into a temporary array
			synchronized(jobQueue){
				size = jobQueue.size();
				for(int i = 0;i < size;i++){
					temp.add(jobQueue.poll());
				}
				
				// Reflect and update the current priority of the task using the temp list, also adds the tasks back from the temporary array to the priority queue
				for (int i = 0;i < temp.size();i++){
					temp.get(i).updatePriority();
					jobQueue.add(temp.get(i));
					list.add(String.format("Job %02d     Job Length: %02ds", temp.get(i).getJob(), temp.get(i).getTime()));
				}
				
				// put into waiting state until a new job enters the priority queue
				while(jobQueue.size() == size){
					try {
						jobQueue.wait();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}

			list.removeAll();
			temp.clear();
			
		}
	}

}
