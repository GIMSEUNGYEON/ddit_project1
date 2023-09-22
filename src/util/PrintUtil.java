package util;
import View.View;

public class PrintUtil {
	public void bar() {
		System.out.println("\n=============================================");
	}

	public void bar2() {
		System.out.print("=============================================");

	}
	
	public void direction1() {
		System.out.println("�� A     D ��");
	}

	public void blank(int line) {
		for (int i = 0; i < line; i++) {
			System.out.println("");
		}
	}

	public void intro() {
		String[] team = { "��", "  ", "��", "T��", "TE��", "TEE��", "TE��", "TEA_", "TEAM��", "TEAM1" };
		String[] name = { "����", "���", "�褵_", "��¡�", "��¿�", "��¿�,", "��¿�, ��", "��¿�, ���", "��¿�, ����_", "��¿�, ������",
						  "��¿�, ������, ", "��¿�, ������, ����", "��¿�, ������, ��_", "��¿�, ������, �ڤ�_", "��¿�, ������, �ڿ��", "��¿�, ������, �ڿ줸��",
						  "��¿�, ������, �ڿ���", "��¿�, ������, �ڿ���, ", "��¿�, ������, �ڿ���, ��_", "��¿�, ������, �ڿ���, ��_", "��¿�, ������, �ڿ���, ��",
						  "��¿�, ������, �ڿ���, ", "��¿�, ������, �ڿ���, ��", "��¿�, ������, �ڿ���, ����_", "��¿�, ������, �ڿ���, ������",
						  "��¿�, ������, �ڿ���, ������, ", "��¿�, ������, �ڿ���, ������, ��_", "��¿�, ������, �ڿ���, ������, �ۡ�", "��¿�, ������, �ڿ���, ������, ����_",
						  "��¿�, ������, �ڿ���, ������, ������_", "��¿�, ������, �ڿ���, ������, ������" };
		for (int i = 0; i < team.length; i++) {
			Utility.wait(400);
			bar();
			blank(3);
			System.out.println(team[i]);
			blank(5);
			bar2();
		}
		for (int i = 0; i < name.length; i++) {
			Utility.wait(250);
			bar();
			blank(3);
			System.out.println(team[9]);
			System.out.println(name[i]);
			blank(4);
			bar2();
		}
		Utility.wait(800);
		bar();
		blank(3);
		System.out.println(team[9]);
		System.out.println(name[30]);
		System.out.print("START ��");
		blank(2);
		bar();
		System.out.print("Enter �Է����� ���� ");
		ScanUtil.nextLine(); //���� �Է����� ����
	}

	public int main() {

		bar();
		blank(1);
		System.out.println("\t\t������絵����");
		System.out.println("\t\t1.�α���");
		System.out.println("\t\t2.ȸ������");
		System.out.println("\t\t3.�Խ�Ʈ���");
		blank(1);

		blank(1);
		bar2();
		System.out.print("\n\t\t >>�Է�  ");
		blank(1);
		switch(ScanUtil.nextInt()) {
		case 1 : return View.MEMBER_LOGIN;
		case 2 : return View.MEMBER_SIGNUP;
		case 3 : return View.GUEST;
		default : return View.ERROR;
		}
		
	}

	public void login() {
		
		bar();
		blank(1);
		System.out.println("\t\t������絵����");
		System.out.println("\t\tID : ");
		System.out.println("\t\tPW : ");

		blank(1);

		blank(2);
		bar2();
		System.out.print("\n>>�Է�");
		ScanUtil.nextLine();
	}

	public void loading() {
		String[] loading = { "��   ��   ��   ", "��   ��   ��   .", "��   ��   ��   .  .", "��   ��   ��   .  .  .",
							 "��   ��   ��   .", "��   ��   ��   .  .", "��   ��   ��   .  .  ." };
		for (int i = 0; i < loading.length; i++) {
			Utility.wait(400);
			bar();
			blank(3);
			System.out.println(loading[i]);
			blank(5);
			bar2();
		}
	}
}
