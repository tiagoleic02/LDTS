package com.t04g05.gui;

import com.t04g05.model.Position;

import java.io.IOException;

public interface GUI {
    void setBackgroundColor(String color);
    void drawText(int x, int y, String text, String color, String background_color);
    void drawElement(Position position, char character, String textColor, String backgroundColor);
    void clear();
    void refresh() throws IOException;
    ACTION getNextAction();
    void close() throws IOException;
    void drawCharacter(Position position) throws IOException;
    void drawWall(Position position);
    void drawEnemy(Position position);
    void drawCoin(Position position) throws IOException;
    void drawDoor(Position position) throws IOException;
    void drawGoldenDoor(Position position) throws IOException;
    void drawScoreLives (int score, int lives);
    enum ACTION {
        UP, DOWN, LEFT, RIGHT, ENTER, ESC, NONE, QUIT
    }
}