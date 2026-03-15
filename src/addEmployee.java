import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class addEmployee extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField empid;
	private JTextField name;
	private JTextField gen;
	private JTextField sal;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					addEmployee frame = new addEmployee();
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
	public addEmployee() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 128));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setVisible(true);
		
		JLabel lblNewLabel = new JLabel("Add Employee");
		lblNewLabel.setFont(new Font("Segoe UI Emoji", Font.BOLD, 11));
		lblNewLabel.setBounds(176, 10, 80, 12);
		contentPane.add(lblNewLabel);
		
		JLabel id = new JLabel("Employee ID: ");
		id.setFont(new Font("Segoe UI Emoji", Font.BOLD, 10));
		id.setForeground(new Color(0, 0, 0));
		id.setBounds(45, 68, 80, 12);
		contentPane.add(id);
		
		JLabel empName = new JLabel("Employee name: ");
		empName.setForeground(Color.BLACK);
		empName.setFont(new Font("Segoe UI Emoji", Font.BOLD, 10));
		empName.setBounds(45, 108, 80, 12);
		contentPane.add(empName);
		
		JLabel gender = new JLabel("Gender: ");
		gender.setForeground(Color.BLACK);
		gender.setFont(new Font("Segoe UI Emoji", Font.BOLD, 10));
		gender.setBounds(45, 154, 80, 12);
		contentPane.add(gender);
		
		JLabel salary = new JLabel("Salary: ");
		salary.setForeground(Color.BLACK);
		salary.setFont(new Font("Segoe UI Emoji", Font.BOLD, 10));
		salary.setBounds(45, 198, 80, 12);
		contentPane.add(salary);
		
		empid = new JTextField();
		empid.setBounds(160, 63, 96, 18);
		contentPane.add(empid);
		empid.setColumns(10);
		
		name = new JTextField();
		name.setColumns(10);
		name.setBounds(160, 103, 96, 18);
		contentPane.add(name);
		
		gen = new JTextField();
		gen.setColumns(10);
		gen.setBounds(160, 149, 96, 18);
		contentPane.add(gen);
		
		sal = new JTextField();
		sal.setColumns(10);
		sal.setBounds(160, 193, 96, 18);
		contentPane.add(sal);
		
		JButton sub = new JButton("Add");
		sub.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String add = "insert into employeesindb values (?,?,?,?)";
				Connection con = null;
				PreparedStatement ps = null;
				try {
					con = DatabaseConfig.getConnection();
					 ps = con.prepareStatement(add);
					int inputId = Integer.parseInt(empid.getText());
					String inputName = name.getText();
					String inputGender = gen.getText();
					int inputSalary = Integer.parseInt(sal.getText());
					
					ps.setInt(1, inputId);
					ps.setString(2, inputName);
					ps.setString(3, inputGender);
					ps.setInt(4, inputSalary);
					
					int nors = ps.executeUpdate();
					if (nors > 0) {
					JOptionPane.showMessageDialog(addEmployee.this, "Employee added successfully" , "success", JOptionPane.INFORMATION_MESSAGE);
					} 

				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}finally {
                    try {
                        if (ps != null) ps.close();
                        if (con != null) con.close();
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                }
			}
		});
		sub.setFont(new Font("Segoe UI Emoji", Font.BOLD, 10));
		sub.setBounds(321, 119, 84, 20);
		contentPane.add(sub);
		
		JButton Back = new JButton("Back");
		Back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Index().setVisible(true);
				dispose();
			}
		});
		Back.setFont(new Font("Segoe UI Emoji", Font.BOLD, 10));
		Back.setForeground(new Color(0, 0, 0));
		Back.setBounds(321, 148, 84, 20);
		contentPane.add(Back);

	}
}
