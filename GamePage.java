import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

public class GamePage {
    public GamePage(String playerName) {
        JFrame frame = new JFrame("Typing Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);
        frame.setLayout(new BorderLayout());

        String randomSentence = TypingGameAppSort.sentences[new Random().nextInt(TypingGameAppSort.sentences.length)];

        JLabel sentenceLabel = new JLabel(randomSentence, JLabel.CENTER);
        sentenceLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        JTextField typingField = new JTextField();
        typingField.setFont(new Font("Arial", Font.PLAIN, 20));
        JLabel timerLabel = new JLabel("Waktu: 0.0 detik", JLabel.CENTER);
        timerLabel.setFont(new Font("Arial", Font.PLAIN, 20));

        frame.add(sentenceLabel, BorderLayout.NORTH);
        frame.add(typingField, BorderLayout.CENTER);
        frame.add(timerLabel, BorderLayout.SOUTH);

        final long[] startTime = {System.currentTimeMillis()};
        final int[] typoCount = {0};

        Thread timerThread = new Thread(() -> {
            try {
                while (true) {
                    long currentTime = System.currentTimeMillis();
                    double elapsed = (currentTime - startTime[0]) / 1000.0;
                    timerLabel.setText(String.format("Waktu: %.1f detik", elapsed));
                    Thread.sleep(100);
                }
            } catch (InterruptedException ex) {
                // Timer berhenti saat permainan selesai
            }
        });
        timerThread.start();

        typingField.addActionListener(e -> {
            String typedText = typingField.getText().trim();
            if (!typedText.equals(randomSentence)) {
                typoCount[0]++;
                JOptionPane.showMessageDialog(frame, "Teks tidak cocok, coba lagi!");
            } else {
                long endTime = System.currentTimeMillis();
                double timeTaken = (endTime - startTime[0]) / 1000.0;
                timerThread.interrupt();

                TypingGameAppSort.players.add(new Player(playerName, typoCount[0], timeTaken));
                JOptionPane.showMessageDialog(frame, "Selesai! Waktu: " + timeTaken + " detik, Typo: " + typoCount[0]);
                frame.dispose();
                TypingGameAppSort.showInputNamePage();
            }
        });

        frame.setVisible(true);
    }
}
