package ru.stqa.pft.sandbox;

public class Main {

  public static void main(String[] args) {

    Point p1 = new Point(1,2);
    System.out.println("Есть точка P1 с координатами x " + p1.x + " и y " + p1.y );

    Point p2 = new Point(5,3);
    System.out.println("Есть точка P2 с координатами x " + p2.x + " и y " + p2.y );

    System.out.println("Расстояние между точками P1 и P2 на плоскоти = " + distance(p1, p2) + "   --Функция");
    System.out.println("Расстояние между точками P1 и P2 на плоскоти = " + p2.distance(p1) + "   --метод");

  }


    public static double distance(Point p1, Point p2) {
    return Math.sqrt((Math.pow(p2.x - p1.x, 2)) + (Math.pow(p2.y - p1.y, 2)));
  }

}

