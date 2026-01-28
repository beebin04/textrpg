package beebs.textgame;

public abstract class Characters {
    
    //variables / attributes
    public String name;
    public int maxHp, hp, xp, lvl, gold;
    public int strength;   //affects melee damage
    public int agility;    //affects dodge chance, 
    public int endurance;  //affects resistance
    public int luck;       //affects critcal chance
    public double dodgeChance = (0.15 * luck);
   

    //constructer for character class
    public Characters(String name, int maxHp, int lvl){
        this.name = name;
        this.maxHp = maxHp;
        this.hp = maxHp;
        this.lvl = lvl;
        this.gold = 0;
    }
    public void displayStats() {
        System.out.println(lvl + " " + name + "'s HP: " + hp + "/" + maxHp);
    }
    public abstract int attack();
    public abstract int defend();
}