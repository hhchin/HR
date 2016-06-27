
import java.util.*;
public class Solution
{
    public static void main (String [] args)
    {
        Scanner sc = new Scanner(System.in);
        long x1 = sc.nextLong();
        long v1 = sc.nextLong();
        long x2 = sc.nextLong();
        long v2 = sc.nextLong();

        boolean solve = false;
        long num = x1-x2;
        long dem = v2-v1;
        if(sign(num)==(dem))
        {
            if(dem==0)
            {
                if(num==0) solve = true;
            }
            else
            {
                num = Math.abs(num);
                dem = Math.abs(dem);
                if(num >= dem && num%dem==0)
                    solve = true;
            }
        }
        if(solve)
            System.out.printf("YES\n");
        else
            System.out.printf("NO\n");
        
    }

    static long sign(long a)
    {
        if(a==0) return 0;
        else return a>0?1:-1;
    }
    static long GCD(long a, long b)
    {
        return a==0 ? b : GCD(b%a,a);
    }
}
