
package Game;

import java.util.ArrayList;
import java.util.Random;

public class MinoQueue
{
    Mino heldMino; 
    ArrayList<Mino> minos; /* une liste chainee des pieces
                               qui vont entrer dans la file d'attente
                           */ 
     
    Random rand; // cette  classe est responsable de generer un nombre(l'indice) aleatoire
    
    public MinoQueue()
    {
        rand = new Random();
        
        heldMino = null; 
        
        minos = new ArrayList<Mino>(); 
        
        for( int i=0; i<5; i++ )
        {
            minos.add( new Mino( rand.nextInt(7)+1 ));
        }
    }
    
    public void shiftUp()
    {
        minos.add(new Mino( rand.nextInt(7)+1 ));// ajouter une nouvelle tetrimino  aleatoire
        minos.remove(0);  // defiler la liste en haut
    }
    
    public Mino newMino()
    {
        return minos.get(0);// prends la 1ere tetriminos qui existe dans la file d'attente
    }
    
    public void setHeld( Mino held )
    {
        heldMino = held; 
    }
    
    public Mino getHeld()
    {
        return heldMino; 
    }
}