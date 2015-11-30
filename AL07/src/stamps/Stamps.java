package stamps;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Stamps {

    public ArrayList<Integer> findstamps(int sum, int[] stamps) {
        int[] m = new int[sum], v = new int[sum];

        Arrays.sort(stamps);

        for(int i = 0; i < sum; i++) {
            m[i] = Integer.MAX_VALUE;

            for(int j = stamps.length - 1; j >= 0; j--) {
                int stamp = stamps[j];

                if(i >= stamp && m[i] > m[i - stamp] + 1) {
                    m[i] = m[i - stamp] + 1;
                    v[i] = stamp;
                }
            }
        }

        ArrayList<Integer> answer = new ArrayList<>();

        int n = 54;

        while(n > 0) {
            answer.add(v[n]);
            n = n - v[n];
        }

        return answer;
    }

}