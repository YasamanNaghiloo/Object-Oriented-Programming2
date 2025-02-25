package assignment4;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Scanner;

/**
 * The app class that runs the program.
 */
public class App {
  // fields of the class

  private BoatClub boatClub; 
  private Scanner scanner;
  private Registration reg;
  private FileManager fileManager;

  /**
   * App class's constructor.
   */
  public App() {
    boatClub = new BoatClub();
    scanner = new Scanner(System.in, StandardCharsets.UTF_8);
    // creating an instance of FileManager and giving it the path of the registry.data file.
    fileManager = new FileManager(
        "D:/Programming/1DV502/Assignments/assignment-4/app/src/main/"
            + "java/assignment4/Registry.data");
    reg = new Registration(scanner, boatClub, fileManager);
  }

  /**
   * method called start that starts to load data from the data file using file
   * manager.
   */
  public void start() {
    try {
      // when we want to start the program we need to load the list of current members first.
      List<Member> loadedMembers = fileManager.loadData();
      System.out.println("\nLoaded " + loadedMembers.size() + " members");
      for (Member member : loadedMembers) {
        boatClub.addMember(member);
      }
    } catch (IOException e) {
      System.out.println("Error loading data: " + e.getMessage());
      return; // whether you want to continue if data cannot be loaded
    }
    // here you get to see the greeting and the aplication menu.
    while (true) {
      System.out.println("--------------------------------------------------------------");
      System.out.println("Ahoy and welcome to Boat Club! Let's sail together!");
      System.out.println("--------------------------------------------------------------");
      System.out.println("1. Add Member");
      System.out.println("2. View Members");
      System.out.println("3. Member's info");
      System.out.println("4. Delete Member");
      System.out.println("5. Add Boat to Member");
      System.out.println("6. Boat's info");
      System.out.println("7. Delete Boat from Member");
      System.out.println("8. Save and Exit");

      System.out.print("Enter your choice: ");
      while (!scanner.hasNextInt()) {
        System.out.println("Invalid input. Please enter a valid integer.");
        scanner.next(); // use the non-integer input
      }
      int choice = scanner.nextInt();
      scanner.nextLine(); // use newline left over

      switch (choice) {
        case 1:
          reg.addMember();
          break;
        case 2:
          reg.viewMembers();
          break;
        case 3:
          reg.selectMembers();
          break;
        case 4:
          reg.deleteMember();
          break;
        case 5:
          reg.addBoatToMember();
          break;
        case 6:
          reg.selectBoat();
          break;  
        case 7:
          reg.deleteBoatFromMember();
          break;
        case 8:
          reg.saveAndExit();
          return; // at this point program will be stopped and will exit the application.
        default:
          // if user enters an  invalid integer this message will be shown.
          System.out.println("Invalid choice. Please choose again."); 
      }
    }
  }

  public static void main(String[] args) {
    new App().start();
  }
}
