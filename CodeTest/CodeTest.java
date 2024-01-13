import java.io.*;
import java.util.Scanner;
import java.text.NumberFormat;



public class CodeTest
{
   public static final String delimiter = ",";
   public static void read(String csvFile) {
      try {
         File file = new File(csvFile);
         FileReader fr = new FileReader(file);
         BufferedReader br = new BufferedReader(fr);
         int LineNr = 0;
         String line = "";
         String[] tempArr;
         float loan=0;
         float minterest=0;
         int payments=0;
                   
         while((line = br.readLine()) != null ) {
                                     
            //Remove empty lines
             if (!((line.isBlank()) || (line.length()<2))){
                 //Remove unwanted characters
                 if (line.contains("\"")){
                 line=line.replace("\"","");
                 line=line.replaceFirst(","," ");
                }
                int i=0;
                    
                tempArr = line.split(delimiter);
                for(String tempStr : tempArr) {
                    //Check if it's numeric
                    if (isNumeric(tempStr)){
                        switch(i){
                            //loan
                            case 0: {
                                loan =Float.valueOf(tempStr);
                                System.out.printf("%-15.2f", loan);
                            }
                            break;
                            //interest
                            case 1: {
                                float ainterest = Float.valueOf(tempStr);
                                System.out.printf("%-15.2f", ainterest);
                                minterest = (ainterest/100/12);
                                
                            }
                            break;
                            //payments
                            case 2: {
                                int years = Integer.parseInt(tempStr);
                                System.out.printf("%-15s",years);
                                payments = years*12;
                                
                            }
                            break;
                            
                         }
                        i++;
                    }    
                    else {
                        if (LineNr==0) {
                            System.out.printf("%-25s", tempStr);    
                        }
                        else if (LineNr>0 && LineNr<3){
                            System.out.printf("%-15s", tempStr);    
                        }
                        else if (LineNr==3){ 
                            System.out.printf("%-15s%-15s", tempStr, "Monthly Payments");
                        
                        }
                        else{
                            System.out.printf("%-25s", tempStr);
                        }    
                    
                        LineNr++;
                        
                    }
                
                }
                
                    
                if (payments>0){
                double mpayment=Math.pow(1+minterest, payments);
                double monthlypayment=loan*(minterest*mpayment/(mpayment-1));
                System.out.printf("%-15.2f", monthlypayment);
                }
        
                System.out.println();
             
             }
             
         }
         br.close();
         } catch(IOException ioe) {
            ioe.printStackTrace();
         }
   }
public static boolean isNumeric(String str) { 
  try {  
    Double.parseDouble(str);  
    return true;
  } catch(NumberFormatException e){  
    return false;  
  }  
}

   public static void main(String[] args) {
      String csvFile = "prospects.txt";
       CodeTest.read(csvFile);
   }
}
