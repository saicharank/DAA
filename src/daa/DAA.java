
package daa;
import java.util.*;

class Graph{
    int v;
   ArrayList<Vertex> arr;
   int[] p;
   int[] d;
   int[] pa;
    Graph(int a){
        this.v=a;
        this.arr=new ArrayList<Vertex>(v);
        this.p=new int[a];
        this.d=new int[a];
        this.pa=new int[a];
    }
     void createGraph(){
         for(int i=0;i<=v;i++){
             this.arr.add(new Vertex());
         }
     }
    public void addEdge(int src,int de,int we){
       adjacenyNode e=new adjacenyNode(de,we);
       adjacenyNode e1=new adjacenyNode(src,we);
       this.arr.get(src).list.add(e);
       //this.arr.get(de).list.add(e1);
      
        //arr.get(de).list.add(new adjacenyNode(src,we));
    }
    public void Display(){
        for(Vertex e:arr){
            for(adjacenyNode n:e.list){
               System.out.print(n.dest+" ");}
            System.out.println();
        }
    }
    public void Bfs(int s){
        boolean[] v=new boolean[this.v];
        for(int i=0;i<this.v;i++)
            v[i]=false;
        v[s]=true;
        LinkedList<Integer> q=new LinkedList<Integer>();
        q.add(s);
        while(q.size()!=0){
            int u=q.poll();
            System.out.print(u+" ");
            for(adjacenyNode n:this.arr.get(u).list){
                if( v[n.dest]== false){
                    q.add(n.dest);
                    v[n.dest]=true;
                }
            }
            
        }
        
    }
    public int mindis(){
        int min=1000,min_index=0;
        for(int i=0;i<this.v;i++){
            if(p[i]==0 && d[i]<=min){
                min=d[i];
                min_index=i;
            }
        }
        return min_index;
    }
    public void print(){
        //int i;
        for(int i=0;i<this.v;i++){
            System.out.println(i+"  "+d[i]);
        }
    }
    
    public void djikstras(int s){
        int j,k,u;
        
        for(int i=0;i<this.v;i++){
            p[i]=0;
            d[i]=Integer.MAX_VALUE;
            
        }
        d[s]=0;
        for(int i=0;i<this.v;i++){
            u=mindis();
            p[u]=1;
            for(adjacenyNode n:this.arr.get(u).list){
                if(p[n.dest]==0 && d[u]+n.weight < d[n.dest]){
                    d[n.dest]=d[u]+n.weight;
                }
            }
            
        }
        print();
    }
    
    
    public void Degree(){
        int n,j=0,k;
        for(int i=0;i<this.v;i++){
            k=i+1;
            System.out.println(i+"' degree    "+this.arr.get(i).list.size());
            j=j+this.arr.get(i).list.size();
            
        }
        System.out.println("The Total degree of all the vertices is"+j);
    }
    
    public void Type(){
        int j,k=1;
        for(int i=0;i<this.v;i++){
            for(adjacenyNode n:this.arr.get(i).list){
                k=Check(n.dest,i);
                if(k==0){
                    break;
                }
                
            }
            if(k==0){
                    break;
                }
        }
        if(k==0){
        System.out.println("The given graph is directed");
                }
        else{
            System.out.println("the given graph is undirected");
        }
    }
    public int Check(int i,int j){
        for(adjacenyNode n:this.arr.get(i).list){
            if(n.dest==j){
                return 1;
            }
    }
        return 0;
    }
    
    
    
    
    
    
    
    
    
    public boolean DfsUtil(boolean[] vi,int s,boolean[] rec){
       if(vi[s]==false){
        vi[s]=true;
        rec[s]=true;
        //System.out.print(s+" ");
       
        for(adjacenyNode n:this.arr.get(s).list){
            if(!vi[n.dest] && DfsUtil(vi,n.dest,rec))
                return true;   
            else if(rec[n.dest]){
                return true;
            }
          
        }
       }
        rec[s]=false;
       return false;
       }
      
    
    public boolean Dfs(){
        boolean[] vi=new boolean[this.v];
        boolean[] rec=new boolean[this.v];
        for(int i=0;i<this.v;i++){
            vi[i]=false;
            rec[i]=false;
        }
        
        for(int i=0;i<this.v;i++)
            if(DfsUtil(vi,i,rec))
                return true;
      
        return false;
    }
}
class Vertex{
    String color;
    ArrayList<adjacenyNode> list=new ArrayList();
}
class adjacenyNode{
    int dest;
    int weight;
    adjacenyNode(int d,int w){
        this.dest=d;
        this.weight=w;
    }
}
public class DAA {

    
    public static void main(String[] args) {
       int v;
       System.out.println("Enter the no. of vertices");
              
       Scanner sc= new Scanner(System.in);
       v=sc.nextInt();
       Graph g=new Graph(v);
       g.createGraph();
       System.out.println("Enter the no. of edges");
       int e=sc.nextInt();
       System.out.println("Enter the source destination and weight of the graph");
       for(int i=0;i<e;i++){
           int s=sc.nextInt();
           int d=sc.nextInt();
           int w=sc.nextInt();
           g.addEdge(s,d,w);
       
       }
       //g.djikstras(0);
       //g.Display();
       //g.Degree();
       if(g.Dfs()){
           System.out.println("There exists a Cycle");
           
       }
       else{
           System.out.println("There does not exist a Cycle");
       }
      /* if(e>=v){
           System.out.println("The Graph has a Cycle");
           
       }
       else{
           System.out.println("The graph has no cycle");
       }*/
    }
    
}
