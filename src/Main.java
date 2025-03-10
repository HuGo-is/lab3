import java.util.Random;

abstract class SuperHero {
    protected String name;
    protected int health;

    public SuperHero(String name, int health) {
        this.name = name;
        this.health = health;
    }

    public abstract void attack(SuperHero adversary);

    public void takeDamage(int amount) {
        health -= amount;
        System.out.println(name + " subit " + amount + " dégâts. Points de vie restants : " + health);
    }

    public boolean isAlive() {
        return health > 0;
    }
}

class SpeedHero extends SuperHero {
    private final static int DAMAGE = 5;

    public SpeedHero(String name, int health) {
        super(name, health);
    }

    @Override
    public void attack(SuperHero adversary) {
        System.out.println(name + " attaque rapidement " + adversary.name);
        adversary.takeDamage(DAMAGE);
        adversary.takeDamage(DAMAGE); // Deux coups rapides
    }
}

class StrongHero extends SuperHero {
    private final static int DAMAGE = 15;

    public StrongHero(String name, int health) {
        super(name, health);
    }

    @Override
    public void attack(SuperHero adversary) {
        System.out.println(name + " frappe puissamment " + adversary.name);
        adversary.takeDamage(DAMAGE);
    }
}

class TelepathHero extends SuperHero {
    private final static int DAMAGE = 10;
    private final static int CRITICAL_DAMAGE = 20;
    private Random random = new Random();

    public TelepathHero(String name, int health) {
        super(name, health);
    }

    @Override
    public void attack(SuperHero adversary) {
        System.out.println(name + " utilise ses pouvoirs télépathiques contre " + adversary.name);
        if (random.nextBoolean()) {
            System.out.println("Coup critique !");
            adversary.takeDamage(CRITICAL_DAMAGE);
        } else {
            adversary.takeDamage(DAMAGE);
        }
    }
}

class BattleArena {
    private static int battleCount = 0;

    public static void fight(SuperHero hero1, SuperHero hero2) {
        battleCount++;
        System.out.println("=== Combat n°" + battleCount + " : " + hero1.name + " VS " + hero2.name + " ===");

        while (hero1.isAlive() && hero2.isAlive()) {
            hero1.attack(hero2);
            if (!hero2.isAlive()) {
                System.out.println(hero2.name + " est vaincu !");
                break;
            }
            hero2.attack(hero1);
            if (!hero1.isAlive()) {
                System.out.println(hero1.name + " est vaincu !");
                break;
            }
        }
        System.out.println("Fin du combat n°" + battleCount);
    }

    public static int getBattleCount() {
        return battleCount;
    }
}

public class Main {
    public static void main(String[] args) {
        // Création de super-héros avec des points de vie initiaux
        SuperHero hero1 = new SpeedHero("Flash", 50);
        SuperHero hero2 = new StrongHero("Hercule", 50);

        // Premier combat
        BattleArena.fight(hero1, hero2);

        // Affichage du nombre total de combats effectués
        System.out.println("\nNombre total de combats réalisés : " + BattleArena.getBattleCount());
    }
}