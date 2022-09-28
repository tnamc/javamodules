import java.io.*;
import java.util.ArrayList;

public class GameSaver{
    ArrayList<GameCharacter> GameCharacterList = new ArrayList<>();


    public static void main(String[] args){
        GameCharacter one = new GameCharacter(50, "Elf", new String[]{"bow","sword","dust"});
        GameCharacter two = new GameCharacter(200, "Troll", new String[]{"bare hands","big ax"});
        GameCharacter three = new GameCharacter(120, "Magician", new String[]{"spells","invisibility"});

        try{
            ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream("Game.ser"));
            os.writeObject(one);
            os.writeObject(two);
            os.writeObject(three);
            os.close();
        }catch (IOException ex){
            ex.printStackTrace();
        }


        // Set three objects to null, so we cannot access the objects on the heap
        one = null;
        two = null;
        three = null;


        try{
            ObjectInputStream is = new ObjectInputStream(new FileInputStream(("Game.ser")));
            GameCharacter teamRestore = (GameCharacter) is.readObject();
                System.out.println(teamRestore.getType());
        }catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }
}
