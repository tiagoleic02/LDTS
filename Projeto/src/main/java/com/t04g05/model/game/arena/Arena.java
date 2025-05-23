package com.t04g05.model.game.arena;

import com.t04g05.model.Position;
import com.t04g05.model.game.elements.*;
import com.t04g05.model.game.elements.Character;

import java.util.*;

public abstract class Arena {
    private final int width;
    private final int height;
    private final Set<Walls> walls;
    protected Character character;
    private final List<Enemy> enemies;
    private final Set<Position> goalPositions;
    protected Position doorPosition;
    private final List<Coin> coins;

    public Arena(int width, int height, Character character, List<Enemy> enemies) {
        this.width = width;
        this.height = height;
        this.walls = new HashSet<>();
        this.character = character;
        this.enemies = (enemies != null) ? enemies : new ArrayList<>();
        this.coins = new ArrayList<>();
        this.goalPositions = new HashSet<>();
        createOutsideWalls();
    }
    public List<Coin> getCoins() {
        return coins;
    }

    public void checkCoinCollision() {
        Iterator<Coin> iterator = coins.iterator();
        while (iterator.hasNext()) {
            Coin coin = iterator.next();
            if (coin.getPosition().equals(character.getPosition())) {
                iterator.remove();
                character.increaseScore();
            }
        }
    }

    public void updateCharacter(Position newPosition) {
        if (canMoveTo(newPosition)) {
            character.setPosition(newPosition);
            checkCoinCollision();
        }
    }

    private void createOutsideWalls() {
        for (int x = 0; x < width; x++) {
            walls.add(new Walls(new Position(x, 3)));
            walls.add(new Walls(new Position(x, height - 1)));
        }
        for (int y = 4; y < height - 1; y++) {
            walls.add(new Walls(new Position(0, y)));
            walls.add(new Walls(new Position(width - 1, y)));
        }
    }
    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Character getCharacter() {
        return character;
    }

    public List<Enemy> getEnemies() {
        return enemies;
    }

    public Set<Walls> getWalls() {
        return walls;
    }

    public void checkCollisions() {
        for (Enemy enemy : enemies) {
            if (enemy.getPosition().equals(character.getPosition())) {
                character.decreaseLives();
                System.out.println("Colisão com inimigo! Vidas restantes: " + character.getLives());
                if (character.getLives() <= 0) {
                    System.out.println("Game Over! O personagem ficou sem vidas.");
                }
                break;
            }
        }
    }

    public boolean canMoveTo(Position position) {
        if (position.getX() < 0 || position.getX() >= width || position.getY() < 0 || position.getY() >= height) {
            return false;
        }
        if (walls.stream().anyMatch(wall -> wall.getPosition().equals(position))) {
            return false;
        }
        return enemies.stream().noneMatch(enemy -> enemy.getPosition().equals(position));
    }

    public List<Element> getElements() {
        List<Element> elements = new ArrayList<>();
        elements.addAll(walls);
        elements.addAll(enemies);
        elements.add(character);
        elements.addAll(coins);
        return elements;
    }

    public abstract void initializeElements();

    public void synchronizeWalls(Set<Walls> additionalWalls) {
        this.walls.addAll(additionalWalls);
    }

    public Position getDoorPosition() {return doorPosition;}

    public void setGoalPositions(int startX, int endX, int startY, int endY) {
        for (int x = startX; x <= endX; x++) {
            for (int y = startY; y <= endY; y++) {
                goalPositions.add(new Position(x, y));
            }
        }
    }

    public Set<Position> getGoalPositions() {
        return goalPositions;
    }
}
