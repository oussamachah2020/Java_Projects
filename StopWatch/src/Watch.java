import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.plaf.basic.BasicInternalFrameTitlePane.RestoreAction;


public class Watch implements ActionListener {
	
	JFrame frame = new JFrame();
	JButton startButton = new JButton("Start");
	JButton resetButton = new JButton("Reset");
	JPanel panel = new JPanel();
	JLabel label = new JLabel();
	int elapsTime = 0;
	int hours = 0;
	int minutes = 0;
	int seconds = 0;
	boolean run = false;
	
	String string_hours = String.format("%02d", hours);
	String string_minutes = String.format("%02d", minutes);
	String string_seconds = String.format("%02d", seconds);
	
	Watch() {		
		label.setText(string_hours + ":" + string_minutes + ":" + string_seconds);
		label.setFont(new Font("Verdana",Font.PLAIN,35));
		label.setBounds(100,100,200,100);
		label.setBorder(BorderFactory.createBevelBorder(1));
		label.setOpaque(true);
		label.setHorizontalAlignment(JLabel.CENTER);
		
		startButton.setFont(new Font("Ink Free",Font.PLAIN,20));
		startButton.setBounds(100,200,100,50);
		startButton.setFocusable(false);
		startButton.addActionListener(this);
		
		resetButton.setFont(new Font("Ink Free",Font.PLAIN,25));
		resetButton.setBounds(200,200,100,50);
		resetButton.setFocusable(false);
		resetButton.addActionListener(this);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(null);
		frame.setSize(400,400);
		frame.setVisible(true);
		frame.add(label);
		frame.add(startButton);
		frame.add(resetButton);
	}
	
	Timer timer = new Timer(1000, new ActionListener() {
		
		public void actionPerformed(ActionEvent e) {
			elapsTime += 1000;
			hours = (elapsTime / 3600000);
			minutes = (elapsTime / 60000) % 60;
			seconds = (elapsTime / 1000) % 60;
			string_hours = String.format("%02d", hours);
			string_minutes = String.format("%02d", minutes);
			string_seconds = String.format("%02d", seconds);
			
			label.setText(string_hours + ":" + string_minutes + ":" + string_seconds);
			
			
		}
	});

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == startButton) {
			if(run == false) {
				run = true;
				startButton.setText("Stop");
				start();
			}else {
				run = false;
				startButton.setText("Start");
				stop();
			}
		}
		
		if(e.getSource() == resetButton) {
			 run = false;
			 startButton.setText("Start");
			 reset();
		}
		
	}
	
	void stop() {
		timer.stop();
	}
	
	void start() {
		timer.start(); 
	}
	
	void reset() {
		timer.stop();
		elapsTime = 0;
		seconds = 0;
		minutes = 0;
		hours = 0;
		
		String string_hours = String.format("%02d", hours);
		String string_minutes = String.format("%02d", minutes);
		String string_seconds = String.format("%02d", seconds);
		
		label.setText(string_hours + ":" + string_minutes + ":" + string_seconds);
	}
	
}
