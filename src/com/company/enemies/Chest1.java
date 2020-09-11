package com.company.enemies;

public class Chest1 extends Enemy {
    public Chest1() {
        super();

        this.setName("Сундук 1");
        this.setHealth(1);
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
