import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static boolean valid(long a, long b, long c)
    {
        return (a+b>c) && (a+c>b) && (b+c>a);
    }
    public static void main(String[] args) 
    {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        long [] arr =  new long[N];
        for(int i=0; i<N; i++)
            arr[i] = sc.nextLong();
        Arrays.sort(arr);

        long max_len = 0;
        int max_i=-1;
        int max_j=-1;
        int max_k=-1;
        for(int i=0; i<arr.length; i++)
        for(int j=i+1; j<arr.length; j++)
        for(int k=j+1; k<arr.length; k++)
        {
            if(valid(arr[i], arr[j], arr[k]))
            {
                long cur_len = arr[i]+arr[j]+arr[k];
                
                if( (cur_len > max_len) ||
                    (cur_len == max_len && arr[k] > arr[max_k] )||
                    (cur_len == max_len && arr[k] == arr[max_k] && arr[i] > arr[max_i]))
                {
                    max_len = cur_len;
                    max_i=i;
                    max_j=j;
                    max_k=k;
                }
                
            }
        }
        if(max_len==0)
            System.out.println(-1);
        else
            System.out.printf("%d %d %d\n", arr[max_i], arr[max_j], arr[max_k]);

    }
}
