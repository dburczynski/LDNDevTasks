package devtasks;

import devtaskutils.IntegerListCmdLineReader;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

/**
 * First dev task
 */
public class DevTask1 {

    /**
     * Gets the amount of integers in a list
     * @param integerList
     * @return
     */
    public long getCount(List<Integer> integerList) {
        return integerList == null ? 0L : integerList.stream().count();
    }

    /**
     * Gets the amount of distinct integers in a list
     * @param integerList
     * @return
     */
    public List<Integer> getDistinct(List<Integer> integerList) {
        return integerList == null ? null : integerList.stream().distinct().collect(Collectors.toList());
    }

    /**
     * Gets smallest integer in a list
     * @param integerList
     * @return
     */
    public Integer getMin(List<Integer> integerList) {
        try {
            return integerList == null ? null : integerList.stream().mapToInt(i -> i).min().orElseThrow(NoSuchElementException:: new);
        }
        catch (NoSuchElementException ex) {
            return null;
        }
    }

    /**
     * Gets largest integer in a list
     * @param integerList
     * @return
     */
    public Integer getMax(List<Integer> integerList) {
        try {
            return integerList == null ? null : integerList.stream().mapToInt(i -> i).max().orElseThrow(NoSuchElementException:: new);
        }
        catch (NoSuchElementException ex) {
            return null;
        }
    }

    /**
     * Ascending sort of a list of integers
     * @param integerList
     * @return
     */
    public List<Integer> sortAsc(List<Integer> integerList) {
        return integerList == null ? null : integerList.stream().sorted().collect(Collectors.toList());
    }

    /**
     * Prints an list of integers
     * @param integerList
     * @return
     */
    public String listToPrettyString(List<Integer> integerList) {
        String readableString = "";
        for (Integer integer : integerList) {
            readableString += integer.toString() + " ";
        }
        return readableString;
    }
    public static void main(String[] args) {
        System.out.println("Executing DevTask1...");
        IntegerListCmdLineReader reader = new IntegerListCmdLineReader();
        DevTask1 task1 = new DevTask1();
        try {
            List<Integer> integerList = reader.readIntegers();
            List<Integer> distinctIntegerList = task1.getDistinct(integerList);

            System.out.println(task1.listToPrettyString(distinctIntegerList));
            System.out.println(String.format("count: %s", task1.getCount(integerList)));
            System.out.println(String.format("distinct: %s", task1.getCount(distinctIntegerList)));
            System.out.println(String.format("min: %s", task1.getMin(integerList)));
            System.out.println(String.format("max: %s", task1.getMax(integerList)));
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}