
/**
 * Write a description of class dijkstra_new here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
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
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.*;
public class dijkstra_2nd
{
   static int aman=0;
 static int xno,yno,k;//this is the integer creating hash function
 static int bucket=0;//this integer stores the bucket size
 static double min_x,max_x,min_y,max_y;
 static double x_slot,y_slot;
 static int dest=0;
 static ArrayList<String> clist=new ArrayList<String>();
 static ArrayList<String> olist=new ArrayList<String>();
 static HashMap<Integer, String> hmap = new HashMap<Integer, String>();
 static Set<String> hash_Set = new HashSet<String>();

 public static void main(String args[])  
 {  
   try  
   {
      File file=new File("nodes.txt");    //creates a new file instance  
       FileReader fr=new FileReader(file);   //reads the file which contains the records
       BufferedReader br=new BufferedReader(fr); 
       
       FileReader frx=new FileReader(file);//creates a buffering character input stream  
       BufferedReader brx=new BufferedReader(frx); 
       max_x=0.00;
       min_x=717230.24047417;
       max_y=0.00;
       min_y=3146166.47134536;
       String line;
       while((line=br.readLine())!=null)  //This loop gives the records to hashing functions.
       { 
         max_min(line);//This function returns the index of the record.
        // int k=insert(c,bucket,line);//this function along with other functions does the work of inserting records in files
       }
       fr.close();
       br.close();
       System.out.println("Please enter the value of k");
       Scanner sc=new Scanner(System.in);
       k=sc.nextInt();
       System.out.println("Please enter the bucket size");
       int b=sc.nextInt();
       double x_dif=max_x-min_x;
       double y_dif=max_y-min_y;
       System.out.println(x_dif+", "+y_dif);
       
       
       
       //x_slot=x_dif/k;
       //y_slot=y_dif/k;
       x_slot=k;
       y_slot=k;
       //System.out.println(x_slot+", "+y_slot);
       while((line=brx.readLine())!=null)  //This loop gives the records to hashing functions.
      { 
         bucket(line);
         String fname=Integer.toString(xno)+"_"+Integer.toString(yno);
        // add_node(fname,line);
         int c = get_index(line);
         hmap.put(c, fname);
         //System.out.println(line+", "+xno+", "+yno);//This function returns the index of the record.
        // int k=insert(c,bucket,line);//this function along with other functions does the work of inserting records in files
      }
      System.out.println("Do you want to get the file name and node coordinates of any node?\n1. YES\n2. NO");
       int input=sc.nextInt();
       if(input==1)
      {
          input=sc.nextInt();
           System.out.println("File in which "+input+" exists is "+hmap.get(input));
           
        }
        frx.close();
        brx.close();
        System.out.println("Enter the source node?");
        int source=sc.nextInt();
        System.out.println("Enter the destination node?");
        dest=sc.nextInt();
        if(source==dest)
        System.out.println("Source and destination are same.");
        String fil=hmap.get(source);
        clist.add(Integer.toString(source)+" "+"0.00 "+Integer.toString(source));
        traverse(source,fil,0.00);
        String s=printpath(source);
        if(olist.size()!=0)
        {
        String[] destin=clist.get(clist.size()-1).split(" ", 3);;
        Double totalcost=Double.parseDouble(destin[1]);
        
        System.out.println("Total cost of reaching from "+source+" to "+dest+" is "+totalcost );
       
        System.out.println("Path: "+s+dest);
       }
        System.out.println(hash_Set);
        System.out.println("Number of files being opened "+hash_Set.size());
    }
   catch(IOException e)  
   {  
       e.printStackTrace();  
   }
  }
  
  
  
  public static void bucket(String s)
  {
      double x=0.0;
      double y=0.0;
      String[] sp= s.split(" ", 3);
      double x_val=Double.parseDouble(sp[1]);
      for(int i=0;i<k;i++)
     { 
       x=min_x+i*x_slot;
       if(x_val>=x && x_val<(x+x_slot))
       {
           xno=i+1;
           break;      
       }
        }
      double y_val=Double.parseDouble(sp[2]);
       for(int i=0;i<k;i++)
     { 
       y=min_y+i*y_slot;
       if(y_val>=y && y_val<(y+y_slot))
       {
           yno=i+1;
           break;      
       }
        } 
  }
  
  
  public static int get_index(String s)//This function returns the index of the record.
 {
    // System.out.println(s);
      String[] sp= s.split(" ", 2);
      int i=Integer.parseInt(sp[0]);
    return i;
 }
 
  public static void max_min(String s)
  {
     String[] sp= s.split(" ", 3);
      double i=Double.parseDouble(sp[1]);
      
      if(min_x>i)
       min_x=i;
      else if(max_x<i)
       max_x=i;
      double j=Double.parseDouble(sp[2]);
      if(min_y>j)
       min_y=j;
      else if(max_y<j)
       max_y=j;
    
     
     // return 0;
  }
  
  
  
   public static void traverse(int node,String s,Double costa)
  {
      try{
       // clist.add(Integer.toString(node)+" "+Double.toString(cost)+" "+Integer.toString(node));
       while(node!=dest)
      {
          File f=new File(s+".txt");
       String line="";
       hash_Set.add(s);
       // System.out.println(s);
        FileReader frt=new FileReader(f); //reads the file which contains the records
        
        BufferedReader brt=new BufferedReader(frt);      
        while(!(line=brt.readLine()).equals("##"))  //This loop gives the records to hashing functions.
        {
          // System.out.println(line);
        }  
          while(!(line=brt.readLine()).equals("**"))  //This loop gives the records to hashing functions.
        {
           //System.out.println(line);
            String[] sp= line.split(" ", 3);
         int x=Integer.parseInt(sp[0]);
         if(x==node)
         { int next=Integer.parseInt(sp[1]);
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
            String fil=hmap.get(asd);
            //System.out.println(asd);
            //System.out.println(dest);
            //System.out.println("olist: "+olist);
            System.out.println("clist: "+clist.get(clist.size()-1) +"\n");
                s=fil;
                costa=x;
                node=asd;
                hash_Set.add(s);
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
      while(source!=Integer.parseInt(index3))
      {
          
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
    return path;
  }
}