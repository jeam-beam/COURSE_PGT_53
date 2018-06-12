package ru.stqa.pft.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

public class PointTests {

  @Test
  public void testPoint1() {
    Point p1 = new Point(1, 2);
    Point p2 = new Point(5, 3);
    Assert.assertEquals(p2.distance(p1),4.123105625617661);
  }

  @Test
  public void testPoint2() {
    Point p1 = new Point(1, 2);
    Point p2 = new Point(5, 3);
    Assert.assertNotEquals(p2, p1);
  }

  @Test
  public void testPoint3() {
    Point p1 = new Point(1, 2);
    Point p2 = new Point(5, 3);
    Assert.assertNotNull(p1);
    Assert.assertNotNull(p2);

  }

}
