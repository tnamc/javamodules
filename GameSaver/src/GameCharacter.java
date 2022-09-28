import java.io.Serializable;
import java.util.ArrayList;

public class GameCharacter implements Serializable {
    int power;
    String type;
    String[] weapons;
    ArrayList<GameCharacter> GameCharacterList = new ArrayList<>();


    // 3-arg constructor
    public GameCharacter(int p, String t, String[] w) {
        power = p;
        type = t;
        weapons = w;
    }

    // getter list


    // getter power
    public int getPower() {
        return power;
    }

    // getter type
    public String getType() {
        return type;
    }

    // getter weapons = weaponList
    public String getWeapons() {
        String weaponList = "";

        for (int i = 0; i < weapons.length; i++) {
            weaponList += weapons[i] + " ";
        }
        return weaponList;
    }
}
