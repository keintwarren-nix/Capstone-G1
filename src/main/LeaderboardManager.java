package main;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class LeaderboardManager {
    private static final String LEADERBOARD_FILE = "leaderboard.txt";
    private static final int MAX_ENTRIES = 10; // Maximum number of entries to keep
    private List<LeaderboardEntry> leaderboard;

    public LeaderboardManager() {
        leaderboard = new ArrayList<>();
        loadLeaderboard();
    }

    public void addEntry(String playerName, int score) {
        System.out.println("Adding to leaderboard: Name=" + playerName + ", Score=" + score);
        leaderboard.add(new LeaderboardEntry(playerName, score));
        sortLeaderboard();
        if (leaderboard.size() > MAX_ENTRIES) {
            leaderboard = leaderboard.subList(0, MAX_ENTRIES);
        }
        saveLeaderboard();
    }


    public List<LeaderboardEntry> getLeaderboard() {
        return leaderboard;
    }

    private void loadLeaderboard() {
        try (Scanner scanner = new Scanner(new File(LEADERBOARD_FILE))) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split(",");
                if (parts.length == 2) {
                    String name = parts[0].trim();
                    try {
                        int score = Integer.parseInt(parts[1].trim());
                        leaderboard.add(new LeaderboardEntry(name, score));
                    } catch (NumberFormatException e) {
                        System.err.println("Error parsing score in leaderboard file: " + line);
                    }
                } else {
                    System.err.println("Invalid format in leaderboard file: " + line);
                }
            }
        } catch (FileNotFoundException e) {
            // It's okay if the file doesn't exist yet
            System.out.println("Leaderboard file not found. Creating a new one.");
        }
        sortLeaderboard();
    }

    private void saveLeaderboard() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(LEADERBOARD_FILE))) {
            for (LeaderboardEntry entry : leaderboard) {
                writer.println(entry.getName() + "," + entry.getScore());
            }
        } catch (IOException e) {
            System.err.println("Error saving leaderboard to file: " + e.getMessage());
        }
    }

    private void sortLeaderboard() {
        Collections.sort(leaderboard, (entry1, entry2) -> Integer.compare(entry2.getScore(), entry1.getScore()));
    }

    // Inner class to represent a leaderboard entry
    public static class LeaderboardEntry {
        private String name;
        private int score;

        public LeaderboardEntry(String name, int score) {
            this.name = name;
            this.score = score;
        }

        public String getName() {
            return name;
        }

        public int getScore() {
            return score;
        }
    }
}