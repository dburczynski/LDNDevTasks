package devtasks;

import devtaskutils.IntegerListCmdLineReader;
import devtaskutils.Tuple;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Second dev task
 */
public class DevTask2 {

    /**
     * Gets all numbers in a list that are smaller than 7
     * @param integerList
     * @return
     */
    public List<Integer> filterByLessThanSeven(List<Integer> integerList) {
        if(integerList == null)
            return null;
        else {
            return integerList.stream().filter(i -> i < 7).collect(Collectors.toList());
        }
    }

    /**
     * Gets all numbers in a list that are greater equal 7
     * @param integerList
     * @return
     */
    public List<Integer> filterByGreaterEqualSeven(List<Integer> integerList) {
        if(integerList == null)
            return null;
        else {
            return integerList.stream().filter(i -> i >= 7).collect(Collectors.toList());
        }
    }

    /**
     * Returns a list of tuples that elements X + Y = 13
     * @param lessThanSevenList
     * @param greaterEqualSevenList
     * @return
     */
    public List<Tuple<Integer, Integer>> getPairsThatEqualThirteen(List<Integer> lessThanSevenList, List<Integer> greaterEqualSevenList) {
        List<Tuple<Integer, Integer>> pairsThatEqualThirteen = new ArrayList<>();

        Collections.sort(lessThanSevenList);

        for(Integer lessThatSeven : lessThanSevenList) {
            //Greats all possible sums that = 13 and makes a list of tuples
            pairsThatEqualThirteen.addAll(greaterEqualSevenList.stream()
                    .filter(greaterEqualSeven ->  (lessThatSeven + greaterEqualSeven) == 13)
                    .map(greaterEqualSeven -> new Tuple<Integer, Integer>(lessThatSeven, greaterEqualSeven))
                    .collect(Collectors.toList()));
        }

        return pairsThatEqualThirteen;
    }

    public static void main(String[] args) {
        System.out.println("Executing DevTask2...");
        IntegerListCmdLineReader reader = new IntegerListCmdLineReader();
        DevTask2 task2 = new DevTask2();
        try {
            List<Integer> integerList = reader.readIntegers();
            List<Integer> lessThanSeven = task2.filterByLessThanSeven(integerList);
            List<Integer> greaterEqualSeven = task2.filterByGreaterEqualSeven(integerList);

            task2.getPairsThatEqualThirteen(lessThanSeven, greaterEqualSeven).forEach(pair -> System.out.println(pair));
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
