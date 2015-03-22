package org.starmod.net.handler;

import org.starmod.net.Handler;
import org.starmod.net.NetworkClient;
import org.starmod.net.command.Login;

public class LoginHandler implements Handler<Login> {

    @Override
    public void handle(NetworkClient client, Login cmd) {
        int returnCode;
        String playerName = cmd.getPlayerName();
        System.out.println("[StarMod][Network][Login] Client " + client.getNetworkId() + " attempting to login with username: " + playerName);
        if (!playerName.matches("[a-zA-Z0-9_-]{3,32}")) {
            returnCode = Login.LoginCode.ERROR_INVALID_USERNAME.getCode();
        } else {
            returnCode = Login.LoginCode.SUCCESS_LOGGED_IN.getCode();
        }
        if (returnCode != Login.LoginCode.SUCCESS_LOGGED_IN.getCode()) {
            System.out.println("[StarMod][Network][Login] Invalid login for Client " + client.getNetworkId() + " (" + Login.LoginCode.getById(returnCode).getMessage() + ")");
        } else {
            System.out.println("[StarMod][Network][Login] Logging in Client " + client.getNetworkId() + " with protected username: " + playerName);
        }
        Login returnLogin = new Login(cmd.getHeader(), playerName, cmd.getVersion(), cmd.getAddress(), cmd.getLoginCode(), returnCode, cmd.getUserAgent(), client.getNetworkId());
        client.send(returnLogin);
        client.onLogin(returnLogin);
    }

}
