package com.group8.spaceshooter.objects.enemy;

public enum EnemyTypes {
    CANDY(1, "enemies/candy.png", 4, 1),
    BACTERIA(2, "enemies/bacteria.png", 6, 1),
    SHELL(5, "enemies/shell.png", 5, 1);

    private final int health;
    private final String filename;
    private final int animationCols;
    private final int animationRows;

    EnemyTypes(int health, String filename, int animationCols, int animationRows) {
        this.health = health;
        this.filename = filename;
        this.animationCols = animationCols;
        this.animationRows = animationRows;
    }

    public int getHealth() { return this.health; }
    public String getFilename() { return this.filename; }
    public int getAnimationCols() { return this.animationCols; }
    public int getAnimationRows() { return this.animationRows; }
}
