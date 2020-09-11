package com.company.player;

import java.util.Random;

import com.company.swords.MetalSword;
import com.company.swords.SWORD_TYPE;
import com.company.swords.Sword;
import com.company.swords.SwordFactory;

import com.company.armour.ARMOUR_TYPE;
import com.company.armour.Armour;
import com.company.armour.ArmourFactory;

/**
 * Основная часть игры
 */
public class Player
{
    /* Поля класса */

    /** значени очков по умолчанию. */
    public static final int DEFAULT_NUMBER_OF_POTIONS = 3;

    /** Параметры делающий текст сообщений читаемым */
    public static final long DELAY = 1000;

    /** генератор случайных чисел */
    public static Random RANDOM = new Random();

    /** количество восстанавливаемого здоровья одним зельем */
    public static final int POTION_HEALING = 30;

    /** максимальное количество здоровя */
    public static final int FULL_HEALTH = 100;

    /** максимальный урон игрока */
    public static final int BASE_ATTACK_DAMAGE = 25;

    /** имя по умолчанию. */
    public static final String NO_NAME = "";

    /* Поля экземпляра */
    private Armour armour;
    private int attackDamage;
    private int enemiesKilled;
    private boolean hasArmour;
    private boolean hasSword;
    private int health;
    private Pouch pouch;
    private String name;
    private int potionsRemaining;
    private Sword sword;

    /**
     * Создание нового игрока.
     */
    public Player()
    {
        name = NO_NAME;
        hasArmour = false;
        armour = null;
        hasSword = false;
        health = FULL_HEALTH;
        potionsRemaining = DEFAULT_NUMBER_OF_POTIONS;
        enemiesKilled = 0;
        sword = null;
        pouch = new Pouch();
    }

    /* Дополнения */
    /**
     * Показывает Имя Игрока.
     */
    public String getName()
    {
        return name;
    }

    /**
     * Показывает здоровье игрока.
     */
    public int health()
    {
        return health;
    }

    /**
     * показывает количество убитых игроков.
     */
    public int enemiesKilled()
    {
        return enemiesKilled;
    }

    /**
     * Показывает количество очков Игрока.
     */
    public int getPotions()
    {
        return potionsRemaining;
    }

    /**
     * Показывает оружие игрока.
     */
    public Sword getSword()
    {
        return sword;
    }

    /**
     * Показывает броню игрока.
     */
    public Armour getArmour()
    {
        return armour;
    }

    /**
     * Показывает инвентарь игрока.
     */
    public Pouch getPouch()
    {
        return pouch;
    }

    /**
     * Проверяет есть ли у игрока оружие.
     */
    public boolean hasSword()
    {
        return sword != null;
    }

    /**
     * показывает есть ли у игрока броня.
     */
    public boolean hasArmour()
    {
        return armour != null;
    }

    /* Изменения */
    /**
     * Устанавливвает значение кличества уитых врагов.
     */
    public void setEnemiesKilled(int enemiesKilled)
    {
        this.enemiesKilled = enemiesKilled;
    }

    /**
     * устанавливает значение здоровья игрока.
     */
    public void setHealth(int healthPoints)
    {
        if (healthPoints > 0 && healthPoints <= FULL_HEALTH)
        {
            health = healthPoints;
        }
    }

    /**
     * Устанавливает Имя игрока.
     */
    public void setName(String name)
    {
        this.name = name;
    }

    /**
     * устанавливает количество зельев у игрока
     */
    
    public void setNumberOfPotions(int potions)
    {
        if (potions >= 0)
        {
            potionsRemaining = potions;
        }
    }

    /* Служебные методы*/

    /* Методы влияющие на здоровье игрока */
    /**
     * Возвращает значение нанесенного игроком урона
     */
    public int attack()
    {
        if (this.hasSword())
        {
            /* Если у игрока есть оружие, использовать его */
            sword.useSword();

            int damage = RANDOM.nextInt(BASE_ATTACK_DAMAGE) + sword.getDamageIncrease();

            /* проверка прочности оружия */
            if (sword.getHitpoints() <= 0)
            {
                /* Предупреждение, что меч сломан */
                System.out.println("\nТвой  " + sword.getName() + " Сломан.");
                this.sword = null;

                try
                {
                    Thread.sleep(DELAY);
                }
                catch (InterruptedException exception)
                {
                    System.out.println("Ошибка игры.");
                    System.out.println("Данные игры не сохранены");
                    System.out.println("Пожалуйста перезагрузите игру");
                    System.exit(0);
                }
                /* Меч сломан, его больше нельзя использовать */
            }

            /* Увеличение урона за счет урона оружия */
            return damage;
        }

        /* У игрока нет оружия, восстановление базового значения урона  */
        return RANDOM.nextInt(BASE_ATTACK_DAMAGE);
    }

    /**
     * Уменьшение здоровья на определенное значение
     */
    public void takeDamage(int damage)
    {
        if (this.hasArmour())
        {
            /* У игрока есть броня, использовать ее для уменьшения получаемого урона */
            armour.useArmour();

            /* Защита от урона броней */
            health = health - Math.max(damage - armour.damageBlocked(), 0);

            /* Проверка прочности брони */
            if (armour.hitpoints() <= 0)
            {
                /* Предупреждение, что броня сломалась */
                System.out.println("\nТвоя " + armour.name() + " Сломалась.");

                try
                {
                    Thread.sleep(DELAY);
                }
                catch (InterruptedException exception)
                {
                    System.out.println("Ошибка игры.");
                    System.out.println("Данные игры не сохранены");
                    System.out.println("Пожалуйста перезагрузите игру");
                    System.exit(0);
                }

                /* Броня сломана, Игрок больше не может ее использовать */
                armour = null;
            }
        }
        else
        {
            /* у Игрока больше нет брони, изменить значение наносимого урона по умолчанию */
            health = health - damage;
        }
    }

    /* Методы влияющие на зелья игрока */
    /**
     * Использование зелья.
     */
    public void usePotion()
    {
        /* Выход из функции если у игрока нет зельев  */
        if (potionsRemaining <= 0) return;

        /* Использование зелья для восстановления здоровья */
        health = health + POTION_HEALING;

        /* декрементация кол-ва зельев0 когда одно использовалось. */
        potionsRemaining--;
    }

    /**
     * Увеличивает количество зелий, которые есть у этого игрока, на определенное количество.
     */
    public void addPotions(int potions)
    {
        potionsRemaining = potionsRemaining + potions;
    }
    
    /**
     * Увеличивает количество убитых врагов
     */
    public void increaseEnemiesKilled()
    {
        enemiesKilled++;
    } 

    /* Методы влияющие на экипировку игрока */
    /**
     * Дает меч игроку
     */
    public void addSword(String type)
    {
        if (type == null) return;

        sword = SwordFactory.createSword(type);


    }

    /**
     * Дает броню игроку.
     */
    public void addArmour(String type)
    {
        if (type == null) return;

        armour = ArmourFactory.createArmour(type);

    }

    /**
     * Возвращает данные игры дл сохранения в текстовом формате.
     */
    public String getData()
    {
        return
        name + " "
        + this.hasSword() + " "
        + hasSword + " "
        + hasArmour() + " "
        + enemiesKilled + " "
        + health + " "
        + potionsRemaining + " "
        + pouch.getCoins();
    }

    /**
     * Сбрасывает состояние игрока.
     */
    public void reset()
    {
        health = FULL_HEALTH;
        potionsRemaining = DEFAULT_NUMBER_OF_POTIONS;
        enemiesKilled = 0;
        sword = null;
        hasArmour = false;
        hasSword = false;
    }
}
