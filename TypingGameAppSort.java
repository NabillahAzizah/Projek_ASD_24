import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class TypingGameAppSort {
    static ArrayList<Player> players = new ArrayList<>();
    static String[] sentences = {
        "Cinta keluarga adalah kekuatan yang tak terlihat",
        "Dalam kasih sayang, kita tumbuh dan berkembang",
        "Bersama keluarga, dunia terasa lebih indah dan damai",
        "Keluarga adalah pelabuhan aman di saat gelisah",
        "Dalam tawa bersama, kebahagiaan semakin terasa lengkap",
        "Setiap langkah bersama keluarga adalah kebahagiaan sejati",
        "Keluarga adalah tempat di mana cinta tumbuh",
        "Bersama keluarga, hari-hari penuh harapan dan cinta",
        "Ikatan keluarga takkan pernah pudar oleh waktu.",
        "Kasih sayang keluarga adalah cahaya dalam gelap",
        "Keluarga memberikan kekuatan dalam menghadapi segala rintangan",
        "Cinta keluarga adalah hadiah terindah dalam hidup",
        "Dalam pelukan keluarga, dunia terasa lebih damai",
        "Keluarga adalah tempat di mana hati merasa tenang",
        "Kasih yang tulus menyatukan hati dalam keluarga",
        "Di tengah keluarga, kita selalu merasa dicintai",
        "Keluarga adalah rumah yang penuh dengan kehangatan",
        "Dalam keluarga, kita belajar arti kebersamaan yang sejati",
        "Cinta keluarga mengajarkan kita untuk saling peduli",
        "Bersama keluarga, hidup menjadi lebih penuh makna"
        };

    public static void main(String[] args) {
        showWelcomePage();
    }

    private static void showWelcomePage() {
        JFrame frame = new JFrame("Typing Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 300);
        frame.setLayout(new BorderLayout());

        JLabel welcomeLabel = new JLabel("Selamat Datang di Typing Game!", JLabel.CENTER);
        welcomeLabel.setFont(new Font("Arial", Font.PLAIN, 30));
        JButton startButton = new JButton("Mulai");
        startButton.setFont(new Font("Arial", Font.PLAIN, 20));

        frame.add(welcomeLabel, BorderLayout.CENTER);
        frame.add(startButton, BorderLayout.SOUTH);

        startButton.addActionListener(e -> {
            frame.dispose();
            showInputNamePage();
        });

        frame.setVisible(true);
    }

    static void showInputNamePage() {
        JFrame frame = new JFrame("Masukkan Nama Peserta");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setLayout(new GridLayout(4, 1));

        JLabel nameLabel = new JLabel("Masukkan Nama Peserta:", JLabel.CENTER);
        nameLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        JTextField nameField = new JTextField();
        nameField.setFont(new Font("Arial", Font.PLAIN, 20));
        JButton nextButton = new JButton("Lanjutkan");
        nextButton.setFont(new Font("Arial", Font.PLAIN, 20));
        JButton rankingButton = new JButton("Lihat Ranking");
        rankingButton.setFont(new Font("Arial", Font.PLAIN, 20));

        rankingButton.setEnabled(players.size() >= 2);

        frame.add(nameLabel);
        frame.add(nameField);
        frame.add(nextButton);
        frame.add(rankingButton);

        nextButton.addActionListener(e -> {
            String name = nameField.getText().trim();
            if (!name.isEmpty()) {
                frame.dispose();
                showCountdownPage(name);
            } else {
                JOptionPane.showMessageDialog(frame, "Nama tidak boleh kosong!");
            }
        });

        rankingButton.addActionListener(e -> {
            frame.dispose();
            showRankingPage();
        });

        frame.setVisible(true);
    }

    private static void showCountdownPage(String playerName) {
        JFrame frame = new JFrame("Bersiaplah");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setLayout(new BorderLayout());

        JLabel countdownLabel = new JLabel("", JLabel.CENTER);
        countdownLabel.setFont(new Font("Arial", Font.BOLD, 72));

        frame.add(countdownLabel, BorderLayout.CENTER);
        frame.setVisible(true);

        new Thread(() -> {
            try {
                for (int i = 3; i > 0; i--) {
                    countdownLabel.setText(String.valueOf(i));
                    Thread.sleep(1000);
                }
                frame.dispose();
                showTypingGamePage(playerName);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }).start();
    }

    private static void showTypingGamePage(String playerName) {
        new GamePage(playerName);
    }

    private static void showRankingPage() {
        new RankingPage();
    }
}