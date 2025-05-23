package com.t04g05;

import com.t04g05.controller.game.GameController;
import com.t04g05.controller.menu.MenuController;
import com.t04g05.gui.GUI;
import com.t04g05.gui.LanternaGUI;
import com.t04g05.model.menu.Menu;
import com.t04g05.states.GameState;
import com.t04g05.states.MenuState;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        GUI gui =null;
        try {
            gui = new LanternaGUI();
            GameState initialState = initializeMenu();
            GameController gameController = new GameController(initialState);
            gameController.process(gui);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (gui != null) {
                try {
                    gui.close();
                } catch (Exception e) {
                    System.err.println("Erro ao fechar a GUI: " + e.getMessage());
                }
            }
        }
    }
    private static GameState initializeMenu() {
        Menu menu = new Menu();
        MenuController controller = new MenuController(menu);
        return new MenuState(controller);
    }
}