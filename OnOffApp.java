package com.example;

import com.fazecast.jSerialComm.SerialPort;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Aplicativo simples com um botão Ligar / Desligar.
 * Ao alternar, tenta enviar '1' (ligar) ou '0' (desligar) pela porta serial
 * para um Arduino conectado (caso a porta tenha sido selecionada).
 */
public class OnOffApp {

    private boolean ligado = false;
    private SerialPort serialPort = null;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            OnOffApp app = new OnOffApp();
            app.createAndShowGui();
        });
    }

    private void createAndShowGui() {
        JFrame frame = new JFrame("On/Off - GUI com Arduino");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(420, 220);
        frame.setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout(10,10));
        panel.setBorder(BorderFactory.createEmptyBorder(12,12,12,12));

        JLabel statusLabel = new JLabel(getStatusText(), SwingConstants.CENTER);
        statusLabel.setFont(new Font("SansSerif", Font.BOLD, 16));

        JButton toggleButton = new JButton(getButtonText());
        toggleButton.setFont(new Font("SansSerif", Font.PLAIN, 14));
        toggleButton.setPreferredSize(new Dimension(140, 40));

        // ComboBox para selecionar porta serial
        JPanel top = new JPanel(new FlowLayout(FlowLayout.CENTER, 8, 0));
        JLabel portLabel = new JLabel("Porta serial:");
        String[] ports = listarPortas();
        JComboBox<String> portCombo = new JComboBox<>(ports);
        portCombo.setSelectedIndex(ports.length > 0 ? 0 : -1);
        JButton connectBtn = new JButton("Conectar");
        connectBtn.addActionListener(e -> {
            String sel = (String) portCombo.getSelectedItem();
            if (sel != null && !sel.isEmpty()) {
                conectarPorta(sel);
            } else {
                JOptionPane.showMessageDialog(frame, "Nenhuma porta selecionada.", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        });

        top.add(portLabel);
        top.add(portCombo);
        top.add(connectBtn);

        toggleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ligado = !ligado;
                statusLabel.setText(getStatusText());
                toggleButton.setText(getButtonText());
                System.out.println("Estado agora: " + (ligado ? "LIGADO" : "DESLIGADO"));
                enviarComandoSerial(ligado ? '1' : '0');
            }
        });

        panel.add(top, BorderLayout.NORTH);
        panel.add(statusLabel, BorderLayout.CENTER);
        JPanel bottom = new JPanel();
        bottom.add(toggleButton);
        panel.add(bottom, BorderLayout.SOUTH);

        frame.setContentPane(panel);
        frame.setVisible(true);
    }

    private String getStatusText() {
        return ligado ? "Estado: LIGADO" : "Estado: DESLIGADO";
    }

    private String getButtonText() {
        return ligado ? "DESLIGAR" : "LIGAR";
    }

    private String[] listarPortas() {
        SerialPort[] sps = SerialPort.getCommPorts();
        String[] names = new String[sps.length];
        for (int i = 0; i < sps.length; i++) {
            names[i] = sps[i].getSystemPortName();
        }
        if (names.length == 0) {
            names = new String[] { "Nenhuma porta encontrada" };
        }
        return names;
    }

    private void conectarPorta(String portName) {
        if (serialPort != null && serialPort.isOpen()) {
            serialPort.closePort();
            serialPort = null;
        }
        serialPort = SerialPort.getCommPort(portName);
        serialPort.setBaudRate(9600);
        if (serialPort.openPort()) {
            JOptionPane.showMessageDialog(null, "Conectado em " + portName);
            System.out.println("Conectado em " + portName);
        } else {
            JOptionPane.showMessageDialog(null, "Falha ao abrir " + portName, "Erro", JOptionPane.ERROR_MESSAGE);
            serialPort = null;
        }
    }

    private void enviarComandoSerial(char c) {
        if (serialPort == null || !serialPort.isOpen()) {
            System.out.println("Porta serial não conectada. Ignorando envio.");
            return;
        }
        byte[] buf = new byte[] { (byte) c };
        serialPort.writeBytes(buf, 1);
    }
}
