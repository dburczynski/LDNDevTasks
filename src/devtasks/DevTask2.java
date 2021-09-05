package devtasks;

import devtaskutils.IntegerListCmdLineReader;
import devtaskutils.DevTaskUtils;
import devtaskutils.Tuple;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class DevTask2 {

    public List<Integer> filterByLessThanSeven(List<Integer> integerList) {
        if(integerList == null)
            return null;
        else {
            return integerList.stream().filter(i -> i < 7).collect(Collectors.toList());
        }
    }

    public List<Integer> filterByGreaterEqualSeven(List<Integer> integerList) {
        if(integerList == null)
            return null;
        else {
            return integerList.stream().filter(i -> i >= 7).collect(Collectors.toList());
        }
    }

    public List<Tuple<Integer, Integer>> getPairsThatEqualThirteen(List<Integer> lessThanSevenList, List<Integer> greaterEqualSevenList) {
        List<Tuple<Integer, Integer>> pairsThatEqualThirteen = new ArrayList<>();

        Collections.sort(lessThanSevenList);

        for(Integer lessThatSeven : lessThanSevenList) {
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
