package game;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Leaderboards {
    private final List<ScoreEntry> highScores;
    private final File file;

    public Leaderboards(){
        highScores = new ArrayList<>();
        file = new File("data/leaderboards.txt");
        load();
    }

    public void save(){
        try(BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(this.file))){
            for (ScoreEntry entry : highScores){
                bufferedWriter.write(entry.name + " " + entry.score + "\n");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void insert(ScoreEntry scoreEntry){
        highScores.add(scoreEntry);
        highScores.sort(Collections.reverseOrder(Comparator.comparingInt(ScoreEntry::getScore)));
    }

    public void load(){
        String line;
        try(BufferedReader bufferedReader = new BufferedReader(new FileReader(this.file))) {
            while ((line = bufferedReader.readLine()) != null){
                String[] parts = line.split(" ");

                if (parts.length == 2) {
                    highScores.add(new ScoreEntry(parts[0], Integer.parseInt(parts[1])));
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public List<ScoreEntry> getList(){
        return highScores;
    }


    public static class ScoreEntry{
        private String name;
        private int score;

        public ScoreEntry(String name, int score){
            this.name = name;
            this.score = score;
        }

        public int getScore(){
            return score;
        }

        public String getName(){
            return name;
        }

    }
}
