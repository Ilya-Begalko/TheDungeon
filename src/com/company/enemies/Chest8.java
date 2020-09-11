package com.company.enemies;

public class Chest8 extends Enemy {
    public Chest8() {
        super();

        this.setName("Сундук 8");
        this.setHealth(4);
    }

    @Override
    public int attack() {
        return 0;
    }

    @Override
    public void takeDamage(int damage) {
        this.setHealth(this.getHealth() - damage);
    }
}
