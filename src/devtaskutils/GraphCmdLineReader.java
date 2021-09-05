package devtaskutils;

import sun.security.pkcs.ParsingException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GraphCmdLineReader {

    public List<Tuple<Integer, Integer>> readGraphConnections() throws IOException {
        List<Tuple<Integer, Integer>> graphConnections = new ArrayList<>();
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
                readLine = reader.readLine();
            }
        }

        System.out.println(String.format("Please input %s graph connections in the format of two integers separated by a space "
                + "then press enter to input another connection", amountOfConnections));

        while(amountOfConnections > 0) {
            try {
                readLine = reader.readLine();
                String[] readLineArray = readLine.split("");
                if(readLineArray.length != 3)
                    throw new ParsingException();

                Integer vertex1 = Integer.parseInt(readLineArray[0]);
                Integer vertex2 = Integer.parseInt(readLineArray[2]);

                graphConnections.add(new Tuple(vertex1, vertex2));
                amountOfConnections--;
            }
            catch(NumberFormatException | ParsingException e){
                System.out.println(String.format("Could not parse %s into a graph connections, please input two integers separated "
                        + "by a space this time.", readLine));
            }
        }

        reader.close();
        return graphConnections;
    }
}
