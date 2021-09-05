package devtaskutils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GraphCmdLineReader {

    public Map<Integer, Integer> readGraphConnections() throws IOException {
        Map<Integer, Integer> graphConnections = new HashMap<>();
        Integer amountOfConnections = null;
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("Please input amount of graph connections as an integer then press enter.");
        String readLine = reader.readLine();

        while(amountOfConnections == null) {
            try {
                amountOfConnections = (Integer.parseInt(readLine));
            }
            catch(NumberFormatException e) {
                System.out.println(String.format("Could not parse %s into an integer, please enter an integer this time", readLine));
            }
        }

        System.out.println(String.format("Please input %s graph connections in the format of two integers separated by a space "
                + "then press enter to input another connection", amountOfConnections));

        while(amountOfConnections > 0) {
            try {
                String[] readLineArray = readLine.split(" ");
                if(readLineArray.length != 3);
                    //TODO throw

                Integer vertex1 = Integer.parseInt(readLineArray[0]);
                Integer vertex2 = Integer.parseInt(readLineArray[2]);

                graphConnections.put(vertex1, vertex2);
                amountOfConnections--;
            }
            catch(NumberFormatException e) {
                System.out.println(String.format("Could not parse %s into a graph connections, please input two integers separated "
                        + "by a space this time.", readLine));
            }
        }

        reader.close();
        return graphConnections;
    }
}
