package homework;

import java.util.Scanner;
import java.util.Map;

public class Login implements Frontmenu{
	private String userId;
	private Map<String, String> usermap;
	
	public Login(Map<String, String> usermap) {
		this.usermap = usermap;
	}
	public boolean execute() {
		boolean isuser = false;
		int count = 0;
		@SuppressWarnings("resource")
		Scanner scan = new Scanner(System.in);
		while(true) {
			System.out.println("-------------------------------------------------------------");
			System.out.print("id:");
			String id = scan.nextLine();
			System.out.print("pw:");
			String pw = scan.nextLine();
			//id를 키로, pw를 value로 하는 해쉬맵 
			//탐색해서 있으면 진행 아니면 다시
			count+=1; //5번 틀리면 초기화
			String truepw = this.usermap.get(id);
			if(pw.equals(truepw)) {
				isuser = true;
				setUserId(id);
				break;
			}
			else {
				if(truepw == null){
					System.out.println("-------------------------------------------------------------");
					System.out.println("없는 id입니다.");
					System.out.println("-------------------------------------------------------------");
				}
				else {
					System.out.println("-------------------------------------------------------------");
					System.out.println("pw를 확인하세요");
					System.out.println("-------------------------------------------------------------");
				}
			}
			if(count == 5) {
				
				break;
			}
		}
		//scan.close();
		return isuser;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
}
