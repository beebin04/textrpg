package beebs.textgame;

import java.util.ArrayList;

public class Player extends Characters {
    public int strength; // affects melee damage,
    public int agility; // affects dodge chance,
    public int endurance; // affects resistance, maxHp
    public int luck; // affects critcal chance
    public int levelAmount = 20;
    public boolean literate;

    public Item equippedWeapon;
    public Item equippedArmour;

    public int itemCount;

    Item starterWeapon = new Item("Wooden Stick", "Weapon", 0, 2);
    Item starterArmour = new Item("Tattered Clothing", "Armour", 0, 1);

    public ArrayList<Item> playerItems = new ArrayList<Item>();

    public String[] attackUpgrades = { "Weapon Mastery I", "Weapon Mastery II", "Weapon Mastery III",
            "Weapon Mastery IV", "Weapons Master" };
    public int atckUpgrCount;

    public Player(String name) {
        super(name, 100, 0);

        this.strength = 1;
        this.agility = 1;
        this.endurance = 1;
        this.luck = 1;
        this.xp = 0;
        this.gold = 600;
        this.literate = false;

        equippedArmour = starterArmour;
        equippedWeapon = starterWeapon;
        playerItems.add(starterArmour);
        playerItems.add(starterWeapon);

        atckUpgrCount = 0;
        itemCount = 2;

        if (xp == levelAmount) {
            lvl += 1;
            levelAmount = levelAmount + (int) Math.pow(lvl, 2);
            chooseTrait();
        }
    }

    @Override
    public int attack() {
        int baseDMG = (int) (((3 * (lvl + 1) * strength * (9.0 / 5.0))
                + ((2.0 / 5.0) * equippedWeapon.stats * (2 * atckUpgrCount))) + Logic.randomNum(luck + strength + lvl)
                + (strength * (lvl + 1)));
        boolean critBool = (Logic.randomNum(5) + luck >= 5) ? true : false;
        int DMG = (critBool) ? (int) ((5.0 / 2.0)) * baseDMG : baseDMG;
        return DMG;
    }

    @Override
    public int defend() {
        int defense = 5 + (2 * endurance) + equippedArmour.stats;
        return defense;
    }

    @Override
    public void displayStats() {
        System.out.println(name + "'s HP: " + hp + "/" + maxHp);
    }

    public void chooseTrait() {
        Logic.clearConsole();
        Logic.printHeading("Select a trait: ");
        System.out.println("(1) Strength:\t" + strength);
        System.out.println("(2) Agility:\t" + agility);
        System.out.println("(3) Endurance:\t" + endurance);
        System.out.println("(4) Luck:\t" + luck);

        int input = Logic.readInt("=>", 4);
        Logic.clearConsole();

        switch (input) {
            case 1:
                Logic.printHeading("You chose Strength!");
                ++strength;
                System.out.println("Strength now equals " + strength);
                break;
            case 2:
                Logic.printHeading("You chose Agility!");
                ++agility;
                System.out.println("Agility now equals " + agility);
                break;
            case 3:
                Logic.printHeading("You chose Endurance!");
                ++endurance;
                System.out.println("Endurance now equals " + endurance);
                break;
            case 4:
                Logic.printHeading("You chose Luck!");
                ++luck;
                System.out.println("Luck now equals " + luck);
                break;
        }
        Logic.anyKey();
        if (lvl % 2 == 0) {
            Logic.printSeperator(40);
            System.out.println("Select a skill: ");
            System.out.println("(1)|\t" + attackUpgrades[atckUpgrCount]);
            // System.out.println("(2)|\t" + defenseUpgrades[defUpgrCount]);
            Logic.printSeperator(40);
            int in = Logic.readInt("=>", 1);
            Logic.clearConsole();
            do {
                switch (in) {
                    case 1:
                        System.out.println("Selected Skill:\t" + attackUpgrades[atckUpgrCount]);
                        atckUpgrCount += 1;
                        break;
                    default:
                        System.out.println("Invalid Option! Try Again!");
                }
            } while (in != 1);
        }

    }

}