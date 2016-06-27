import java.util.*;

public class Solution
{

    public static int N, M;
    public static long K;
    public static double [] cur = null;
    public static double [] next = null;
    public static double []  deg = null;
    public static ArrayList<Edge> E = null;
    public static LinkedList<double[]> Qstate = null;
    public static void main(String [] args)
    {
        Scanner sc = new Scanner(System.in);
        int nocase = sc.nextInt();
        
        for(int ind=0; ind<nocase; ind++)
        {
            N = sc.nextInt(); 
            M = sc.nextInt();
            K = sc.nextLong();
            cur = new double[N];
            next = new double[N];
            deg = new double[N];
            Qstate = new LinkedList<double[]>();
            E = new ArrayList<Edge>();
            for(int m=0; m<M; m++)
            {
                Edge temp = new Edge(sc.nextInt(), sc.nextInt());
                E.add(temp);
                deg[temp.s]++; deg[temp.t]++;
            }

            for(int n=0; n<N; n++)
            {
                cur[n] = sc.nextDouble();
                deg[n] = 1/deg[n];
            }

            
          //  System.out.println(Arrays.toString(deg));
            
            long k = 0;

            MAINLOOP:
            while(k<K)
            {

//                System.out.println("cur->"+Arrays.toString(cur));
                iter();
//                System.out.println("next->"+Arrays.toString(next));
                for(int i=0; i<Qstate.size(); i++)
                {
                    if(is_close(next, Qstate.get(i)))
                    {
                       // System.out.println("found->"+Arrays.toString(next)+":"+Arrays.toString(Qstate.get(i)));
                        if(k!=0)
                        {
                            int rem = (int)((K-k)%(i+1));
                            cur = Qstate.get(i-rem);
                        }
                        else
                            cur = next;
                        break MAINLOOP;
                    }
                }
                if(Qstate.size()>=2)
                    Qstate.removeLast();
                double [] nextS = new double[N];
                System.arraycopy(next, 0, nextS,0, next.length);
                Qstate.addFirst(nextS);    
                k++;
                System.arraycopy(next,0,cur,0, next.length);
                next = new double[N];
                
            }

            long [] soln = new long[N];
            for(int i=0; i<soln.length; i++)
                soln[i] = Math.round(cur[i]);
            Arrays.sort(soln);

            for(int i=soln.length-1; i>=soln.length-5; i--)
                System.out.print(soln[i]+" ");
            System.out.println();

        }
    }

    private static void iter()
    {
        for(Edge e : E)
        {
            next[e.t]+= cur[e.s]*deg[e.s];
            next[e.s]+= cur[e.t]*deg[e.t];
        }
    }
    public static boolean is_close( double[] L1, double[] L2)
    {
        for(int i=0; i<L1.length; i++)
        {
            if(Math.abs(L1[i]-L2[i])>1e-6)
                return false;
        }
        return true;
    }

    static class Edge
    {
        int s, t;
        public Edge(int s, int t)
        {
            this.s = s;
            this.t = t;
        }
    }


}
