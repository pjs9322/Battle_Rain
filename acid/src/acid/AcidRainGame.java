package acid;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Vector;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
public class AcidRainGame extends JFrame implements ActionListener, Runnable {
 static final int WIDTH = 400;
 static final int HEIGHT = 400;
 int life = 10;
 Vector<String> words;
 Vector<Word> viewingWords;
 BufferedReader inputStream;
 Thread t;
 long time;
 
 DropArea da1;
 JTextField t1;
 
 public AcidRainGame() throws IOException {
  // 단어 목록 읽기
  setTitle("산성비");
  setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  try {
   inputStream = new BufferedReader(new FileReader("words.txt"));
  } catch (FileNotFoundException e) {
   // TODO Auto-generated catch block
   e.printStackTrace();
  }
  words = new Vector<String>();
  viewingWords = new Vector<Word>();
  String w;
  while((w = inputStream.readLine()) != null)
  {
   words.add(w);
  }
  da1 = new DropArea();
  da1.setPreferredSize(new Dimension(WIDTH, HEIGHT));
  add(da1, BorderLayout.CENTER);
  t1 = new JTextField(20);
  t1.addActionListener(this);
  add(t1, BorderLayout.SOUTH);
  
  t = new Thread(this);
  
  pack();
  setVisible(true);
  t.start();
 }
  
 @Override
 public void actionPerformed(ActionEvent e) {
  // TODO Auto-generated method stub
  int index = -1;
  for(Word w : viewingWords) {
   if(t1.getText().equals(w.str))
   {
    index = viewingWords.indexOf(w);
   }
  }
  if(index != -1)
  {
   viewingWords.remove(index);
   repaint();
  }
  t1.selectAll();
 }
 @Override
 public void run() {
  // TODO Auto-generated method stub
  while(true)
  {
   try {
    t.sleep(100);
   } catch (InterruptedException e) {
    // TODO Auto-generated catch block
    e.printStackTrace();
   }
   time++;
   
   for(Word w : viewingWords)
   {
    w.y += 10;
   } 
   if(!viewingWords.isEmpty())
   {
    if(viewingWords.get(0).y > HEIGHT-50)
    {
     life--;
     viewingWords.remove(0);
    }
   }
   
   if(life <= 0)
   {
    JOptionPane.showMessageDialog(this, "게임종료(게임시간:" + time/10.0 + "초)");
    t.stop();
   }
   
   if(time % 50 == 0)
   {
    viewingWords.add(new Word());
   }
   
   repaint();
   
  }
  
 }
 
 class DropArea extends JComponent
 {
  public void paint(Graphics g)
  {
   g.drawString("life="+life, 10, 10);
   for(Word w: viewingWords)
   {
    g.setColor(Color.BLACK);
    g.drawString(w.str, w.x, w.y);
   }
  }
 }
 
 class Word
 {
  public int x;
  public int y;
  String str;
  
  Word()
  {
   x = (int) (Math.random() * WIDTH - 40);
   y = 0;
   
   str = words.get((int)(Math.random() * words.size()));
  }
 }
 public static void main(String[] args) throws IOException {
  // TODO Auto-generated method stub
  new AcidRainGame();
 }
}