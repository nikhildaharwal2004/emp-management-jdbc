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
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class updateEmployee extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField inputid;
    private JTextField inputname;
    private JTextField sal;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                updateEmployee frame = new updateEmployee();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public updateEmployee() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);

        contentPane = new JPanel();
        contentPane.setBackground(new Color(255, 255, 128));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblTitle = new JLabel("Update Employee");
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lblTitle.setBounds(150, 10, 150, 25);
        contentPane.add(lblTitle);

        JLabel lblId = new JLabel("Enter ID:");
        lblId.setFont(new Font("Segoe UI", Font.BOLD, 12));
        lblId.setBounds(49, 71, 80, 15);
        contentPane.add(lblId);

        inputid = new JTextField();
        inputid.setBounds(143, 66, 96, 20);
        contentPane.add(inputid);

        JLabel lblName = new JLabel("Enter Name:");
        lblName.setFont(new Font("Segoe UI", Font.BOLD, 12));
        lblName.setBounds(49, 127, 80, 15);
        contentPane.add(lblName);

        inputname = new JTextField();
        inputname.setBounds(143, 122, 96, 20);
        contentPane.add(inputname);

        JLabel lblSalary = new JLabel("Enter Salary:");
        lblSalary.setFont(new Font("Segoe UI", Font.BOLD, 12));
        lblSalary.setBounds(49, 184, 80, 15);
        contentPane.add(lblSalary);

        sal = new JTextField();
        sal.setBounds(143, 179, 96, 20);
        contentPane.add(sal);

        JButton btnUpdate = new JButton("Update");
        btnUpdate.setFont(new Font("Segoe UI Emoji", Font.BOLD, 10));
        btnUpdate.setBounds(304, 121, 84, 25);
        contentPane.add(btnUpdate);
        
        JButton Back = new JButton("Back");
        Back.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		new Index().setVisible(true);
        		dispose();
        	}
        });
        Back.setFont(new Font("Segoe UI Emoji", Font.BOLD, 11));
        Back.setBounds(304, 156, 84, 20);
        contentPane.add(Back);

        btnUpdate.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                // 🔐 Confirmation popup
                int confirm = JOptionPane.showConfirmDialog(
                        updateEmployee.this,
                        "Are you sure you want to update this employee?",
                        "Confirm Update",
                        JOptionPane.YES_NO_OPTION
                );

                if (confirm != JOptionPane.YES_OPTION) {
                    return;
                }

                Connection con = null;
                PreparedStatement ps = null;

                try {
                    int id = Integer.parseInt(inputid.getText());
                    int salary = Integer.parseInt(sal.getText());
                    String name = inputname.getText();

                    con = DatabaseConfig.getConnection();

                    String sql = "UPDATE employeesindb SET name = ?, salary = ? WHERE id = ?";
                    ps = con.prepareStatement(sql);

                    ps.setString(1, name);
                    ps.setInt(2, salary);
                    ps.setInt(3, id);

                    int rows = ps.executeUpdate();

                    if (rows > 0) {
                        JOptionPane.showMessageDialog(
                                updateEmployee.this,
                                "Employee Updated Successfully!",
                                "Success",
                                JOptionPane.INFORMATION_MESSAGE
                        );
                    } else {
                        JOptionPane.showMessageDialog(
                                updateEmployee.this,
                                "Employee ID not found!",
                                "Update Failed",
                                JOptionPane.WARNING_MESSAGE
                        );
                    }

                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(
                            updateEmployee.this,
                            "ID and Salary must be numeric!",
                            "Input Error",
                            JOptionPane.ERROR_MESSAGE
                    );
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(
                            updateEmployee.this,
                            "Database Error!",
                            "Error",
                            JOptionPane.ERROR_MESSAGE
                    );
                } finally {
                    try {
                        if (ps != null) ps.close();
                        if (con != null) con.close();
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });
    }
}

