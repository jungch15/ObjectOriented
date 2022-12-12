package homework;

import java.util.Map;
import java.util.Scanner;

public class Signup implements Frontmenu{
	
	private Map<String, String> usermap;
	
	public Signup(Map<String, String> usermap) {
		this.usermap = usermap;
	}
	public boolean execute() {
		@SuppressWarnings("resource")
		Scanner scan = new Scanner(System.in);
		while(true) {
			System.out.println("-------------------------------------------------------------");
			System.out.println("회원가입을 진행할게요");
			System.out.print("원하는 id:");
			String newid = scan.nextLine();
			//만약 해쉬맵으로 아이디 찾아서 존재하면 안된다고 하기
			String truepw = usermap.get(newid);
			if(truepw != null) {
				System.out.println("-------------------------------------------------------------");
				System.out.println("오류! 이미 존재하는 id입니다.");
				System.out.println("다시 설정해주세요");
				System.out.println("-------------------------------------------------------------");
				continue;
			}
			System.out.print("원하는 pw:");
			String newpw = scan.nextLine();
			System.out.print("e-mail:");
			String newemail = scan.nextLine();
			System.out.println("-------------------------------------------------------------");
			//파일에 새로운 id, pw 입력해줌
			Userinfo adduser = new Userinfo();
			adduser.writeUser(newid, newpw,newemail);
			System.out.println("-------------------------------------------------------------");
			System.out.println("회원가입이 완료되었습니다!");
			System.out.println("로그인하고 진행해주세요!");
			System.out.println("-------------------------------------------------------------");
			break;
		}
		return true;
	}
}
