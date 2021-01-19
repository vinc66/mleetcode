

/**
 * @author wangaixu
 * @date 2021-01-19 20:51
 */
public class SolutionEasy {

    public static void main(String[] args) {

        System.out.println(reverse(-2147483648));

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
