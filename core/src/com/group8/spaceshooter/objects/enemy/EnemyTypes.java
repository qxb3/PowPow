package com.group8.spaceshooter.objects.enemy;

public enum EnemyTypes {
    CANDY(1, "enemies/candy.png", 4, 1),
    BACTERIA(2, "enemies/bacteria.png", 6, 1),
    SHELL(5, "enemies/shell.png", 5, 1);

    final int health;
    final String filename;
    final int animationCols;
    final int animationRows;

    EnemyTypes(int health, String filename, int animationCols, int animationRows) {
        this.health = health;
        this.filename = filename;
        this.animationCols = animationCols;
        this.animationRows = animationRows;
    }
}
