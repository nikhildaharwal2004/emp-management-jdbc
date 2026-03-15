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

public class deleteEmployee extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField deleteidofemp;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					deleteEmployee frame = new deleteEmployee();
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
	public deleteEmployee() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 128));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Delete Employee");
		lblNewLabel.setFont(new Font("Segoe UI Emoji", Font.BOLD, 11));
		lblNewLabel.setBounds(166, 10, 95, 24);
		contentPane.add(lblNewLabel);
		
		JLabel deleteid = new JLabel("Enter ID: ");
		deleteid.setFont(new Font("Segoe UI Emoji", Font.BOLD, 10));
		deleteid.setBounds(53, 108, 44, 12);
		contentPane.add(deleteid);
		
		deleteidofemp = new JTextField();
		deleteidofemp.setBounds(165, 103, 96, 18);
		contentPane.add(deleteidofemp);
		deleteidofemp.setColumns(10);
		
		JButton btnNewButton = new JButton("Delete");
		btnNewButton.setFont(new Font("Segoe UI Emoji", Font.BOLD, 10));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				  int confirm = JOptionPane.showConfirmDialog(
	                        deleteEmployee.this,
	                        "Are you sure you want to delete this employee?",
	                        "Confirm Update",
	                        JOptionPane.YES_NO_OPTION
	                );

	                if (confirm != JOptionPane.YES_OPTION) {
	                    return;
	                }

	                Connection con = null;
	                PreparedStatement ps = null;
				try {
					con = DatabaseConfig.getConnection();
					ps = con.prepareStatement("Delete from employeesindb where id = ?");
					int ids = Integer.parseInt(deleteidofemp.getText());
					ps.setInt(1, ids);
					ps.executeUpdate();
					JOptionPane.showMessageDialog(btnNewButton, "Employee Deleted Successfully", "success", JOptionPane.INFORMATION_MESSAGE);
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
		btnNewButton.setBounds(316, 102, 84, 20);
		contentPane.add(btnNewButton);
		
		JButton Back = new JButton("Back");
		Back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Index().setVisible(true);
				dispose();
			}
		});
		Back.setFont(new Font("Segoe UI Black", Font.BOLD, 10));
		Back.setBounds(166, 166, 84, 20);
		contentPane.add(Back);

	}
}
