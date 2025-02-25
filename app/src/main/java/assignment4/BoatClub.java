package assignment4;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * BoatClub class taking care of members' operations in theboat club.
 */
public class BoatClub {
  private List<Member> members;
  private List<Boat> boats;

  public BoatClub() {  // constructor
    this.members = new ArrayList<>();
    this.boats = new ArrayList<>();
  }

  // copy constructor
  public BoatClub(BoatClub other) {
    this.members = new ArrayList<>(other.members);
    this.boats = new ArrayList<>(other.boats);
  }

  public void addMember(Member member) {  // add meber to the members arraylist.
    members.add(member);
  }

  public boolean removeMember(String memberId) {  //remove the member form members arraylist by ID.
    return members.removeIf(member -> member.getMemberId().equals(memberId));
  }

  /**
   * search for members by their id.
   *
   * @param memberId gets members id as a parameter.
   *
   * @return returns the desired member.
   */
  public Member findMemberById(String memberId) {
    return members.stream()  //streaming all existing members.
        .filter(member -> member.getMemberId().equals(memberId))  //filter by id.
        .findFirst() // find the first one tht matches.
        .orElse(null);
  }

  /**
   * this method finds boats of the members by its' name.
   *
   * @param boatName a string for boat's name.
   *
   * @param member obvious member parameter.
   *
   * @return returnes the desired boat.
   */
  public Boat findBoatByNameForMember(String boatName, Member member) {
    return member.getBoats().stream()  //streaming all boats that a member have.
        .filter(boat -> boat.getName().equalsIgnoreCase(boatName))  // filter them by boatName.
        .findFirst() //find the first one.
        .orElse(null);
  }



  public List<Member> getMembers() {
    return Collections.unmodifiableList(new ArrayList<>(members));
  }

  public void setMembers(List<Member> members) {
    this.members = new ArrayList<>(members);
  }
}
