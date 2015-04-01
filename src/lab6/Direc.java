package lab6;

import java.util.*;

public class Direc {
 

  private LinkedList<String> WQ;
  private int number;
  private int NumOfDirec;  
  private boolean Okay;  
 
 
 public Direc()
 {
  WQ = new LinkedList<String>();
  Okay = false;
  NumOfDirec = 0;
 }
 
 public synchronized void add(String s) 
 {
  WQ.add(s);
  NumOfDirec++;
  notifyAll();
 }
 
 public synchronized String remove()
 {
  String B;	 
  String A;
  
  while (!Okay && NumOfDirec == 0)
  {
   try {
    wait();
   } catch (Exception e) {};
  }
  if (NumOfDirec > 0) 
  {
   A = WQ.remove();
   NumOfDirec--;
   
   notifyAll();
  }
  
   else 
   A = null;
  return A;
 }
 
 public synchronized void finish() 
 {
  Okay = true;
  notifyAll();
 }
}
