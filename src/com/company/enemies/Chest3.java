package com.company.enemies;

public class Chest3 extends Enemy {
    public Chest3() {
        super();

        this.setName("Сундук 3");
        this.setHealth(2);
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