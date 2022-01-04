import java.util.*;

class Solution {

    HashMap<String, Integer> toIndex = new HashMap<>();
    int[] parentOf;
    int[] result;

    public int[] solution(String[] enroll, String[] referral, String[] seller, int[] amount) {
        int n = enroll.length;
        parentOf = new int[n];
        result = new int[n];

        toIndex.put("-", -1);
        for (int i = 0; i < n; i++) {
            toIndex.put(enroll[i], i);
            parentOf[i] = toIndex.get(referral[i]);
        }
        for (int i = 0; i < amount.length; i++) {
            int sellerIndex = toIndex.get(seller[i]);
            dfs(sellerIndex, amount[i] * 100);
        }

        return result;
    }

    void dfs(int from, int money) {
        if (from == -1) return;
        if (money < 10) {
            result[from] += money;
            return;
        }

        result[from] += (money - money / 10);
        dfs(parentOf[from], money / 10);
    }
}

public class App {
    public static void main(String[] args) throws Exception {
        Solution s = new Solution();

        String[] enroll = {"john", "mary", "edward", "sam", "emily", "jaimie", "tod", "young"};
        String[] referral = {"-", "-", "mary", "edward", "mary", "mary", "jaimie", "edward"};
        String[] seller = {"young", "tod", "john", "tod", "emily", "mary"};
        int[] amount = {12,6,4,2,5,10};

        System.out.println(Arrays.toString(s.solution(enroll, referral, seller, amount)));
    }
}
