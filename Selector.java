import java.awt.*;
import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
public class Selector{
  
  private int x = 0;
  private int y = 0;
  private Boolean showDebug = false;
  
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
    if (e.getKeyCode() == KeyEvent.VK_1&&LevelEditor.loaded==true){
      LevelEditor.levelArray[y][x] = 'x';
    }
    if (e.getKeyCode() == KeyEvent.VK_2&&LevelEditor.loaded==true){
      LevelEditor.levelArray[y][x] = 'o';
    }
  }
  public void debug(Graphics2D g2d){
   g2d.drawString("x: " + x,50,50);
   g2d.drawString("y: " + y,50,60);
  }
  public void draw(Graphics2D g2d){
    g2d.setColor(Color.BLACK);
    g2d.drawRect( x*LevelEditor.tileSize, y*LevelEditor.tileSize,LevelEditor.tileSize,LevelEditor.tileSize);
    if(showDebug == true)
      debug(g2d);
  }
}