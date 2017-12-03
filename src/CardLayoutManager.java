import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.peer.ButtonPeer;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class CardLayoutManager extends JFrame {

	private JPanel cardPanel;
	private CardLayout cl;
	ClientInterface clientInterface = new ClientInterface();
	Color color = new Color(25, 30, 40);

	public CardLayoutManager() {

		setTitle("ATM Simulation");
		setSize(1000, 1000);
		cardPanel = new JPanel();

		// getContentPane().add(cardPanel);
		cl = new CardLayout(10, 10);
		cardPanel.setLayout(cl);
		JPanel jp1 = new JPanel();
		JPanel jp2 = new JPanel();
		JPanel jp3 = new JPanel();
		JPanel jp4 = new JPanel();
		JPanel jp5 = new JPanel();
		JPanel jp6 = new JPanel();
		JPanel jp7 = new JPanel();
		JPanel buttonPanel = new JPanel();
		buttonPanel.setBackground(color);
        JButton logoutButton = new JButton("Logout");
        JButton menuButton = new JButton("Menu");
        
        buttonPanel.add(logoutButton);
        buttonPanel.add(menuButton);
        
		cardPanel.add(jp1, "1");
		cardPanel.add(jp2, "2");
		cardPanel.add(jp3, "3");
		cardPanel.add(jp4, "4");
		cardPanel.add(jp5, "5");
		cardPanel.add(jp6, "6");
		cardPanel.add(jp7, "7");

		jp1.setLayout(new GridLayout(0, 1));

		JTextField textCustomerId = new JTextField(15);
		JTextField textPassword = new JTextField(15);

		JLabel lableCustomerId = new JLabel("Customer ID");
		JLabel labelPassword = new JLabel("Password");

		JButton submitButton = new JButton("Submit");

		JLabel errorLabel = new JLabel();

		jp1.add(lableCustomerId);
		jp1.add(textCustomerId);
		jp1.add(labelPassword);
		jp1.add(textPassword);
		jp1.add(submitButton);
		jp1.add(errorLabel);

		jp2.setLayout(new GridLayout(0, 1));
		//JButton logoutButton = new JButton("logout");
		JButton mainMenuButton = new JButton("Menu");
		JLabel userCreationlabel = new JLabel("Enter Following to create a new user");
		JLabel newUsername = new JLabel("Name");
		JTextField textNewUsername = new JTextField();
		JLabel newPassword = new JLabel("Password");
		JTextField textNewPassword = new JTextField();
		JButton createUserButton = new JButton("Create User");
		JLabel newCustID = new JLabel("UserID");
		JTextField textNewCustId = new JTextField();
		JLabel userCreatedLable = new JLabel();

		jp2.add(userCreationlabel);
		jp2.add(newCustID);
		jp2.add(textNewCustId);
		jp2.add(newUsername);
		jp2.add(textNewUsername);
		jp2.add(newPassword);
		jp2.add(textNewPassword);
		jp2.add(createUserButton);

		jp2.add(userCreatedLable);

		jp3.setLayout(new GridLayout(0, 1));
		JTextField withdrawlAmount = new JTextField();
		JComboBox<String> typeOfAccount = new JComboBox<String>();
		typeOfAccount.addItem("saving");
		typeOfAccount.addItem("checking");
		JButton withdraw = new JButton("WIthdraw");
		JLabel withdrawMessageLabel = new JLabel();

		jp3.add(new JLabel("Withdrawl Screen"));
		jp3.add(new JLabel("Amount"));
		jp3.add(withdrawlAmount);
		jp3.add(new JLabel("Select type of account"));
		jp3.add(typeOfAccount);
		jp3.add(withdraw);
		
		jp3.add(withdrawMessageLabel);

		jp4.setLayout(new GridLayout(0, 1));
		JTextField depositAmount = new JTextField();
		JComboBox<String> accountTyp = new JComboBox<String>();
		accountTyp.addItem("saving");
		accountTyp.addItem("checking");
		
		JButton deposit = new JButton("Deposit");
		JLabel depositMessageLabel = new JLabel();

		jp4.add(new JLabel("Deposit Screen"));
		jp4.add(new JLabel("Amount"));
		jp4.add(depositAmount);
		jp4.add(new JLabel("Select type of account"));
		jp4.add(accountTyp);
		jp4.add(deposit);
		
		
		jp4.add(depositMessageLabel);
		
		
		jp6.setLayout(new GridLayout(0, 1));
		JTextField transferAmount = new JTextField();
		JComboBox<String> transferFrom = new JComboBox<String>();
		transferFrom.addItem("saving to checking");
		transferFrom.addItem("checking to saving");
		JLabel transferMessageLabel = new JLabel();
		JButton transferButton = new JButton("Transfer");

		jp6.add(new JLabel("Transfer Screen"));
		jp6.add(new JLabel("Amount"));
		jp6.add(transferAmount);
		jp6.add(new JLabel("Transfer from"));
		jp6.add(transferFrom);
		jp6.add(transferButton);
		
		jp6.add(transferMessageLabel);

		jp5.setLayout(new GridLayout(0, 1));
		JLabel welcomeLabel = new JLabel("Select the activity :");
		JRadioButton balance = new JRadioButton("Check Balance");
		JRadioButton wdraw = new JRadioButton("Withdraw Money");
		JRadioButton depositMoney = new JRadioButton("Deposit Money");
		JRadioButton trans = new JRadioButton("Transfer Money");
		JButton welcomeScreenButton = new JButton("Submit");
		jp5.add(welcomeLabel);
		jp5.add(balance);
		jp5.add(wdraw);
		jp5.add(depositMoney);
		jp5.add(trans);
		jp5.add(welcomeScreenButton);

		jp7.setLayout(new GridLayout(0, 1));
		JComboBox<String> balanceAccountType = new JComboBox<String>();
		balanceAccountType.addItem("saving");
		balanceAccountType.addItem("checking");
		JButton showBalance = new JButton("Show Balance");
		JLabel balanceMessageLabel = new JLabel();

		jp7.add(balanceAccountType);
		jp7.add(showBalance);
		
		jp7.add(balanceMessageLabel);

		logoutButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				clientInterface.logout();
				cl.show(cardPanel, "1");

			}
		});

		menuButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				cl.show(cardPanel, "5");

			}
		});

		submitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				String loginCustId = textCustomerId.getText();
				String loginPassword = textPassword.getText();
				if (clientInterface.constructAndSendAuthenticationObject(loginCustId, loginPassword)) {
					errorLabel.setText("");
					if (loginCustId.equals("admin")) {
						cl.show(cardPanel, "2");
					} else {
						cl.show(cardPanel, "5");
					}
				} else {
					errorLabel.setText("Authentication failed");
				}

			}
		});

		createUserButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String newCustID = textNewCustId.getText();
				String newUserName = textNewUsername.getText();
				String loginPassword = textNewPassword.getText();
				String message = clientInterface.createNewUser(newUserName, newCustID, loginPassword);
				userCreatedLable.setText(message);

			}
		});

		welcomeScreenButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (balance.isSelected()) {
					cl.show(cardPanel, "7");
				} else if (wdraw.isSelected()) {
					cl.show(cardPanel, "3");
				} else if (trans.isSelected()) {
					cl.show(cardPanel, "6");
				} else {
					cl.show(cardPanel, "4");
				}

			}
		});

		withdraw.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				String amount = withdrawlAmount.getText();
				String accountType = typeOfAccount.getSelectedItem().toString();
				withdrawMessageLabel.setText(clientInterface.withdrawAmountFromAccount(amount, accountType));

			}
		});

		deposit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				String amount = depositAmount.getText();
				String accountType = accountTyp.getSelectedItem().toString();
				depositMessageLabel.setText(clientInterface.depositMoneyToAccount(amount, accountType));

			}
		});
		showBalance.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String accountForBalance = balanceAccountType.getSelectedItem().toString();
				balanceMessageLabel.setText(clientInterface.CheckBalanceInAccount(accountForBalance));
			}
		});

		transferButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String typeOfTransfer = transferFrom.getSelectedItem().toString();
				String amountTransfered = transferAmount.getText();
				transferMessageLabel.setText(clientInterface.transferMoney(typeOfTransfer, amountTransfered));

			}
		});

		getContentPane().add(cardPanel, BorderLayout.NORTH);
		getContentPane().add(buttonPanel, BorderLayout.SOUTH);

	}

	public static void main(String[] args) {
		CardLayoutManager cl = new CardLayoutManager();
		cl.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		cl.setVisible(true);
	}
}