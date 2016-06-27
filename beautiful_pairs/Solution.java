import java.util.*;

public class Solution
{
    public static void main(String [] args)
    {
        Scanner sc = new Scanner(System.in);
        int LEN = sc.nextInt();
        int [] A = new int[LEN];
        int [] B = new int[LEN];
        for(int i=0; i<A.length; i++)
            A[i] = sc.nextInt();
        for(int i=0; i<B.length; i++)
            B[i] = sc.nextInt();

        HashMap<Integer, Integer> Amap = tally(A);
        HashMap<Integer, Integer> Bmap = tally(B);
        
        int soln = 0;
        Set<Integer> key = Amap.keySet();
        //add the smallest of each partition
        for(Integer K: key)
            if(Bmap.containsKey(K))
                soln += Math.min(Amap.get(K), Bmap.get(K));
        
        soln = soln<LEN ? soln+1 : soln-1;

        System.out.println(soln);

    }

    static HashMap<Integer, Integer> tally(int [] A)
    {
        HashMap<Integer, Integer> Amap = new HashMap<Integer, Integer>();
        for(int i=0; i<A.length; i++)
            if(Amap.containsKey(A[i]))
                Amap.put(A[i], Amap.get(A[i])+1);
            else
                Amap.put(A[i], 1);
        return Amap;
    }
}
