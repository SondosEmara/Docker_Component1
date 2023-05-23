
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.io.File;
import java.io.FileNotFoundException;


public class Registration {

    FileWriter dataBaseWriter;
    Scanner scanner;

    public Registration() {
        scanner = new Scanner(System.in);
        //Write data of Student in Registration_DataBase.txt
        try {
            this.dataBaseWriter = new FileWriter("data//Registration_DataBase.txt", true);
        } catch (IOException ex) {
            Logger.getLogger(Registration.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    
    public void addOneStudent() {

        System.out.print("Enter Name  ID course [Enter the courses as comma separated list like Math1,Math2 \n");
        String name = scanner.nextLine();
       
        String id = scanner.nextLine();
       
        String courses = scanner.nextLine();

        try {
            String str = name + ';' + id + ';' + courses+'\n';
            dataBaseWriter.write(str);
        } catch (IOException ex) {
            Logger.getLogger(Registration.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    
    public void addBatchStudents() {
        //Adding Bathch
        File dataFolder = new File("data//batch");
       
        File[] eligibleFiles = dataFolder.listFiles((dir, name) -> name.contains("verified"));
        
		for (int i = 0; i < eligibleFiles.length; i++) {
            System.out.println((i+1)+"- " + eligibleFiles[i].getName());
        }
        System.out.print("Enter file number to Data From It: ");
        int fileID = scanner.nextInt();

        File fileSelected = eligibleFiles[fileID - 1];

        Scanner fileIterator = null;
        try {
            fileIterator = new Scanner(fileSelected);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Registration.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            while (fileIterator.hasNextLine()) {
                String line = fileIterator.nextLine();
                String[] parts = line.split(";");
                String studName = parts[0];
                int id = Integer.parseInt(parts[1]);
                String courses = parts[2];
                String str = studName + ';' + id + ';' + courses+'\n';
                dataBaseWriter.write(str);
            }
            
        } catch (IOException ex) {
            Logger.getLogger(Registration.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("Data inserted successfully\n");

    }

    
    public static void main(String[] args){
  
        Registration register = new Registration();
        Scanner scanner = new Scanner(System.in);
        int userChoice;
        do {
            //Menu
            System.out.println("1- Add student data");
            System.out.println("2- Add batch students data");
            System.out.println("3- Exist");
            System.out.print("Enter your choice: ");
            userChoice = scanner.nextInt();
            scanner.nextLine();
            switch (userChoice) {
                case 1:
                    register.addOneStudent();
                    break;
                case 2:
                    register.addBatchStudents();
                    break;
                case 3:
                {
                    try {
                        register.dataBaseWriter.close();
                    } catch (IOException ex) {
                        Logger.getLogger(Registration.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                    break;


                default:
                    System.out.println("Please Choice Correct Result\n");
            }
        } while (userChoice != 3);
    }


}
