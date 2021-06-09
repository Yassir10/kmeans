package kmeans;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;


public class Main {

	/**
     *  <p>The user have to input the path of the image then the  method clusterImage is called the perform the task of clustering</p>
     *  @throws IOException <p>In the case of failed or interrupted I/O operations.</p>
     * @param args <p>I didn't use this parameter in my project.</p>
     */
     public static void main(String[] args) throws IOException{
                // TODO Auto-generated method stub
                
                  
               
                Scanner s = new Scanner(System.in);
                

                int n, k, dT;
                 String  cheminImage, outPutPath;
                
                    System.out.println("Please tap 0 for the default test and 1 for the advanced test");
                    n = s.nextInt(); 
                    
                   if(n == 1){
                       s.nextLine();
                    System.out.println("Enter the name of the image file with its extension");
                      cheminImage = s.nextLine();
                    System.out.println("Enter the number of clusters");
                     k = s.nextInt();
                     s.nextLine();
                     System.out.println("Tap 0 to choose the euclidean distance and 1 to choose the Chebyshev distance");
                    dT = s.nextInt(); 
                    
                    outPutPath = KMeans.clusterImage(cheminImage, k,dT);
                    if(outPutPath == "-1") {
                    	System.out.println("Error");
                    } else {
                        File f = new File(outPutPath);
                        Desktop d = Desktop.getDesktop();
                        d.open(f);       	
                    }
             
                   }
                   else if(n == 0){
                       outPutPath = KMeans.clusterImage("tiger.jpg", 4,0);
                       if(outPutPath == "-1") {
                       	System.out.println("Error");
                       } else {
                           File f = new File(outPutPath);
                           Desktop d = Desktop.getDesktop();
                           d.open(f);    	
                       }
                   }
                   else{
                       System.out.println("Please tap 0 or 1");
                   }
               }

}
