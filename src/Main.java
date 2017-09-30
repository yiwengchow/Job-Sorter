import java.awt.EventQueue;
import java.awt.Label;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.awt.TextField;

public class Main {

	private JFrame frame;
	private LinkedList<Job> jobList = new LinkedList<Job>();
	private PriorityQueue<Job> jobQueue = new PriorityQueue<Job>();
	private ArrayList<Label> Lists = new ArrayList<Label>();
	private ArrayList<Label> jobCounters = new ArrayList<Label>();
	private ArrayList<Servers> servers = new ArrayList<Servers>();
	private ArrayList<Thread> threads = new ArrayList<Thread>();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main window = new Main();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Main() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setLayout(null);
		frame.setSize(555, 500);
		
		JLabel server1 = new JLabel("Server 1:");
		server1.setHorizontalAlignment(SwingConstants.LEFT);
		server1.setBounds(26, 47, 62, 14);
		frame.getContentPane().add(server1);
		
		JLabel server2 = new JLabel("Server 2:");
		server2.setHorizontalAlignment(SwingConstants.LEFT);
		server2.setBounds(180, 47, 62, 14);
		frame.getContentPane().add(server2);
		
		JLabel server3 = new JLabel("Server 3:");
		server3.setHorizontalAlignment(SwingConstants.LEFT);
		server3.setBounds(344, 47, 62, 14);
		frame.getContentPane().add(server3);
		
		JLabel queue = new JLabel("Job in Queue:");
		queue.setBounds(26, 109, 78, 14);
		frame.getContentPane().add(queue);

		Label serverOneJob = new Label("");
		serverOneJob.setBounds(26, 67, 102, 22);
		frame.getContentPane().add(serverOneJob);
		
		Label serverTwoJob = new Label("");
		serverTwoJob.setBounds(180, 67, 102, 22);
		frame.getContentPane().add(serverTwoJob);
		
		Label serverThreeJob = new Label("");
		serverThreeJob.setBounds(344, 67, 102, 22);
		frame.getContentPane().add(serverThreeJob);
		
		TextField timer = new TextField();
		timer.setEditable(false);
		timer.setBounds(421, 10, 108, 22);
		frame.getContentPane().add(timer);
		
		Label label = new Label("Time Elapsed:");
		label.setAlignment(Label.RIGHT);
		label.setBounds(313, 10, 102, 22);
		frame.getContentPane().add(label);
		
		List list = new List();
		list.setBounds(26, 129, 397, 185);
		frame.getContentPane().add(list);
		
		JButton start = new JButton("Start");
		start.setBounds(440, 129, 89, 23);
		frame.getContentPane().add(start);
		
		Label arrival_rate = new Label("0");
		arrival_rate.setBounds(196, 385, 32, 22);
		frame.getContentPane().add(arrival_rate);
		
		Label label_1 = new Label("Arrival Rate: ");
		label_1.setBounds(10, 385, 180, 22);
		frame.getContentPane().add(label_1);
		
		Label label_2 = new Label("Total no. of job processed:");
		label_2.setBounds(10, 329, 180, 22);
		frame.getContentPane().add(label_2);
		
		Label total_job = new Label("0");
		total_job.setBounds(196, 329, 32, 22);
		frame.getContentPane().add(total_job);
		
		Label label_3 = new Label("No. of job processed / min: ");
		label_3.setBounds(10, 357, 180, 22);
		frame.getContentPane().add(label_3);
		
		Label avg_jobProcessed = new Label("0");
		avg_jobProcessed.setBounds(196, 357, 32, 22);
		frame.getContentPane().add(avg_jobProcessed);
		
		Label serverOneJobCounter = new Label("0");
		serverOneJobCounter.setBounds(94, 47, 32, 14);
		frame.getContentPane().add(serverOneJobCounter);
		
		Label serverTwoJobCounter = new Label("0");
		serverTwoJobCounter.setBounds(248, 47, 32, 14);
		frame.getContentPane().add(serverTwoJobCounter);
		
		Label serverThreeJobCounter = new Label("0");
		serverThreeJobCounter.setBounds(412, 47, 32, 14);
		frame.getContentPane().add(serverThreeJobCounter);
		
		Label lblNewLabel = new Label("No. of jobs requeued 0 times:");
		lblNewLabel.setBounds(275, 329, 175, 22);
		frame.getContentPane().add(lblNewLabel);
		
		Label lblNoOfJobs = new Label("No. of jobs requeued 1 time:");
		lblNoOfJobs.setBounds(275, 357, 175, 22);
		frame.getContentPane().add(lblNoOfJobs);
		
		Label lblNoOfJobs_1 = new Label("No. of jobs requeued 2 times:");
		lblNoOfJobs_1.setBounds(275, 385, 175, 22);
		frame.getContentPane().add(lblNoOfJobs_1);
		
		Label jobProcessed_once = new Label("0");
		jobProcessed_once.setBounds(453, 329, 32, 22);
		frame.getContentPane().add(jobProcessed_once);
		
		Label jobRequeued_once = new Label("0");
		jobRequeued_once.setBounds(453, 357, 32, 22);
		frame.getContentPane().add(jobRequeued_once);
		
		Label jobRequeued_twice = new Label("0");
		jobRequeued_twice.setBounds(453, 385, 32, 22);
		frame.getContentPane().add(jobRequeued_twice);

		JButton close = new JButton("Close");
		close.setBounds(440, 164, 89, 23);
		frame.getContentPane().add(close);
		
		//Start sorting
		//start.addActionListener(new Main(start,list,serverOneJob,serverTwoJob,serverThreeJob,serverOneJobCounter,serverTwoJobCounter,serverThreeJobCounter,timer, total_job, avg_jobProcessed, arrival_rate, jobProcessed_once,jobRequeued_once,jobRequeued_twice));
		start.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				startSorting(start,list,serverOneJob,serverTwoJob,serverThreeJob,serverOneJobCounter,serverTwoJobCounter,
						serverThreeJobCounter,timer, total_job, avg_jobProcessed, arrival_rate, jobProcessed_once,
						jobRequeued_once,jobRequeued_twice);
			}
		});
		
		//Close button to close the program
		close.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				System.exit(0);
			}
			
		});
	}
	
	private void startSorting(JButton start, List displayList, Label serverOneJob,
			Label serverTwoJob, Label serverThreeJob, Label serverOneJobCounter,
			Label serverTwoJobCounter, Label serverThreeJobCounter, TextField time, Label total_job,
			Label avg_jobProcessed, Label arrival_rate, Label jobProcessed_once, Label jobRequeued_once,
			Label jobRequeued_twice) {
		
		Lists.add(serverOneJob);
		Lists.add(serverTwoJob);
		Lists.add(serverThreeJob);
		
		jobCounters.add(serverOneJobCounter);
		jobCounters.add(serverTwoJobCounter);
		jobCounters.add(serverThreeJobCounter);
		
		start.setEnabled(false);
		
		//Creates jobs
		Creator c = new Creator(jobList,jobQueue);
		Thread t1 = new Thread(c);
		t1.start();
		
		//3 Servers that takes job
		
		for (int i = 0; i < 3; i ++){
			servers.add(new Servers(jobQueue,jobList,Lists.get(i), jobCounters.get(i), total_job,jobProcessed_once,jobRequeued_once,jobRequeued_twice));
			threads.add(new Thread(servers.get(i)));
			threads.get(i).start();
		}
		
		//Updates the list in the interface to reflect the jobs currently in queue
		Update u = new Update(jobQueue,displayList);
		Thread t5 = new Thread(u);
		t5.start();
		
		//The timer which also counts the average job processed and the arrival rate of jobs to the queue
		Time t = new Time(time,avg_jobProcessed, arrival_rate);
		Thread t6 = new Thread(t);
		t6.start();
	}
}
