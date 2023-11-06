package gui;

import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import daoo.CustomerDao;

import java.awt.BorderLayout;
import net.sf.jasperreports.swing.JRViewer;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

public class ReportCus extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JRViewer viewer;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ReportCus frame = new ReportCus();
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
	public ReportCus() {
		setTitle("Report");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 823, 533);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		showreport();
		
	}
	
	public void showreport() {
		try {
			List<Map<String,?>> list = new ArrayList<Map<String,?>>();
			var dao = new CustomerDao();
			dao.getListCus(1, 100).stream().forEach(cus -> {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("idCus", cus.getIdCus());
				map.put("fullname", cus.getFullname());
				map.put("gender", cus.isGender());
				map.put("picture", cus.getPicture());
				map.put("dob", cus.getDob());
				list.add(map);
			});;
			var datasource = new JRBeanCollectionDataSource(list);
			String file = "report/Customer.jrxml";
			var rp = JasperCompileManager.compileReport(file);
			JasperPrint print = JasperFillManager.fillReport(rp, null, datasource);
			viewer = new JRViewer(print);
			contentPane.add(viewer, BorderLayout.CENTER);
//			pack(); //vẽ lại
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
