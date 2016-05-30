package main;

import view.Display;

public class Main {
	
	public static Display Battle_Rain;
	//본 프로젝트에서 view에 해당하는 display 클래스를 선언
	
	public Main() {
		//empty
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Battle_Rain = new Display();
		//main 클래스는 display 클래스를 호출 후 종료한다.
	}
}
