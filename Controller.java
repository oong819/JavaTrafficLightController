package vn.stvk.java.advtrflcn;
// ⚠require Java 1.7!
/*
 * 1 or 2 digits (0-99) output are countdown seconds
 *
 * 3 digits output start with number 1 are light signal
 *
 * 3 digits output start with number 2 are error and/or exception, when error and/or exception occur, yellow light signal will be sent to prevent dangerous light malfunction
 * 3 digits output start with number 0 mean System.exit(0); will be called
 */
// 211: Java Exception | 200: Java Error
import java.util.Scanner;

final class Controller {
  // DO NOT use 3 digits count down seconds!
  // also this controller count down to 1 and change signal
  private static boolean input;
  private static byte currentCountDown = 5;
  private static final byte redCountDown = 5;
  private static final byte yellowCountDown = 2;
  private static final byte greenCountDown = 6;
  private static byte signalState = 110; // 110red|111yellow|112green

  public static void main(final String[] args) {
    Scanner sc = new Scanner(System.in);
    input = sc.nextBoolean();
    if (input) {
      sc.close();
      System.out.println(000);
      System.gc();
      System.exit(0);
    } else {
      try {
        try {
          while (currentCountDown >= 0) {
            System.out.println(currentCountDown);
            delay(1000);
            currentCountDown--;

            if (currentCountDown == 0) {
              changeSignal();
              System.out.println(signalState);
              // throw new Error("\nJust testing!");
            }
          }

        } catch (Exception e) {
          System.out.println(111);
          System.out.println(211);
          delay(1000);
          System.out.println("Java Exception: \n" + e);
        }
      } catch (Error e) {
        System.out.println(111);
        System.out.println(200);
        delay(1000);
        System.out.println("Java Error: \n" + e);
      }
    }
    System.gc();
  }

  private static void delay(int sec) {
    try {
      Thread.sleep(sec);
      System.gc();
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    }
  }

  private static void changeSignal() {
    switch (signalState) {
      case 110:
        signalState = 112;
        currentCountDown = greenCountDown;
        break;
      case 111:
        signalState = 110;
        currentCountDown = redCountDown;
        break;
      case 112:
        signalState = 111;
        currentCountDown = yellowCountDown;
        break;
    }
  }
}
