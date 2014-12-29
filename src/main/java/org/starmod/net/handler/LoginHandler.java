package org.starmod.net.handler;

import org.starmod.net.Client;
import org.starmod.net.Handler;
import org.starmod.net.command.Login;

public class LoginHandler implements Handler<Login> {

	@Override
	public void handle(Client client, Login cmd) {
		int returnCode;
		String playerName = cmd.getPlayerName();
		System.out.println("[StarMod][Network][Login] Client " + client.getId() + " attempting to login with username: " + playerName);
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
		    System.out.println("[StarMod][Network][Login] Invalid login for Client " + client.getId() + " (" + Login.LoginCode.getById(returnCode).getMessage() + ")");
		} else {
		    System.out.println("[StarMod][Network][Login] Logging in Client " + client.getId() + " with protected username: " + playerName);
		}
		Login returnLogin = new Login(cmd.getHeader(), playerName, cmd.getVersion(), cmd.getUid(), cmd.getLoginCode(), returnCode, client.getId());
		client.send(returnLogin);
		client.onLogin(cmd);
	}

}
