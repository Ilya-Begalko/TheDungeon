package com.company.armour;

public class ArmourFactory {

	/**
     * Создает броню для игрока.
     */
    public static Armour createArmour(String type)
    {

        if(type == "gold" ) {
            Armour goldArmour = new GoldArmour();
            goldArmour.damageBlocked=10;
            goldArmour.hitpoints=20;
            goldArmour.name="gold armour";
            return goldArmour;
        }

        if(type == "iron") {
            Armour ironArmour = new IronArmour();
            ironArmour.damageBlocked = 5;
            ironArmour.hitpoints = 10;
            ironArmour.name = "iron armour";
            return ironArmour;
        }else{
            Armour leatherArmour = new LeatherArmour();
            leatherArmour.damageBlocked = 2;
            leatherArmour.hitpoints = 5;
            leatherArmour.name = "leather Armour";
            return leatherArmour;
        }

    }

}
