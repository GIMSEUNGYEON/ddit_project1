package util;

import java.util.Scanner;

public class ScanUtil {
	static Scanner sc = new Scanner(System.in);
	public static String nextLine() {
		return sc.nextLine();
	}
//   public static String nextLine(String message) {
//      System.out.println(message);
//      return sc.nextLine();
//   }
	public static int nextInt() {
		while(true) {
			try {
				int result = Integer.parseInt(sc.nextLine());
				return result;
			}catch (NumberFormatException e) {
				System.out.println("ÀÔ·Â°ªÀÌ Àß¸øµÆ½À´Ï´Ù.");
			}
		}
	}
}
