package View;

public interface View {
	final int MAIN = 0; // �α��� OR ȸ������ ȭ��
	final int GUEST = 111;
	final int HOME = 1; // �α��� �� �¼� ����, �Խ��� �� Ȯ�� ȭ��
	final int ADMIN_HOME = 11; // �α��� �� �¼� ����, �Խ��� �� Ȯ�� ȭ��
	final int ERROR = 999;
	// ȸ�����
	final int MEMBER = 2;
	final int MEMBER_SIGNUP = 21; // ȸ������
	final int MEMBER_LOGIN = 22; // �α���
	final int MEMBER_INQUIRY = 23; // ���������ȸ
	final int MEMBER_ADMIN_INQUIRY = 231; // ���������ȸ
	final int MEMBER_UPDATE = 24; // �����������
	final int MEMBER_WITHDRAW = 25; // ���Ż��
	final int MEMBER_LOGOUT = 26; // ���Ż��
	final int MEMBER_LIST = 27;
	// ���� ���
	final int RESERVATION = 3; // �Խ�, ��� ����
	final int RESERVATION_RNO = 311; // ��
	final int RESERVATION_SNO = 312;
	final int RESERVATION_IN = 31;
	final int RESERVATION_OUT = 32;
	final int RESERVATION_OUT_ADMIN = 321;
	
//	int RESERVATION_ADD = 33; //����
	
	// ���� ���
	final int BOARD = 4;
	final int BOARD_LIST = 41;
	final int BOARD_INSERT = 42;
	final int BOARD_DELETE = 43;
	final int BOARD_UPDATE = 44;
	final int BOARD_DETAIL = 45;
	
}
