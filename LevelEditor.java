//MOVE CURSOR WITH WASD
//LOAD WITH L
//NEW LEVEL WITH N
//UNLOAD WITH U
//SAVE WITH ENTER
//CHANGE TYPE OF TILE WITH THE NUMBER KEYS
//MORE FUNCTIONALITY COMING SOON

//testtesttest

import java.awt.*;
import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener; 
import java.io.*;

public class LevelEditor extends JPanel{
  private Color backGroundGreen = new Color(59,206,113);  
  private Selector selector = new Selector();
  private String fileName = "";
  private EditorInput input;
  //DUDE ENCAPSULATION LMAO
  public static int tileSize = 16;
  public static boolean loaded = false;
  public static char[][] levelArray = null;
  public static int width = 0;
  public static int height = 0;
  
  public LevelEditor(){
    addKeyListener(new KeyListener() {
      @Override
      public void keyTyped(KeyEvent e) {
      }
      @Override
      public void keyReleased(KeyEvent e) {
      }
      @Override
      public void keyPressed(KeyEvent e) {
        selector.keyPressed(e);
        if(e.getKeyCode() == KeyEvent.VK_N&&loaded==false){
          createLevel();
        }
        if(e.getKeyCode() == KeyEvent.VK_L&&loaded==false){
          loadLevel();
          loaded = true;
        }
        if(e.getKeyCode() == KeyEvent.VK_P)
          printArray();
        if(e.getKeyCode() == KeyEvent.VK_U)
          unload();
        if(e.getKeyCode() == KeyEvent.VK_ENTER)
          save();
      }
    });
    setFocusable(true); 
  } 
  
  @Override
  public void paint(Graphics g)  {
    Graphics2D g2d = (Graphics2D) g;
    g2d.setColor(backGroundGreen);
    g2d.fillRect(0,0,1920,1080);
    drawArray(g2d);
    selector.draw(g2d);
    
  }
  
  public static void main(String[] args) throws InterruptedException, IOException {
    
    JFrame frame = new JFrame("Editor");
    LevelEditor p = new LevelEditor();
    frame.add(p);
    frame.setSize(1000, 1000);
    frame.setVisible(true);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    
    while (true){
      p.repaint();
      Thread.sleep(10);
    }
  }
  
  public void createLevel(){
    InputStreamReader r = new InputStreamReader(System.in);
    BufferedReader br = new BufferedReader(r);
    System.out.println("Enter a file name, then level width, then level height");
    try{
      input = new EditorInput(this);
    }
    catch (Exception e){
      fileName = "";
    }
  }
  
  public void finishCreate(){
    fileName = input.getName();
    height = input.getHeight();
    width = input.getWidth();
    
    try{
      FileWriter fw = new FileWriter(fileName + ".txt");
    } catch (Exception e){
      System.out.println("literally how did this even happen");
    }
    levelArray = new char[height][width];
    loaded = true;
  }
  
  //I really do appologize for what you're about to see
  //...
  //i am very tired
  //...
  //i am suffering
  //..
  //OK HERE WE GO HAVE FUN
  public void loadLevel(){
    //get the file you're going to load
    InputStreamReader r = new InputStreamReader(System.in);
    BufferedReader br = new BufferedReader(r);
    
    System.out.println("file to load??");
    
    try{
      fileName = br.readLine();
    } 
    catch (Exception e){
      fileName = "";
    }
    //load the file
    try{
      FileReader fr = new FileReader(fileName + ".txt");
      BufferedReader br2 = new BufferedReader(fr);
      String line = "";
      int tempHeight = 0;
      //gets the height and width (i hope o lord do i hope)
      while ((line=br2.readLine()) != null){
        //im just assuming that all the lines are the same length LMAO ITS ELEVEN O'CLOCK I REALLY HOPE THIS WORKS
        width = line.length(); 
        tempHeight++;
      }
      height = tempHeight;
    } catch (Exception e){
      System.out.println("LEVEL PROBABLY CANT BE LOADED L M A O");
    }
    levelArray = new char[height][width];
    //sigh.........
    try{
      
      FileReader fr2 = new FileReader(fileName + ".txt");
      BufferedReader br3 = new BufferedReader(fr2);
      int currentLine = 0;
      //o lord another one im so tired i cant even think must continue at all costs
      String line ="";
      while ((line=br3.readLine()) != null){
        for(int i = 0; i<= width-1; i++){
          
          levelArray[currentLine][i] = line.charAt(0);
          
          line = line.substring(1);
          
        }
        currentLine++;
      }
    } catch(Exception e){
      System.out.println("dear god i dont even remember what im doing im just on autopilot here hope it works");
    }
  }
  
  public void printArray(){
    for(int i = 0; i <= height-1; i++){
      for(int j = 0; j <= width-1; j++){
        System.out.print(levelArray[i][j]);
      }
      System.out.println();
    }
  }
  
  public void drawArray(Graphics2D g2d){
    for(int i = 0; i <= height-1; i++){
      for(int j = 0; j <= width-1; j++){
        if(levelArray[i][j] == 'x'){
          g2d.setColor(Color.RED);
          g2d.fillRect( j*LevelEditor.tileSize, i*LevelEditor.tileSize,LevelEditor.tileSize,LevelEditor.tileSize);
        }
      }
    }
  }
  public void unload(){
    width = 0;
    height = 0;
    levelArray = null;
    loaded = false;
    fileName = "";
  }
  public void save(){
    try{
  FileWriter fw = new FileWriter(fileName + ".txt");
  PrintWriter pw = new PrintWriter(fw);
    String currentLine = "";
   for(int i = 0; i <= height-1; i++){
     for(int j = 0; j <= width-1; j++){
    currentLine = currentLine + levelArray[i][j];
     }
     pw.println(currentLine);
     currentLine = "";
   }
   pw.close();
    } catch(IOException e){
    System.out.println("Save Failed");
    }
  }
}