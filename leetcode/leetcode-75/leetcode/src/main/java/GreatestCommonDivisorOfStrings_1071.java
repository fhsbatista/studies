import java.util.List;

public class GreatestCommonDivisorOfStrings_1071 {
    public static void main(String[] args) {
        System.out.println(gcdOfStrings("ABCABC", "ABC")); //OUTPUT: "ABC"
    }

    public static String gcdOfStrings(String str1, String str2) {
        int len1 = str1.length();
        int len2 = str2.length();

        if (!(str1 + str2).equals(str2 + str1)) {
            return "";
        }
        String output = str1.substring(0, gcd(len1, len2));
        return output;
    }

    public static int gcd(int n1, int n2) {
        if (n2 == 0) return n1;

        return gcd(n2, n1 % n2);
    }
}
