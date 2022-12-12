package homework;

import java.io.*;
import java.util.*;

public class Userinfo {
	//파일에서 유저 정보 받아서 배열로 변환
	public List<List<String>> readUser(){
		List<List<String>> userlist = new ArrayList<List<String>>();
		File csv = new File("./data/user/user.csv");
		BufferedReader br = null;
		String line = "";
		
		try {
			br = new BufferedReader(new FileReader(csv));
			while((line = br.readLine())!=null) {
				List<String> line1 = new ArrayList<String>();
				String[] lineArr = line.split(",");
				line1 = Arrays.asList(lineArr);
				userlist.add(line1);
			}
		} catch(FileNotFoundException e) {
			e.printStackTrace();
		} catch(IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if(br != null) {
					br.close();
				}
			} catch(IOException e) {
				e.printStackTrace();
			}
		}
		//System.out.println(userlist);
		return userlist;
	}
	public void writeUser(String id, String pw,String email) {
		//String ddd = "C:/Users/chan/eclipse-workspace-java/homework";
		File csv = new File("./data/user/user.csv");
		BufferedWriter bw = null;
		try {
			bw = new BufferedWriter(new FileWriter(csv,true));
			//id, pw, email을 string으로 합쳐서 파일에 저장
			String adduser = id+","+pw+","+email;
			bw.write(adduser);
			bw.newLine();
		//각종 예외 처리
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
