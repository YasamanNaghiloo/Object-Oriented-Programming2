package assignment4;

/**
 * this is the class for boat.
 */
public class Boat {
  private String name;
  private String type;
  private double length;
  private Double depth; // depth can be null.
  private Integer enginePower; // also engine power can be null for some boats.

  /** the constructor for boat.
   *
   * @param name boat name.
   * @param type boat type.
   * @param length length of the boat.
   * @param depth depth of some boats.
   * @param enginePower engine power of some boats.
   */
  public Boat(String name, String type, double length, Double depth, Integer enginePower) {
    this.name = name;
    this.type = type;
    this.length = length;
    this.depth = depth;
    this.enginePower = enginePower;
  }

  public String getName() {
    return name;
  }

  public String getType() {
    return type;
  }

  public double getLength() {
    return length;
  }

  public Double getDepth() {
    return depth;
  }

  public Integer getEnginePower() {
    return enginePower;
  }
}
