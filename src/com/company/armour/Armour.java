package com.company.armour;

/**
 * Блокирует заданное количество урона от атаки противника.
 */
public abstract class Armour
{

    /* интерфейс */
    protected String name;
    protected int hitpoints;
    protected int damageBlocked;

    /**
     * Возвращает значение блокированноко урона броней
     */
    public int damageBlocked()
    {
        return damageBlocked;
    } // конец метода  damageBlocked()

    /**
     * Возвращает значение оставщейся прочности брони.
     */
    public int hitpoints()
    {
        return hitpoints;
    }

    /**
     * Возвращает название брони.
     */
    public String name()
    {
        return name;
    } // конец метода name()

    /**
     * Меняет имя брони на специальное
     */
    public void setName(String name)
    {
        this.name = name;
    }

    /**
     * Использует эту броню при атаке противника. Расходует hitpoints.
     */
    public void useArmour()
    {
        hitpoints--;
    } 

    /**
     * Увеличивает hitpoints восстанавливает броню.
     */
    public void repairArmour(int hitpointsToRepair)
    {
        hitpoints = hitpoints + hitpointsToRepair;
    }
}
