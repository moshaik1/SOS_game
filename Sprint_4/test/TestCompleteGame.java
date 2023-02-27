package my_java_sos_project.Sprint_4.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import javax.swing.JButton;

import org.junit.jupiter.api.Test;
import org.junit.After;
import org.junit.Before;


import sprint3_1.product.TicTacToeGUI;
import sprint3_1.product.TicTacToeGame;
import sprint3_1.product.TicTacToeGame.GameState;
import my_java_sos_project.Sprint_4.product.Game;
import my_java_sos_project.Sprint_4.product.GUI;


class TestCompleteGame {

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
	public void testhasWon() {
		game.makeMove(3, 0);
		assertEquals("", game.getGameState(), game.hasWon(null) ); 
		new GUI(game); 
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testWon() {
		game.makeMove(0, 0);
		assertEquals("", game.getGameState(), game.hasWon(null)); 
		new GUI(game); 
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testDraw() {
		// game.hasWon(game.buttons);
		assertEquals("", game.getGameState(), game.isDraw()); 
		
		new GUI(game); 
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

}