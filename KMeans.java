package kmeans;

import java.awt.Color;
import java.awt.Desktop;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Scanner;
import javax.imageio.ImageIO;


    /**<p> This is the main class of the project. It contains no attributes but almost all the functions 
     * I'm going to need in my project.</p>
     * @author Yassir
     */

    public class KMeans {	
    
        /** <p>This method transforms the image into a list of points by calling first the method <b>toPunktMenge</b> that , then calls
         * the method <b>toCluster</b>  that performs the task of clustering using the algorithm of kmeans and finally draws the pixels after clustering them using the method setColor</p>. 
         * @param cheminImage  <p> The path of the image to be clustered.</p>
         *  @param k    <p>The number of clusters desired</p>
         * @param distanceType <p>An integer that defines wether we use the euclidean distance function or the Chebyshev distance.</p>
         * @throws IOException  <p>In the case of  failed or interrupted I/O operations.</p>
       */
        public static String clusterImage(String cheminImage, int k, int distanceType) throws IOException {
        	
            if(distanceType==1 || distanceType==0){
//            	File file= new File(cheminImage);
//              BufferedImage hugeImage = ImageIO.read(file);

                    	
            	  URL cheminAbs = Main.class.getResource(cheminImage);
                  File classPathInput = new File(cheminAbs.getFile());
                  BufferedImage hugeImage = ImageIO.read(classPathInput);                  
                  
                  PunktMenge pk;
                  PunktMenge zm;
                  pk = KMeans.toPunktMenge(hugeImage);
                   KMeans km = new KMeans();
                   zm = km.toCluster(pk,k, distanceType);
                   	
                   for(int j=0;j<pk.size();j++){
                      Point p = pk.get(j);
                      Point z =  zm.get(p.getCluster());
                      Color c = new Color((int)z.getX(),(int)z.getY(),(int)z.getZ());
                                            
                      //if(p.getRow()<width && p.getCol()<height)
                          hugeImage.setRGB(p.getCol(), p.getRow(),c.getRGB());
                   }
                   
                   
                   String cheminAbsStr = cheminAbs.toString();
                   int occPoint = cheminAbsStr.lastIndexOf(".");
               	   String extension = cheminAbsStr.substring(occPoint+1);
                   String cheminOutputStr = cheminAbsStr.substring(0, occPoint).concat("-kmeans.").concat(extension);
                   
                   ImageIO.write(hugeImage, extension, new File(new URL(cheminOutputStr).getFile()));      
                   
                   return cheminOutputStr.replace("file:/", "");

        } else{
            System.out.println("I said tap 0 for euclidean distance or 1 for Chebyshev distance");
            return "-1";
            }
        }
        
        /** <p>This method is the main method of the class. It performs the task of clustering the points(the pixels of the image read as an input based on the algorithm of kmeans). </p>
         * @param tab <p>The list of points to be clustered(List of each pixel of the image and its color(rgb))</p>
         * @param k   <p>The number of clusters.</p>
         * @param distanceType <p>An integer that defines wether we use the euclidean distance function or the Chebyshev distance.</p>
         * @return    <p>A list of points already clustered using the algorithm of kmeans</p>
         */
        public PunktMenge toCluster(PunktMenge tab, int k, int distanceType) {
            PunktMenge c = createPunkt(k);
            cluster(tab, c, distanceType);
            PunktMenge c1 = new PunktMenge();
            Point z =  new Point(0,0,0);
            for(int p=0;p<k;p++)
            c1.add(z);
            for(int i=0;i<k;i++)
                c1.set(i,new Point(c.get(i).getX(),c.get(i).getY(),c.get(i).getZ()));
            do{
                for(int j=0;j<k;j++)
                    c.set(j,new Point(c1.get(j).getX(),c1.get(j).getY(),c1.get(j).getZ()));
                for(int l = 0 ; l < k; l++) {
                    PunktMenge p = new PunktMenge();
                    for(int o = 0 ; o < tab.size(); o++) {
                       if(tab.get(o).getCluster()==l)
                       p.add(tab.get(o));
                    }
                   c1.set(l,findCentroid(p));
                }	
                cluster(tab, c1, distanceType);
             } while(!comparePM(c1,c));
            return c;
            }

        /**   <p> This method creates a random list of zentroids used at the first iteration of the Kmeans algorithm</p>
         *
         * @param k <p>The number of clusters(the number of centroids)</p>
         * @return  <p>It returns a list of randomly chosen centroids </p>
         */
        public PunktMenge createPunkt(int k){

                    PunktMenge c = new PunktMenge();
                    for(int i=0;i<k;i++) {
                    c.add(new Point((int) ((Math.random() * 900) + 100) / 100.0,(int) ((Math.random() * 900) + 100) / 100.0,(int) ((Math.random() * 900) + 100) / 100.0));
                    }
                    return c;
            }

        /**
         *  <p>This method looks for the points that belong to the same cluster and make its arithmetic mean which is the centroid used in the next iteration.</p>

         * @param pk <p>The list of points to be clustered</p>
         * @return <p>The aritmetic mean of the points that belongs to the same cluster.</p>
         */
        public Point findCentroid(PunktMenge pk) {
                    Point z;
                    if(pk.isEmpty()) return new Point(0.0,0.0,0.0);
                    double a=0,b=0,c=0;
                    int i;
                    for( i=0 ; i < pk.size() ; i++) {
                            a+=pk.get(i).getX();
                            b+=pk.get(i).getY();
                            c+=pk.get(i).getZ();
                    }
                    z = new Point((double)(a/(i)),(double)(b/(i)),(double)(b/(i)));		
            return z;
            }

        /**
         * <p>This method computes the euclidean distance from each point to all the centroids and chooses the closest one  and then assigns the point to this cluster.</p>
         * @param tab   <p>The points to be clustered</p>
         * @param c     <p>The list of the centroids</p>
         * @param distanceType <p>An integer that defines wether we use the euclidean distance function or the Chebyshev distance.</p>
         */
        public void cluster(PunktMenge tab, PunktMenge c, int distanceType){
                   double[][] d = new double[tab.size()][c.size()];
                    int i;
                   if(distanceType == 0){
                    for(i=0 ; i < tab.size() ; i++){
                            for(int j=0 ; j < c.size() ; j++){
                                    d[i][j] = this.distEuc(tab.get(i), c.get(j));	
                            }
                            tab.get(i).setCluster(min(d[i]));
                    }
            }
                   else if(distanceType ==1 ){
                          for(i=0 ; i < tab.size() ; i++){
                            for(int j=0 ; j < c.size() ; j++){
                                    d[i][j] = this.distMax(tab.get(i), c.get(j));	
                            }
                            tab.get(i).setCluster(min(d[i]));
                    }
                   
                   }
                    }
        /**
         *<p>This method returns the minimum of an array</p>
         * @param tab   <p>The array</p>
         * @return  <p>The minimum of the array</p>
         */
        public int min(double[] tab) {
                    int min = 0;
                    for(int i = 1 ; i < tab.length ; i++) 
                            if(tab[i]<tab[min])
                                    min = i;
                    return min;
            }

       public  static double max(double d1, double d2, double d3){
           double max = d1;
           if(d2>max)
               max= d2;
           if(d3>max)
               max = d3;
         return max;
        }
        
        /**
         *  <p>This mothod compares two lists of points</p>
         * @param pk1   <p>The first list to be compared</p>
         * @param pk2   <p>The second list to be compared</p>
         * @return      <p>True if the lists contain the same elements, false if not.</p>
         */
        public boolean comparePM(PunktMenge pk1, PunktMenge pk2) {
                    if(pk1.size() != pk2.size()) 
                            return false;

                    for(int i=0 ; i < pk1.size() ; i++)
                            if(!(pk1.get(i).getX() == pk2.get(i).getX() && pk1.get(i).getY() == pk2.get(i).getY() && pk1.get(i).getZ() == pk2.get(i).getZ()))
                                return false;
                    return true;
            }

        /**
         *  <p>This method computes the euclidean distance between two points.</p>
         * @param a <p>The first point</p>
         * @param b <p>The second point</p>
         * @return  <p>The euclidean distance between the two points</p>
         */
        public double distEuc(Point a, Point b) {
                    return Math.sqrt(Math.pow(a.getX()-b.getX(),2) + Math.pow(a.getY()-b.getY(),2)+Math.pow(a.getZ()-b.getZ(),2));

        }
        
        /**
         *  <p>This method computes the Chebyshev distance(Max-Distanzfunktion) between two points. </p>
         * @param a <p>The first point</p>
         * @param b <p>The second point</p>
         * @return  <p>The Chebyshev distance between the two points</p>
         */
        public double distMax(Point a, Point b){
            return max(Math.abs(a.getX()-b.getX()),Math.abs(a.getY()-b.getY()),Math.abs(a.getZ()-b.getZ()));
        }


        /**
         *  <p>This method takes an image as an input and then transforms it into a list of points using the function <b>getRGB()</b></p>
         * @param image <p>The image </p>
         * @return  <p>A list of points that are the pixels of the image given as an input. The attributs of the point (x,y,z) are the three integers representing the amount of red,green and blue of the
         * color of the pixel</p>
         */

      static PunktMenge toPunktMenge(BufferedImage image) {
          int width = image.getWidth();
          int height = image.getHeight();
          int[][] result = new int[height][width];

          PunktMenge pk = new PunktMenge();
          Point p;

          for (int row = 0; row < height; row++) {
             for (int col = 0; col < width; col++) {
                result[row][col] = image.getRGB(col, row);
                int clr=   result[row][col];
                int  red   = (clr & 0x00ff0000) >> 16;
                int  green = (clr & 0x0000ff00) >> 8;
                int  blue  =  clr & 0x000000ff;

                p = new Point(red,green,blue,row,col);
               pk.add(p);
             }
          }
          return pk;
      }
    }

