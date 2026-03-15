import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;

public class fetchEmployee extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField enterid;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					fetchEmployee frame = new fetchEmployee();
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
	public fetchEmployee() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 128));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Fetch Employees Details");
		lblNewLabel.setFont(new Font("Segoe UI Emoji", Font.BOLD, 11));
		lblNewLabel.setForeground(new Color(0, 0, 0));
		lblNewLabel.setBounds(143, 10, 134, 12);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Enter Employee id:");
		lblNewLabel_1.setFont(new Font("Segoe UI Emoji", Font.BOLD, 13));
		lblNewLabel_1.setBounds(38, 77, 134, 15);
		contentPane.add(lblNewLabel_1);
		
		enterid = new JTextField();
		enterid.setBounds(198, 74, 96, 18);
		contentPane.add(enterid);
		enterid.setColumns(10);
		
		JTextArea print = new JTextArea();
		print.setBounds(48, 102, 347, 130);
		contentPane.add(print);
		
		JButton btnNewButton = new JButton("Fetch");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Connection con = null;
				PreparedStatement ps = null;
				 ResultSet rs = null;
				try {
					con = DatabaseConfig.getConnection();
					ps = con.prepareStatement("Select * from employeesindb where id = ?");
					int id = Integer.parseInt(enterid.getText());
					 ps.setInt(1, id);
					 rs = ps.executeQuery();
					if (rs.next()) {
						print.append("Employee Details\n");
                        print.append("-------------------------\n");
                        print.append("ID      : " + rs.getInt("id") + "\n");
                        print.append("Name    : " + rs.getString("name") + "\n");
                        print.append("Salary  : " + rs.getInt("salary") + "\n");
					} else {
                        JOptionPane.showMessageDialog(
                                fetchEmployee.this,
                                "Employee not found!",
                                "No Record",
                                JOptionPane.WARNING_MESSAGE
                        );
                    }
					
				} catch (Exception e2) {
					// TODO: handle exception
					e2.printStackTrace();
				} finally {
                    try {
                        if (rs != null) rs.close();
                        if (ps != null) ps.close();
                        if (con != null) con.close();
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                }
			}
		});
		btnNewButton.setFont(new Font("Segoe UI Emoji", Font.BOLD, 10));
		btnNewButton.setBounds(327, 74, 84, 20);
		contentPane.add(btnNewButton);
		
		JButton Back = new JButton("Back");
		Back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Index().setVisible(true);
				dispose();
			}
		});
		Back.setFont(new Font("Segoe UI Emoji", Font.BOLD, 10));
		Back.setBounds(175, 242, 84, 20);
		contentPane.add(Back);
	}
}
