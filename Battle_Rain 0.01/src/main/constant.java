package main;

public class constant {

	public static enum STATE { Login, Wait, Room, Playing};

	public final static String F_Title = "Battle Rain";
	public final static int F_Width = 815;
	public final static int F_Height = 635;

	public final static String V_Route = "display_Set.";

	public final static String[] L_I_Route = {
		"rsc/image/Login/background.gif",
		"rsc/image/Login/login_0.gif",
		"rsc/image/Login/login_1.gif",
		"rsc/image/Login/join_0.gif",
		"rsc/image/Login/join_1.gif",
		"rsc/image/Login/exit_0.gif",
		"rsc/image/Login/exit_1.gif"
	};
	
	public final static String[] W_I_Route = {
		"rsc/image/Wait/background.gif",
		"rsc/image/Wait/search_0.gif",
		"rsc/image/Wait/search_1.gif",
		"rsc/image/Wait/make_0.gif",
		"rsc/image/Wait/make_1.gif",
		"rsc/image/Wait/logout_0.gif",
		"rsc/image/Wait/logout_1.gif",
		"rsc/image/Wait/exit_0.gif",
		"rsc/image/Wait/exit_1.gif"
	};
	
	public final static String[] C_I_Route = {
		"rsc/image/Chara/00.gif",
		"rsc/image/Chara/01.gif",
		"rsc/image/Chara/02.gif",
		"rsc/image/Chara/03.gif",
		"rsc/image/Chara/04.gif",
		"rsc/image/Chara/05.gif",
		"rsc/image/Chara/06.gif",
		"rsc/image/Chara/07.gif"
	};
	
	public final static String[] R_I_Route = {
		"rsc/image/Room/background.gif",
		"rsc/image/Room/wordfeild.gif",
		"rsc/image/Room/start_0.gif",
		"rsc/image/Room/start_1.gif",
		"rsc/image/Room/ready_0.gif",
		"rsc/image/Room/ready_1.gif"
	};
}
