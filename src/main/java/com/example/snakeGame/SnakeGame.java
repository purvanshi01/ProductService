package com.example.snakeGame;

import java.util.Stack;

import com.example.snakeGame.exceptions.InvalidInputException;

public class SnakeGame {
    int width;
    int height;
    int[][] food;
    int lengthOfTheSnake = 1;
    Stack<int[]> snake;
    int[] head;
    int count = 0;
    int score = 0;
    boolean[][] snakeBody;

    public SnakeGame(int width, int height, int[][] food) {
        // Initialize the game
        this.width = width;
        this.height = height;
        this.food = food;
        head = new int[2];
        snake = new Stack<>();
        snake.add(new int[]{0, 0});
        snakeBody = new boolean[height][width];
    }

    public int move(String direction) throws InvalidInputException {
        // Move the snake
        int[] newHead = getNewHead(direction);


        if (!isValidPosition(newHead)) {
            throw new InvalidInputException("Invalid position");
        }

        snake.add(new int[]{newHead[0], newHead[1]});

        if (count < food.length && newHead[0] == food[count][0] && newHead[1] == food[count][1]) {
            lengthOfTheSnake++;
            Stack<int[]> newSnake = new Stack<>();
            for (int i = 0; i < lengthOfTheSnake && !snake.isEmpty(); i++) {
                int[] snakePositions = snake.pop();
                snakeBody[snakePositions[0]][snakePositions[1]] = true;
                newSnake.add(new int[]{snakePositions[0], snakePositions[1]});
            }
            snake.clear();
            while (!newSnake.isEmpty()) {
                snake.push(newSnake.pop()); // to ensure head is always at the top
            }
            score++;
            count++;
        }

        head[0] = snake.peek()[0];
        head[1] = snake.peek()[1];
        return score;
    }

    private int[] getNewHead(String direction) {
        int[] newHead = new int[2];
        switch (direction) {
            case "U" -> {
                newHead[0] = head[0] - 1;
                newHead[1] = head[1];
            }
            case "D" -> {
                newHead[0] = head[0] + 1;
                newHead[1] = head[1];
            }
            case "L" -> {
                newHead[0] = head[0];
                newHead[1] = head[1] - 1;
            }
            case "R" -> {
                newHead[0] = head[0];
                newHead[1] = head[1] + 1;
            }
        }
        return newHead;
    }

    private boolean isValidPosition(int[] newHead) {
        return newHead[0] >= 0 && newHead[1] >= 0 && newHead[0] < height && newHead[1] < width && !snakeBody[newHead[0]][newHead[1]];
    }
}
