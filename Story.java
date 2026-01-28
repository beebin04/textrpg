package beebs.textgame;

public class Story {

    private static String[] parentInfo = {"Family of nobility and wealth", "Travelling mercenary group", "Family of blacksmiths", "Poor family of farmers", "Scholarly family"};
    private static String[] youngAgeDiscovery = {"Fight with other kids", "Do hard labour for money", "Make things like shoes to sell", "Read by looking at signs and papers", "Steal from unsuspecting adults run from the authourities"};
    private static String[] parentTeachings = {"Use a sword", "Ride a horse", "Play with cards", "Handle a great-axe", "Swindle people on sales"};

    public static void printIntro(Player player){

        Logic.clearConsole();
        System.out.println("You were born to a-");
        Logic.printList(parentInfo);
        int in = Logic.readInt("=>", parentInfo.length);
        
           switch (in) {
            case 1:
                player.luck += 1;
                player.agility += 1;
                break;
            case 2:
                player.strength += 1;
                player.agility += 1;
                break;
            case 3:
                player.strength += 1;
                player.endurance += 1;
                break;
            case 4:
                player.agility += 1;
                player.endurance += 1;
                break;
            case 5:
                player.luck += 1;
                player.gold += 50;
                player.literate = true;
                break;
            default:
                break;
           }
        Logic.clearConsole();
        System.out.println("At a young age, your learned how to-");
        Logic.printList(youngAgeDiscovery);
        int in2 = Logic.readInt("=>", youngAgeDiscovery.length);
           switch (in2) {
            case 1:
                player.endurance += 2;
                player.strength += 1;
                break;
            case 2:
                player.strength += 2;
                player.endurance += 1;
                break;
            case 3:
                player.agility += 2;
                player.luck += 1;
                break;
            case 4:
                player.luck += 1;
                player.literate = true;
                break;
            case 5:
                player.agility += 2;
                player.gold += 50;
                break;
            default:
                break;
           }
        Logic.clearConsole();
        System.out.println("You parents taught you how to-");
        Logic.printList(parentTeachings);
        int in3 = Logic.readInt("=>", parentTeachings.length);
           switch (in3) {
            case 1:
                player.agility += 1;
                player.strength += 1;
                break;
            case 2:
                player.endurance += 2;
                break;
            case 3:
                player.agility += 1;
                player.luck += 2;
                break;
            case 4:
                player.strength += 2;
                break;
            case 5:
                player.agility += 1;
                player.luck += 1;
                player.gold += 50;
                break;
            default:
                break;
           }
        Logic.clearConsole();
        System.out.println("You are abord a passenger trip heading for the New World, along this vessel's journey you cross hectic waters.");
        System.out.println("");



    }
    public static void roam() {
        Logic.clearConsole();
        System.out.println("You decide to wander around... who knows what you may come across...");
    }
    public static void startAdventure() {
        Logic.clearConsole();
        System.out.println("You begin your journey by travelling north-west towards a harbor.");
        System.out.println("Entering the dense forest, you expect to come across hostiles here...");
    }
}
