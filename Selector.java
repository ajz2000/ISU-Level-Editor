import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.*;
public class Selector{
  
  private int x = 0;
  private int y = 0;
  private int mouseY;
  private int mouseX;
  private Boolean showDebug = false;
  private LevelEditor le;
  private char tileType = ' ';
  
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
      if(y+1<le.getHeight())
      y++;
    }
    if (e.getKeyCode() == KeyEvent.VK_D){
      if(x+1<le.getWidth())
      x++;
    }
    if (e.getKeyCode() == KeyEvent.VK_Q){
      showDebug = true;
    }
    if (e.getKeyCode() == KeyEvent.VK_2&&le.getLoaded()==true){
      le.setLevelArray(y, x, 'x');
      tileType = 'x';
    }
    if (e.getKeyCode() == KeyEvent.VK_1&&le.getLoaded()==true){
      le.setLevelArray(y, x, ' ');
      tileType = ' ';
    }
     if (e.getKeyCode() == KeyEvent.VK_3&&le.getLoaded()==true){
      le.setLevelArray(y, x, 't');
      tileType = 't';
    }
     if (e.getKeyCode() == KeyEvent.VK_4&&le.getLoaded()==true){
      le.setLevelArray(y, x, 'c');
      tileType = 'c';
    }
  }
  public void debug(Graphics2D g2d){
   g2d.drawString("x: " + x,50,50);
   g2d.drawString("y: " + y,50,60);
  }
  public void draw(Graphics2D g2d){
    g2d.setColor(Color.BLACK);
    g2d.drawRect( x*le.getTileSize(), y*le.getTileSize(), le.getTileSize(),le.getTileSize());
    if(showDebug == true)
      debug(g2d);
  }
  
  private int[] collisionWithTile(){
    mouseX = ((int)(MouseInfo.getPointerInfo().getLocation().getX())) - ((int)(le.getLocationOnScreen().getX()));
    mouseY = (int)(MouseInfo.getPointerInfo().getLocation().getY()) - ((int)(le.getLocationOnScreen().getY()));
    int tileX = 0;
    int tileY = 0;
    
    for(int i = 0; i < le.getHeight(); i++)
    {
      for(int j = 0; j < le.getWidth(); j++)
      {
        tileX = j * le.getTileSize();
        tileY = i * le.getTileSize();
        if(mouseX > tileX && mouseX < tileX + le.getTileSize() && mouseY > tileY + le.getTileSize() && mouseY < tileY){
          int[] k = new int[] {j, i};
          return k;
        }
      }
    }
    
    int[] i = new int[] {-1, -1};
    return i;
  }
  
  public void mousePressed(MouseEvent e) {
    int[] i = new int[] {-1, -1};
    
    while(le.getMouseHeld())
    {
      int[] colTile = collisionWithTile();
      if(!(Arrays.equals(colTile, i)))
      {
        le.setLevelArray(colTile[0], colTile[1], tileType);
      }
    }
  }
}