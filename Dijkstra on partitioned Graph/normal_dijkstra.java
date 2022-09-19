
import java.io.*;  
import java.io.File;  // Import the File class
import java.io.IOException;  // Import the IOException class to handle errors
import java.io.FileWriter;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
public class normal_dijkstra
{
 static int dest=0;
 static ArrayList<String> clist=new ArrayList<String>();
 static ArrayList<String> olist=new ArrayList<String>();
 //static HashMap<Integer, String> hmap = new HashMap<Integer, String>();
 //static Set<String> hash_Set = new HashSet<String>();
 public static void main(String args[])  
 {  
    
     
       Scanner sc=new Scanner(System.in);
        System.out.println("Enter the source node?");
        int source=sc.nextInt();
        System.out.println("Enter the destination node?");
        dest=sc.nextInt();
        if(source==dest)
        System.out.println("Source and destination are same.");
        String x=Integer.toString(source)+" 0.00 "+Integer.toString(source);
         clist.add(x);
         
         traverse(source,dest);
         System.out.println(clist);
         String s=printpath(source);
         if(olist.size()!=0)
        {
        String[] destin=clist.get(clist.size()-1).split(" ", 3);;
        Double totalcost=Double.parseDouble(destin[1]);
        
        System.out.println("Total cost of reaching from "+source+" to "+dest+" is "+totalcost );
       
        System.out.println("Path: "+s+dest);
       }
      
  }
  
 
  
  
   public static void traverse(int source,int destination)
  {
      try{
          Double costa=0.0;
         int node=source;
       // clist.add(Integer.toString(node)+" "+Double.toString(cost)+" "+Integer.toString(node));
       while(node!=dest)
      {
          File f=new File("sample_edges.txt");
         String line="";
       
         // System.out.println(s);
         FileReader frt=new FileReader(f); //reads the file which contains the records
        
        BufferedReader brt=new BufferedReader(frt);      
          
          while((line=brt.readLine())!=null)  //This loop gives the records to hashing functions.
        {
           //System.out.println(line);
            String[] sp= line.split(" ", 3);
         int x=Integer.parseInt(sp[0]);
         if(x==node)
         { 
          int next=Integer.parseInt(sp[1]);
          //System.out.println(hmap.get(next));
          Double cost=Double.parseDouble(sp[2]);
           // Double costa=0.0;
          for(int c=0;c<olist.size();c++)
          {
              String[] spa= (olist.get(c)).split(" ", 3);
              int asd=Integer.parseInt(spa[0]);
              //Double costa=Double.parseDouble(sp[1]);
              if(asd==node)
              {
                 // costa=Double.parseDouble(spa[1]);
                  break;
              }
            }
            int asd=0;
          String[] spa= line.split(" ", 3);
          //Double costa=Double.parseDouble(sp[1]);
          cost=cost+costa;
          for(int i=0;i<clist.size();i++)
          {
             String[] spx= (clist.get(i)).split(" ", 2);
             asd =Integer.parseInt(spx[0]);
              //Double costa=Double.parseDouble(sp[1]);
              if(asd==next)
              {
                 // costa=Double.parseDouble(spa[1]);
                  break;
              } 
            }
            int asdx=0;
            Double costx=0.0;
            for(int i=0;i<olist.size();i++)
          {
             String[] spxt= (olist.get(i)).split(" ", 3);
             asdx =Integer.parseInt(spxt[0]);
             costx=Double.parseDouble(spxt[1]);
              //Double costa=Double.parseDouble(sp[1]);
              if(asdx==next)
              {
                  if(cost<costx)
                  {
                    olist.remove(i);
                    break;  
                    }
                  
              } 
              
            }
            
            
          if(asd!=next)
          {
              olist.add(Integer.toString(next)+" "+Double.toString(cost)+" "+Integer.toString(node));
           }
            
         }
         }
          //olist.remove(1);
          //System.out.println(olist);
        frt.close();
          brt.close();
          // shortest();
          //public static void shortest()
          //{
        int least=0;
        if(olist.size()==0)
        {
        System.out.println("Node not reachable");
        break;
       }
        String[] spa= (olist.get(0)).split(" ", 3);
        Double x=Double.parseDouble(spa[1]);
        //Double x1=0.0;
        //System.out.println("x "+x);
        for(int c=0;c<olist.size();c++)
          {
              String[] spat= (olist.get(c)).split(" ", 3);
              //System.out.println("asd "+spat[1]);
              Double asd=Double.parseDouble(spat[1]);
              if(asd<x)
              {
                  x=asd;
                least=c;
                
              }
              //Double costa=Double.parseDouble(sp[1]);
              
            }
           //System.out.println(least);
            if(0!=olist.size())
          {
            String[] spat= (olist.get(least)).split(" ", 2);
            //System.out.println(spat[0]);
              int asd=Integer.parseInt(spat[0]);
            String xyz=olist.get(least);
              // System.out.println("olist: "+olist);
            olist.remove(least);
            clist.add(xyz);
            //String fil=hmap.get(asd);
            //System.out.println(asd);
            //System.out.println(dest);
            //System.out.println("olist: "+olist);
            //System.out.println("clist: "+clist.get(clist.size()-1) +"\n");
               // s=fil;
                costa=x;
                node=asd;
                //hash_Set.add(s);
            if(asd!=dest)
            {
                
             //traverse(asd,fil,x);
             
            }
            
           }else
           {
           System.out.println("Node not reachable");
           
          }
       }
    }
    catch(IOException e)
    {
         e.printStackTrace();  
    }
  }
  
  
  
  
    public static String printpath(int source)
  {
      int s=0;
      String path="";   
      String[] destination=clist.get(clist.size()-1).split(" ",3);
      String index3=destination[2];
      s=Integer.parseInt(index3);
      int x=0;
      while(source!=Integer.parseInt(index3))
      {
          x++;
          for(int i=0;i<clist.size();i++)
          {
            destination=clist.get(i).split(" ",3);
            index3=destination[0]; 
            
            if(s==Integer.parseInt(index3))
            {
                s=Integer.parseInt(destination[2]);
                break;            
          }
          
        }
        path=index3+"->"+path;
      }
      if(source==Integer.parseInt(index3) && x==0)
      {
          path=index3+"->"+path;
        }
    return path;
  }
}
