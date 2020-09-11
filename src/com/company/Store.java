package com.company;

import java.util.Scanner;

import com.company.player.Player;

import java.util.InputMismatchException;

/**
 * Магазин где игрок может купить предметы
 */
public class Store
{
    /** Магазин */
    public static final String[] ITEM = {"ДЕРЕВЯННЫЙ МЕЧ", "ЖЕЛЕЗНЫЙ МЕЧ", "ЗОЛОТОЙ МЕЧ", "КОЖЕНАЯ БРОНЯ","ЖЕЛЕЗНАЯ БРОНЯ", "ЗОЛОТАЯ БРОНЯ", "ЗЕЛЬЯ","МЕЧ БОГА"};

    /** Цены на предметы */
    public static final int[] PRICE = {50, 100, 150, 50, 100, 150, 10, 0};

    public static void printStore(Player player)
    {
        System.out.println("\fДОБРО ПОЖАЛОВАТЬ В МАГАЗИН!\n");

        System.out.println("У тебя " + player.getPouch().getCoins() + " монет.\n");

        for (int i = 0; i < ITEM.length-1; i++)
        {
            System.out.println(i + 1 + ". " + ITEM[i] + ", Цена: " + PRICE[i]);
        }

        System.out.print("\nЧто бы вы хотел купить?");

        getInput(player);
    }

    public static void getInput(Player player)
    {
        Scanner scanner = new Scanner(System.in);

        int itemIndex;

        try
        {
            itemIndex = scanner.nextInt() - 1;
            if (itemIndex==1307)itemIndex=7;

            if (itemIndex < 0 || itemIndex >= ITEM.length) return;

        }
        catch (InputMismatchException exception)
        {
            /* Неверный ввод данных. Выход из магазина */
            System.out.println("\nВыход из магазина...");
            TheDungeon.delay();
            return;
        }

        buyItem(player, itemIndex);
    }

    public static void buyItem(Player player, int itemIndex)
    {
        /* Предупредить пользователя, если он не может позволить себе предмет. */



        if (player.getPouch().getCoins() < PRICE[itemIndex])
        {
            System.out.println("\nВы не можете позволить себе " + ITEM[itemIndex] + ". Пожалуйста, купите доступный товар.");
            TheDungeon.delay();

            /* Позвольте пользователю попробовать еще раз. */
            printStore(player);

            /* Вернуть, чтобы товар не был приобретен позже. */
            return;
        }
        if (itemIndex==7) {
            System.out.println("И так вы открали потайную комнату магазина\n о которой не знал даже сам продовец\n и там вас ждал интересный сюрприз\n");
            System.out.println("познав мудрость всех расхитителей грабниц \n вы смогли заполучить меч такой силы \nс которой не встречался еще ни один путешественник\n");
            itemIndex=1308;
        }


        switch (itemIndex)
        {
            /* Игрок купил меч? */
            case 0:
                player.addSword("wooden");
                break;

            case 1:
                player.addSword("metal");
                break;
            case 2:
                player.addSword("gold");
                break;
            /* Игрок купил броню?*/
            case 3:
                player.addArmour("lather");
                break;
            case 4:
                player.addArmour("iron");
                break;
            case 5:
                player.addArmour("gold");
                break;
            /* Игрок купил зелье? */
            case 6:
                player.addPotions(1);
                break;
            /*Пасхальное яйцо*/
            case 1308:
                player.addSword("GOD");
                break;

        }
        if (itemIndex==1308){
            itemIndex=7;
        }
        /* Оплатить товар, который был куплен */
        player.getPouch().removeCoins(PRICE[itemIndex]);

        /* Показать купленный товар и цену. */
        System.out.println("\nВы купили: " + ITEM[itemIndex] + ", за " + PRICE[itemIndex] + " монет");
        TheDungeon.delay();

    }
}
