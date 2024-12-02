package com.example.yeoulk_comm228lab5;

public class Game {
    private int gameId;
    private String gameTitle;


    public Game(int gameId, String gameTitle) {
        this.gameId = gameId;
        this.gameTitle = gameTitle;
    }

    // Getter 및 Setter 메서드
    public int getGameId() {
        return gameId;
    }

    public void setGameId(int gameId) {
        this.gameId = gameId;
    }

    public String getGameTitle() {
        return gameTitle;
    }

    public void setGameTitle(String gameTitle) {
        this.gameTitle = gameTitle;
    }

    @Override
    public String toString() {
        return "Game ID: " + gameId + ", Game Title: " + gameTitle;
    }
}
