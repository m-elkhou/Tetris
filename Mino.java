package Game;
/*
 cette clase s'occupe de la dessination des pieces et associer
 a chacune d'elles une couleur specifiee,ensuite met une interface 
 graphique au pieces  , et le mouvement des pieces a droit,a gauche,
 et faire une rotation de 90 degres
 */


import java.awt.Color;
import java.awt.Point; 
import java.awt.Graphics; 

public class Mino 
{
    Color color ; // il n'est pas declaree prive puisqu'il on a besion dans la classe Ghost 
    Point[] pCoors; 
    
    int id; 
    
    public Mino (Mino mino, boolean reset)
    {
        this.id = mino.id; 
        this.color = mino.color; 
        
        if (reset)
            this.pCoors = getStartCoors();
        else
        {
            this.pCoors = new Point[4];
            for(int i=0; i<4; i++)
            {
                pCoors[i] = new Point(mino.pCoors[i].x, mino.pCoors[i].y);
            }
        }
    }
    
    public Mino( int id )
    {   
        this.id = id; 
        
        pCoors = getStartCoors();//pCoors est un tableau de 4 points qui definient la forme des pieces de tetris
    }
    
    public Point[] getStartCoors()
    {
    	/* cette methiode s'occupe de creer  les piesces et associer a chaucunes un id specifique */
        pCoors = new Point[4];   // la forme de tetris est cree a l'aide de 4 ppoints
        
        switch (id) // creation des pieces de tetris et associer a chacune d'elles un identificateur et une couleur
        {
            case 1:   // piece O
            {
                color = Color.yellow;
                pCoors[0] = new Point(4, -1);
                pCoors[1] = new Point(5, -1);
                pCoors[2] = new Point(4, 0);
                pCoors[3] = new Point(5, 0);
                
                break;
            }
            case 2: // piece I
            {
                color = Color.cyan;
                pCoors[0] = new Point(3, 0);
                pCoors[1] = new Point(4, 0);
                pCoors[2] = new Point(5, 0);
                pCoors[3] = new Point(6, 0);
                
                break;
            }
            case 3://   T
            {
                color = Color.magenta;
                pCoors[0] = new Point(3, 0);
                pCoors[1] = new Point(4, 0);
                pCoors[2] = new Point(5, 0);
                pCoors[3] = new Point(4, -1);
                
                break;
            }
            case 4: // Z
            {
                color = Color.red;
                pCoors[0] = new Point(3, -1);
                pCoors[1] = new Point(4, -1);
                pCoors[2] = new Point(4, 0);
                pCoors[3] = new Point(5, 0);
                
                break;
            }
            case 5:  // L
            {
                color = Color.orange;
                pCoors[0] = new Point(3, 0);
                pCoors[1] = new Point(4, 0);
                pCoors[2] = new Point(5, 0);
                pCoors[3] = new Point(5, -1);
                
                break;
            }
            case 6:   // J
            {
                color = Color.blue;
                
                pCoors[0] = new Point(3, 0);
                pCoors[1] = new Point(4, 0);
                pCoors[2] = new Point(5, 0);
                pCoors[3] = new Point(3, -1);
                
                break;
            }
            case 7:    // S
            {
                color = Color.green;
                pCoors[0] = new Point(4, -1);
                pCoors[1] = new Point(5, -1);
                pCoors[2] = new Point(3, 0);
                pCoors[3] = new Point(4, 0);
                
                break;
            }
        }
        
        return pCoors; 
    }
    
    public void draw(Graphics g)        
    {
        for(int i=0; i<4; i++)
        {
            g.setColor(color); 
            g.fillRect(GamePanel.dim*pCoors[i].x, GamePanel.dim*pCoors[i].y, 
            		GamePanel.dim,             GamePanel.dim);
            
            g.setColor(Color.white);
            g.drawRect(GamePanel.dim*pCoors[i].x, GamePanel.dim*pCoors[i].y, //met la couleur entre les carreaux de la piece
            		GamePanel.dim-1,           GamePanel.dim-1);  
            
            g.setColor(Color.black);
            g.drawRect(GamePanel.dim*pCoors[i].x, GamePanel.dim*pCoors[i].y, // ........
            		GamePanel.dim,             GamePanel.dim);

        }
    }
    
   public void drawInQueue(Graphics g, int width, int height)// dessiner en file d'attente
    {
        int qDim   = GamePanel.dim/6*5; 
        int xOffset = (width - (qDim*3))/2;// adapter la position de la piece dans le carreau d'attente
        int yOffset = (height - (qDim * 2))/2;
        
        switch(id)
        {
            case 1:
            {
                xOffset += -1*qDim/2; 
                break;
            }
            case 2: 
            {
                xOffset += -1*qDim/2; 
                yOffset += -1*qDim/2;  
            }
        }
        
        for(int i=0; i<4; i++)
        {
            g.setColor(color); 
            g.fillRect(qDim*(pCoors[i].x-3)+xOffset, qDim*(pCoors[i].y+1)+yOffset, 
                       qDim,   
                       qDim);
            
            g.setColor(Color.white);
            g.drawRect(qDim*(pCoors[i].x-3)+xOffset, qDim*(pCoors[i].y+1)+yOffset, 
                       qDim-1,   
                       qDim-1);
            
            g.setColor(Color.black);
            g.drawRect(qDim*(pCoors[i].x-3)+xOffset, qDim*(pCoors[i].y+1)+yOffset, 
                       qDim,                                 
                       qDim);  
        }
    }
    
    public void move(Point shift)   // bouger la piece a droit ou a gauche
    {
         for(int i=0; i<4; i++)
         {
             pCoors[i].x+=shift.x; 
             pCoors[i].y+=shift.y; 
         }
    }
    
    public void rotate(int n)  // cette fonction s'occupe de rotationner la piece selon sa forme !!
    {   
        switch (id) 
        {
            case 1:
            {
                break;// pour la piece O pendant la rotation on change rien dans sa forme
            }

            case 2:// I
            {
                
                if (pCoors[0].y != pCoors[1].y)
                {
                    n *= -1;// n prends  2 valeurs (soit 1 soit -1) selon le sens de la rotation
                }
                pCoors[0].x += 1*n;      
                pCoors[0].y +=-1*n;
                pCoors[1].x += 0*n;
                pCoors[1].y += 0*n;
                pCoors[2].x +=-1*n; 
                pCoors[2].y += 1*n;
                pCoors[3].x +=-2*n;
                pCoors[3].y += 2*n;
                /* 
                 
                 * */
                
                break;
            }
            case 3:    // T
            {
                
                if (pCoors[0].y == pCoors[1].y && pCoors[1].y == pCoors[2].y)
                {
                    if (pCoors[1].y > pCoors[3].y)
                    {
                        pCoors[0].x += 1*n;
                        pCoors[0].y +=-1*n;
                        pCoors[1].x += 0;
                        pCoors[1].y += 0;
                        pCoors[2].x +=-1*n; 
                        pCoors[2].y += 1*n;
                        pCoors[3].x += 1*n;
                        pCoors[3].y += 1*n;
                    }
                    else
                    {
                        pCoors[0].x +=-1*n;
                        pCoors[0].y += 1*n;
                        pCoors[1].x += 0;
                        pCoors[1].y += 0;
                        pCoors[2].x += 1*n; 
                        pCoors[2].y +=-1*n;
                        pCoors[3].x +=-1*n;
                        pCoors[3].y +=-1*n;
                    }
                }
                else
                {
                    if (pCoors[1].x < pCoors[3].x)
                    {
                        pCoors[0].x += 1*n;
                        pCoors[0].y += 1*n;
                        pCoors[1].x += 0;
                        pCoors[1].y += 0;
                        pCoors[2].x +=-1*n; 
                        pCoors[2].y +=-1*n;
                        pCoors[3].x +=-1*n;
                        pCoors[3].y += 1*n;
                    }
                    else
                    {
                        pCoors[0].x +=-1*n;
                        pCoors[0].y +=-1*n;
                        pCoors[1].x += 0;
                        pCoors[1].y += 0;
                        pCoors[2].x += 1*n; 
                        pCoors[2].y += 1*n;
                        pCoors[3].x += 1*n;
                        pCoors[3].y +=-1*n;
                    }
                }
                break;
            }
            case 4:   // Z
            {   
                if (pCoors[0].y != pCoors[1].y)
                {
                    n *= -1;
                }
                pCoors[0].x += 2*n;
                pCoors[0].y +=-1*n;
                pCoors[1].x += 1*n;
                pCoors[1].y += 0*n;
                pCoors[2].x += 0*n; 
                pCoors[2].y +=-1*n;
                pCoors[3].x +=-1*n;
                pCoors[3].y += 0*n;
                
                break;
            }
            case 5: // L
            {
                if (pCoors[0].y == pCoors[2].y && pCoors[1].y == pCoors[2].y)
                {
                    if (pCoors[1].y > pCoors[3].y)
                    {
                        pCoors[0].x += 1*n;
                        pCoors[0].y +=-1*n;
                        pCoors[1].x += 0;
                        pCoors[1].y += 0;
                        pCoors[2].x +=-1*n; 
                        pCoors[2].y += 1*n;
                        pCoors[3].x += 0;
                        pCoors[3].y += 2*n;
                    }
                    else
                    {
                        pCoors[0].x +=-1*n;
                        pCoors[0].y += 1*n;
                        pCoors[1].x += 0;
                        pCoors[1].y += 0;
                        pCoors[2].x += 1*n; 
                        pCoors[2].y +=-1*n;
                        pCoors[3].x += 0;
                        pCoors[3].y +=-2*n;
                    }
                }
                else if (pCoors[0].x == pCoors[2].x && pCoors[1].x == pCoors[2].x)
                {
                    if (pCoors[1].x < pCoors[3].x)
                    {
                        pCoors[0].x += 1*n;
                        pCoors[0].y += 1*n;
                        pCoors[1].x += 0;
                        pCoors[1].y += 0;
                        pCoors[2].x +=-1*n; 
                        pCoors[2].y +=-1*n;
                        pCoors[3].x +=-2*n;
                        pCoors[3].y += 0;
                    }
                    else
                    {
                        pCoors[0].x +=-1*n;
                        pCoors[0].y +=-1*n;
                        pCoors[1].x += 0;
                        pCoors[1].y += 0;
                        pCoors[2].x += 1*n; 
                        pCoors[2].y += 1*n;
                        pCoors[3].x += 2*n;
                        pCoors[3].y += 0;
                    }
                }
                break;
            }
            case 6:    // J
            {           
                if (pCoors[0].y == pCoors[2].y && pCoors[1].y == pCoors[2].y)
                {
                    if (pCoors[1].y > pCoors[3].y)
                    {
                        pCoors[0].x += 1*n;
                        pCoors[0].y +=-1*n;
                        pCoors[1].x += 0;
                        pCoors[1].y += 0;
                        pCoors[2].x +=-1*n; 
                        pCoors[2].y += 1*n;
                        pCoors[3].x += 2*n;
                        pCoors[3].y += 0;
                    }
                    else
                    {
                        pCoors[0].x +=-1*n;
                        pCoors[0].y += 1*n;
                        pCoors[1].x += 0;
                        pCoors[1].y += 0;
                        pCoors[2].x += 1*n; 
                        pCoors[2].y +=-1*n;
                        pCoors[3].x +=-2*n;
                        pCoors[3].y += 0;
                    }
                }
                else if (pCoors[0].x == pCoors[2].x && pCoors[1].x == pCoors[2].x)
                {
                    if (pCoors[1].x < pCoors[3].x)
                    {
                        pCoors[0].x += 1*n;
                        pCoors[0].y += 1*n;
                        pCoors[1].x += 0;
                        pCoors[1].y += 0;
                        pCoors[2].x +=-1*n; 
                        pCoors[2].y +=-1*n;
                        pCoors[3].x += 0;
                        pCoors[3].y += 2*n;
                    }
                    else
                    {
                        pCoors[0].x +=-1*n;
                        pCoors[0].y +=-1*n;
                        pCoors[1].x += 0;
                        pCoors[1].y += 0;
                        pCoors[2].x += 1*n; 
                        pCoors[2].y += 1*n;
                        pCoors[3].x += 0;
                        pCoors[3].y +=-2*n;
                    }
                }
                break;
            }
            case 7:   // S
            {                
                if (pCoors[0].y != pCoors[1].y)
                {
                    n *= -1;
                }
                pCoors[0].x += 1*n;
                pCoors[0].y += 0*n;
                pCoors[1].x += 0*n;
                pCoors[1].y += 1*n;
                pCoors[2].x += 1*n; 
                pCoors[2].y +=-2*n;
                pCoors[3].x += 0*n;
                pCoors[3].y +=-1*n;
                
                break;
            }
        }
    }
    
    public void reverseRotate()
    {
        for(int i=0; i<getReverseRotationIndexById(id); i++)
        {
            rotate(1);
        }
    }
    
    private int getReverseRotationIndexById(int id) // ????????????
    {
        switch(id)
        {
            case 2: 
            case 4:
            case 7:
            {
                return 1; 
            }
            case 3:
            case 5:
            case 6:
            {
                return 3; 
            }
        }
        return 0;   // case 1 (O): return 0;
    }
}