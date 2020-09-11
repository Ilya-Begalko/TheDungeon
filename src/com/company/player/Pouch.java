package com.company.player;

/**
 * Собирает монетки в кошелек игрока.
 */
public class Pouch
{
    /* instance fields */
    private int coins;

    /**
     * создает начальное значение монет.
     */
    public Pouch()
    {
        coins = 0;
    } // конец конструктора Pouch()

    /**
     * возвращает значение кол-ва монет
     */
    public int getCoins()
    {
        return coins;
    } // конец метода getCoins()

    /**
     * Добавление монет в кошелек.
     */
    public void addCoins(int coins)
    {
    	if(coins > 0) {
    		this.coins += coins;
    	}
    }

    /**
     * Удаление монет из кошелька.
     */
    public void removeCoins(int coins)
    {
    	if(coins > 0) {
    		this.coins -= coins;
    		if(coins < 0) {
    			coins = 0;
    		}
    	}
    } // Конец метода removeCoins(int coins)

    /**
     * Показывает кол-во монет в кошельке
     */
    public void setCoins(int coins)
    {
    	if(coins >= 0) {
    		this.coins = coins;
    	}
    }
}
