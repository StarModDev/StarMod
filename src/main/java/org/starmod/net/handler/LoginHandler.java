package org.starmod.net.handler;

import org.starmod.ModClient;
import org.starmod.net.Handler;
import org.starmod.net.command.Login;

public class LoginHandler implements Handler<Login> {

	@Override
	public void handle(ModClient modClient, Login cmd) {
		int returnCode;
		String playerName = cmd.getPlayerName();
		System.out.println("[StarMod][Network][Login] Client " + modClient.getNetworkId() + " attempting to login with username: " + playerName);
		if (playerName.length() <= 0) {
		    returnCode = Login.LoginCode.ERROR_INVALID_USERNAME.getCode();
		} else if (playerName.length() > 32) {
		    returnCode = Login.LoginCode.ERROR_INVALID_USERNAME.getCode();
		} else if (playerName.length() <= 2) {
		    returnCode = Login.LoginCode.ERROR_INVALID_USERNAME.getCode();
		} else if (playerName.matches("[_-]+")) {
		    returnCode = Login.LoginCode.ERROR_INVALID_USERNAME.getCode();
		} else if (!playerName.matches("[a-zA-Z0-9_-]+")) {
		    returnCode = Login.LoginCode.ERROR_INVALID_USERNAME.getCode();
		} else {
		    returnCode = Login.LoginCode.SUCCESS_LOGGED_IN.getCode();
		}
		if (returnCode != Login.LoginCode.SUCCESS_LOGGED_IN.getCode()) {
		    System.out.println("[StarMod][Network][Login] Invalid login for Client " + modClient.getNetworkId() + " (" + Login.LoginCode.getById(returnCode).getMessage() + ")");
		} else {
		    System.out.println("[StarMod][Network][Login] Logging in Client " + modClient.getNetworkId() + " with protected username: " + playerName);
		}
		Login returnLogin = new Login(cmd.getHeader(), playerName, cmd.getVersion(), cmd.getAddress(), cmd.getLoginCode(), returnCode, cmd.getUserAgent(), modClient.getNetworkId());
		modClient.send(returnLogin);
		modClient.onLogin(cmd);
	}

}
