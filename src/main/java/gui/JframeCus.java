package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Image;

import javax.swing.JScrollPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import java.awt.Component;
import javax.swing.UIManager;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextField;
import javax.swing.border.MatteBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;

import dao.CustomerDaoo;
import daoo.CustomerDao;
import entity.Customer;

import java.awt.Color;
import java.awt.SystemColor;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.ZoneId;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.text.SimpleDateFormat;

import javax.swing.JCheckBox;
import com.toedter.calendar.JDateChooser;

import dao.CustomerDaoo;

public class JframeCus extends JFrame {

	private JPanel contentPane;
	private JButton btnLoadCus;
	private JScrollPane scrollPane;
	private JTable table;
	private JButton btnFirst;
	private JButton btnPrevious;
	private JButton btnNext;
	private JButton btnLast;
	private JComboBox<String> comboBox;
	private JTextField txtPage;
	private JLabel lblStatusPage;
	private JLabel lblTotalOfRows;
	
//	khai báo thêm các cái field để phân trang
	Integer pageNumber = 1;
	Integer rowsOfPage = 10;
	Integer totalOfRows = 0;
	Double  totalPage = 0.0;
	private JLabel lblPicture;
	private JTextField txtId;
	private JTextField txtname;
	private JCheckBox chckbxGender;
	private JDateChooser dateChooser;
	private JButton btnupdate;
	private JButton btnPicture;
	
	private String filename= null;//lưu lại tên file
	private String fileBefore= null;//tên file trước đó
	private String driBefore= null;//Ddường dẫn trước đó
	private String driAfter= null;//đường dân hiện tại
	private JButton btnreport;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		} catch (Throwable e) {
			e.printStackTrace();
		}
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JframeCus frame = new JframeCus();
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
	public JframeCus() {
		setTitle("Customer");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 867, 434);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		
		btnLoadCus = new JButton("<html>&hearts; Load Customer</html>");
		btnLoadCus.addActionListener(e -> btnLoadCusActionPerformed(e));
		btnLoadCus.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		
		scrollPane = new JScrollPane();
		
		btnFirst = new JButton("First");
		btnFirst.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnFirstActionPerformed(e);
			}
		});
		
		btnPrevious = new JButton("Previous");
		btnPrevious.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnPreviousActionPerformed(e);
			}
		});
		
		btnNext = new JButton("Next");
		btnNext.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnNextActionPerformed(e);
			}
		});
		
		btnLast = new JButton("Last");
btnLast.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnLastActionPerformed(e);
			}
		});
		
		comboBox = new JComboBox<>();
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				comboBoxActionPerformed(e);
			}
		});
		comboBox.setToolTipText("Current Page");
		comboBox.setModel(new DefaultComboBoxModel<>(new String[] {"10", "20", "25", "50", "100"}));
		
		txtPage = new JTextField();
		txtPage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtPageActionPerformed(e);
			}
		});
		txtPage.setBackground(SystemColor.window);
		txtPage.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)));
		txtPage.setToolTipText("Jump To Page");
		txtPage.setHorizontalAlignment(SwingConstants.CENTER);
		txtPage.setText("1");
		txtPage.setColumns(10);
		
		lblStatusPage = new JLabel("Page 1 of 0");
		
		lblTotalOfRows = new JLabel("Rows Count 0");
		
		lblPicture = new JLabel("");
		lblPicture.setOpaque(true);
		lblPicture.setBackground(new Color(255, 128, 64));
		
		txtId = new JTextField();
		txtId.setColumns(10);
		
		txtname = new JTextField();
		txtname.setColumns(10);
		
		chckbxGender = new JCheckBox("Gender");
		
		dateChooser = new JDateChooser();
		
		btnupdate = new JButton("Update");
		btnupdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnupdateActionPerformed(e);
			}
		});
		
		btnPicture = new JButton("choose picture");
		btnPicture.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnPictureActionPerformed(e);
			}
		});
		
		btnreport = new JButton("Show Report");
		btnreport.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnreportActionPerformed(e);
			}
		});
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(45)
							.addComponent(lblPicture, GroupLayout.PREFERRED_SIZE, 182, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(chckbxGender)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(dateChooser, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(txtId, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(txtname, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addComponent(btnupdate, GroupLayout.PREFERRED_SIZE, 193, GroupLayout.PREFERRED_SIZE)))
					.addPreferredGap(ComponentPlacement.RELATED, 69, Short.MAX_VALUE)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(btnLoadCus, GroupLayout.PREFERRED_SIZE, 163, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(btnreport, GroupLayout.PREFERRED_SIZE, 129, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
							.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 523, GroupLayout.PREFERRED_SIZE)
							.addGroup(gl_contentPane.createSequentialGroup()
								.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
									.addGroup(gl_contentPane.createSequentialGroup()
										.addComponent(btnFirst, GroupLayout.PREFERRED_SIZE, 67, GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(ComponentPlacement.UNRELATED)
										.addComponent(btnPrevious))
									.addGroup(gl_contentPane.createSequentialGroup()
										.addGap(10)
										.addComponent(lblStatusPage)))
								.addGap(18)
								.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
									.addGroup(gl_contentPane.createSequentialGroup()
										.addComponent(comboBox, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addGap(18)
										.addComponent(btnNext, GroupLayout.PREFERRED_SIZE, 73, GroupLayout.PREFERRED_SIZE)
										.addGap(18))
									.addGroup(gl_contentPane.createSequentialGroup()
										.addComponent(txtPage, GroupLayout.PREFERRED_SIZE, 167, GroupLayout.PREFERRED_SIZE)
										.addGap(109)))
								.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
									.addComponent(btnLast, GroupLayout.PREFERRED_SIZE, 73, GroupLayout.PREFERRED_SIZE)
									.addComponent(lblTotalOfRows)))))
					.addGap(36))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(86)
					.addComponent(btnPicture)
					.addContainerGap(666, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(btnPicture)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lblPicture, GroupLayout.PREFERRED_SIZE, 181, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(txtId, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(txtname, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(chckbxGender))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGap(11)
									.addComponent(dateChooser, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)))
							.addGap(18)
							.addComponent(btnupdate, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(btnLoadCus)
								.addComponent(btnreport))
							.addGap(18)
							.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 209, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(btnFirst)
								.addComponent(btnLast)
								.addComponent(btnNext)
								.addComponent(btnPrevious)
								.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(txtPage, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblStatusPage)
								.addComponent(lblTotalOfRows))))
					.addContainerGap(32, Short.MAX_VALUE))
		);
		gl_contentPane.linkSize(SwingConstants.VERTICAL, new Component[] {btnLoadCus, btnreport});
		gl_contentPane.linkSize(SwingConstants.VERTICAL, new Component[] {btnLast, lblTotalOfRows});
		gl_contentPane.linkSize(SwingConstants.VERTICAL, new Component[] {btnFirst, lblStatusPage});
		gl_contentPane.linkSize(SwingConstants.HORIZONTAL, new Component[] {btnLoadCus, btnreport});
		gl_contentPane.linkSize(SwingConstants.HORIZONTAL, new Component[] {btnLast, lblTotalOfRows});
		gl_contentPane.linkSize(SwingConstants.HORIZONTAL, new Component[] {btnFirst, btnPrevious, lblStatusPage});
		gl_contentPane.linkSize(SwingConstants.HORIZONTAL, new Component[] {comboBox, txtPage});
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				tableMouseClicked(e);
			}
		});
		scrollPane.setViewportView(table);
		contentPane.setLayout(gl_contentPane);
	}
	protected void btnLoadCusActionPerformed(ActionEvent e) {
		var model = new DefaultTableModel() {
			@Override
			public Class<?> getColumnClass(int column){
				switch (column) {
					case 0: return Integer.class;
					case 1: return String.class;
					case 2: return Boolean.class;
					case 3: return ImageIcon.class;//hiểm thị đườn dẫn
					case 4: return String.class;//hiểm thiij đường dẫn hình
					case 5: return String.class;//hiểm thị dob
					default: return String.class;
				}
			}
			public boolean isCellEditerTable(int row,int col){
				switch (col) {
					case 3: return false;
					default: return true;
				}
			}
		};
		
		model.addColumn("idCus");
		model.addColumn("fullname");
		model.addColumn("gender");
		model.addColumn("picture");
		model.addColumn("pathpicture");
		model.addColumn("dob");
		
		CustomerDao dao = new CustomerDao();
		totalOfRows = dao.countCus(); //tổng số hàng trong cái bảng customer
		totalPage = Math.ceil(totalOfRows.doubleValue()/rowsOfPage.doubleValue()); //tính só trang cần hiển thị 
		lblStatusPage.setText("Page " + pageNumber + " of " + totalPage.intValue());
		lblTotalOfRows.setText("Rows Count " + totalOfRows);
		dao.getListCus(pageNumber, rowsOfPage).stream().forEach(cus -> model.addRow(new Object[] {
				cus.getIdCus(),
				cus.getFullname(),
				cus.isGender(),
				new ImageIcon(
						new ImageIcon(
							cus.getPicture()
						)
						.getImage()
						.getScaledInstance(120, 60, Image.SCALE_SMOOTH)),
				cus.getPicture(),
				cus.getDob()}
		));
		table.setModel(model);
		table.setRowHeight(60);
		table.getColumn("pathpicture").setMinWidth(0);
		table.getColumn("pathpicture").setMaxWidth(0);
		table.getColumn("pathpicture").setWidth(0);
	}
	
	public void loadCus() {
		var model = (DefaultTableModel)table.getModel();
		//xóa sạch dữ liệu trong cái table
		model.setRowCount(0);
		CustomerDao dao = new CustomerDao();
		totalPage = Math.ceil(totalOfRows.doubleValue()/rowsOfPage.doubleValue()); //tính só trang cần hiển thị 
		lblStatusPage.setText("Page " + pageNumber + " of " + totalPage.intValue());
		lblTotalOfRows.setText("Rows Count " + totalOfRows);
		dao.getListCus(pageNumber, rowsOfPage).stream().forEach(cus -> model.addRow(new Object[] {
				cus.getIdCus(),
				cus.getFullname(),
				cus.isGender(),
				new ImageIcon(
						new ImageIcon(
								cus.getPicture()
						)
						.getImage()
						.getScaledInstance(120, 50, Image.SCALE_SMOOTH)),
				cus.getPicture(),
				cus.getDob()
		}));
		table.setModel(model);
		table.setRowHeight(60);
		table.getColumn("pathpicture").setMinWidth(0);
		table.getColumn("pathpicture").setMaxWidth(0);
		table.getColumn("pathpicture").setWidth(0);
	}
protected void btnNextActionPerformed(ActionEvent e) {
		if(pageNumber < totalPage.intValue()) {
			pageNumber++;
			txtPage.setText(pageNumber.toString());
			loadCus();
		}
	}
	protected void btnPreviousActionPerformed(ActionEvent e) {
		if(pageNumber > 1) {
			pageNumber--;
			txtPage.setText(pageNumber.toString());
			loadCus();
		}
	}
	protected void btnLastActionPerformed(ActionEvent e) {
		pageNumber = totalPage.intValue();
		txtPage.setText(pageNumber.toString());
		loadCus();
	}
	protected void btnFirstActionPerformed(ActionEvent e) {
		pageNumber = 1;
		txtPage.setText(pageNumber.toString());
		loadCus();
	}
	protected void comboBoxActionPerformed(ActionEvent e) {
		if(table != null) {
			pageNumber=1;
			txtPage.setText(pageNumber.toString());
			rowsOfPage = Integer.parseInt(comboBox.getSelectedItem().toString());
			loadCus();
		}
	}
	protected void txtPageActionPerformed(ActionEvent e) {
		int page = Integer.parseInt(txtPage.getText());
		if(page >=1 && page <= totalPage.intValue()) {
			pageNumber = page;
			loadCus();
		}else {
			JOptionPane.showMessageDialog(txtPage, "Page must between 1 and " + totalPage.intValue());
			txtPage.setText(pageNumber.toString());
		}
	}
	protected void tableMouseClicked(MouseEvent e) {
		int rowindex = table.getSelectedRow();
		
		txtId.setText(table.getValueAt(rowindex, 0).toString());
		txtname.setText(table.getValueAt(rowindex, 1).toString());
		chckbxGender.setSelected(table.getValueAt(rowindex, 2).toString().equals("true")? true:false);
		lblPicture.setIcon(
				new ImageIcon(
				((ImageIcon)table.getValueAt(rowindex, 3))
				.getImage()
				.getScaledInstance(182, 181, Image.SCALE_SMOOTH)
				
			)
		);
		fileBefore = table.getValueAt(rowindex, 4).toString();
		
		try {
			dateChooser.setDate(
					new SimpleDateFormat("yyyy-MM-dd").parse(table.getValueAt(rowindex, 5).toString())
					);
		} catch (Exception e2) {
			// TODO: handle exception
		}
	}
	protected void btnupdateActionPerformed(ActionEvent e) {
		if(lblPicture.getIcon()==null) {
			JOptionPane.showMessageDialog(null, e);
		}
		Customer cus = new Customer();
		CustomerDaoo dao = new CustomerDaoo();
		cus.setFullname(txtname.getText());
		cus.setGender(chckbxGender.isSelected());
		
		if(filename!=null) {
			driAfter = System.getProperty("user.dir") +"\\hinh";
			Path pathBefore = Paths.get(driBefore);
			Path pathAfter = Paths.get(driAfter);
			try {
				Files.copy(pathBefore, pathAfter.resolve(filename),
						StandardCopyOption.REPLACE_EXISTING
						);
			} catch (Exception e2) {
				e2.printStackTrace();
			}
			cus.setPicture("hinh/" + filename);
		}else {
			cus.setPicture(fileBefore);
		}
		cus.setDob(
				LocalDate.ofInstant(dateChooser.getDate().toInstant(),ZoneId.systemDefault())
				);
		cus.setIdCus(Integer.parseInt(txtId.getText()));
		dao.updateCus(cus);
		filename = null;
		lblPicture.setIcon(null);
		loadCus();
		
	}
	protected void btnPictureActionPerformed(ActionEvent e) {
		var chooser = new JFileChooser("C:\\Users\\nguye\\OneDrive\\Pictures");
		chooser.setDialogTitle("open image");
		chooser.setFileFilter(
				new FileNameExtensionFilter("image (jpg,png,gif)","jpg","png","gif")
				);
		chooser.setAcceptAllFileFilterUsed(false);
		var result = chooser.showOpenDialog(null);
		if(result == chooser.APPROVE_OPTION) {
			File f = chooser.getSelectedFile();
			filename = f.getName();
			driBefore = f.getAbsolutePath();
			
			lblPicture.setIcon(
					new ImageIcon(
						new ImageIcon(
								f.getAbsolutePath()
								
									)
						.getImage()
						.getScaledInstance(210, 221, Image.SCALE_SMOOTH)
							)
					);
		}
	}
	protected void btnreportActionPerformed(ActionEvent e) {
		ReportCus report = new ReportCus();
		report.setVisible(true);
	}
}