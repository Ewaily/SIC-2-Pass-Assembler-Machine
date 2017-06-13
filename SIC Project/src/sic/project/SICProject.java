/*
    * Muhammad Ashraf Ewaily        14101380
    * System Programming Project 1 | SIC 2-Pass Assembler
 */
package sic.project;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class SICProject {

    
    public static void main(String[] args) throws FileNotFoundException  {
        
        File F = new File ("inSIC.txt");
        Scanner scan = new Scanner(F);
        String[][] table = new String[19][4];
        String[][] table2 = new String[19][5];
        int [] label = new int [32];
        String[] row = new String[20];
        String S;
        int z = 0;
        
        //Scanning the txt file
        
        while ( scan.hasNext() ){
            
            
            S = scan.nextLine();
            row = S.split(" "); //Splitting the words with respect of Space
            table[z][1] = row[0];
            table2[z][1] = table[z][1];
            table[z][2] = row[1];
            table2[z][2] = table[z][2];
            table[z][3] = row[2];
            table2[z][3] = table[z][3];
            z ++ ;
            
        
    }
        
        
        // ><><><><><><><><><><>< PASS 1 ><><><><><><><><><><><><><
        
        table[1][0] = table[0][3]; // setting the counter starting location
        table2[1][0] = table[1][0];
        table[0][0] = " ";
        table2[0][0] = table[0][0];
        label[1] = Integer.parseInt(table[1][0]);
        int x = 0; z = 1; 
        
                // Setting location counter
                
        while ( !"END".equals(table[z][2])){
            
            if ( "WORD".equals(table[z][2])){
                
                    label[z+1] = label[z] +3;
                    table[z+1][0] = Integer.toHexString(label[z+1]);
                    table[z+1][0] = ("0000" + table[z+1][0]).substring(table[z+1][0].length());
                    table2[z+1][0] = table[z+1][0];
            }
            
            else if ( "BYTE".equals(table[z][2])){
                
                if(table[z][2].startsWith("C")){
                    
                    label[z+1] = label[z] + (table[z][2].length()) - 3;
                    table[z+1][0] = Integer.toHexString(label[z+1]);
                    table[z+1][0] = ("0000" + table[z+1][0]).substring(table[z+1][0].length());
                    table2[z+1][0] = table[z+1][0];
                }
                
                else{
                    
                    label[z+1] = label[z] + ((table[z][2].length())/2) - 3;
                    table[z+1][0] = Integer.toHexString(label[z+1]);
                    table[z+1][0] = ("0000" + table[z+1][0]).substring(table[z+1][0].length());
                    table2[z+1][0] = table[z+1][0];
                }
            }
            
           else if ( "RESW".equals(table[z][2])){
                
                    label[z+1] = label[z] + (Integer.parseInt(table[z][3])*3);
                    table[z+1][0] = Integer.toHexString(label[z+1]);
                    table[z+1][0] = ("0000" + table[z+1][0]).substring(table[z+1][0].length());
                    table2[z+1][0] = table[z+1][0];
                  //  System.out.println(table[z+1][0]);
            }
            
           else if ( "RESB".equals(table[z][2])){
                
                    x = Integer.parseInt(table[z][3]);
                    label[z+1] = label[z] + x ;
                    table[z+1][0] = Integer.toHexString(label[z+1]);
                    table[z+1][0] = ("0000" + table[z+1][0]).substring(table[z+1][0].length());
                    table2[z+1][0] = table[z+1][0];
            }
           
           
            
            else {
                    
                    label[z+1] = label[z] + 3 ;
                    table[z+1][0] =  Integer.toHexString(label[z+1]);
                    table[z+1][0] = ("0000" + table[z+1][0]).substring(table[z+1][0].length());
                    table2[z+1][0] = table[z+1][0];

                    
            }
            
        z ++;
        x = 0;
        
        
        
                
        }
        // Printing the table with the location counter
        
        System.out.println("PASS ONE:\n *Set Location Counter:\n");
        for (int j = 0; j < 19; j++) {
            for (int k = 0; k < 4; k++) {
                System.out.print(table[j][k] +"\t");

            }
            System.out.println();

        }
        
        // Printing the Symbol Table Generation
        
        System.out.println(" \n\n*Symbol Table Generation:\n");
        
        String [][] g = new String [18][2];
        
        int gg=1;
        int yy=1;
        
        g[0][0] = "Symbol"; g[0][1] = "Location";
        
        for (int y = 2 ; y < 18 ; y ++){
            
            if( !"".equals(table[y][1]) ){
                
                g[gg][0] = table[y][1];
                g[gg][1] = table[y][0];
                gg ++ ;
            }
            
            
        }
        
       for (int j = 0; j < 8; j++) {
            for (int k = 0; k < 2; k++) {
                System.out.print(g[j][k] +"\t");

            }
            System.out.println();

        }
       
       System.out.println(" \n\n*Program Lenght:\n");
       
       int l = (label[z]) - (label[1]);
       String Length = Integer.toHexString(l);
       
       System.out.println((Integer.toHexString(l)));
       
       
       
     System.out.println("\n\nENd OF PASS ONE\n\n\n");
     
     
     
     
     // ><><><><><><><><><><>< PASS 2 ><><><><><><><><><><><><><
     
     
    
     int o = 1;
     int t = 0;
     String LDA = "00 ";
     String STA = "0C ";
     String LDX = "04 ";
     String ADD = "18 ";
     String COMP = "28 ";
     String TIX = "2C ";
     String JLT = "38 ";
    String r [] = new String [2];

       while ( !"END".equals(table2[o][2])){
           
           
           for( t = 0 ; t < 18 ; t ++ ){
               
               r = table2[o][3].split(",");
               if(r[0].equals(table2[t][1])){
                   
                   break;
               }
           }
           
           
           
           
           
            if ("STA".equals(table2[o][2])){
               //**************
             if (table2[o][3].contains(",X")){
                 
                 int tmp = Integer.parseInt(table2[t][0]);
                 tmp = tmp + 32780;
                 String tp = Integer.toHexString(tmp);
                 table2[o][4] = STA + tp;
             }
               //**************
                
             else  table2[o][4] = STA + table2[t][0];
               
           }
                   
            
               else if ("LDA".equals(table2[o][2])){
               if (table2[o][3].contains(",X")){
                 
                 int tmp = Integer.parseInt(table2[t][0]);
                 tmp = tmp + 32780;
                 String tp = Integer.toHexString(tmp);
                 table2[o][4] = LDA + tp;
             }
               else table2[o][4] = LDA + table2[t][0];
               
           }
                   
          else if ("LDX".equals(table2[o][2])){
               
              if (table2[o][3].contains(",X")){
                 
                 int tmp = Integer.parseInt(table2[t][0]);
                 tmp = tmp + 32780;
                 String tp = Integer.toHexString(tmp);
                 table2[o][4] = LDX + tp;
             }
              
              else table2[o][4] = LDX + table2[t][0];
               
           }
           else if ("ADD".equals(table2[o][2])){
               
               if (table2[o][3].contains(",X")){
                 
                 int tmp = Integer.parseInt(table2[t][0]);
                 tmp = tmp + 32780;
                 String tp = Integer.toHexString(tmp);
                 table2[o][4] = ADD + tp;
             }
               
               else  table2[o][4] = ADD + table2[t][0];
               
           }
           else if ("COMP".equals(table2[o][2])){
               
               if (table2[o][3].contains(",X")){
                 
                 int tmp = Integer.parseInt(table2[t][0]);
                 tmp = tmp + 32780;
                 String tp = Integer.toHexString(tmp);
                 table2[o][4] = COMP + tp;
             }
               
               else table2[o][4] = COMP + table2[t][0];
               
           }
           else if ("TIX".equals(table2[o][2])){
               
               if (table2[o][3].contains(",X")){
                 
                 int tmp = Integer.parseInt(table2[t][0]);
                 tmp = tmp + 32780;
                 String tp = Integer.toHexString(tmp);
                 table2[o][4] = TIX + tp;
             }
               
               else table2[o][4] = TIX + table2[t][0];
               
           }
           else if ("JLT".equals(table2[o][2])){
               
               if (table2[o][3].contains(",X")){
                 
                 int tmp = Integer.parseInt(table2[t][0]);
                 tmp = tmp + 32780;
                 String tp = Integer.toHexString(tmp);
                 table2[o][4] = JLT + tp;
             }
               
               else table2[o][4] = JLT + table2[t][0];
               
           }
           else if ("RESW".equals(table2[o][2])){
               
               table2[o][4] = "No Obj. Code";
               
           }
          
           else if ("WORD".equals(table2[o][2])){
               
               
          //        table2[o][4] = table2[o][3];
          int n = Integer.parseInt(table[o][3]);
           
                      table2[o][4] = Integer.toHexString(n);
                      table2[o][4] = ("000000" + table2[o][4]).substring(table2[o][4].length());
                    
           }
            
           else 
               table2[o][4] = "No Obj. Code";
               
           
           o ++;
           
           
           
       }
       table2[0][4] = " ";
       table2[18][4] = " ";
       
       System.out.println("PASS TWO:\n ");
        for (int j = 0; j < 19; j++) {
            for (int k = 0; k < 5; k++) {
                System.out.print(table2[j][k] +"\t");

            }
            System.out.println();

        }
        //             ("000000" + table[1][0]).substring(table[1][0].length())
        
        System.out.println("\n\nHTE RECORD:\n ");
        
        System.out.println("H."+("XXXXXX" + table[0][1]).substring(table[0][1].length())+"."+("000000" + table[1][0]).substring(table[1][0].length())+"."+("000000" + Length).substring(Length.length()));
        
        
        //**************************************************
                
        int flag = 0, u = 0;
        int v = 1;
        String st;
        String[] temp = new String[30];        
        
        while ( flag == 0 ) {
            
            
            if ( ("RESW".equals(table2[v][2])) || ("RESB".equals(table2[v][2]))) {
               v++;
            }
            else
            {   
                st = table2[v][0];
                    
                while (!"RESW".equals(table2[v][2]) && !"RESB".equals(table2[v][2])){
                    
                    
                   if ("END".equals(table2[v][2])){
                       
                       flag = 1;
                       break;
                   }
                   
                   else{
                       
                       temp[u] = table2[v][4];
                       u++ ;
                     //   System.out.print("."+table2[v][4]);
                        
                       
                       
                   }
                   
                   
                   v++;
                }
                String leng = Integer.toHexString(3*(u));
                System.out.print("T."+("000000" + st).substring(st.length())+"."+("00" + leng).substring(leng.length()));
               
                for ( int i = 0 ; i < u ; i ++ ){
                    
                    System.out.print("."+temp[i]);
                    
                }
                System.out.println();
                
                u = 0;
                
                
            }
        }
       
     
        
        //**************************************************
        
        
        
        System.out.println("E."+("000000" + table[1][0]).substring(table[1][0].length()));

        
        
    
        
        System.out.println();
        System.out.println("\n\nENd OF PASS ONE\n\n\n");
       
        
    }
    

    
}
