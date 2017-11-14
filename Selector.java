import java.awt.*;
import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
public class Selector{
  
  private int x = 0;
  private int y = 0;
  private Boolean showDebug = false;
  private LevelEditor le;
  
  public Selector(LevelEditor le){
    this.le = le;
  }
  
  public void keyPressed(KeyEvent e) {
    if (e.getKeyCode() == KeyEvent.VK_SPACE){
    }
    if (e.getKeyCode() == KeyEvent.VK_A){
      if(x-1>=0)
        x--;
    }
    if (e.getKeyCode() == KeyEvent.VK_W){
      if(y-1>=0)
      y--;
    }
    if (e.getKeyCode() == KeyEvent.VK_S){
      if(y+1<LevelEditor.height)
      y++;
    }
    if (e.getKeyCode() == KeyEvent.VK_D){
      if(x+1<LevelEditor.width)
      x++;
    }
    if (e.getKeyCode() == KeyEvent.VK_Q){
      showDebug = true;
    }
    if (e.getKeyCode() == KeyEvent.VK_2&&le.getLoaded()==true){
      le.setLevelArray(y, x, 'x');
    }
    if (e.getKeyCode() == KeyEvent.VK_1&&le.getLoaded()==true){
      le.setLevelArray(y, x, ' ');
    }
     if (e.getKeyCode() == KeyEvent.VK_3&&le.getLoaded()==true){
      le.setLevelArray(y, x, 't');
    }
     if (e.getKeyCode() == KeyEvent.VK_4&&le.getLoaded()==true){
      le.setLevelArray(y, x, 'c');
    }
  }
  public void debug(Graphics2D g2d){
   g2d.drawString("x: " + x,50,50);
   g2d.drawString("y: " + y,50,60);
  }
  public void draw(Graphics2D g2d){
    g2d.setColor(Color.BLACK);
    g2d.drawRect( x*le.getTileSize(), y*le.getTileSize(),le.getTileSize(),le.getTileSize());
    if(showDebug == true)
      debug(g2d);
  }
}