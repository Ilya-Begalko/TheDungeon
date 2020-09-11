package com.company.enemies;


    public class Chest0 extends Enemy {
        public Chest0() {
            super();

            this.setName("Сундук 0");
            this.setHealth(3);
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

