
/**
 * Write a description of class graph_partition here.
 * 
 * @author (Anshu) 
 * @version (11/09/2021)
 */import java.io.*;  
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
public class graph_partition
{
   static int xno,yno,k;//this is the integer creating hash function
 static int bucket=0;//this integer stores the bucket size
 static double min_x,max_x,min_y,max_y;
 static double x_slot,y_slot;
  static HashMap<Integer, String> hmap = new HashMap<Integer, String>();
 public static void main(String args[])  
 {  
   try  
   {  
       //h1=2;//Hash function starts this mod 2.
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
       System.out.println(min_x+", "+max_x+", "+min_y+", "+max_y);
      System.out.println("Please enter the value of k");
      Scanner sc=new Scanner(System.in);
       k=sc.nextInt();
      System.out.println("Please enter the bucket size");
       int b=sc.nextInt();
       double x_dif=max_x-min_x;
       double y_dif=max_y-min_y;
       System.out.println(x_dif+", "+y_dif);
       
       
       
       x_slot=x_dif/k;
       y_slot=y_dif/k;
       System.out.println(x_slot+", "+y_slot);
      while((line=brx.readLine())!=null)  //This loop gives the records to hashing functions.
      { 
         bucket(line);
         String fname=Integer.toString(xno)+"_"+Integer.toString(yno);
         add_node(fname,line);
         int c = get_index(line);
         hmap.put(c, fname);
         //System.out.println(line+", "+xno+", "+yno);//This function returns the index of the record.
        // int k=insert(c,bucket,line);//this function along with other functions does the work of inserting records in files
      }
       
     for(int i=1;i<=k;i++)
     {
         for(int j=1;j<=k;j++)
         {
             String fname=i+"_"+j;
            // System.out.println(fname);
     File f=new File(fname+".txt");
     if(f.exists()) 
     {
      FileReader fry=new FileReader(fname+".txt");   //reads the file  
      BufferedReader bry=new BufferedReader(fry);
      FileWriter myWriter = new FileWriter(fname+".txt", true);
      myWriter.write("##\n");
       //
       //  System.out.println(fulline+" "+fname);
      fry.close();
      //String line="";
      
      myWriter.close();
       fr.close();
     }
    }
    }
     File filex=new File("edges.txt");    //creates a new file instance  
     FileReader frb=new FileReader(filex);   //reads the file which contains the records
     BufferedReader brb=new BufferedReader(frb);
     while((line=brb.readLine())!=null)  //This loop gives the records to hashing functions.
     { 
           int x = get_index(line);
           String var= hmap.get(x);
         //  System.out.println(var);
           add_edges(line,var);
        // max_min(line);//This function returns the index of the record.
        // int k=insert(c,bucket,line);//this function along with other functions does the work of inserting records in files
     } 
    
    
    
    for(int i=1;i<=k;i++)
     {
         for(int j=1;j<=k;j++)
         {
             String fname=i+"_"+j;
            // System.out.println(fname);
     File f=new File(fname+".txt");
     if(f.exists()) 
     {
      FileReader fry=new FileReader(fname+".txt");   //reads the file  
      BufferedReader bry=new BufferedReader(fry);
      FileWriter myWriter = new FileWriter(fname+".txt", true);
      myWriter.write("**\n");
       //
       //  System.out.println(fulline+" "+fname);
      fry.close();
      //String line="";
      
      myWriter.close();
       fr.close();
     }
     }
    }
    /*
    for(int i=1;i<=k;i++)
     {
         for(int j=1;j<=k;j++)
         {
             String fname=i+"_"+j;
            // System.out.println(fname);
        File f=new File(fname+".txt");
        if(f.exists()) 
     { 
        FileReader frt=new FileReader(f);   //reads the file which contains the records
       BufferedReader brt=new BufferedReader(frt); 
      
        while((line=brt.readLine())!="##" &&(line=brt.readLine())!=null)  //This loop gives the records to hashing functions.
      {
          
      }
      while((line=brt.readLine())!="**"&&(line=brt.readLine())!=null)  //This loop gives the records to hashing functions.
      {
         String[] sp= line.split(" ", 3);
        int x=Integer.parseInt(sp[1]);
        System.out.println(fname);
         String var= hmap.get(x);
         if(!var.equals(fname))
        {
            
            String bnode=search(x);
            add_node(fname,bnode);
            
        }
      }
      }
    }*/
    
    
    
    
    
        fr.close(); 
       br.close();
            
      
    }
   catch(IOException e)  
   {  
       e.printStackTrace();  
   }  
 } 
 
 
  public static void add_node(String fname,String fulline)//this function adds the records to the files
 {  
  try  
  { File f=new File(fname+".txt");
    if(!f.exists()) 
    {
      File myObj = new File(fname+".txt");//creates new file for records
      myObj.createNewFile();
      
    }
      FileReader fr=new FileReader(fname+".txt");   //reads the file  
      BufferedReader br=new BufferedReader(fr);
      FileWriter myWriter = new FileWriter(fname+".txt", true);
      myWriter.write(fulline+"\n");
      System.out.println(fulline+" "+fname);
      fr.close();
      String line="";
    /* else
    {
      
       FileReader fr=new FileReader(fname+".txt");   //reads the file  
      BufferedReader br=new BufferedReader(fr);
      FileWriter myWriter = new FileWriter(fname+".txt",true);
      myWriter.write(fulline+"\n");
      System.out.println(fulline+"\n");
    
     }*/
       myWriter.close();
       fr.close();
   }  
   catch(IOException e)  
   {  
      e.printStackTrace();  
   }
 }
 
 public static String search(int node)//This function returns the index of the record.
 {String line="";
     try{
     File file=new File("nodes.txt");    //creates a new file instance  
       FileReader fr=new FileReader(file); 
       //reads the file which contains the records
       BufferedReader br=new BufferedReader(fr);
        while((line=br.readLine())!=null )  //This loop gives the records to hashing functions.
       { 
           String[] sp= line.split(" ", 2);
         int i=Integer.parseInt(sp[0]);
         
        if(node==i)
         break;
       }
     }
   catch(IOException e)  
   {  
      e.printStackTrace();  
   } 
    return line;
   
 }
 
 
 public static void add_edges(String edge,String fname)//this function adds the records to the files
 {  
  try  
  {
     File f=new File(fname+".txt");
   
      FileReader fr=new FileReader(fname+".txt");   //reads the file  
      BufferedReader br=new BufferedReader(fr);
      FileWriter myWriter = new FileWriter(fname+".txt", true);
      myWriter.write(edge+"\n");
      //System.out.println(fulline+" "+fname);
      fr.close();
      myWriter.close();
       fr.close();
      //String line="";    
  
      
      
      
      
      
   }  
   catch(IOException e)  
   {  
      e.printStackTrace();  
   }
 }
 
 
 
 public static int get_index(String s)//This function returns the index of the record.
 {
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
   
} 

