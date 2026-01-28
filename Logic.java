package beebs.textgame;

import java.util.Random;
import java.util.Scanner;


public class Logic {

    static Random rng = new Random(231);
    static Player player;
    
    
    static Scanner scnr = new Scanner(System.in);

    public static boolean isRunning;

    public static String[] encounters = {"Battle", "Battle", "Battle", "Shop", "Rest"};
    public static String[] combatOptions = {"Attack", "Guard", "Run", "Items"};


    public static int place = 0, act;
    public static String[] placeStrings = {"Dungeons", "Ancient City"};

    public static int readInt(String prompt, int userChoises){
        int input;
        do{
            System.out.println(prompt);
            try{
                input = Integer.parseInt(scnr.next());
            }catch(StringIndexOutOfBoundsException e) {
                input = -1;
                System.out.println("Please enter a valid number!");
            }catch(Exception e){
                input = -1;
                System.out.println("Please enter a valid number!");
            }


        }while(input < 1 || input > userChoises );
        return input;


    }
    //clears console
    public static void clearConsole(){
        for(int i = 0; i < 100; i++)
        System.out.println();

    }
    //print a seperator with length 'n'
    public static void printSeperator(int n){
        for(int i = 0; i < n; i++)
        System.out.print('~');
    System.out.println();
    }
    //print a heading with titleName
    public static void printHeading(String titleName){
        printSeperator(30);
        System.out.println(titleName);
        printSeperator(30);
    }

    public static void anyKey(){
        System.out.println("\n Enter any key to continue...");
        scnr.next();
    }

    public static void gameStart(){

        boolean nameSet = false;
        String name;
        //title screen
        clearConsole();
        printSeperator(40);
        printSeperator(40);
        System.out.println("FIX-ME: ADD NAME");
        printSeperator(40);
        printSeperator(40);
        anyKey();

        //getting player name
        do{
            clearConsole();
            printHeading("What is your name?");
            name = scnr.next();
            clearConsole();

            printHeading("Your name is " + name + ", is that correct?" );
            System.out.println("(Y) Yes that's my name.");
            System.out.println("(N) No, let me change it.");
           
            char tmpInput = scnr.next().charAt(0);
            char input = Character.toUpperCase(tmpInput);

            if (input == 'Y'){
                nameSet = true;
            }
        }while(!nameSet); 

        player = new Player(name);

        isRunning = true;

        Story.printIntro(player);
        act = 1;

        gameLoop();
    }
    public static void pauseMenu(){
        clearConsole();
        printHeading(placeStrings[place]);
        printSeperator(40);
        System.out.println("(E) Continue game");
        System.out.println("(C) " + player.name + "'s stats and skills");
        System.out.println("(I) " + player.name + "'s Inventory");
        System.out.println("(F) Exit game");
    }
    public static void playerStats(){
        clearConsole();
        printHeading("Character Information");
        printSeperator(30);
        System.out.println("Player name:\t" + player.name);
        System.out.println("Health:\t" + player.hp + "/" + player.maxHp);
        System.out.println("Player XP:\t" + player.xp);
        System.out.println("Player level:\t LVL:" + player.lvl);
        System.out.println("Gold:\tG$" + player.gold);
        System.out.println("===========================");
        System.out.println(player.name + "'s Traits: ");
        System.out.println("STR:\t" + player.strength);
        System.out.println("AGL:\t" + player.agility);
        System.out.println("END:\t" + player.endurance);
        System.out.println("LCK:\t" + player.luck);
        if(player.atckUpgrCount > 0) {
            System.out.println("============================");
            System.out.println(player.name + "'s Skills: ");
            System.out.println("ATK Skill:\t" + player.attackUpgrades[player.atckUpgrCount - 1]);
            System.out.println();
        }
        System.out.println("============================");
        System.out.println(player.name + "'s Gear: ");
        System.out.println("Equipped Weapon:\t" + player.equippedWeapon.name);
        System.out.println("Eqquiped Armour:\t" + player.equippedArmour.name);
        System.out.println("Weapon ATK: " + player.equippedWeapon.stats);
        System.out.println("Armour DEF: " + player.equippedArmour.stats);
        
        anyKey();
    }
    public static void playerInventory() {
        int in;
        do {
            clearConsole();
            printHeading("Character Inventory");
            System.out.println("\tName\t\tType\t\tStats (DMG/DEF)");
            System.out.println();
            for(int i = 0; i < player.itemCount; ++i) {
                System.out.print((i + 1) + "|");
                System.out.printf("%-22s|%-16s %8s|%n", player.playerItems.get(i).name, player.playerItems.get(i).itemType, player.playerItems.get(i).stats);
            }
            printSeperator(40);
            System.out.println("(1)|\tEquip Item");
            System.out.println("(2)|\tBack to Menu");
            in = readInt("=>", 2);
  
            switch (in) {
                case 1:
                    System.out.println("Enter number of Item to equip:");
                    int in2 = readInt("Pick an Item: ", player.itemCount);
                    if(player.playerItems.get(in2 - 1).itemType == "Weapon") {
                        if(!player.equippedWeapon.equals(player.playerItems.get(in2 - 1))) {
                            player.equippedWeapon = player.playerItems.get(in2 - 1);
                            clearConsole();
                            System.out.println("Equipped Weapon:\t" + player.equippedWeapon.name);
                            anyKey();
                            break;
                        }
                        else {
                            clearConsole();
                            System.out.println("Weapon is Already Equipped!");
                            anyKey();
                            break;
                        }
                    }
                    else if (player.playerItems.get(in2 - 1).itemType == "Armour") {
                        if (!player.equippedArmour.equals(player.playerItems.get(in2 - 1))) {
                            player.equippedArmour = player.playerItems.get(in2 - 1);
                            clearConsole();
                            System.out.println("Equipped Armour:\t" + player.equippedArmour.name);
                            anyKey();
                            break;
                        }
                        else {
                            clearConsole();
                            System.out.println("Armour is Already Equipped!");
                            anyKey();
                            break;

                        }
                    }
                default:
                    break;
            }

        }while(in != 2);
    }
    public static void printList(String[] list) {
        for (int i = 0; i < list.length; i++) {
            System.out.print("(" + (i + 1) + ")\t");
            System.out.printf("%-30s%n", list[i]);
        }
    }
    public static void continueOrRoam() {
        clearConsole();
        System.out.println("Would you like to press forward or wander around this area?");
        System.out.println("(1)| Continue Journey");
        System.out.println("(2)| Wander");
        System.out.println("(3)| Retire");
        int in = readInt("->", 2);
        switch (in) {
            case 1 -> continueGame();
            case 2 -> randomEvent();
            case 3 -> isRunning = false;
        }
    }
    public static void checkAct() {
        randomEvent();
    }
    public static void randomEvent(){
        int event = (int)(Math.random() * encounters.length);
        switch(encounters[event]) {
            case "Battle":
               randomBattle();
               break;
            case "Shop":
                enterShop();
                break;
            case "Rest":
                rest();
                break;
        }
    }
    public static void randomBattle() {
        Enemy enemy = new Enemy(Enemy.act1Enemies[randomNum(Enemy.act1Enemies.length)], (((player.lvl + 1) * act) + 50 + (randomNum(15 * (player.lvl + 1)))), (player.lvl + 1) + randomNum(4));
        System.out.println("You encounter an enemy " + enemy.name + "\t(lvl: " + enemy.lvl + ")");
        anyKey();
        boolean playerGuarding;
        boolean enemyGuarding;
        boolean run = false;
        boolean enemyDead = false;
        boolean playerDead = false;
    
        while(enemyDead == false && playerDead == false && run == false) {
            playerGuarding = false;
            enemyGuarding = false;
            clearConsole();
            printHeading("BATTLE");
            enemy.displayStats();
            player.displayStats();
            System.out.println();
            for (int i = 0; i < combatOptions.length; i++) {
                System.out.print("(" + (i + 1) + ")\t");
                System.out.println(combatOptions[i]);
            }
            int in = readInt("=>", combatOptions.length);
            int enemyIn = 0;
            switch (in) {
                case 1:
                    clearConsole();
                    int dmg = player.attack();
                    if (enemyGuarding) {
                        System.out.println("The " + enemy.name + " blocks some damage...");
                        dmg -= enemy.defend();
                        enemyGuarding = false;
                    }
                    System.out.println("You hit the " + enemy.name + " for " + dmg + " damage!");
                    enemy.hp -= dmg;
                    anyKey();
                    if(enemy.hp <= 0) {
                        enemyDead = true;
                        break;
                    }
                    break;
                case 2:
                    clearConsole(); 
                    playerGuarding = true;
                    System.out.println("You put up your arms ready for the enemies next attack...");
                    anyKey();
                    break;
                case 3:
                    boolean bool = (rng.nextInt(30) + 3 * player.luck >= 18) ? true : false;
                    run = bool;
                    break;
                case 4:
                    playerInventory();
                    break;
            }
            
            enemyIn = randomNum(2) + 1;
            if (enemyIn == 1 && enemyGuarding == false) {
                clearConsole();
                int dmg = enemy.attack();
                boolean critBool = (randomNum(5) + enemy.luck >= 5) ? true : false;
                dmg = (critBool) ? (int)( (5.0 / 2.0)) * dmg : dmg;
                if (playerGuarding) {
                    dmg -= player.defend();
                    playerGuarding = false;
                }
                System.out.println("You were hit for " + dmg + " damage");
                player.hp -= dmg;
                anyKey();
                if(player.hp <= 0) {
                    playerDead = true;
                }
            }
            else {
                clearConsole();
                System.out.println("The " + enemy.name + " raises it's arms in preparation for your next attack");
                enemyGuarding = true;
                anyKey();
            }
        }
        if (run) {
            clearConsole();
            System.out.println("You escaped from the " + enemy.name);
            anyKey();
        }
        else if (enemy.hp <= 0) {
            clearConsole();
            System.out.println("You defeated the " + enemy.name + "!!!");
            System.out.println("You gained " + enemy.gold + " gold and earned " + (int) (enemy.xp / 1.2) + " experience");
            player.gold += enemy.gold;
            player.xp += (int) (enemy.xp / 1.2);
            anyKey();
        }
        else if (player.hp <= 0) {
            clearConsole();
            printHeading("You Died");
            isRunning = false;
            
        }
    }
    public static void exit() {
        isRunning = false;
    }
    public static void rest() {
        System.out.println("You find an area that seems safe enough to take a break...");
        player.hp = player.maxHp;
        System.out.println();
        System.out.println("HP Refilled!");
        System.out.println(player.name + "'s HP:\t" + player.hp + "/" + player.maxHp);
        anyKey();
    }
    public static int randomNum(int upperBound) {
        return rng.nextInt(upperBound);
    }
    public static void enterShop() {

        System.out.println("You find a small store carrying a selection of items that will surely aid you in your adventure...");
        int in;
        
        do {
            System.out.println("The items displayed on the shelf are:");
            Item.displayItemList();
            printSeperator(40);
            System.out.println(player.name + "'s gold: G$" + player.gold);
            System.out.println("(1)|\t Purchase an Item");
            System.out.println("(2)|\t Leave Shop");
            in = readInt("=>", 2);
       
            if (in == 1) {
                System.out.println("Enter number of Item");
                    int tmp = scnr.nextInt();
                        if(player.gold >= Item.list.get(tmp - 1).shopPrice) {
                            player.gold = player.gold - Item.list.get(tmp - 1).shopPrice;
                            player.itemCount++;
                            player.playerItems.add(Item.list.get(tmp - 1));
                            Item.list.remove(tmp - 1);
                            System.out.println("You have acquired: " + player.playerItems.get(player.itemCount - 1).name);
                            anyKey();
                        }
                        else {
                            System.out.println("Not enough gold!");
                            anyKey();
                        }
            }

        }while(in != 2);
        
    }
    public static void continueGame(){

        checkAct();

        if(act != 4){
           randomEvent();
        }

    }
    
    public static void gameLoop(){
        while(isRunning == true){
            pauseMenu();
            scnr.nextLine();
            try {
                char input = Character.toUpperCase(scnr.nextLine().charAt(0));
                
                if (input == 'E'){
                    continueGame();
                }
                else if(input == 'C'){
                    playerStats();
                }
                else if(input == 'I'){
                    playerInventory();
                }
                else if (input == 'F'){
                    isRunning = false;
                }
            } catch (Exception e) {
                System.out.println("Please enter a valid character!!!");
                anyKey();
            }
        }
    }
}
