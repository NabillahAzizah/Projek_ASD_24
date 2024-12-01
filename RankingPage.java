import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class RankingPage {
    public RankingPage() {
        JFrame frame = new JFrame("Ranking Peserta");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 300);
        frame.setLayout(new BorderLayout());

        JPanel rankingPanel = new JPanel(new GridLayout(1, 2));

        JTextArea typoRankingArea = new JTextArea();
        JTextArea timeRankingArea = new JTextArea();
        typoRankingArea.setEditable(false);
        timeRankingArea.setEditable(false);

        Font bigFont = new Font("Arial", Font.PLAIN, 20);
        typoRankingArea.setFont(bigFont);
        timeRankingArea.setFont(bigFont);

        ArrayList<Player> sortedByTypo = new ArrayList<>(TypingGameAppSort.players);
        Sorters.insertionSortByTypo(sortedByTypo);
        ArrayList<Player> sortedByTime = new ArrayList<>(TypingGameAppSort.players);
        Sorters.mergeSortByTime(sortedByTime);

        StringBuilder typoRankingText = new StringBuilder("Ranking berdasarkan Typo:\n");
        StringBuilder timeRankingText = new StringBuilder("Ranking berdasarkan Waktu:\n");

        for (Player p : sortedByTypo) {
            typoRankingText.append(p.name).append(" - Typo: ").append(p.typoCount).append("\n");
        }

        for (Player p : sortedByTime) {
            timeRankingText.append(p.name).append(" - Waktu: ").append(p.time).append(" detik\n");
        }

        typoRankingArea.setText(typoRankingText.toString());
        timeRankingArea.setText(timeRankingText.toString());

        rankingPanel.add(new JScrollPane(typoRankingArea));
        rankingPanel.add(new JScrollPane(timeRankingArea));

        Player bestOverallPlayer = TypingGameAppSort.players.stream()
                .min((p1, p2) -> Double.compare(p1.time, p2.time))
                .orElse(null);

        JTextArea winnerMessage = new JTextArea("Selamat " + bestOverallPlayer.name + " dan kembangkan terus ya!");
        winnerMessage.setFont(new Font("Arial", Font.BOLD, 30));
        winnerMessage.setEditable(false);
        winnerMessage.setWrapStyleWord(true);
        winnerMessage.setLineWrap(true);
        winnerMessage.setOpaque(false);  
        winnerMessage.setPreferredSize(new Dimension(800, 50));  

        JPanel winnerPanel = new JPanel();
        winnerPanel.setLayout(new BoxLayout(winnerPanel, BoxLayout.X_AXIS));
        winnerPanel.add(Box.createHorizontalGlue()); 
        winnerPanel.add(winnerMessage);
        winnerPanel.add(Box.createHorizontalGlue());  

        frame.add(rankingPanel, BorderLayout.NORTH);
        frame.add(winnerPanel, BorderLayout.CENTER);  

        JButton backButton = new JButton("Kembali ke Input Nama");
        backButton.setFont(new Font("Arial", Font.PLAIN, 20));
        backButton.setPreferredSize(new Dimension(800, 50));
        backButton.addActionListener(e -> {
            frame.dispose();
            TypingGameAppSort.showInputNamePage();
        });

        frame.add(backButton, BorderLayout.SOUTH);  

        frame.setVisible(true);
    }
}
