package interview;

public class Solution {

    public static void main(String[] args) {

        System.out.println(getLongest("ababbacaabbbb", 1));
        System.out.println(getLongest("wedding", 0));
        System.out.println(getLongest("apple", 25));
        System.out.println(getLongest("ababbaca", 1));

    }

    public static String getLongest(String str, int k){

        int l = 0, r=0;
        char prev, curr;
        prev = str.charAt(l);
        int max = 0;
        String longest = "";
        while(r<str.length()){
            curr = str.charAt(r);
            if(Math.abs(curr-prev) <= k){
                r++;
                if(r-l >max){
                    max = r-l;
                    longest = str.substring(l, r);
                }
            }else{
                l = r;
            }
            if(str.length()-l <= max){
                break;
            }


            prev = curr;
        }
        return longest;
    }
}
