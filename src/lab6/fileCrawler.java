package lab6;

import java.io.*;
import java.util.*;


public class fileCrawler 
{
 
  private Direc WQ;
  int j=0;
  static int i = 0;
 
 private class Worker implements Runnable 
 {
 
  private Direc Line1;
  private Direc Line;
  
 
  public Worker(Direc L) 
  {
   Line = L;
   //Line1=L1;
  }
 

  public void run()
  {
   String StringName;
   
   while ((StringName = Line.remove()) != null) 
   {
    File file = new File(StringName);
    String entries[] = file.list();
    if (entries == null)
    {
     continue;
    }
    for (String entry : entries) 
    {
     if (entry.compareTo(".") == 0)
     {
      continue;
     }
     if (entry.compareTo("..") == 0)
     {
      continue;
     }
     
     String fn = StringName + "\\" + entry;
     System.out.println(fn);
    }
   }
  }
 }
 
 public fileCrawler()
 {
  WQ = new Direc();
 }
 
 public Worker createWorker() {
  return new Worker(WQ);
 }
 
 

 
 public void processDirectory(String dir) {
   try{
   File file = new File(dir);
   if (file.isDirectory()) {
    String entries[] = file.list();
    if (entries != null)
     WQ.add(dir);
 
    for (String entry : entries) 
    {
     String subdir;
     if (entry.compareTo(".") == 0)
      continue;
     if (entry.compareTo("..") == 0)
      continue;
     if (dir.endsWith("\\"))
      subdir = dir+entry;
     else
      subdir = dir+"\\"+entry;
     processDirectory(subdir);
    }
   }}catch(Exception e){}
 }
 
 public static void main(String Args[]) 
 {
 
  fileCrawler fc = new fileCrawler();
 

 
  int k=0;
  int Num = 10;
  int i=0;
  ArrayList<Thread> thread = new ArrayList<Thread>(Num);
  
  for ( i = 0; i < Num; i++) 
  {
   Thread t = new Thread(fc.createWorker());
   thread.add(t);
   t.start();
  }
 

 
  fc.processDirectory("C:\\Users\\test1\\Downloads\\Lab4_Naila_0874");
 
//  indicate that there are no more directories to add
 
  fc.WQ.finish();
 //int i=0;
  for (i = 0; i < Num; i++)
  {
   try {
    thread.get(i).join();
   } catch (Exception e) {};
  }
 }
}
