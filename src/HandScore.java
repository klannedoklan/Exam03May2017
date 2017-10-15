import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class HandScore {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String[] input = reader.readLine().split("\\s+");
        Deque<String> cards = new ArrayDeque<>();
        cards.addAll(Arrays.asList(input));
        int sum = 0;
        while (!cards.isEmpty()){
            String element = cards.pop();
            int seqSum = getCardPower(element);
            int count = 1;
            while (cards.peek()!= null && cards.peek().substring(cards.peek().length()-1).equals(element.substring(element.length()-1))){
                count++;
                seqSum += getCardPower(cards.pop());
            }
            sum += seqSum * count;
        }
        System.out.println(sum);
    }
    private static int getCardPower(String card){
        List<String> powers = Arrays.asList("","","2","3","4","5","6","7","8","9","10","","J","Q","K","A");
        return  powers.indexOf(card.substring(0,card.length()-1));
    }
}
