package com.company;

import java.util.Scanner;

import com.company.enemies.*;
import com.company.player.Player;
import com.company.player.Pouch;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import java.io.FileNotFoundException;
import java.io.IOException;

public class TheDungeon
{
    /** Пункт меню для атаки*/
    public static final int ATTACK = 1;

    /** Задержка вывода сообщения. */
    public static final long DELAY = 2000;

    /** Пункт меню "ВЫХОД" */
    public static final int EXIT = 5;

    /** Задержка для сообщения */
    public static final int MAXIMUM_GOLD_DROP = 30;

    /** Монеты/здоровье штраф за побег,  */
    public static final int PENALTY_FOR_RUNNING = 5;

    /** Генератор случайных чисел для игры */
    public static final Random RANDOM = new Random();

    /** Пункт меню побега. */
    public static final int RUN = 3;

    /** Пункт меню мазазина */
    public static final int VISIT_STORE = 4;

    /** Считывание входных данных. */
    public static final Scanner SCANNER = new Scanner(System.in);

    /** Пункт меню для использования зелья */
    public static final int USE_POTION = 2;

    /** Пункт меню неопределен */
    public static final int UNDEFINED = 6;
    private static final int HELP = 0;

    /** The dungeon */
    
    public static void main(String[] argument)
    {
        // Главный герой
        Player player = new Player();
        Pouch pouch = player.getPouch();

        // Игровые переменные
        /* Проценты: */
        int armourDropChance = 10;
        int healthPotionDropChance = 50;
        int swordDropChance = 10;
        boolean running = true;
        boolean ranAway = false;

        // Введение в игровой процесс
        clear();
        System.out.println("\fПРЕВЕТСТВУЕМ В 'THE DUNGEON' ");

        System.out.print("Хотите загрузить предыдущую игру? ");
        String loadGameState = SCANNER.nextLine();

        if (CONFIRMATION.isConfirmation(loadGameState))
        {
            System.out.print("\nКак тебя зовут? ");
            String name = SCANNER.nextLine();

            /* Игнорировать пропуск ввода имени */
            name = name.replaceAll("\\s+","");
            player.setName(name);

            /* Поиск имени пользователя в безе данных */
            try
            {
                State.loadState(player);
            }
            catch (FileNotFoundException exception)
            {
                System.out.println("\nВаша сохраненная игра не была найдена. Запуск новой несохраненной игры.");
                delay();
            }
            catch (IOException exception)
            {
                System.out.println("Ввод с клавиатуры не может быть прочитан. Пожалуйста, перезапустите игру.");
            }
        }

        // Игровой цикл
        while (running)
        {
            // Главный враг
            Enemy villain = null;
            List<Class<? extends Enemy>> list = Arrays.asList(
                // ЛВЛ 1
                Bat.class,
                Slime.class,
                Snake.class,
                Spider.class,
                Chest1.class,
                // ЛВЛ 2
                Ghost.class,
                Goblin.class,
                Skeleton.class,
                Werewolf.class,
                Chest3.class,
                // ЛВЛ 3
                Vampire.class,
                Warrior.class,
                Witch.class,
                Zombie.class,
                Chest0.class,
                // ЛВЛ 4
                Dragon.class,
                Giant.class,
                    Chest8.class
            );

            try {
                villain = list.get(new Random().nextInt(list.size())).newInstance();
            } catch (Exception e) {
            }

            while (villain.getHealth() > 0)
            {
                printStatistics(player, villain);

                startBattle();

                int choice;

                try
                {
                    choice = Integer.parseInt(SCANNER.nextLine());
                }
                catch (NumberFormatException exception)
                {
                    System.out.println("Неверная команда, пожалуйста, попробуйте еще раз.");
                    continue;
                }
                
                switch (choice)
                {
                    case ATTACK:
                        ranAway = false;
                        int playerAttack = player.attack();
                        int enemyAttack = villain.attack();

                        System.out.println("\nВы нанесли " + playerAttack + " урона.");
                        System.out.println("Вы получили " + enemyAttack + " урона.");

                        villain.takeDamage(playerAttack);
                        player.takeDamage(enemyAttack);

                        delay();
                        break;

                    case USE_POTION:
                        if (player.health() > player.FULL_HEALTH - player.POTION_HEALING)
                        {
                            System.out.println("\nВы здоровы, и вам не нужно зелье!");
                            TheDungeon.delay();
                            break;
                        }

                        if(player.getPotions() > 0)
                        {
                            player.usePotion();

                            System.out.println("\nВы выпили зелье. Здоровье восстановлено:" + Player.POTION_HEALING + " HP");
                            System.out.println("Текущее HP: " + player.health());
                        }
                        else
                        {
                            System.out.println("У вас нет зелий.");
                            System.out.println("Текущее HP: " + player.health());
                        }

                        delay();
                        break;

                    case RUN:

                        /* Штраф игрока, забрав(обокрав) его монеты или здоровье */
                        if (player.getPouch().getCoins() > PENALTY_FOR_RUNNING)
                        {
                            System.out.println("\n" + PENALTY_FOR_RUNNING + " монеты были украдены " + villain.getName());
                            pouch.removeCoins(PENALTY_FOR_RUNNING);
                        }
                        /* Если у игрока не хватает монет на штраф, отнимаем здоровье */
                        else
                        {
                            System.out.println("\nВраг нанес " + PENALTY_FOR_RUNNING + " урона, прежде чем вам удалось сбежать!");
                            player.takeDamage(PENALTY_FOR_RUNNING);
                        }

                        System.out.println("\nВы успешно сбежали!");
                        delay();

                        /* Смерть врага при наносении ему урона, равному его здоровью. */
                        villain.takeDamage(villain.getHealth());

                        ranAway = true;
                        break;

                    case VISIT_STORE:
                        /* Асортимент магазина*/
                        clear();
                        Store.printStore(player);
                        break;

                    case HELP:
                        clear();
                        System.out.println("\fСоветы игрокам:");
                        System.out.println("1)В игре 3 типа брони и 3 типа мечей");
                        System.out.println("2)Зелье восстанавливает 30 HP");
                        System.out.println("3)Из мобов выпадают монеты, а так же, если вам повезет, зелья, мечи и броня");
                        System.out.println("4)В магазине вы можете приобрести все необходимое для боя");
                        System.out.println("5)Вы автоматически побеждаете, когда убиваете 50 мобов");
                        System.out.println("6)Если моб для вас слишком сильный, попробуйте сбежать и усилиться");
                        System.out.println("7)В игре есть скрытое оружие");
                        System.out.println("\n");
                        System.out.println("$$ ПРИЯТНОЙ ИГРЫ $$");
                        System.out.println("\n");
                        break;

                    case EXIT:
                        clear();
                        System.out.println("\fВыход из игры...");
                        System.out.print("Хотите сохранить свой прогресс?");

                        if (CONFIRMATION.isConfirmation(SCANNER.nextLine()))
                        {
                            State.saveState(player);
                        }

                        running = false;
                        return;
                    case UNDEFINED:
                        System.out.println("Пожалуйста, выберите один из пунктов меню");
                        delay();
                        break;
                }

                if (player.health() <= 0)
                {
                    System.out.println("\nОй! Вы умерли, игра окончена.");

                    System.out.print("Вы хотели бы возродиться?");
                    String continueGame = SCANNER.nextLine();

                    if (CONFIRMATION.isConfirmation(continueGame))
                    {
                        running = true;
                        player.reset();
                    }
                    else
                    {
                        System.out.println("\nПрограмма прекращена.");

                        /* Убейте врага, нанося урон, равный его здоровью. */
                        villain.takeDamage(villain.getHealth());
                        running = false;
                        return;
                    }
                }
            }

            if (!ranAway)
            {
                /* Враг умер, и игрок не убежал. Это означает, что игрок убил врага. Награда игрока. */
                player.increaseEnemiesKilled();

                if(villain.getName()=="Сундук 1"){
                    pouch.addCoins(25);
                    System.out.println("Итак ты окрываешь сундук и что же там...\n");
                    System.out.println("25 золотых монет\n");
                    System.out.println(" Странный клочек бумаги :\n---\n|1 \n---\n");
                    System.out.println(" И выссказывание великих расхитителей гробниц номер 322: \n\" Самостоятельность не означает одиночество. Шепард, Mass Effect \"");
                }
                if(villain.getName()=="Сундук 3"){
                    pouch.addCoins(50);
                    System.out.println("Итак ты окрываешь сундук и что же там...\n");
                    System.out.println("50 золотых монет\n");
                    System.out.println(" Странный клочек бумаги :\n---\n 3 \n---\n");
                    System.out.println(" И выссказывание великих расхитителей гробниц номер 458:\n\"Как бы я хотела вернуть то время, которое было у нас украдено! Лара Крофт, Tomb Raider \"");
                }
                if(villain.getName()=="Сундук 0"){
                    pouch.addCoins(75);
                    System.out.println("Итак ты окрываешь сундук и что же там...\n");
                    System.out.println("75 золотых монет\n");
                    System.out.println(" Странный клочек бумаги :\n---\n 0 \n---\n");
                    System.out.println(" И выссказывание великих расхитителей гробниц номер 826: \n\" Знаешь что, Карло? Последние десять лет я только убивал и всё. Я убивал за свою страну. Убивал за свою семью.\n Убивал каждого, кто переходил мне дорогу. Но это… Это для меня! Вито Скалетто, Mafia 2\"");
                }
                if(villain.getName()=="Сундук 8"){
                    pouch.addCoins(100);
                    System.out.println("Итак ты окрываешь сундук и что же там...\n");
                    System.out.println("100 золотых монет\n");
                    System.out.println(" Странный клочек бумаги :\n---\n 8|\n---\n");
                    System.out.println(" И выссказывание великих расхитителей гробниц номер 1000: \n\"Я уже говорил тебе, что такое безумие, а? \n Безумие — это точное повторение одного и того же действия. Раз за разом, в надежде на изменение. Это есть безумие. Ваас, Far Cry 3\"");
                }

                /* Дайте игроку немного золота за убийство врага. */
                pouch.addCoins(RANDOM.nextInt(MAXIMUM_GOLD_DROP));

                if (RANDOM.nextInt(100) < swordDropChance)
                {
                    if (player.hasSword())
                    {
                        System.out.println("\nИз " + villain.getName() + " упал меч, но у вас уже есть.");
                    } // end of if (player.hasSword())
                    else
                    {
                        int i=RANDOM.nextInt(2);
                        String[] typeOfsword={"wooden","metal","gold"};
                        player.addSword(typeOfsword[i]);
                        System.out.println("\nИз" + villain.getName() + " упал " + player.getSword().getName() + ".\nУрон от вашей атаки теперь увеличен на " + player.getSword().getDamageIncrease() + ".");
                    }
                    delay();
                }

                else if (RANDOM.nextInt(100) < armourDropChance)
                {
                    if (player.hasArmour())
                    {   int i=RANDOM.nextInt(2);
                        String[] typeOfsword={"wooden","metal","gold"};
                        player.addSword(typeOfsword[i]);
                        System.out.println("\nИз " + villain.getName() + " упали доспехи, но у вас уже есть.");
                    }
                    else
                    {
                        int i=RANDOM.nextInt(2);
                        String[] typeOfArmour={"leather","iron","gold"};
                        player.addArmour(typeOfArmour[i]);
                        System.out.println("\nИз " + villain.getName() + " упали " + player.getArmour().name() + ".\nПолучаемый урон теперь уменьшен на " + player.getArmour().damageBlocked() + ".");
                    }
                    delay();
                }

                else if (RANDOM.nextInt(100) < healthPotionDropChance)
                {
                    player.addPotions(1);
                    System.out.println("\nИз " + villain.getName() + " упало зелье здоровья.");
                    delay();
                }
            }
            if(player.enemiesKilled() >= 50){
                System.out.println("ВЫ ПОБЕДИТЕЛЬ ИГРЫ THE DUNGEON!");
                System.out.println("░▀█░█████████████████▀▀░░░██░████");
                System.out.println("▄▄█████████████████▀░░░░░░██░████");
                System.out.println("███▀▀████████████▀░░░░░░░▄█░░████");
                System.out.println("███▄░░░░▀▀█████▀░▄▀▄░░░░▄█░░▄████");
                System.out.println("░███▄▄░░▄▀▄░▀███▄▀▀░░▄▄▀█▀░░█████");
                System.out.println("▄▄█▄▀█▄▄░▀▀████████▀███░░▄░██████");
                System.out.println("▀████▄▀▀▀██▀▀██▀▀██░░▀█░░█▄█████░");
                System.out.println("░░▀▀███▄░▀█░░▀█░░░▀░█░░░▄██████░▄");
                System.out.println("████▄▄▀██▄░█░░▄░░█▄░███░████▀▀░▄█");
                System.out.println("█▀▀▀▀▀▀░█████▄█▄▄████████▀░▄▄░▄██");

                clear();
                System.out.println("\fВыход из игры...");
                running = false;
                return;
            }

        }
    } 

    /**
     * Меню подсказки в битве.
     */
    public static void startBattle()
    {
        System.out.println("\n1. Атака.");
        System.out.println("2. Использование зелья.");
        System.out.println("3. Побег!");
        System.out.println("4. Магазин.");
        System.out.println("5. Выход из игры.");
        System.out.println("0. Советы и помощь.");

        System.out.print("\nТвой выбор? ");
    } 
    /**
     * Вывод статистики этой игры, включая состояние игрока и противника.
     */
    public static void printStatistics(Player player, Enemy villain)
    {
        // Статистика
        clear();
        System.out.println("\f# " + villain.getName() + " появился #");

        System.out.println("\n# У вас " + player.health() + " HP #");
        System.out.println("# У Врага " + villain.getHealth() + " HP #");
        System.out.println("# Осталось: " + player.getPotions() + " зелей #");
        System.out.println("# У вас " + player.getPouch().getCoins() + " монет #");
        System.out.println("# Убитые враги: " + player.enemiesKilled() + " #");

        // Меч
        if (player.hasSword())
        {
            System.out.println("\n# Тип меча: " + player.getSword().getName() + " | Очки: " + player.getSword().getHitpoints() + "  #");
        }

        // Броня
        if (player.hasArmour())
        {
            System.out.println("\n# Тип брони: " + player.getArmour().name() + " | Очки брони: " + player.getArmour().hitpoints() + "  #");
        }
    }

    /**
     * Переводит поток в спящий режим, чтобы пользователь мог читать сообщения на дисплее.
     */
    public static void delay()
    {
        try
        {
            Thread.sleep(DELAY);
        }
        catch (InterruptedException exception)
        {
            clear();
            System.out.println("\fВ игре произошло прерванное исключение.");
            System.out.println("Игра не была сохранена.");
            System.out.println("Пожалуйста перезапустите игру.");

            System.exit(0);
        }
    }

    public static void clear()
    {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

} 