package main.java.adventofcode.year15.days;

import main.java.adventofcode.common.Utils;
import main.java.adventofcode.year15.Day2015;

import java.io.File;

public class Day21 extends Day2015 {
    public Day21() {
        super(21);
    }

    public static void main(String[] args) {
        new Day21().printParts();
    }

    @Override
    public Object part1() {
        File in = Day21.getResource("day21.txt", 2015);
        File test = Day21.getResource("test21.txt", 2015);

        var input = Utils.realOrTest(in, test);
        return "For some reason the commented out solution worked in another editor, but not this one :(";
    }

    @Override
    public Object part2() {
        File in = Day21.getResource("day21.txt", 2015);
        File test = Day21.getResource("test21.txt", 2015);

        var input = Utils.realOrTest(in, test);
        return 1;
    }
}

//package Year2015;
//
//import java.util.ArrayList;
//import java.util.LinkedHashMap;
//import java.util.Optional;
//
//public class Day21 {
//
//    public static class Player {
//        String name;
//        int health, damage, armor;
//
//        public Player(String name, int health, int damage, int armor) {
//            this.name = name;
//            this.health = health;
//            this.damage = damage;
//            this.armor = armor;
//        }
//
//        public String getName()    {return this.name;}
//        public int getHealth()     {return this.health;}
//        public int getDamage()     {return this.damage;}
//        public int getArmor()      {return this.armor;}
//
//        public void setHealth(int newHealth) {this.health = newHealth;}
//        public void setArmor(int newArmor) {this.armor = newArmor;}
//        public void setDamage(int newDamage) {this.damage = newDamage;}
//
//        @Override
//        public String toString() {
//            return name + " has " + health + " health remaining, deals: " + damage + " damage, and has: " + armor + " armor.";
//        }
//
//        /*
//         * Damage dealt by an attacker is the attacker's damage - defender's armor
//         * If this value is negative, damage is 1
//         */
//        public void attack(Player that) {
//            int dealt = this.getDamage() - that.getArmor();
//            if(dealt <= 0) dealt = 1;
//            that.setHealth(that.getHealth() - dealt);
//            System.out.println(this.getName() + " deals " + dealt + " to " + that.getName() + " (Health: "+that.getHealth()+ ")");
//        }
//
//        public void equip(Item.BuildComponent item) {
//            this.damage += item.getDamage();
//            this.armor += item.getArmor();
//        }
//
//    }
//
//    public interface Item{
//        public static interface BuildComponent {
//            public int getCost();
//            public int getArmor();
//            public int getDamage();
//        }
//
//        public static class Weapon implements BuildComponent {
//            String name;
//            int cost, damage, armor;
//
//            public Weapon(String name, int cost, int damage, int armor) {
//                this.name = name;
//                this.cost = cost;
//                this.damage = damage;
//                this.armor = armor;
//            }
//
//            public int getDamage()  {return this.damage;}
//            public int getCost()    {return this.cost;}
//            public int getArmor()   {return this.armor;}
//
//            @Override
//            public String toString() {
//                return name + " C:" + cost + " D:" + damage + " A:" + armor;
//            }
//        }
//
//        public static class Armor implements BuildComponent {
//            String name;
//            int cost, damage, armor;
//
//            public Armor(String name, int cost, int damage, int armor) {
//                this.name = name;
//                this.cost = cost;
//                this.damage = damage;
//                this.armor = armor;
//            }
//
//            public int getDamage()  {return this.damage;}
//            public int getCost()    {return this.cost;}
//            public int getArmor()   {return this.armor;}
//
//            @Override
//            public String toString() {
//                return name + " C:" + cost + " D:" + damage + " A:" + armor;
//            }
//        }
//
//        public static class Ring implements BuildComponent {
//            String name;
//            int cost, damage, armor;
//
//            public Ring(String name, int cost, int damage, int armor) {
//                this.name = name;
//                this.cost = cost;
//                this.damage = damage;
//                this.armor = armor;
//            }
//
//            public int getDamage()  {return this.damage;}
//            public int getCost()    {return this.cost;}
//            public int getArmor()   {return this.armor;}
//
//            @Override
//            public String toString() {
//                return name + " C:" + cost + " D:" + damage + " A:" + armor;
//            }
//        }
//    }
//
//    private static Optional<Player> losingPlayer(Player p1, Player p2) {
//        return (p1.getHealth() <= 0) ? Optional.of(p1) : ((p2.getHealth() <= 0) ? Optional.of(p2) : Optional.empty());
//    }
//
//    private static int calcCost(ArrayList<Item.BuildComponent> inventory) {
//        int totalCost = 0;
//        for (Item.BuildComponent item : inventory) {
//            totalCost += item.getCost();
//        }
//        return totalCost;
//    }
//
//    public static void main(String[] args) {
//        int MY_HEALTH = 100;
//        var bossStats = AoCUtils.textToStringAL("Completed\\Day21");
//        var shop = AoCUtils.textToStringAL("Completed\\Day21Shop");
//        ArrayList<Item.Weapon> weapons = new ArrayList<>();
//        ArrayList<Item.Armor> armors = new ArrayList<>();
//        ArrayList<Item.Ring> rings = new ArrayList<>();
//
//        Day21 outer = new Day21();
//
//        // Preps Weapons List
//        int count = 0;
//        for(int i = 0; i < shop.size(); ++i) {
//            var tmp = shop.get(i);
//            if(tmp.equals("")) break;
//            else {
//                var arr = shop.get(i).split(" ");
//                ArrayList<String> tempArrayList = new ArrayList<>();
//                for(var v : arr) {
//                    if(v.trim() == "") {}//do nothing
//                    else tempArrayList.add(v.trim());
//                }
//                if(count == 0) {}//do nothing
//                else weapons.add(new Item.Weapon(
//                        tempArrayList.get(0),
//                        Integer.parseInt(tempArrayList.get(1)),
//                        Integer.parseInt(tempArrayList.get(2)),
//                        Integer.parseInt(tempArrayList.get(3))));
//                count++;
//            }
//        }
//        for(int i = 0; i <= count; ++i) shop.remove(0);
//
//        // Preps Armor List
//        count = 0;
//        for(int i = 0; i < shop.size(); ++i) {
//            var tmp = shop.get(i);
//            if(tmp.equals("")) break;
//            else {
//                var arr = shop.get(i).split(" ");
//                ArrayList<String> tempArrayList = new ArrayList<>();
//                for(var v : arr) {
//                    if(v.trim() == "") {}//do nothing
//                    else tempArrayList.add(v.trim());
//                }
//                if(count == 0) {}//do nothing
//                else armors.add(new Item.Armor(
//                        tempArrayList.get(0),
//                        Integer.parseInt(tempArrayList.get(1)),
//                        Integer.parseInt(tempArrayList.get(2)),
//                        Integer.parseInt(tempArrayList.get(3))));
//                count++;
//            }
//        }
//        for(int i = 0; i <= count; ++i) shop.remove(0);
//
//        // Preps Rings List
//        count = 0;
//        for(int i = 0; i < shop.size(); ++i) {
//            var tmp = shop.get(i);
//            if(tmp.equals("")) break;
//            else {
//                var arr = shop.get(i).split(" ");
//                ArrayList<String> tempArrayList = new ArrayList<>();
//                for(var v : arr) {
//                    if(v.trim() == "") {}//do nothing
//                    else tempArrayList.add(v.trim());
//                }
//                if(count == 0) {}//do nothing
//                else rings.add(new Item.Ring(
//                        (tempArrayList.get(0) + " " + tempArrayList.get(1)),
//                        Integer.parseInt(tempArrayList.get(2)),
//                        Integer.parseInt(tempArrayList.get(3)),
//                        Integer.parseInt(tempArrayList.get(4))));
//                count++;
//            }
//        }
//        armors.add(new Item.Armor("none", 0, 0, 0));
//        rings.add(new Item.Ring("none", 0, 0, 0));
//        int bossDamage = Integer.parseInt(bossStats.get(1).split(": ")[1]);
//        int bossHealth = Integer.parseInt(bossStats.get(0).split(": ")[1]);
//        int bossArmor = Integer.parseInt(bossStats.get(2).split(": ")[1]);
//
//        ArrayList<ArrayList<BuildComponenet>> combinations = new ArrayList<>();
//        for(int i = 0; i < weapons.size(); ++i) {
//            Item.Weapon w = weapons.get(i);
//            for(int j = 0; j < armors.size(); ++j) {
//                Item.Armor a = armors.get(j);
//                for(int k = 0; k < rings.size(); ++k) {
//                    Item.Ring r = rings.get(k);
//                    rings.remove(r);
//                    for(int l = 0; l < rings.size(); ++l) {
//                        Item.Ring r2 = rings.get(l);
//
//                        ArrayList<Item> tmp = new ArrayList<>();
//                        tmp.add(w); tmp.add(a); tmp.add(r); tmp.add(r2);
//                        combinations.add(tmp);
//                    }
//                    rings.add(r);
//                }
//            }
//        }
//        LinkedHashMap<ArrayList<Item>, Integer> costs = new LinkedHashMap<>();
//        ArrayList<ArrayList<Item>> winners = new ArrayList<>();
//
//        for(var combo : combinations) {
//            Player player = new Player("Player", MY_HEALTH, 0, 0);
//            Player boss = new Player("Boss", bossHealth, bossDamage, bossArmor);
//            costs.put(combo, calcCost(combo));
//            for(Item.BuildComponent item : combo) player.equip(item);
//            System.out.println("Combo: " + combo);
//            if(!playGame(player, boss).equals(boss)) winners.add(combo);
//            // the !playGame ensures I lose!
//
//        }
//        System.out.println(winners);
//        int maxCost = 0;
//        ArrayList<Item.BuildComponent> winningCombo = new ArrayList<>();
//        for(var combo : winners) {
//            if(costs.get(combo) > maxCost) {
//                maxCost = costs.get(combo);
//                winningCombo = combo;
//            }
//        }
//        System.out.println("Winning Combo is: " + winningCombo + " with a cost of " + maxCost);
//    }
//
//    public static Player playGame(Player p1, Player p2) {
//        // p2 is boss
//        while(!losingPlayer(p1, p2).isPresent()) {
//            p1.attack(p2);
//            if(losingPlayer(p1, p2).isPresent()) break;
//            p2.attack(p1);
//        }
//        return losingPlayer(p1, p2).get();
//    }
//}