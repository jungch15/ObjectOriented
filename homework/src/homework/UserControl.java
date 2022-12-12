package homework;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class UserControl extends Userinfo{
	
	public void controlBoard() { //관리자의 유저 정보 변경
		while(true) {
			System.out.println("-------------------------------------------------------------");
			List<List<String>> userlist = this.readUser();
			for(int i=0;i<userlist.size();i++) {
				List<String> user = userlist.get(i);
				String id = user.get(0);
				String pw = user.get(1);
				System.out.println("ID: " + id + "  " + "PW: " + pw);
			}
			System.out.println("-------------------------------------------------------------");
			System.out.println("0:뒤로가기, 1:사용자 추가, 2: 사용자 제거, 3: 사용자 수정");
			System.out.println("-------------------------------------------------------------");
			@SuppressWarnings("resource")
			Scanner scan = new Scanner(System.in);
			System.out.print("입력: ");
			String order = scan.nextLine();
			if(order.equals("0")) {
				break;
			}
			else if(order.equals("1")) {
				System.out.print("ID: ");
				String newid = scan.nextLine();
				System.out.print("PW: ");
				String newpw = scan.nextLine();
				System.out.print("Email: ");
				String newemail = scan.nextLine();
				this.writeUser(newid, newpw,newemail);
			}
			else if(order.equals("2")) {
				System.out.print("ID: ");
				String removeId = scan.nextLine();
				this.removeUser(removeId);
			}
			else if(order.equals("3")) {
				System.out.print("ID: ");
				String reviseId = scan.nextLine();
				System.out.println("-------------------------------------------------------------");
				System.out.println("어떤 것을 수정하고 싶은가요?");
				System.out.println("1: pw");
				System.out.println("-------------------------------------------------------------");
				System.out.print("입력: ");
				String option = scan.nextLine();
				System.out.print("수정할 값:");
				String value = scan.nextLine();
				this.reviseUser(option, reviseId, value);
			}
		}
		
	}
	//사용자 제거함수
	public void removeUser(String removeId) {
		Frontpage fp = new Frontpage();
		Map<String, String> usermap = fp.userload();
		//해쉬맵으로 key값을 넣어서 존재하는지 찾아봄
		if(usermap.containsKey(removeId)) {
			usermap.remove(removeId);
		}
		else {
			System.out.println("-------------------------------------------------------------");
			System.out.println("없는 id입니다.");
			System.out.println("-------------------------------------------------------------");
			return;
		}
		//유저 파일을 수정하기 위해 접속
		File csv = new File("./data/user/user.csv");
		BufferedWriter bw = null;
		try {
			bw = new BufferedWriter(new FileWriter(csv));
			//iterator을 사용하여 해쉬맵에 있는 것들을 모두 출력
			Iterator<Map.Entry<String, String>> entry = usermap.entrySet().iterator();
		    while (entry.hasNext()) {
		        Map.Entry<String, String> element = entry.next();
		        String adduser = element.getKey()+","+element.getValue();
		        //새롭게 덮어쓰기
				bw.write(adduser);
				bw.newLine();
		    }
		    
			
		} catch(FileNotFoundException e) {
			e.printStackTrace();
		} catch(IOException e){
			e.printStackTrace();
		} finally {
			try {
				if(bw!=null) {
					bw.flush();
					bw.close();
				}
			} catch(IOException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	//사용자 수정함수
	public void reviseUser(String option, String reviseId, String value) {
		Frontpage fp = new Frontpage();
		Map<String, String> usermap = fp.userload();
		if(usermap.containsKey(reviseId)) {
			//key값에 대한 value 교체
			usermap.replace(reviseId, value);
		}
		else {
			System.out.println("-------------------------------------------------------------");
			System.out.println("없는 id입니다.");
			System.out.println("-------------------------------------------------------------");
			return;
		}
		//밑의 코드는 새롭게 유저정보 수정하는 것
		File csv = new File("./data/user/user.csv");
		BufferedWriter bw = null;
		try {
		
			bw = new BufferedWriter(new FileWriter(csv));
			Iterator<Map.Entry<String, String>> entry = usermap.entrySet().iterator();
		    while (entry.hasNext()) {
		        Map.Entry<String, String> element = entry.next();
		        String adduser = element.getKey()+","+element.getValue();
				bw.write(adduser);
				bw.newLine();
		    }
		    
			
		} catch(FileNotFoundException e) {
			e.printStackTrace();
		} catch(IOException e){
			e.printStackTrace();
		} finally {
			try {
				if(bw!=null) {
					bw.flush();
					bw.close();
				}
			} catch(IOException e) {
				e.printStackTrace();
			}
		}
		
	}
}
