package com.ca.actions;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.SessionAware;
import org.apache.struts2.interceptor.validation.SkipValidation;

import com.ca.database.Database;
import com.ca.pojo.AddUserByAdmin;
import com.opensymphony.xwork2.ActionSupport;

public class UpdateUsersByAdminAction extends ActionSupport implements
		SessionAware {
	AddUserByAdmin updateuser;
	private SessionMap<String, Object> usernameSession;

	public UpdateUsersByAdminAction() {
		// TODO Auto-generated constructor stub
	}

	public AddUserByAdmin getUpdateuser() {
		return updateuser;
	}

	public void setUpdateuser(AddUserByAdmin updateuser) {
		this.updateuser = updateuser;
	}

	public SessionMap<String, Object> getUsernameSession() {
		return usernameSession;
	}

	public void setUsernameSession(SessionMap<String, Object> usernameSession) {
		this.usernameSession = usernameSession;
	}

	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		System.out.println("Here in update");
		try{
		Database database = new Database();
		Connection con = database.Get_Connection();
		PreparedStatement ps = con
				.prepareStatement("UPDATE EMPLOYEE_INFO SET EMPLOYEE_NAME=?,PASSWORD=?,ADDRESS=?,CONTACT_NO=?,EMAIL=?,DOB=?,ROLE=? WHERE USERNAME=?");
		ps.setString(1,updateuser.getFullName());
		ps.setString(2, updateuser.getPassWord());
		ps.setString(3,updateuser.getUserAddress());
		ps.setString(4, updateuser.getUserContact());
		ps.setString(5,updateuser.getUserEmail());
		ps.setString(6, updateuser.getUserBirthDate());
		ps.setString(7, updateuser.getUserRole());
		System.out.println(updateuser.getUserName());
		ps.setString(8, (String) this.getUsernameSession().get("userNameOfUser") );
		ps.executeUpdate();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		System.out.println("Updated na baa....!!");
		return "success";
	}

	@Override
	public void validate() {
		// TODO Auto-generated method stub
		super.validate();
		System.out.println("inside Validation");
		/*
		 * if(updateuser.getFullName().isEmpty()) {
		 * addFieldError("updateuser.fullName", "Please Enter Full Name.."); }
		 * 
		 * if(updateuser.getUserName().isEmpty()) {
		 * addFieldError("updateuser.userName",
		 * "Please Enter Existing Username Of Selected User.."); }
		 * if(updateuser.getPassWord().isEmpty()) {
		 * addFieldError("updateuser.passWord", "Please Enter Password.."); }
		 * if(updateuser.getUserAddress().isEmpty()) {
		 * addFieldError("updateuser.userAddress", "Please Enter Address.."); }
		 * if(updateuser.getUserBirthDate().isEmpty()) {
		 * addFieldError("updateuser.userBirthDate"
		 * ,"Please Enter Birth Date in YYYY-MM-DD Format.."); }
		 * if(updateuser.getUserContact().isEmpty()) {
		 * addFieldError("updateuser.userContact", "Please Enter Contact.."); }
		 * else { String expression = "^\\+?[0-9\\-]+\\*?$"; CharSequence
		 * inputStr = updateuser.getUserContact(); Pattern pattern =
		 * Pattern.compile(expression, Pattern.CASE_INSENSITIVE); Matcher
		 * matcher = pattern.matcher(inputStr); if (!matcher.matches())
		 * addFieldError("adduser.userContact", "Invalid Contact Number.."); }
		 * 
		 * if(updateuser.getUserEmail().isEmpty()) {
		 * addFieldError("updateuser.userEmail", "Please Enter Email ID.."); }
		 * else { String expression =
		 * "^[\\w\\-]([\\.\\w])+[\\w]+@([\\w\\-]+\\.)+[A-Z]{2,4}$"; CharSequence
		 * inputStr = updateuser.getUserEmail(); Pattern pattern =
		 * Pattern.compile(expression, Pattern.CASE_INSENSITIVE); Matcher
		 * matcher = pattern.matcher(inputStr); if (!matcher.matches())
		 * addFieldError("adduser.userEmail", "Invalid Email Address.."); }
		 */
	}

	@Override
	public void setSession(Map<String, Object> arg0) {
		// TODO Auto-generated method stub
		usernameSession = (SessionMap) arg0;
	}

}
