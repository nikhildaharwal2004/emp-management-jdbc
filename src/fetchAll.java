import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JOptionPane;

public class fetchAll extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTable table;
    private DefaultTableModel model;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                fetchAll frame = new fetchAll();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public fetchAll() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 600, 400);

        contentPane = new JPanel();
        contentPane.setBackground(new Color(255, 255, 128));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblNewLabel = new JLabel("Fetch All Employee Details");
        lblNewLabel.setFont(new Font("Segoe UI Black", Font.BOLD, 14));
        lblNewLabel.setBounds(180, 10, 250, 25);
        contentPane.add(lblNewLabel);

        // Table Model
        model = new DefaultTableModel(
                new String[]{"ID", "NAME", "GENDER", "SALARY"}, 0);

        table = new JTable(model);
        table.setRowHeight(25);

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(20, 100, 540, 200);
        contentPane.add(scrollPane);

        JButton btnNewButton = new JButton("FETCH");
        btnNewButton.setFont(new Font("Segoe UI Emoji", Font.BOLD, 11));
        btnNewButton.setBounds(250, 60, 100, 25);
        contentPane.add(btnNewButton);
        
        JButton btnBack = new JButton("BACK");
        btnBack.setFont(new Font("Segoe UI Emoji", Font.BOLD, 11));
        btnBack.setBounds(20, 320, 80, 25);
        contentPane.add(btnBack);

        btnBack.addActionListener(e -> {
            new Index().setVisible(true);
            dispose();
        });


        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                model.setRowCount(0); // clear old data

                try (Connection con = DatabaseConfig.getConnection();
                     PreparedStatement ps = con.prepareStatement(
                             "SELECT * FROM employeesindb");
                     ResultSet rs = ps.executeQuery()) {

                    while (rs.next()) {
                        int id = rs.getInt("id");
                        String name = rs.getString("name");
                        String gender = rs.getString("gender");
                        int salary = rs.getInt("salary");

                        model.addRow(new Object[]{
                                id, name, gender, salary
                        });
                    }

                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(
                            fetchAll.this,
                            ex.getMessage(),
                            "Error",
                            JOptionPane.ERROR_MESSAGE
                    );
                }
            }
        });
    }
}

