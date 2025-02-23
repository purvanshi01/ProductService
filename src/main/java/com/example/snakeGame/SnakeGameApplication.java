package com.example.snakeGame;

import com.example.snakeGame.exceptions.InvalidInputException;

public class SnakeGameApplication {
    public static void main(String[] args) {
        int[][] food = {
                {1, 2},
                {0, 1}
        };
        SnakeGame snakeGame = new SnakeGame(3, 2, food);
        try {
            System.out.println(snakeGame.move("R"));
            System.out.println(snakeGame.move("D"));
            System.out.println(snakeGame.move("R"));
            System.out.println(snakeGame.move("U"));
            System.out.println(snakeGame.move("L"));
            System.out.println(snakeGame.move("U"));
        } catch (InvalidInputException exception) {
            System.out.println(exception.getMessage());
        }
    }
}
