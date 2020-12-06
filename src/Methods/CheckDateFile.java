package Methods;

import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.util.Scanner; // Import the Scanner class to read text files


public class CheckDateFile {



    public boolean CheckDate() throws IOException {
        Date date = null;

        boolean flag = false;
        File myObj = null;

        try {
             myObj = new File("CheckingFile.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                date = Date.valueOf(myReader.nextLine());
                System.out.println(date);
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        Date CurrentDate = Date.valueOf(LocalDate.now());

        if(!CurrentDate.equals(date))
        {
            myObj.delete();
            myObj = new File("app\\CheckingFile.txt");
            myObj.createNewFile();
            FileWriter myWriter = new FileWriter("CheckingFile.txt");
            //myWriter.write(date.toString());
            myWriter.close();
            flag = true;

        }


        return flag;
    }

}
