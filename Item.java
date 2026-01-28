package beebs.textgame;

import java.util.ArrayList;
import java.util.Random;

public class Item {

    public static ArrayList<Item> list = new ArrayList<Item>();

    private static String[] weaponNames = {"Iron Sword", "Wooden Stick", "Steel Axe",};
    private static String[] armourNames = {"Chainmail", "Knight Armour", "Leather"};

    public String name;
    public int shopPrice;
    public int stats;
    public String itemType;

    static Random rndm = new Random();
    static Player player = new Player(null);




    public Item (String itemName, String itemType, int shopPrice, int stat){
        name = itemName;
        this.itemType = itemType;
        this.shopPrice = shopPrice;
        stats = stat;

 }

    private static void generateItemList() {
        

        for (int i = 0; i < 5; i++) {   // creates list

            int val = rndm.nextInt(2); //determines whether item is armour or weapon

            if(val == 0) {
                int dmg = 0;
                String str = weaponNames[rndm.nextInt(weaponNames.length)];
                
                if (player.lvl < 2) {
                    dmg = (int)(Math.random() * 5) + 2;
                }
                else if (player.lvl >= 2 && player.lvl < 5) {
                    dmg = (int)((Math.random() * 5) + (Math.random() * (double) (10 / 3)));
                }
                else if (player.lvl >= 5 && player.lvl < 10) {
                    dmg = (int)(((Math.random() * 10) + 10) + (Math.random() * 5));
                }
                else if (player.lvl >= 10) {
                    dmg = (int) (Math.random() * 20 + 10);
                }

                int price = (int) (((double) dmg) / player.luck) * 20;

                list.add(new Item(str, "Weapon", price, dmg));
            }
            else if (val == 1) {

                int def = 0;

                String str = armourNames[rndm.nextInt(armourNames.length)];

                if (player.lvl < 2) {
                    def = (int)(Math.random() * 3) + 2;
                }
                else if (player.lvl >= 2 && player.lvl < 5) {
                    def = (int)((Math.random() * 4) + (Math.random() * (double) (10 / 4)));
                }
                else if (player.lvl >= 5 && player.lvl < 10) {
                    def = (int)(((Math.random() * 5) + 10) + (Math.random() * 5));
                }
                else if (player.lvl >= 10) {
                    def = (int) (Math.random() * 20 + 10);
                }

                int price = (int) (((double) def / player.luck) + 30);

                list.add(new Item(str, "Armour", price, def));
            }
        }
        
    }
    public static void displayItemList() {

        generateItemList();

        System.out.println("Name" + "\t\t\t" + "Type" + "\t\t\t" + "Price" + "\t\t\t" + "Stats");
        System.out.println();
        for (int i = 0; i < 5; ++i) {

            System.out.print("(" + (i + 1) + ")|\t" );
            System.out.println(list.get(i).name + "\t\t\t" + list.get(i).itemType + "\t\t\tG$ " + list.get(i).shopPrice + "\t\t\t" + list.get(i).stats);
        }

    }
    

}
