
public class DevTaskCmdLineReader {

    private BufferedReader teader;

    public void DevTaskCmdLineReader() {
        this.reader = new BufferedRead(new InputStreamReader(System.in));
    }

    public List<Integer> readIntsUntilExit() {
        List<Integer> readIntegers = new ArrayList<Integer>();
        String welcomeString = "Please input integer numbers to get data for devTask.\n" //
                + "After every entered number press enter to enter another number.\n"
                + "Typing in \"exit\" and then pressing the enter button will quit\n"
                + "the command line reading part and proceed to solving the executed devTask.\n";

        String readLine = reader.readLine();

        while(!readline.toUpperCase.equals("EXIT")) {
            try {
                readIntegers.add(Integers.parstInt(readLine));
            }
            catch(NumberFormatException e) {
                System.out.println(String.format("Could not parse %s into an integer, please enter an integer this time!\n", readLine);
            }
            finally() {
                readLine = reader.readLine();
            }
        }

        return readIntegers;
    }
}