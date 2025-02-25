package assignment4;

import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * This is a class for saving and loading data.
 */
public class FileManager {
  private final String fileName;

  public FileManager(String fileName) {
    this.fileName = fileName;
  }

  // Copy constructor
  public FileManager(FileManager other) {
    this.fileName = other.fileName;
  }

  /**
   * a method for saving new data.
   *
   * @param members gets a list of members as a parameter.
   *
   * @throws IOException if file had any problems.
   */
  public void saveData(List<Member> members) throws IOException {
    // use FileOutputStream to overwrite data instead of appending
    try (BufferedWriter writer = new BufferedWriter(
        new OutputStreamWriter(new FileOutputStream(fileName, false), StandardCharsets.UTF_8))) {
      for (Member member : members) {
        // this is how mwmber's details will be written.
        writer.write("MEMBER:" + member.getName() + ":" + member.getEmail()
            + ":" + member.getMemberId());
        writer.newLine();
        for (Boat boat : member.getBoats()) {
          // Write boat details
          writer.write("BOAT:" + boat.getName() + ":" + boat.getType() + ":" + boat.getLength());
          if (boat.getDepth() != null) {
            writer.write(":" + boat.getDepth());
          }
          if (boat.getEnginePower() != null) {
            writer.write(":" + boat.getEnginePower());
          }
          writer.newLine();
        }
      }
      writer.flush(); // Ensure all data is written to the file
    }
  }

  /**
   * a method to load the data.
   *
   * @return all the members and info they have.
   *
   * @throws FileNotFoundException if file had any probems.
   */
  public List<Member> loadData() throws FileNotFoundException {
    List<Member> members = new ArrayList<>();
    try (Scanner scanner = new Scanner(new FileInputStream(fileName), StandardCharsets.UTF_8)) {
      Member currentMember = null;
      // this is how data will be loaded.
      while (scanner.hasNextLine()) {
        String line = scanner.nextLine();
        if (line.startsWith("MEMBER:")) {
          String[] parts = line.split(":");
          currentMember = new Member(parts[1], parts[2].isEmpty() ? null : parts[2], parts[3]);
          members.add(currentMember);
        } else if (line.startsWith("BOAT:") && currentMember != null) {
          String[] parts = line.split(":");
          String boatName = parts[1];
          String boatType = parts[2];
          double length = Double.parseDouble(parts[3]);
          Double depth = null;
          Integer enginePower = null;

          if (boatType.equals("sailboat") || boatType.equals("motorsailer")) {
            depth = Double.parseDouble(parts[4]);
            if (boatType.equals("motorsailer") && parts.length > 5) {
              enginePower = Integer.parseInt(parts[5]);
            }
          } else if (boatType.equals("motorboat") && parts.length > 4) {
            enginePower = Integer.parseInt(parts[4]);
          }

          Boat newBoat = new Boat(boatName, boatType, length, depth, enginePower);
          currentMember.addBoat(newBoat);
        }
      }
    }
    return members;
  }
}
