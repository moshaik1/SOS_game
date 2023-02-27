package my_java_sos_project.Sprint_4.test;

import static org.junit.jupiter.api.Assertions.*;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import javax.swing.JButton;

import org.junit.jupiter.api.Test;
import org.junit.After;
import org.junit.Before;

import my_java_sos_project.Sprint_4.product.Game;
import my_java_sos_project.Sprint_4.product.GUI;

class TestGameGUI {
private Game game;
private GUI board;
	
	@Before
	public void setUp() throws Exception {
		game = new Game();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testEmptyBoard() {	
		new GUI(game); 
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void test() {
		game.makeMove(0,1);	
		new GUI(game); 
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}