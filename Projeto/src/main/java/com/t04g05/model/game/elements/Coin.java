package com.t04g05.model.game.elements;

import com.t04g05.model.Position;

public class Coin extends Element {
    public Coin(Position position) {
        super(position.getX(), position.getY());
    }
}