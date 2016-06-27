import java.util.*;

public class Solution 
{

    static int min_ts, inc;
    static boolean isvalid(int val)
    {
        return val == (inc*((val-min_ts)/inc));
    }

    public static void main(String [] args)
    {
        ArrayList<Port> arr = new ArrayList<Port>();

        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        min_ts = sc.nextInt();
        inc = sc.nextInt();
        int pool = sc.nextInt();
        sc.nextLine();
        int tot = 0;
        for(int ind = 0; ind < N; ind++)
        {
            String [] T = sc.nextLine().split(" ");
            int cur = Integer.parseInt(T[1]);
            tot+=cur;
            arr.add(new Port(T[0], cur));
        }

        Collections.sort(arr);
        TreeMap<String, Integer> alloc = new TreeMap<String, Integer>();
        if(tot<= pool)
        {
            for(Port P : arr)
                alloc.put(P.id, P.trade);
            
        }   
        else
        {
            for(Port P : arr)
            {
                if(pool ==0)
                {
                    alloc.put(P.id, 0);
                    continue;
                }

                int cur_req = (int)(((double)P.trade/(double)tot)*pool);
//                System.out.println(P.trade+"->"+cur_req);
                int cur_alloc = 0;
                if(cur_req < min_ts)
                {
                    if(cur_req >= min_ts/2 && min_ts <= pool)
                        cur_alloc = min_ts;
                }
                else
                {
                    if(cur_req > P.trade)
                    {
                        int cur_valid_pool = min_ts + (((pool-min_ts)/inc)*inc);
                        cur_alloc = Math.min(P.trade, cur_valid_pool);
                    }
                    else
                    {
                        int max_m = (cur_req-2*min_ts)/inc;
                        int valid_m = (cur_req-min_ts)/inc;
                        
                        int cur_valid = min_ts + valid_m*inc;
                        int cur_valid_pool = min_ts + (((pool-min_ts)/inc)*inc);
                        cur_alloc = Math.min(cur_valid, cur_valid_pool);
                        
                        int cur_mult = (cur_alloc-min_ts)/inc;
                        if(P.trade - cur_alloc > 0)
                        {
                            boolean fixed = false;
                            for(int i=Math.min(max_m, cur_mult); i>=0; i--)
                            {
                                int new_alloc = min_ts+i*inc;
                                int rem = P.trade- new_alloc;
                                if(isvalid(new_alloc) && isvalid(rem))
                                {
                                    cur_alloc = new_alloc;
                                    fixed = true;
                                    break;
                                }
                            }
                            if(!fixed)
                            cur_alloc = 0;

                        }
  //                      System.out.println(P.trade+"->"+cur_alloc);
                    }
                }
                
                int leftover = P.trade - cur_alloc;
                if( leftover !=0 && leftover != (min_ts + ((leftover-min_ts)/inc)*inc))
                    cur_alloc = 0;
                //System.out.println("cur "+P.id+":"+P.trade+":"+pool);
                pool -= cur_alloc;
                tot -= P.trade;
                alloc.put(P.id, cur_alloc);
            }
        }
        for(String S : alloc.keySet())
            System.out.println(S+" "+alloc.get(S));    



    }

    static class Port implements Comparable<Port>
    {
        String id;
        int trade;
        public Port(String id, int trade)
        {
            this.id = id;
            this.trade = trade;
        }

        public int compareTo(Port P)
        {
            if( trade==P.trade)
                return id.compareTo(P.id);
            else 
                return trade-P.trade; 
        }
    }
}

