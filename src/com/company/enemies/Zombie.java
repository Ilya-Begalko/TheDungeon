package com.company.enemies;

public class Zombie extends Enemy {

	public Zombie() {
		super();

		this.setName(this.getClass().getSimpleName());
		this.setHealth(RANDOM.nextInt(TIER3_MAXIMUM_HEALTH - TIER3_MINIMUM_HEALTH) + TIER3_MINIMUM_HEALTH);
	}

	@Override
	public int attack() {
		return RANDOM.nextInt(TIER3_MAXIMUM_ATTACK_DAMAGE);
	}

	@Override
	public void takeDamage(int damage) {
		this.setHealth(this.getHealth() - damage);
	}
}
