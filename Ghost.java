package Game;

import java.awt.Color;
import java.awt.Graphics; 
/*
 *  cette classe s'occupe de dessiner la fantome de la piece   !
 * 
 * */

public class Ghost extends Mino
{
    public Ghost(Mino mino)
    {
        super(mino, false);
        color = new Color (mino.color.getRed(), mino.color.getGreen(), mino.color.getBlue(),100);
    }
    
    @Override
    public void draw(Graphics g)   //dessiner la fantome de la piece qui tombe
    {
        for(int i=0; i<4; i++)
        {                     
            g.setColor(color);
            g.fillRect(GamePanel.dim*pCoors[i].x+1, GamePanel.dim*pCoors[i].y+1, GamePanel.dim-2,GamePanel.dim-2);  
            g.setColor(Color.black);
            g.drawRect(GamePanel.dim*pCoors[i].x+1, GamePanel.dim*pCoors[i].y+1, GamePanel.dim-3, GamePanel.dim-3);  
        }
    }
}
