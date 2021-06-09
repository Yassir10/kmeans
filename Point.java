package kmeans;

/**
 *  <p>This class represents the structre of a point and  contains 6 attributes :
 *  x, y and z : integers representing the amount of red, green and blue in the color of the point,
 *  cluster :  which cluster the point belongs to,
 *  row, col : The coordinates of the point in the image.,
 * and some other useful methods.
 * </p>
 * @author Yassir
 */
public class Point{
	private double x;
	private double y;
	private double z;
	private int cluster;
        private int row;
        private int col;

    /**
     *  <p>This constructor initialize the three first attributes of the class x,y and z of the object.</p>
     * @param x <p>Integer representing the amount of red in the color of the point.</p>
     * @param y <p>Integer representing the amount of green in the color of the point.</p>
     * @param z <p>Integer representing the amount of blue in the color of the point.</p>
     */
    public Point(double x, double y, double z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
    /**
     *  <p>Same as the previous constructor but this time we initialize the coordinates of the pixel in the image too.</p>
      * @param x <p>Integer representing the amount of red in the color of the point.</p>
     * @param y <p>Integer representing the amount of green in the color of the point.</p>
     * @param z <p>Integer representing the amount of blue in the color of the point.</p>
     * @param row <p>Integer representing the abscissa of the pixel in the image.</p>
     * @param col <p>Integer representing the ordinate of the pixel in the image.</p>
     */
    public Point(double x, double y, double z,int row,int col){
            this.x = x;
            this.y = y;
            this.z = z;
            this.row=row;
            this.col=col;
        }

	
    /**
     *  <p>Compares this object with the point 'a' by compairing its values of x,y and z</p>
     * @param a <p>The point to be compared with theis object</p>
     * @return <p>true if a and this object are equal false if not</p> 
     */
    public boolean equals(Point a) {
		return a.x==this.x && a.y==this.y &&  a.z==this.z;
	}
	
    /**
     *<p>A method that returns the value of the attribute x of the object.</p>
     * @return <p>The value of the attribute x of the object.</p>
     */
    public double getX() {
		return this.x;
	}	
	
    
    
     /**<p>A method that returns the value of the attribute y of the object.</p>
     * @return <p>The value of the attribute y of the object.</p>
     */
    public double getY() {
		return this.y;
	}



     /**<p>A method that returns the value of the attribute z of the object.</p>
     * @return <p>The value of the attribute z of the object.</p>
     */
    public double getZ() {
		return this.z;
	}	
	
    /**<p>A method that returns the value of the attribute row of the object.</p>
     * @return <p>The value of the attribute row of the object.</p>
     */
    public int getRow() {
		return this.row;
	}

	
    /**<p>A method that returns the value of the attribute col of the object.</p>
     * @return <p>The value of the attribute col of the object.</p>
     */
    public int getCol() {
		return this.col;
	}
	
	
     /**<p>A method that returns the value of the attribute cluster of the object.</p>
     * @return <p>The value of the attribute cluster of the object.</p>
     */
    public int getCluster() {
		return this.cluster;
	}

    /**<p>A method that modifies the value of the attribute cluster of the object.</p>
     * @param cluster <p>The new value that replaces the value of the attribute cluster.</p>
     */
    public void setCluster(int cluster){
		this.cluster = cluster;
	}
	
}
