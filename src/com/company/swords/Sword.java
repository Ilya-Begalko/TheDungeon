package com.company.swords;

/**
 * Оружие, которое наносит врагу увеличенное количество урона.
 */
public abstract class Sword
{

    /* поля экземпляров */
    protected  int damageIncrease;
    protected int hitpoints;
    protected  String name ;

    /**
     * Показывает Имя меча
     */
    public String getName()
    {
        return name;
    } // конец метода name()

    /**
     * показывает прочность меча.
     */
    public int getHitpoints()
    {
        return hitpoints;
    }

    public void setHitpoints(int hitpoints){
        this.hitpoints = hitpoints;
    }

    /**
     * показывает сколько урона добавляет меч
     */
    public int getDamageIncrease()
    {
        return damageIncrease;
    } // конец метода damageIncrease()

    public void setDamageIncreasse(int damageIncrease){
        this.damageIncrease = damageIncrease;
    }

    /**
     * Изменяет имя меча.
     */
    public void setName(String name)
    {
        this.name = name;
    }

    /**
     * Использованеи меча при атаке врагов, уменьшение его прочности
     */
    public void useSword()
    {
        hitpoints--;
    }

    /**
     * Восстанавливает прочность меча
     */
    public void repairSword(int hitpointsToRepair)
    {
        hitpoints = hitpoints + hitpointsToRepair;
    }
}
