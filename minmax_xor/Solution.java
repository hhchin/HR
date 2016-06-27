import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution 
{
    public static void main(String[] args) 
    {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int [] arr = new int[N];
        for(int n=0; n<N; n++)
            arr[n] = sc.nextInt();

        int fmax = Integer.MAX_VALUE;
        int fmax_ind = -1;

        while(true)
        {
            boolean progress = false;
            for(int i=0; i<N-1; i++)
            {
                int cur_max = arr[i]^arr[i+1];
                if( cur_max < fmax)
                {
                    fmax = cur_max;
                    fmax_ind = i;
                }
            }

            int left = i;
            int right = 
            if(!progress) break;

        }
        System.out.println(cur_max);
    }
}
