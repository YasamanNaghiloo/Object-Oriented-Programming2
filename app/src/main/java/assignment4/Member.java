package assignment4;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * member class.
 */
public class Member {
  private String name;
  private String email; // the email can be null and is unique.
  private String memberId; //unique member id.
  private List<Boat> boats;

  /**
   * the constructor.
   *
   * @param name member name.
   *
   * @param email member email.
   *
   * @param memberId member id.
   *
   */
  public Member(String name, String email, String memberId) {
    this.name = name;
    this.email = email;
    this.memberId = memberId;
    this.boats = new ArrayList<>();
  }

  // add boats to the boats list.
  public void addBoat(Boat boat) {
    boats.add(boat);
  }

  // ignore case when comparing boat names.
  public boolean removeBoat(String boatName) {
    return boats.removeIf(boat -> boat.getName().equalsIgnoreCase(boatName));
  }

  public String getName() {
    return name;
  }

  public String getEmail() {
    return email;
  }

  public String getMemberId() {
    return memberId;
  }

  public List<Boat> getBoats() {
    return Collections.unmodifiableList(boats);
  }
}
