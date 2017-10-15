import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

public class FootballStats {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        List<String[]> mathcScores = new ArrayList<>();

        while (true){
            String input = reader.readLine();
            if ("Season End".equals(input)){
                break;
            }
            String[] tokens = input.split("result");
            String[] teams = Arrays.stream(tokens[0].split("-"))
                    .map(String::trim)
                    .toArray(String[]::new);
            String[] score = Arrays.stream(tokens[1].split(":"))
                    .map(String::trim)
                    .toArray(String[]::new);

            String straightMatch = teams[0] + " - " + teams[1];
            String straightScore = score[0] + ":" + score[1];

            String reversedMatch = teams[1] + " - " + teams[0];
            String reversedScore = score[1] + ":" + score[0];
            mathcScores.add(new String[]{straightMatch,straightScore});
            mathcScores.add(new String[]{reversedMatch,reversedScore});
        }
        String[] query = reader.readLine().split(", ");
        for (int i = 0; i < query.length; i++) {
            String host = query[i];
            List<String[]> filtered = mathcScores.stream()
                    .filter(strings -> strings[0].startsWith(host))
                    .collect(Collectors.toList());

            Collections.sort(filtered, new Comparator<String[]>() {
                @Override
                public int compare(String[] o1, String[] o2) {
                    return o1[0].compareTo(o2[0]);
                }
            });

            for (String[] row : filtered) {
                System.out.printf("%s -> %s%n",row[0],row[1]);
            }
        }
    }
}
