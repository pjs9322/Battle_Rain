package display_Set;

import java.awt.Graphics;
import java.awt.Label;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.im.InputContext;

import javax.swing.JComponent;
import javax.swing.JTextField;
import javax.swing.KeyStroke;

import control.Control;

public class Playing extends display_Set {
	private static final long serialVersionUID = 1L;
	
	private JTextField texts = new JTextField(40);
	
	public Playing() {
		super();

		this.add(texts);
		this.texts.registerKeyboardAction(this, "insert_Word",
				KeyStroke.getKeyStroke(KeyEvent.VK_ENTER,0), JComponent.WHEN_FOCUSED);
		
	}

	public void init_View(Control control) {
		this.control = control;
		this.control.make_Word();
		
		for (Label word: this.control.getWord_List()) {
			this.add(word);
		}
	}
	
	@Override
	public void draw(Graphics g) {}

	@Override
	public void init_Parts() {
		this.texts.requestFocus();
		try {  
	           InputContext inCtx = texts.getInputContext();  
	           Character.Subset[] subset = { Character.UnicodeBlock.HANGUL_SYLLABLES };  
	            inCtx.setCharacterSubsets( subset );  
	        }catch (Exception x) { } 
	}
	
	public void insert_Word() {
		Label temp = control.insert_Word(texts.getText());
		if (temp != null) {
			this.remove(temp);
		}
		this.texts.setText(null);
	}
	
	class KeyHandler implements KeyListener {
		public void keyPressed(KeyEvent e) {
			if (e.getKeyCode() == KeyEvent.VK_ENTER) {
				insert_Word();
			}
		}
		public void keyReleased(KeyEvent e) {}
		public void keyTyped(KeyEvent e) {}
	}
}
