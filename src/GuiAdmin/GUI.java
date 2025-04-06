package GuiAdmin;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

public class GUI {
    private JFrame frame;
    private JTextField clientNameField;
    private JTextField clientPhoneField;
    private JComboBox<String> trainerComboBox;
    private JSpinner sessionTimeSpinner;
    private DataBase dbManager;

    public GUI() {
        dbManager = new DataBase();
        frame = new JFrame("Контроль тренировок");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setLayout(new GridLayout(5, 2));

        // имя клиента
        frame.add(new JLabel("Имя клиента:"));
        clientNameField = new JTextField();
        frame.add(clientNameField);

        // телефон клиента
        frame.add(new JLabel("Телефон клиента:"));
        clientPhoneField = new JTextField();
        frame.add(clientPhoneField);

        // выбрать тренера
        frame.add(new JLabel("Выбрать тренера:"));
        trainerComboBox = new JComboBox<>();
        loadTrainers();
        frame.add(trainerComboBox);

        // время тренировки
        frame.add(new JLabel("Время тренировки:"));
        sessionTimeSpinner = new JSpinner(new SpinnerDateModel());
        JSpinner.DateEditor editor = new JSpinner.DateEditor(sessionTimeSpinner, "yyyy-MM-dd HH:mm");
        sessionTimeSpinner.setEditor(editor);
        frame.add(sessionTimeSpinner);

        // кнопка
        JButton addButton = new JButton("Добавить тренировку");
        addButton.addActionListener(new AddSessionAction());
        frame.add(addButton);

        frame.setVisible(true);
    }

    private void loadTrainers() {
        try {
            List<String> trainers = dbManager.getTrainers();
            for (String trainer : trainers) {
                trainerComboBox.addItem(trainer);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(frame, "Ошибка добавления тренера: " + e.getMessage());
        }
    }

    private class AddSessionAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String clientName = clientNameField.getText();
            String clientPhone = clientPhoneField.getText();
            String trainerName = (String) trainerComboBox.getSelectedItem();
            Timestamp sessionTime = new Timestamp(((java.util.Date) sessionTimeSpinner.getValue()).getTime());

            try {
                // добавление клиента
                dbManager.addClient(clientName, clientPhone);

                // получение ID тренера
                int trainerId = dbManager.getTrainerId(trainerName);
                if (trainerId != -1) {
                    // получение ID клиента
                    int clientId = dbManager.getClientId(clientName, clientPhone);
                    if (clientId != -1) {
                        // добавление сессии
                        dbManager.addSession(trainerId, clientId, sessionTime);
                        JOptionPane.showMessageDialog(frame, "Тренировка добавлена!");
                    } else {
                        JOptionPane.showMessageDialog(frame, "Клиент не найден.");
                    }
                } else {
                    JOptionPane.showMessageDialog(frame, "Тренер не найден.");
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(frame, "Ошибка добавления тренировки: " + ex.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(GUI::new);
    }
}