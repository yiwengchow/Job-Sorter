
import java.util.Date;
import java.util.Random;

public class Job implements Comparable<Job>{

	int time;
    int jobID;
    int priority;
    int timeStart;
    int currentTime;
    int requeues = 0;
    
    Date d;
	
	public Job(int count){
		Random rand = new Random();
		time = rand.nextInt(14)+5;
		jobID = count;
		d = new Date();
		timeStart = (int) d.getTime();
		updatePriority();
	}
	
	public void setRequeues(int requeues){
		this.requeues = requeues;
	}
	
	public int getRequeues(){
		return requeues;
	}
	
	public void setTime(int time){
		this.time = time;
	}
	
	public int getTime(){
		return time;
	}
	
	public int getJob(){
		return jobID;
	}
	
	public void updatePriority(){
		d = new Date();
		currentTime = (int) d.getTime();
		priority =  time - ((currentTime - timeStart) / 1000);
	}
	
	public int getPriority(){
		
		return priority;
	}
	
    @Override
    public int compareTo(Job job) {
    	
    	updatePriority();
        
        if(this.getPriority()<job.getPriority())
        {
            return -1;
        }
        if(this.getPriority()>job.getPriority())
        {
            return 1;
        }

            return 0;

    }
}
