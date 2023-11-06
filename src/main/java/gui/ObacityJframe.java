package gui;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

public class ObacityJframe extends JFrame {

	private JPanel contentPane;
	private Point mouseDownPont = null;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ObacityJframe frame = new ObacityJframe();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ObacityJframe() {
		addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				thisMouseDragged(e);
			}
		});
		addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				thisMousePressed(e);
			}
		});
		setUndecorated(true);
		setBackground(new Color(0,0,0,0));
		Image i = new ImageIcon(
				new ImageIcon(
						ObacityJframe.class.getResource("/images/caino.png")
				).getImage()
				.getScaledInstance(729, 653, Image.SCALE_SMOOTH)
			).getImage();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel() {
		@Override
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			g.drawImage(i,0,0,null);
			
		}
		};
		contentPane.setBackground(new Color(0,0,0,0));
//		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
	}

	protected void thisMousePressed(MouseEvent e) {
		mouseDownPont = e.getPoint();
	}
	protected void thisMouseDragged(MouseEvent e) {
		Point cur = e.getLocationOnScreen();
		this.setLocation(cur.x - mouseDownPont.x , cur.y - mouseDownPont.y);
		
	}
}
