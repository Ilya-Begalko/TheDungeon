package com.company;

import java.util.Scanner;

import com.company.player.Player;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.File;
import java.io.PrintWriter;

import java.io.FileNotFoundException;
import java.io.IOException;


public class State
{
    public static final Scanner scanner = new Scanner(System.in);

    /**
     * Сохраняет информацию о игроке в файл. */
    public static void saveState(Player player)
    {
        /* Проверьте, если у пользователя нет имени, если да, получите имя */
        if (player.getName().equals(""))
        {
            System.out.print("Введите ваше имя: ");
            String name = scanner.nextLine();

            /* Удалить пробелы из имени. */
            name = name.replaceAll("\\s+","");
            player.setName(name);
        }

        try
        {
            File userData = new File("users/" + player.getName() + ".txt");

            /* Создайте родительские каталоги, если они не существуют. */
            userData.getParentFile().mkdirs();

            PrintWriter writer = new PrintWriter(userData);

            /* Сохранить зашифрованные данные игрока в файл.*/
            String encrypted = Cipher.encrypt(player.getData());

            writer.println(encrypted);

            writer.close();

            System.out.println("Ваши данные были сохранены. Теперь вы можете безопасно закрыть консоль.");
        }
        catch (FileNotFoundException exception)
        {
            System.out.println("Файл не может быть записан. Данные не были сохранены.");
        } 
    }

    /**
     * Загружает состояние проигрывателя из текстового файла и обновляет проигрыватель, используя эти данные.
     */
    public static void loadState(Player player) throws FileNotFoundException, IOException
    {
        /* Порядок данных [имя, меч, броня, враги убиты, здоровье, количество очков, монеты] */
        
        BufferedReader reader = new BufferedReader(new FileReader("users/" + player.getName() + ".txt"));

        /* Загрузить сохраненные расшифрованные данные игрока */
        String[] data = Cipher.decrypt(reader.readLine()).split(" ");

        reader.close();

        /* Извлечь данные в локальные переменные из массива строк */
        String name = data[0];
        boolean hasSword = Boolean.parseBoolean(data[1]);
        boolean hasArmour = Boolean.parseBoolean(data[2]);
        int enemiesKilled = Integer.parseInt(data[3]);
        int health = Integer.parseInt(data[4]);
        int numberOfPotions = Integer.parseInt(data[5]);
        int coins = Integer.parseInt(data[6]);

        /* Установите имя этого игрока. */
        player.setName(name);

        /* Добавьте деревянный меч, если у игрока был меч. */
        if (hasSword) player.addSword("wooden");

        /*Добавьте кожаную броню, если у игрока была броня.*/
        if (hasArmour) player.addArmour("lather");

        /* Установите счет этого игрока с точки зрения убитых врагов. */
        player.setEnemiesKilled(enemiesKilled);

        /* Установите очки здоровья этого игрока на основе данных.*/
        player.setHealth(health);

        /* Установить количество зелий этого игрока. */
        player.setNumberOfPotions(numberOfPotions);

        /* Установите монеты этого игрока. */
        player.getPouch().setCoins(coins);
    }

}
