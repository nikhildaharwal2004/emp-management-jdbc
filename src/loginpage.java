import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class loginpage extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField user;
	JPasswordField pinTxt;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			try {
				loginpage frame = new loginpage();
				frame.setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public loginpage() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 128));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Login / Signup");
		lblNewLabel.setFont(new Font("Segoe UI Emoji", Font.BOLD, 12));
		lblNewLabel.setBounds(167, 10, 120, 29);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Username:");
		lblNewLabel_1.setFont(new Font("Segoe UI Emoji", Font.BOLD, 11));
		lblNewLabel_1.setBounds(92, 100, 70, 12);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Password:");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 10));
		lblNewLabel_2.setBounds(92, 138, 61, 12);
		contentPane.add(lblNewLabel_2);
		
		user = new JTextField();
		user.setBounds(192, 95, 96, 18);
		contentPane.add(user);
		
		 pinTxt = new JPasswordField();
         pinTxt.setBounds(192,130, 95, 18);
         getContentPane().add(pinTxt);
		
		// LOGIN BUTTON
		JButton btnLogin = new JButton("Login");
		btnLogin.setFont(new Font("Segoe UI Emoji", Font.BOLD, 10));
		btnLogin.setBounds(139, 182, 84, 20);
		contentPane.add(btnLogin);

		btnLogin.addActionListener(e -> loginUser());
		
		// SIGNUP BUTTON
		JButton btnSignup = new JButton("Signup");
		btnSignup.setFont(new Font("Segoe UI Emoji", Font.BOLD, 10));
		btnSignup.setBounds(139, 215, 84, 20);
		contentPane.add(btnSignup);

		btnSignup.addActionListener(e -> signupUser());
	}

	// ================= LOGIN METHOD =================
	private void loginUser() {
		String username = user.getText();
		String password = pinTxt.getText();

		if (username.isEmpty() || password.isEmpty()) {
			JOptionPane.showMessageDialog(this, "Please fill all fields");
			return;
		}

		try (Connection con = DatabaseConfig.getConnection()) {
			PreparedStatement ps = con.prepareStatement("SELECT * FROM users WHERE username=? AND password=?");
			ps.setString(1, username);
			ps.setString(2, password);

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				JOptionPane.showMessageDialog(this, "Login Successful");
				new Index().setVisible(true);  // 👉 OPEN INDEX PAGE
				dispose();
			} else {
				JOptionPane.showMessageDialog(this, "Invalid Username or Password");
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	// ================= SIGNUP METHOD =================
	private void signupUser() {
		String username = user.getText();
		String password = pinTxt.getText();

		if (username.isEmpty() || password.isEmpty()) {
			JOptionPane.showMessageDialog(this, "Please fill all fields");
			return;
		}

		try (Connection con = DatabaseConfig.getConnection()) {
			PreparedStatement ps = con.prepareStatement(
				"INSERT INTO users(username, password) VALUES (?,?)");
			ps.setString(1, username);
			ps.setString(2, password);

			ps.executeUpdate();
			JOptionPane.showMessageDialog(this, "Signup Successful! Now Login");

		} catch (Exception ex) {
			JOptionPane.showMessageDialog(this, "Username already exists!");
		}
	}
}

