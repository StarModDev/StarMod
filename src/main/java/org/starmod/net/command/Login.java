package org.starmod.net.command;

import org.starmod.net.Command;
import org.starmod.net.Header;

public class Login implements Command {

	private final Header header;
	private final String playerName;
	private final float version;
	private final String address;
	private final String loginCode;
	private final byte userAgent;
	private int returnCode;
	private int id;

	public Login(Header header, String playerName, float version, String uid, String loginCode, byte userAgent) {
		this(header, playerName, version, uid, loginCode, 0, userAgent, 0);
	}

	public Login(Header header, String playerName, float version, String address, String loginCode, int returnCode, byte userAgent, int id) {
		this.header = header;
		this.playerName = playerName;
		this.version = version;
		this.address = address;
		this.loginCode = loginCode;
		this.returnCode = returnCode;
		this.userAgent = userAgent;
		this.id = id;
	}

	public Header getHeader() {
		return header;
	}

	public String getPlayerName() {
		return playerName;
	}

	public float getVersion() {
		return version;
	}

	public String getAddress() {
		return address;
	}

	public String getLoginCode() {
		return loginCode;
	}

	public int getReturnCode() {
		return returnCode;
	}

	public byte getUserAgent() {
		return userAgent;
	}

	public int getId() {
		return id;
	}

	@Override
	public boolean isAsync() {
		return false;
	}

	@Override
	public String toString() {
		return "Login{" +
			"header=" + header +
			", playerName='" + playerName + '\'' +
			", version=" + version +
			", address='" + address + '\'' +
			", loginCode='" + loginCode + '\'' +
			", returnCode=" + returnCode +
			", userAgent=" + userAgent +
			", id=" + id +
			'}';
	}

	public enum LoginCode {

		SUCCESS_LOGGED_IN(0, ""),
		ERROR_GENERAL_ERROR(-1, "Server: general error"),
		ERROR_ALREADY_LOGGED_IN(-2, "Server: name already logged in on this server\n\n\n\n(If you are retrying after a socket exception,\nplease wait 3 minutes for the server to time-out your old connection)"),
		ERROR_ACCESS_DENIED(-3, "Server: access denied"),
		ERROR_SERVER_FULL(-4, "Server: this server is full. Please try again later."),
		ERROR_WRONG_CLIENT_VERSION(-5, ""),
		ERROR_YOU_ARE_BANNED(-6, "Server: you are banned from this server"),
		ERROR_AUTHENTICATION_FAILED(-7, "Server Reject: this login name is protected on this server!\n\nYou either aren't logged in via uplink,\nor the protected name belongs to another user!\n\nPlease use the \"Uplink\" menu to authenticate this name!"),
		ERROR_NOT_ON_WHITELIST(-8, "Server: you are not whitelisted on this server"),
		ERROR_INVALID_USERNAME(-9, "Server: your username is not allowed"),
		ERROR_AUTHENTICATION_FAILED_REQUIRED(-10, "Server: this server requires authentication via uplink.\nPlease use the uplink button on startup with your Star-Made.org credentials");

		private final int code;
		private final String msg;

		public static LoginCode getById(final int id) {
			for (LoginCode loginCode : LoginCode.values()) {
			    if (loginCode.getCode() == id) return loginCode;
			}
			throw new IllegalArgumentException("Unknown ID: " + id);
		}

		private LoginCode(int code, String msg) {
			this.code = code;
			this.msg = msg;
		}

		public int getCode() {
			return code;
		}

		public String getMessage() {
			return msg;
		}
	}

}
