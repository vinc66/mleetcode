
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.junit.Test;

/**
 * @author wangaixu
 * @date 2021-01-19 20:51
 */
public class SolutionEasy {

    @Test
    public void test() {
//                System.out.println(reverse(-2147483648));
//        System.out.println(lengthOfLastWord("hi jack "));
//        System.out.println(hasGroupsSizeX(new int[]{1,1,1,2,2,2,2,2,2}));

//        System.out.println(mySqrt(2147395599));

        System.out.println(powerfulIntegers(2, 1, 10));


    }


    //    给定两个正整数 x 和 y，如果某一整数等于 x^i + y^j，其中整数 i >= 0 且 j >= 0，那么我们认为该整数是一个强整数。
//    返回值小于或等于 bound 的所有强整数组成的列表。
//    你可以按任何顺序返回答案。在你的回答中，每个值最多出现一次。
    public List<Object> powerfulIntegers(int x, int y, int bound) {

        ArrayList<Integer> res = new ArrayList<Integer>();
        for (int i = 0; i < Integer.MAX_VALUE; i++) {
            int tmpx = 1;
            for (int j = 0; j < i; j++) {
                tmpx *= x;
            }
            if (tmpx > bound) {
                break;
            }
            for (int j = 0; j < Integer.MAX_VALUE; j++) {
                int tmpy = 1;
                for (int k = 0; k < j; k++) {
                    tmpy *= y;
                }
                if (tmpy > bound) {
                    break;
                }
                int sum = tmpx + tmpy;
                if (sum <= bound) {
                    res.add(sum);
                }
            }
        }
        return res.stream().distinct().sorted().collect(Collectors.toList());
    }


    //    实现 int sqrt(int x) 函数。
//
//    计算并返回 x 的平方根，其中 x 是非负整数。
//
//    由于返回类型是整数，结果只保留整数的部分，小数部分将被舍去。
    public int mySqrt(int num) {
        long x = num;
        if (x == 1) {
            return 1;
        }
        long st = 0;
        long en = x;
        while (true) {
            long tmp = (en + st) / 2;
            if (tmp * tmp > x) {
                en = (en + st) / 2;
            } else if (tmp * tmp < x) {
                st = (en + st) / 2;
            } else {
                return (int) tmp;
            }
            if (en - st == 1) {
                return (int) st;
            }
        }
    }

    //    给定一副牌，每张牌上都写着一个整数。
//    此时，你需要选定一个数字 X，使我们可以将整副牌按下述规则分成 1 组或更多组：
//    每组都有 X 张牌。
//    组内所有的牌上都写着相同的整数。
//    仅当你可选的 X >= 2 时返回 true
    public boolean hasGroupsSizeX(int[] deck) {
        if (deck == null || deck.length == 0) {
            return false;
        }
        int MIN_GROUP = 2;
        Map<Integer, Integer> mps = new HashMap<Integer, Integer>();
        for (int n : deck) {
            if (mps.containsKey(n)) {
                mps.put(n, mps.get(n) + 1);
            } else {
                mps.put(n, 1);
            }
        }
        HashSet<Integer> values = new HashSet<Integer>(mps.values());
        int min = Integer.MAX_VALUE;
        for (Integer value : values) {
            if (value < min) {
                min = value;
            }
            if (min < MIN_GROUP) {
                return false;
            }
        }

        for (int i = MIN_GROUP; i <= min; i++) {
            boolean existGroup = true;
            for (Integer value : values) {
                if (value % i != 0) {
                    existGroup = false;
                    break;
                }
            }
            if (existGroup) {
                return true;
            }
        }
        return false;

    }

    //    给定一个仅包含大小写字母和空格 ' ' 的字符串 s，返回其最后一个单词的长度。如果字符串从左向右滚动显示，那么最后一个单词就是最后出现的单词。如果不存在最后一个单词，请返回 0 。
    public int lengthOfLastWord(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }

        char[] chars = s.toCharArray();
        int wordCount = 0;
        int finalCount = 0;
        for (int i = 0; i < chars.length; i++) {
            char aChar = chars[i];
            if (aChar == ' ') {
                wordCount = 0;
                continue;
            }
            wordCount++;
            if (wordCount > 0) {
                finalCount = wordCount;
            }
        }

        return finalCount;
    }

    //    假设我们的环境只能存储得下 32 位的有符号整数，则其数值范围为 [−231,  231 − 1]。请根据这个假设，如果反转后整数溢出那么就返回 0
    public static int reverse(int x) {
        long xn = x;
        xn = xn < 0 ? -xn : xn;
        char[] chars = String.valueOf(xn).toCharArray();
        for (int i = 0; i < chars.length / 2; i++) {
            char tmp = chars[i];
            chars[i] = chars[chars.length - i - 1];
            chars[chars.length - i - 1] = tmp;
        }
        Long nn = Long.valueOf(String.valueOf(chars));
        nn = x < 0 ? -nn : nn;
        if ((x > 0 && nn > Integer.MAX_VALUE) || (x < 0 && nn < Integer.MIN_VALUE)) {
            return 0;
        }
        return nn.intValue();
    }

}
