package homework;

import java.io.*;
import java.util.Scanner;

public class Article {
	private String src;        //어떤 글을 볼것인지
	private String userId;     //id가 뭔지 - 글 작성에 필요
	private String boardName;  //어떤 게시판인지
	
	public Article(String openfile, String userId, String boardName){
		this.setSrc(openfile);
		this.userId = userId;
		this.boardName = boardName;
	}
	
	//게시글 작성 함수
	public void writeArticle() {
		File file = new File("./data/board/" + this.boardName + "/article/" + this.getSrc()+".txt");
		BufferedWriter bw = null;
		try {
			@SuppressWarnings("resource")
			Scanner scan = new Scanner(System.in);
			bw = new BufferedWriter(new FileWriter(file,true));
			System.out.println("-------------------------------------------------------------");
			System.out.println("작성그만하기:0");
			System.out.println("-------------------------------------------------------------");
			String addline = "";
			bw.write(this.src);
			bw.newLine();
			bw.write(this.userId);
			bw.newLine();
			while(true) {
				addline = scan.nextLine();
				if(addline.equals("0")) {
					break;
				}
				bw.write(addline);
				bw.newLine();
			}
			
		//파일이 없거나, 입출력예외 발생하면 실행
		} catch(FileNotFoundException e) {
			e.printStackTrace();
		} catch(IOException e){
			e.printStackTrace();
		} finally {
			try {
				if(bw!=null) {
					//버퍼 비워주고 닫아주기
					bw.flush();
					bw.close();
				}
			} catch(IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	//게시판 정보 중, 게시글 출력 
	public void readArticle() {
		String user = null;
		String title = null;
		int i=0;
		try{
            //파일 객체 생성
			File file = new File("./data/board/" + this.boardName + "/article/" + this.getSrc());
            //입력 스트림 생성
            FileReader filereader = new FileReader(file);
            //입력 버퍼 생성
            BufferedReader bufReader = new BufferedReader(filereader);
            String line = "";
            //게시글이 작성되는 구조에 따라 i로 작성자, 제목, 내용을 구분해주었다.
            while((line = bufReader.readLine()) != null){
            	if(i==0) {
            		title=line;
            	}
            	else if(i == 1) {
            		user=line;
            	}
            	else if(i==2){
            		this.userTitle(title, user);
            		System.out.println(line);
            	}
            	else {
            		System.out.println(line);
            	}
            	i++;
                
            }
            //.readLine()은 끝에 개행문자를 읽지 않는다.            
            bufReader.close();
        }catch (FileNotFoundException e) {
            e.printStackTrace();
        }catch(IOException e){
            e.printStackTrace();
        }
		while(true) {
			@SuppressWarnings("resource")
			Scanner scan = new Scanner(System.in);
			System.out.println("-------------------------------------------------------------");
			System.out.println("뒤로가기 :0, 댓글달기: wc, 댓글보기: rc");
			System.out.println("-------------------------------------------------------------");
			System.out.print("입력: ");
			
			String order = scan.nextLine();
			if(order.equals("wc")) {
				this.putComment();
			}
			else if(order.equals("0")){
				break;
			}
			else if(order.equals("rc")){
				System.out.println("#############################");
				this.readComment();
			}
			else {
				System.out.println("잘못된 입력입니다!");
			}
		}
	}
	
	public void userTitle(String title, String user){
		System.out.println("#############################");
		System.out.println("제목: " + title);
		System.out.println("작성자: " + user);
		System.out.println("#############################");
		
	}
	//게시글에 댓글 달기 
	public void putComment() {
		File file = new File("./data/board/" + this.boardName + "/comment/" + this.getSrc());
		BufferedWriter bw = null;
		
		try {
			@SuppressWarnings("resource")
			Scanner scan = new Scanner(System.in);
			//텍스트 파일이 존재하면 이어쓰고 없으면 새로 만듦
			bw = new BufferedWriter(new FileWriter(file,true));
			System.out.println("-------------------------------------------------------------");
			System.out.println("작성그만하기:0");
			System.out.println("-------------------------------------------------------------");
			String addline = "";
			bw.write(this.userId);
			bw.newLine();
			while(true) {
				addline = scan.nextLine();
				if(addline.equals("0")) {
					break;
				}
				bw.write(addline);
				bw.newLine();
			}
			bw.write("");
			bw.newLine();
			
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
	public void readComment() {
		try{
            //파일 객체 생성
            File file = new File("./data/board/" + this.boardName + "/comment/" + this.getSrc());
            if(!file.exists()) {
            	//파일이 존재하지 않으면 댓글이 없는 것 
    			System.out.println("댓글이 없어요!");
    			return;
    		}
            //입력 스트림 생성
            FileReader filereader = new FileReader(file);
            //입력 버퍼 생성
            BufferedReader bufReader = new BufferedReader(filereader);
            String line = "";
            int i=0;
            while((line = bufReader.readLine()) != null){
            	if(i == 0) {
            		System.out.print("작성자: ");
            	}
            	if(line.equals("")) {
            		System.out.println("#############################");
            		i=-1;
            	}
            	else {
            		System.out.println(line);
            	}
            	
                i++;
            }
            //.readLine()은 끝에 개행문자를 읽지 않는다.            
            bufReader.close();
        }catch (FileNotFoundException e) {
            e.printStackTrace();
        }catch(IOException e){
            e.printStackTrace();
        }
	}
	public String getSrc() {
		return src;
	}
	public void setSrc(String src) {
		this.src = src;
	}
}
