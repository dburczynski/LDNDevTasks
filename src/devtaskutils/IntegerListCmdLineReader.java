package devtaskutils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Integer list reader
 */
public class IntegerListCmdLineReader {

    /**
     * Reads one integer after another and returns a list
     * @return
     * @throws IOException
     */
    public List<Integer> readIntegers() throws IOException {
        List<Integer> readIntegers = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("Please input integer numbers to get data for the executed DevTask.\n"
                + "After every entered number press enter to enter another number.\n"
                + "Typing in \"exit\" and then pressing the enter button will quit\n"
                + "the command line reading part and proceed to solving the executed devTask.");
        String readLine = reader.readLine();

        while (!readLine.equalsIgnoreCase("EXIT")) {
            try {
                readIntegers.add(Integer.parseInt(readLine));
            } catch (NumberFormatException e) {
                System.out.println(String.format("Could not parse %s into an integer, please enter an integer this time", readLine));
            } finally {
                readLine = reader.readLine();
            }
        }

        reader.close();
        return readIntegers;
    }
}