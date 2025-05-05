package com.manager.views;

import com.manager.controllers.HTTPController;
import com.manager.models.AuthenticationResponse;

import javax.swing.*;
import javax.swing.border.MatteBorder;
import java.awt.*;

public class SignInView {

    private static final int frameWidth = 680;
    private static final int frameHeight = 400;

    private static final String backgroundColor = "#FAFAFA";

    // Colores
    private static final Color textFieldBorderColor = new Color(36, 114, 200);
    private static final Color textFieldFontColor = new Color(36, 114, 200);
    private static final Color labelTextColor = new Color(0, 0, 0);

    private static final Color buttonBaseColor = new Color(60, 60, 60);
    private static final Color buttonHoverColor = new Color(90, 90, 90);


    // Bordes y fuente
    private static final MatteBorder textFieldBorder = BorderFactory.createMatteBorder(
            0, 0, 2, 0, textFieldBorderColor
    );
    private static final Font textFieldFont = new Font("Segoe UI", Font.PLAIN, 12);
    private static final Font labelTextFont = new Font("Segoe UI", Font.PLAIN, 15);



    public SignInView() {


        // Ventana principal
        JFrame frame = new JFrame("Iniciar sesión");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(frameWidth, frameHeight);
        frame.setLayout(null);
        frame.getContentPane().setBackground(Color.decode(backgroundColor)); // BACKGROUND_COLOR
        frame.setResizable(false);
        frame.setLocationRelativeTo(null); // 🎯 Centra la ventana

        // Panel lateral para logo (lado izquierdo)
        JPanel logoPanel = new JPanel();
        logoPanel.setBounds(0, 0, 350, frameHeight);
        logoPanel.setBackground(Color.LIGHT_GRAY); // Puedes reemplazar con imagen o color de marca
        logoPanel.setLayout(null);

        frame.add(logoPanel);


        // Texto y campo del usuario
        JLabel usernameLabel = new JLabel("Usuario");
        usernameLabel.setBounds(400, 60, 240, 15);
        usernameLabel.setForeground(labelTextColor);
        usernameLabel.setFont(labelTextFont);
        frame.add(usernameLabel);

        JTextField usernameTextField = new JTextField();
        usernameTextField.setBounds(400, 80, 240, 25); // Justo debajo del label
        usernameTextField.setOpaque(false); // Fondo transparente
        usernameTextField.setBackground(new Color(0, 0, 0, 0)); // RGBA transparente
        usernameTextField.setForeground(textFieldFontColor); // Texto azul
        usernameTextField.setHorizontalAlignment(JTextField.CENTER); // Centrado
        usernameTextField.setFont(textFieldFont); // Fuente moderna
        usernameTextField.setBorder(textFieldBorder); // Línea inferior azul
        frame.add(usernameTextField);

        // Texto y campo de la contraseña del usuario
        JLabel passwordLabel = new JLabel("Contraseña");
        passwordLabel.setBounds(400, 140, 240, 15);
        passwordLabel.setForeground(labelTextColor);
        passwordLabel.setFont(labelTextFont);
        frame.add(passwordLabel);

        JPasswordField passwordField = new JPasswordField();
        passwordField.setBounds(400, 160, 240, 25);
        passwordField.setOpaque(false);
        passwordField.setBackground(new Color(0, 0, 0, 0));
        passwordField.setForeground(textFieldFontColor);
        passwordField.setHorizontalAlignment(JTextField.CENTER);
        passwordField.setFont(textFieldFont);
        passwordField.setBorder(textFieldBorder);
        frame.add(passwordField);

        usernameTextField.setCaretColor(textFieldFontColor);
        passwordField.setCaretColor(textFieldFontColor);

        // Botón "Iniciar sesión"
        JButton signInButton = new JButton("Iniciar sesión");
        signInButton.setBounds(400, 220, 240, 30);

        // Estilo Material UI plano
        signInButton.setFocusPainted(false);
        signInButton.setBorderPainted(false);
        signInButton.setContentAreaFilled(false);
        signInButton.setOpaque(true);

        // Colores del botón

        signInButton.setBackground(buttonBaseColor);
        signInButton.setForeground(Color.WHITE);

        // Efecto hover
        signInButton.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                signInButton.setBackground(buttonHoverColor);
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                signInButton.setBackground(buttonBaseColor);
            }
        });

        signInButton.addActionListener(e -> {
            String username = usernameTextField.getText();
            String password = new String(passwordField.getPassword());

            if (username.isBlank() || password.isBlank()) {
                JOptionPane.showMessageDialog(frame, "Por favor completa todos los campos", "Atención", JOptionPane.WARNING_MESSAGE);
                return;
            }

            HTTPController httpController = new HTTPController();



            AuthenticationResponse authenticationResponse = httpController.signIn(username, password);

            System.out.println(authenticationResponse);

            if (
                    authenticationResponse == null || !authenticationResponse.isSuccess()
            ) {
                JOptionPane.showMessageDialog(frame, "Credenciales inválidas", "Error", JOptionPane.ERROR_MESSAGE);

            } else {
                httpController.setAccessToken(authenticationResponse.accessToken());
                // Aquí puedes guardar token en Session, abrir otra vista, etc.
                JOptionPane.showMessageDialog(frame, "Bienvenido " + username);
                frame.dispose(); // cerrar login si quieres


                MenuView menuView = new MenuView();

                System.out.println(authenticationResponse.accessToken());
            }
        });


        frame.add(signInButton);

        // Mostrar ventana
        frame.setVisible(true);
    }

    private boolean buildUI(){
        return true;
    }

}
