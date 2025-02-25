package assignment4;

import java.io.IOException;
import java.util.Scanner;

/**
 * Registration class.
 */
public class Registration {

  private final BoatClub boatClub;
  private final Scanner scanner;
  private final FileManager fileManager;

  /**
   * The constructor.
   *
   * @param scanner     scanner for reading user input.
   * @param boatClub    second parameter for member methods.
   * @param fileManager for load and save data.
   */
  protected Registration(Scanner scanner, BoatClub boatClub, FileManager fileManager) {
    this.scanner = scanner; // Use the existing scanner instance
    this.boatClub = boatClub; // Directly use the passed instance
    this.fileManager = new FileManager(fileManager); // Using the copy constructor
  }

  /**
   * Method needs to generate a unique 6 character alphanumeric.
   *
   * @return the unique id.
   */
  public String generateMemberId() {
    return java.util.UUID.randomUUID().toString().substring(0, 6);
  }

  /**
   * adding the member info questions UI method.
   */
  public void addMember() {
    System.out.print("Enter member name: ");
    final String name = scanner.nextLine();
    System.out.print("Enter email (or leave blank): ");
    String email = scanner.nextLine();
    for (Member member : boatClub.getMembers()) {
      if (email.equals(member.getEmail())) {
        System.out.println("This email is already taken try again!");
        return;
      }
    }
    if (email.isEmpty()) {
      email = null;
    }
    String memberId = generateMemberId(); // generate unique id
    Member newMember = new Member(name, email, memberId);
    boatClub.addMember(newMember);
    System.out.println("Member added successfully with ID: " + memberId);
  }

  /**
   * View the list of existing members.
   */
  public void viewMembers() {
    System.out.println("------------------------------------------------");
    for (Member member : boatClub.getMembers()) {
      System.out.println("Name: " + member.getName() + ", Member ID: " + member.getMemberId());
      for (Boat boat : member.getBoats()) {
        System.out.println("  Boat: " + boat.getName());
      }
    }
  }

  /**
   * ability to select a member and see the detailed info.
   */
  public void selectMembers() {
    System.out.print("Enter member ID to view details:");
    String memberId = scanner.nextLine();
    Member member = boatClub.findMemberById(memberId);
    if (member == null) {
      System.out.println("Member not found.");
      return;
    }
    System.out.println(
        "Member ID: " + member.getMemberId()
            + ", Name: " + member.getName() + ", Email: " + member.getEmail());
  }

  /**
   * Delete member UI method.
   */
  public void deleteMember() {
    System.out.print("Enter member ID to delete: ");
    String memberId = scanner.nextLine();
    if (boatClub.removeMember(memberId)) {
      System.out.println("Member deleted successfully.");
    } else {
      System.out.println("Member not found.");
    }
  }

  /**
   * Adding boat to a member UI method.
   */
  public void addBoatToMember() {
    System.out.print("Enter member ID: ");
    final String memberId = scanner.nextLine();
    Member member = boatClub.findMemberById(memberId);
    if (member == null) {
      System.out.println("Member not found.");
      return;
    }
    System.out.print("Enter boat name: ");
    final String name = scanner.nextLine();
    System.out.print("Enter boat type (sailboat, motorboat, motorsailer, canoe): ");
    final String type = scanner.nextLine();
    System.out.print("Enter length: ");
    final double length = scanner.nextDouble();
    scanner.nextLine(); // newline
    Double depth = null;
    Integer enginePower = null;
    if (type.equals("sailboat") || type.equals("motorsailer")) {
      System.out.print("Enter depth: ");
      depth = scanner.nextDouble();
      scanner.nextLine(); // newline
    }
    if (type.equals("motorboat") || type.equals("motorsailer")) {
      System.out.print("Enter engine power: ");
      enginePower = scanner.nextInt();
      scanner.nextLine(); // newline
    }
    Boat newBoat = new Boat(name, type, length, depth, enginePower);
    member.addBoat(newBoat);
    System.out.println("Boat added successfully.");
  }

  /**
   * ability to select a boat and see the detailed info.
   */
  public void selectBoat() {
    System.out.print("Enter member ID to view their Boats: ");
    String memberId = scanner.nextLine();
    Member member = boatClub.findMemberById(memberId);
    if (member == null) {
      System.out.println("Member not found.");
      return;
    }
    for (Boat boat : member.getBoats()) {
      System.out.println("  Boat: " + boat.getName());
    }
    System.out.print("Enter boat name to view details:");
    String boatName = scanner.nextLine();
    Boat boat = boatClub.findBoatByNameForMember(boatName, member);
    if (boat == null) {
      System.out.println("Boat not found.");
      return;
    }
    System.out.println("  Boat: " + boat.getName()
        + ", Type: " + boat.getType() + ", Length: " + boat.getLength()
        + (boat.getDepth() != null ? ", Depth: " + boat.getDepth() : "")
        + (boat.getEnginePower() != null ? ", Engine Power: " + boat.getEnginePower() : ""));
  }

  /**
   * ability to delete a boat from member method.
   */
  public void deleteBoatFromMember() {
    System.out.print("Enter member ID: ");
    String memberId = scanner.nextLine();
    Member member = boatClub.findMemberById(memberId);
    if (member == null) {
      System.out.println("Member not found.");
      return;
    }
    System.out.println("Available boats:");
    for (Boat boat : member.getBoats()) {
      System.out.println(boat.getName());
    }
    System.out.print("Enter boat name to delete: ");
    String boatName = scanner.nextLine();
    if (member.removeBoat(boatName)) {
      System.out.println("Boat deleted successfully.");
    } else {
      System.out.println("Boat not found. Ensure the boat name is entered correctly.");
    }
  }

  /**
   * Saving data and exit UI.
   */
  public void saveAndExit() {
    try {
      fileManager.saveData(boatClub.getMembers());
      System.out.println("Data saved successfully.");
    } catch (IOException e) {
      System.out.println("Error saving data: " + e.getMessage());
    }
    System.out.println("Exiting application.");
  }

}
