package com.company.swords;

public class SwordFactory {

	/**
     * Создание меча с особыми характеристиками
     */
    public static Sword createSword(String type)
    {
        if (type=="metal") {

            Sword metalsword = new MetalSword();
            metalsword.hitpoints=15;
            metalsword.name="Metal Sword";
            metalsword.damageIncrease=10;
            return metalsword;
        }
        if (type == "gold") {
            Sword goldsword = new GoldSword();
            goldsword.hitpoints=20;
            goldsword.name="Gold Sword";
            goldsword.damageIncrease=15;
            return goldsword;
        }
        if (type == "GOD") {
            Sword godsword = new GodSword();
            godsword.hitpoints=100;
            godsword.name="Gods Amazing Unbelivable Sword";
            godsword.damageIncrease=150;
            return godsword;
        }
        else {
        Sword woodsword = new WoodSword();
            woodsword.hitpoints=10;
            woodsword.name="Wood Sword";
            woodsword.damageIncrease=5;
        return woodsword;
            }


    }

}
