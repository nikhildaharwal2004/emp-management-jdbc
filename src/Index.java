import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;

public class Index extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Index frame = new Index();
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
	public Index() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 128));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Employee Management System");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel.setBackground(new Color(128, 128, 128));
		lblNewLabel.setForeground(new Color(0, 0, 0));
		lblNewLabel.setBounds(119, 10, 182, 21);
		contentPane.add(lblNewLabel);
		
		JButton AddEmployee = 
				
				new JButton("Add Employee");
		AddEmployee.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new addEmployee();

			}
		});
		
		AddEmployee.setFont(new Font("Segoe UI Semilight", Font.BOLD, 11));
		AddEmployee.setForeground(new Color(255, 0, 0));
		AddEmployee.setBounds(37, 84, 130, 20);
		contentPane.add(AddEmployee);
		
		
		JButton updateEmployee = new JButton("Update Employee");
		updateEmployee.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new updateEmployee().setVisible(true);
			}
		});
		updateEmployee.setForeground(Color.RED);
		updateEmployee.setFont(new Font("Segoe UI Semilight", Font.BOLD, 11));
		updateEmployee.setBounds(268, 85, 130, 20);
		contentPane.add(updateEmployee);
		
		JButton fetch1 = new JButton("Fetch Employee Details");
		fetch1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new fetchEmployee().setVisible(true);;
			}
		});
		fetch1.setForeground(Color.RED);
		fetch1.setFont(new Font("Segoe UI Semilight", Font.BOLD, 11));
		fetch1.setBounds(268, 135, 130, 20);
		contentPane.add(fetch1);
		
		JButton Fetchall = new JButton("Fetch all Employee details");
		Fetchall.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new fetchAll().setVisible(true);
			}
		});
		Fetchall.setForeground(Color.RED);
		Fetchall.setFont(new Font("Segoe UI Semilight", Font.BOLD, 11));
		Fetchall.setBounds(138, 196, 163, 20);
		contentPane.add(Fetchall);
		
		JButton DeleteEmployee = new JButton("Delete Employee");
		DeleteEmployee.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new deleteEmployee().setVisible(true);
			}
		});
		DeleteEmployee.setHorizontalAlignment(SwingConstants.RIGHT);
		DeleteEmployee.setForeground(Color.RED);
		DeleteEmployee.setFont(new Font("Segoe UI Semilight", Font.BOLD, 11));
		DeleteEmployee.setBounds(37, 136, 130, 20);
		contentPane.add(DeleteEmployee);

	}

}
