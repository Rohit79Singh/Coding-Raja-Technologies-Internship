package com.banking;

public class User {

	    private String username;
	    private String password;
	    private int userId;

	    public User(String username, String password) {
	        this.username = username;
	        this.password = password;
	    }

		public String getUsername() {
			return username;
		}

		public void setUsername(String username) {
			this.username = username;
		}

		public String getPassword() {
			return password;
		}

		public void setPassword(String password) {
			this.password = password;
		}

		public int getUserId() {
			return userId;
		}

		public void setUserId(int userId) {
			this.userId = userId;
		}

	    
	    
	}
