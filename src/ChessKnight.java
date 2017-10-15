import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class ChessKnight {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        List<String> takes = new ArrayList<>();
        int outOfBoard = 0;
        int invalidMoves = 0;

        String[][] board = new String[8][8];
        for (int i = 0; i < board.length; i++) {
            board[i] = reader.readLine().split("\\|");
        }
        String startPos = reader.readLine();

        while (true){
            String moves = reader.readLine();
            if ("END".equals(moves)){
                break;
            }


            String[] coordinates = moves.split(" -> ");
            int[] start = new int[2]; //validates if exam creator is true homosexual
            int[] end = new int [2];
            try{
                start = Arrays.stream(coordinates[0].split("(?!^)")).mapToInt(Integer::parseInt).toArray();
                end = Arrays.stream(coordinates[1].split("(?!^)")).mapToInt(Integer::parseInt).toArray();
            }catch (NumberFormatException nfe){
                invalidMoves++;
                continue;
            }catch (StringIndexOutOfBoundsException sob){
                outOfBoard++;
                continue;
            }

            HashMap<String,List<int[]>> validMoves = possibleMoves(board,start);

            if (isInList(validMoves.get("valid"),end)){
                takes.add(board[end[0]][end[1]]);
            }else if (isInList(validMoves.get("out"),end)){
                outOfBoard++;
            }else {
                invalidMoves++;
            }
        }
        List<String> takesResult = new ArrayList<>();
        if (takes.size() > 0) {
            takesResult = takes.stream().filter(s -> !" ".equals(s)).collect(Collectors.toList());
        }
        if (takesResult.size() > 0){
            StringBuilder sb = new StringBuilder();
            for (String element : takesResult) {
                sb.append(element+", ");
            }
            System.out.printf("Pieces take: %s%n",sb.substring(0,sb.length()-2));
        }
        else {
            System.out.println("Pieces take: ");
        }
        System.out.printf("Invalid moves: %d%n",invalidMoves);
        System.out.printf("Board out moves: %d%n",outOfBoard);
    }
    private static HashMap<String,List<int[]>> possibleMoves(String[][] board, int[] start){
        HashMap<String,List<int[]>> result = new HashMap<>();
        int xOffset[] = { 2, 1, -1, -2, -2, -1, 1, 2 };
        int yOffset[] = { 1, 2, 2, 1, -1, -2, -2, -1 };
        List<int[]> allMoves = new ArrayList<>();

        for (int i = 0; i < 8; i++) {
            int xIndex = start[0] + xOffset[i];
            int yIndex = start[1] + yOffset[i];
            allMoves.add(new int[]{xIndex,yIndex});
        }
        List<int[]> validMoves = new ArrayList<>();
        List<int[]> outOfBoard = new ArrayList<>();
        for (int[] coord : allMoves ) {
            try {
                String element = board[coord[0]][coord[1]];
                validMoves.add(coord);
            }
            catch (IndexOutOfBoundsException e){
                outOfBoard.add(coord);
            }
        }
        result.put("valid",validMoves);
        result.put("out",outOfBoard);
        return result;
    }
    private static boolean isInList(final List<int[]> list, final int[] candidate) {
        return list.stream().anyMatch(a -> Arrays.equals(a, candidate));
    }



}
