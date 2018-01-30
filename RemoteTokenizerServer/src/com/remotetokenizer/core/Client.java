/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.remotetokenizer.core;

import com.remotetokenizer.contracts.IAlert;
import com.remotetokenizer.contracts.IAuthentication;
import com.remotetokenizer.contracts.IRegister;
import com.remotetokenizer.contracts.IRetriever;
import com.remotetokenizer.contracts.ITokenizer;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.SwingConstants;

/**
 *
 * @author Georgi Spasov
 */
public class Client extends javax.swing.JFrame implements IAlert {

    private IAlert stub;

    private UUID cookie;

    private Registry registry;

    /**
     * Creates new form ClientPanelTest
     */
    public Client() throws RemoteException {
        initComponents();
        pnlLogout.setVisible(false);
        pnlResult.setVisible(false);
        btnCopy.setVisible(false);
        pnlRegister.setVisible(false);
        tabsMainPane.setEnabledAt(1, false);
        lblUsername.setHorizontalAlignment(SwingConstants.RIGHT);

//        stub = (IAlert) UnicastRemoteObject.exportObject(this, 0);
    }

    // Client methods =============================================================================================
    public static String logedInUsername;
    public static boolean isLogedIn;

    public boolean login(String username, String password) {
        UUID receivedCookie = null;
        boolean success = false;
        IAuthentication authentication;
        try {
            this.registry = LocateRegistry.getRegistry(1099);
            // Getting Object using Remote Address
            authentication = (IAuthentication) registry.lookup(IAuthentication.LOOKUPNAME);
            IAlert stub = (IAlert) UnicastRemoteObject.exportObject(this, 0);
            // Invoking the Method
            this.stub = stub;
            receivedCookie = authentication.authenticate(username, password, stub);
            if (receivedCookie != null) {
                this.cookie = receivedCookie; // Reuse for later requests ===========================================
                success = true;
                // Do Something....
            } else {
                UnicastRemoteObject.unexportObject(this, true);
            }
        } catch (RemoteException | NotBoundException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }

        return success;
    }

    public boolean logout() throws NotBoundException {
        boolean status = false;
        IAuthentication authentication;
        try {
            this.registry = LocateRegistry.getRegistry(1099);
            // Getting Object using Remote Address
            authentication = (IAuthentication) registry.lookup(IAuthentication.LOOKUPNAME);
//            IAlert stub = (IAlert) UnicastRemoteObject.exportObject(this, 0);
            // Invoking the Method
//            this.stub = stub;
            authentication.logOut(this.cookie, stub);
            this.cookie = null;
            UnicastRemoteObject.unexportObject(this, true);
        } catch (RemoteException /*| NotBoundException*/ e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }

        return status;
    }

    public String tokenize() {
        String bankId = txtInput.getText();
        boolean success = false;
        String token = "";
        ITokenizer tokenizer;
        try {
            Registry registry = LocateRegistry.getRegistry(1099);
            tokenizer = (ITokenizer) registry.lookup(ITokenizer.LOOKUPNAME);
//            IAlert stub = (IAlert) UnicastRemoteObject.exportObject(this, 0);
            token = tokenizer.createToken(bankId, cookie, this.stub);
            if (!token.isEmpty()) {
                success = true;
//                UnicastRemoteObject.unexportObject(this, true);
            } else {
//                UnicastRemoteObject.unexportObject(this, true); //stub still in use!!!
            }
        } catch (RemoteException | NotBoundException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }

        return token;
    }

    public String retrieve() {
        String token = txtInput.getText();
        boolean success = false;
        String retrievedCardId = "";
        IRetriever retriever;
        try {
            Registry registry = LocateRegistry.getRegistry(1099);
            retriever = (IRetriever) registry.lookup(IRetriever.LOOKUPNAME);
//            IAlert stub = (IAlert) UnicastRemoteObject.exportObject(this, 0);
            retrievedCardId = retriever.getCardId(token, cookie, this.stub);
            if (!retrievedCardId.isEmpty()) {
                success = true;
//                UnicastRemoteObject.unexportObject(this, true);
            } else {
//                UnicastRemoteObject.unexportObject(this, true); //stub still in use!!!
            }
        } catch (RemoteException | NotBoundException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }

        return retrievedCardId;
    }

    public void register() {
        String bankId = txtInput.getText();
        boolean success = false;
        String name = txtRegisterUsername.getText();
        String pass = txtRegisterPassword.getText();
        boolean canTokenize = chkCanTokenize.isSelected();
        boolean canRetrieve = chkCanRetrieve.isSelected();
        IRegister registrar;

        try {
            Registry registry = LocateRegistry.getRegistry(1099);
            registrar = (IRegister) registry.lookup(IRegister.LOOKUPNAME);
            registrar.register(name, pass, canTokenize, canRetrieve, cookie, stub);
        } catch (RemoteException | NotBoundException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    // Client methods />=============================================================================================
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlClientGUI = new javax.swing.JPanel();
        tabsMainPane = new javax.swing.JTabbedPane();
        pnlHome = new javax.swing.JPanel();
        lblWelcome = new javax.swing.JLabel();
        pnlOperations = new javax.swing.JPanel();
        pnlLogout = new javax.swing.JLayeredPane();
        lblUsername = new javax.swing.JLabel();
        btnLogout = new javax.swing.JButton();
        pnlResult = new javax.swing.JLayeredPane();
        btnTokenize = new javax.swing.JButton();
        lblOutput = new javax.swing.JLabel();
        btnCopy = new javax.swing.JButton();
        txtInput = new javax.swing.JTextField();
        btnRetrieve = new javax.swing.JButton();
        lblResultType = new javax.swing.JLabel();
        pnlUser = new javax.swing.JLayeredPane();
        pnlLogin = new javax.swing.JLayeredPane();
        txtUsername = new javax.swing.JTextField();
        btnLogin = new javax.swing.JButton();
        txtPassword = new javax.swing.JPasswordField();
        pnlRegister = new javax.swing.JLayeredPane();
        chkCanRetrieve = new javax.swing.JCheckBox();
        chkCanTokenize = new javax.swing.JCheckBox();
        btnRegisterUser = new javax.swing.JButton();
        txtRegisterUsername = new javax.swing.JTextField();
        txtRegisterPassword = new javax.swing.JPasswordField();
        lblLog = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Remote Tokenizer");

        lblWelcome.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        lblWelcome.setText("     Welcome to RMI Tokenizer System");
        lblWelcome.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        lblWelcome.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout pnlHomeLayout = new javax.swing.GroupLayout(pnlHome);
        pnlHome.setLayout(pnlHomeLayout);
        pnlHomeLayout.setHorizontalGroup(
            pnlHomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlHomeLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblWelcome, javax.swing.GroupLayout.DEFAULT_SIZE, 375, Short.MAX_VALUE)
                .addContainerGap())
        );
        pnlHomeLayout.setVerticalGroup(
            pnlHomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlHomeLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblWelcome, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE))
        );

        tabsMainPane.addTab("Home", pnlHome);

        pnlLogout.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        lblUsername.setText("Loged User");

        btnLogout.setText("Logout");
        btnLogout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLogoutActionPerformed(evt);
            }
        });

        pnlLogout.setLayer(lblUsername, javax.swing.JLayeredPane.DEFAULT_LAYER);
        pnlLogout.setLayer(btnLogout, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout pnlLogoutLayout = new javax.swing.GroupLayout(pnlLogout);
        pnlLogout.setLayout(pnlLogoutLayout);
        pnlLogoutLayout.setHorizontalGroup(
            pnlLogoutLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlLogoutLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblUsername, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnLogout)
                .addContainerGap())
        );
        pnlLogoutLayout.setVerticalGroup(
            pnlLogoutLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlLogoutLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(btnLogout)
                .addComponent(lblUsername))
        );

        pnlResult.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        btnTokenize.setText("Tokenize");
        btnTokenize.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTokenizeActionPerformed(evt);
            }
        });

        lblOutput.setText("Output");

        btnCopy.setText("Copy");
        btnCopy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCopyActionPerformed(evt);
            }
        });

        txtInput.setToolTipText("Input");

        btnRetrieve.setText("Retrieve");
        btnRetrieve.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRetrieveActionPerformed(evt);
            }
        });

        lblResultType.setText("Resul Type");

        pnlResult.setLayer(btnTokenize, javax.swing.JLayeredPane.DEFAULT_LAYER);
        pnlResult.setLayer(lblOutput, javax.swing.JLayeredPane.DEFAULT_LAYER);
        pnlResult.setLayer(btnCopy, javax.swing.JLayeredPane.DEFAULT_LAYER);
        pnlResult.setLayer(txtInput, javax.swing.JLayeredPane.DEFAULT_LAYER);
        pnlResult.setLayer(btnRetrieve, javax.swing.JLayeredPane.DEFAULT_LAYER);
        pnlResult.setLayer(lblResultType, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout pnlResultLayout = new javax.swing.GroupLayout(pnlResult);
        pnlResult.setLayout(pnlResultLayout);
        pnlResultLayout.setHorizontalGroup(
            pnlResultLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlResultLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlResultLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlResultLayout.createSequentialGroup()
                        .addComponent(lblResultType, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblOutput, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlResultLayout.createSequentialGroup()
                        .addComponent(txtInput, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnTokenize)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlResultLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnRetrieve)
                    .addComponent(btnCopy, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlResultLayout.setVerticalGroup(
            pnlResultLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlResultLayout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(pnlResultLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtInput, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnTokenize)
                    .addComponent(btnRetrieve))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlResultLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblResultType)
                    .addComponent(lblOutput)
                    .addComponent(btnCopy))
                .addContainerGap(17, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout pnlOperationsLayout = new javax.swing.GroupLayout(pnlOperations);
        pnlOperations.setLayout(pnlOperationsLayout);
        pnlOperationsLayout.setHorizontalGroup(
            pnlOperationsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlOperationsLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlOperationsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(pnlResult)
                    .addComponent(pnlLogout))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlOperationsLayout.setVerticalGroup(
            pnlOperationsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlOperationsLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlLogout, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlResult))
        );

        tabsMainPane.addTab("Operations", pnlOperations);

        pnlUser.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        pnlLogin.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        txtUsername.setText("Username");

        btnLogin.setText("Login");
        btnLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLoginActionPerformed(evt);
            }
        });

        txtPassword.setText("jPasswordField1");

        pnlLogin.setLayer(txtUsername, javax.swing.JLayeredPane.DEFAULT_LAYER);
        pnlLogin.setLayer(btnLogin, javax.swing.JLayeredPane.DEFAULT_LAYER);
        pnlLogin.setLayer(txtPassword, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout pnlLoginLayout = new javax.swing.GroupLayout(pnlLogin);
        pnlLogin.setLayout(pnlLoginLayout);
        pnlLoginLayout.setHorizontalGroup(
            pnlLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlLoginLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(txtUsername, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnLogin)
                .addContainerGap())
        );
        pnlLoginLayout.setVerticalGroup(
            pnlLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(btnLogin)
                .addComponent(txtUsername, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(txtPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pnlRegister.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Register User"));

        chkCanRetrieve.setText("Retrieve");

        chkCanTokenize.setText("Tokenize");

        btnRegisterUser.setText("Register");
        btnRegisterUser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegisterUserActionPerformed(evt);
            }
        });

        txtRegisterUsername.setText("Username");

        txtRegisterPassword.setText("jPasswordField1");

        pnlRegister.setLayer(chkCanRetrieve, javax.swing.JLayeredPane.DEFAULT_LAYER);
        pnlRegister.setLayer(chkCanTokenize, javax.swing.JLayeredPane.DEFAULT_LAYER);
        pnlRegister.setLayer(btnRegisterUser, javax.swing.JLayeredPane.DEFAULT_LAYER);
        pnlRegister.setLayer(txtRegisterUsername, javax.swing.JLayeredPane.DEFAULT_LAYER);
        pnlRegister.setLayer(txtRegisterPassword, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout pnlRegisterLayout = new javax.swing.GroupLayout(pnlRegister);
        pnlRegister.setLayout(pnlRegisterLayout);
        pnlRegisterLayout.setHorizontalGroup(
            pnlRegisterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlRegisterLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlRegisterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlRegisterLayout.createSequentialGroup()
                        .addComponent(chkCanTokenize)
                        .addGap(18, 18, 18)
                        .addComponent(chkCanRetrieve)
                        .addGap(38, 38, 38)
                        .addComponent(btnRegisterUser)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(pnlRegisterLayout.createSequentialGroup()
                        .addComponent(txtRegisterUsername, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtRegisterPassword)))
                .addContainerGap())
        );
        pnlRegisterLayout.setVerticalGroup(
            pnlRegisterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlRegisterLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlRegisterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtRegisterUsername, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtRegisterPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlRegisterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(chkCanTokenize)
                    .addComponent(chkCanRetrieve)
                    .addComponent(btnRegisterUser))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pnlUser.setLayer(pnlLogin, javax.swing.JLayeredPane.DEFAULT_LAYER);
        pnlUser.setLayer(pnlRegister, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout pnlUserLayout = new javax.swing.GroupLayout(pnlUser);
        pnlUser.setLayout(pnlUserLayout);
        pnlUserLayout.setHorizontalGroup(
            pnlUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlUserLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlRegister, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pnlLogin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlUserLayout.setVerticalGroup(
            pnlUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlUserLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlLogin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlRegister, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tabsMainPane.addTab("Login/Register", pnlUser);

        lblLog.setText("Log");

        javax.swing.GroupLayout pnlClientGUILayout = new javax.swing.GroupLayout(pnlClientGUI);
        pnlClientGUI.setLayout(pnlClientGUILayout);
        pnlClientGUILayout.setHorizontalGroup(
            pnlClientGUILayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tabsMainPane)
            .addGroup(pnlClientGUILayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblLog, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlClientGUILayout.setVerticalGroup(
            pnlClientGUILayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlClientGUILayout.createSequentialGroup()
                .addComponent(tabsMainPane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblLog, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlClientGUI, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlClientGUI, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLoginActionPerformed
        boolean isRegistered = login(txtUsername.getText(), txtPassword.getText());
        if (isRegistered) {
            pnlResult.setVisible(true);
            pnlLogin.setVisible(false);
            lblUsername.setText(txtUsername.getText());
            pnlLogout.setVisible(true);
            if (txtUsername.getText().equals("admin")) {
                pnlRegister.setVisible(true);
            } else {
                tabsMainPane.setEnabledAt(2, false);
            }
            tabsMainPane.setEnabledAt(1, true);
            tabsMainPane.setSelectedIndex(1);
        }
    }//GEN-LAST:event_btnLoginActionPerformed

    private void btnTokenizeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTokenizeActionPerformed
        lblOutput.setText(this.tokenize());
        lblResultType.setText("Token: ");
        btnCopy.setVisible(true);
    }//GEN-LAST:event_btnTokenizeActionPerformed

    private void btnCopyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCopyActionPerformed
        String myString = lblOutput.getText();
        StringSelection stringSelection = new StringSelection(myString);
        Clipboard clpbrd = Toolkit.getDefaultToolkit().getSystemClipboard();
        clpbrd.setContents(stringSelection, null);
    }//GEN-LAST:event_btnCopyActionPerformed

    private void btnRetrieveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRetrieveActionPerformed
        // TODO add your handling code here:
        lblOutput.setText(this.retrieve());
        lblResultType.setText("Card Id: ");
        btnCopy.setVisible(true);
    }//GEN-LAST:event_btnRetrieveActionPerformed

    private void btnLogoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLogoutActionPerformed
        try {
            logout();
        } catch (NotBoundException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
        pnlLogout.setVisible(false);
        pnlLogin.setVisible(true);
        txtUsername.setText("Username");
        txtPassword.setText("   ");
        pnlResult.setVisible(false);
        txtInput.setText("");
        lblOutput.setText("");
        lblResultType.setText("");
        btnCopy.setVisible(false);
        tabsMainPane.setSelectedIndex(2);
        pnlRegister.setVisible(false);
        tabsMainPane.setEnabledAt(1, false);
        tabsMainPane.setEnabledAt(2, true);
    }//GEN-LAST:event_btnLogoutActionPerformed

    private void btnRegisterUserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegisterUserActionPerformed
        // TODO add your handling code here:
        this.register();
    }//GEN-LAST:event_btnRegisterUserActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Client.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Client.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Client.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Client.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        // Client methods =============================================================================================
        // Client methods =============================================================================================        
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            try {
                new Client().setVisible(true);
            } catch (RemoteException ex) {
                Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCopy;
    private javax.swing.JButton btnLogin;
    private javax.swing.JButton btnLogout;
    private javax.swing.JButton btnRegisterUser;
    private javax.swing.JButton btnRetrieve;
    private javax.swing.JButton btnTokenize;
    private javax.swing.JCheckBox chkCanRetrieve;
    private javax.swing.JCheckBox chkCanTokenize;
    private javax.swing.JLabel lblLog;
    private javax.swing.JLabel lblOutput;
    private javax.swing.JLabel lblResultType;
    private javax.swing.JLabel lblUsername;
    private javax.swing.JLabel lblWelcome;
    private javax.swing.JPanel pnlClientGUI;
    private javax.swing.JPanel pnlHome;
    private javax.swing.JLayeredPane pnlLogin;
    private javax.swing.JLayeredPane pnlLogout;
    private javax.swing.JPanel pnlOperations;
    private javax.swing.JLayeredPane pnlRegister;
    private javax.swing.JLayeredPane pnlResult;
    private javax.swing.JLayeredPane pnlUser;
    private javax.swing.JTabbedPane tabsMainPane;
    private javax.swing.JTextField txtInput;
    private javax.swing.JPasswordField txtPassword;
    private javax.swing.JPasswordField txtRegisterPassword;
    private javax.swing.JTextField txtRegisterUsername;
    private javax.swing.JTextField txtUsername;
    // End of variables declaration//GEN-END:variables

    @Override
    public void alert(String message) throws RemoteException {
        lblLog.setText(message);
    }
}
