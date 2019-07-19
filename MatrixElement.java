
package Game;

import java.awt.Color;
import java.awt.Point; 
import java.awt.Graphics; 


/*
 * cette classe s'occupe de dessiner 
 * 
 * un carreau de la piece et lui  donner une dimension  precise (coors)
 * 
 * 
 * */
public class MatrixElement 
{
    Color color; 
    Point coors; 
    
    public MatrixElement(Color clr, Point coors)  // il prends comme parametre une couleur et un point
    {
        this.color = clr  ;
        this.coors = coors; 
    }
    public MatrixElement(MatrixElement elem)
    {
        this.color = new Color(elem.color.getRGB()); //   getRGB(): retourner le code de la couleur
        this.coors = new Point(elem.coors.x, elem.coors.y);
    }
    
    public void draw(Graphics g, int dim)
    {
        g.setColor(new Color(120,120,120)); // les degres de trois couleurs yellow+red+green
        g.setColor(color);
        g.fillRect(coors.x*dim+1, coors.y*dim+1, dim-1, dim-1);// dessines les carreaux de la piece
        g.setColor(Color.white);
        g.drawRect(coors.x*dim+1, coors.y*dim+1, dim-2, dim-2);
        g.setColor(Color.black);
        g.drawRect(coors.x*dim+1, coors.y*dim+1, dim-1, dim-1);
    }
}
