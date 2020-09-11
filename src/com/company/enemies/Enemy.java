package com.company.enemies;

import java.util.Random;

public abstract class Enemy
{
    /** Массив возможных типов врагов. */
    public static final String[] ENEMY_NAMES = {
        // ЛВЛ 1
        "Летучая мышь",
        "Слайм",
        "Змея",
        "Паук",
            "Сундук 1",
        // ЛВЛ 2
        "Призрак",
        "Гоблин",
        "Скелет",
        "Оборотень",
            "Сундук 3",
        // ЛВЛ 3
        "Вампир",
        "Варвар",
        "Ведьма",
        "Зомби",
            "Сундук 0",
        // ЛВЛ 4
        "Дракон",
        "Великан",
            "Сундук 8"
    };

    /** Максимальный урон атаки этого врага. */
    public static final int TIER1_MAXIMUM_ATTACK_DAMAGE = 2;
    public static final int TIER2_MAXIMUM_ATTACK_DAMAGE = 8;
    public static final int TIER3_MAXIMUM_ATTACK_DAMAGE = 14;
    public static final int TIER4_MAXIMUM_ATTACK_DAMAGE = 20;

    /** Максимальное здоровье этого врага. */
    public static final int TIER1_MAXIMUM_HEALTH = 25;
    public static final int TIER2_MAXIMUM_HEALTH = 50;
    public static final int TIER3_MAXIMUM_HEALTH = 75;
    public static final int TIER4_MAXIMUM_HEALTH = 100;

    /** Минимальное здоровье этого врага. */
    public static final int TIER1_MINIMUM_HEALTH = 1;
    public static final int TIER2_MINIMUM_HEALTH = 25;
    public static final int TIER3_MINIMUM_HEALTH = 50;
    public static final int TIER4_MINIMUM_HEALTH = 75;

    /** Генератор случайных чисел этого врага. */
    public static final Random RANDOM = new Random();

    /* поля */
    protected int health;
    protected String name;
    protected String type;

    /**
     * Создание нового врага.
     */
    public Enemy()
    {
        /* Получить случайное имя из списка врагов. */
        name = ENEMY_NAMES[RANDOM.nextInt(ENEMY_NAMES.length)];

    }

    public void setHealth(int health) {
        this.health = health;
    }

    public void setName(String name) {
        this.name = name;
    }

    /**
     * Возвращает урон, нанесенный этим врагом.
     */
    public abstract int attack();
    
    /**
     * Уменьшает HP этого врага на указанное значение.
     */
    public abstract void takeDamage(int damage);


    /**
     * Имя этого врага.
     */
    public String getName()
    {
        return name;
    }

    /**
     * Возвращает здоровье этого врага.
     */
    public int getHealth()
    {
        return health;
    }

    /**
     * Возвращает тип этого врага.
     */
    public String getType()
    {
        return type;
    }

}
