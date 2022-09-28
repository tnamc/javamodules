import java.io.*;

public class Exercise9_2 {
    public static void main (String[] args){

        try{
            File myFile = new File("xxx.txt");
            FileReader fileReader = new FileReader(myFile);
            BufferedReader reader = new BufferedReader(fileReader);

            FileWriter writer = new FileWriter("xxx_copy.txt");

            String line = null;
            while ((line = reader.readLine()) != null){
                System.out.println(line);
            writer.write(line);
            writer.write('\n');
            }
            reader.close();
            writer.close();
        }
        catch (IOException exp)
        {
            exp.printStackTrace();
        }
    }
}
