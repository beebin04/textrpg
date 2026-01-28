package beebs.textgame;

/**
 * Enemies
 */
public class Enemy extends Characters{

    public static String[] act1Enemies = {"Crazed prisoner", "Lunatic prison guard", "Lunatic priest"};

    public Enemy(String name, int maxHp, int lvl) {
        super(name, maxHp, lvl);
        this.gold = Logic.randomNum((int) ((lvl * 23 / 3.5))) + 20;
        this.strength = Logic.randomNum(3) + lvl;
        this.agility = Logic.randomNum(3) + lvl;
        this.endurance = Logic.randomNum(3) + lvl;
        this.luck = Logic.randomNum(3) + lvl;
        this.xp = lvl * (Logic.randomNum(10) + 5);
    }
    @Override
    public int attack() {
        int dmg  = (int) ((Math.random() * 10) * lvl + (Math.random() * (3 * strength)));
        return dmg;
    }
    @Override
    public int defend() {
        return 3 * endurance;
    }

    
}