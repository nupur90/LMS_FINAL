package com.ca.actions;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.SessionAware;

import com.ca.database.Database;
import com.ca.pojo.AddUserByAdmin;
import com.opensymphony.xwork2.ActionSupport;

public class DataForViewOrEditUsers extends ActionSupport implements SessionAware
{
	
	private SessionMap<String, Object> usernameSession;

	AddUserByAdmin selectuser;
	List<AddUserByAdmin> selectUserList;
	
	public DataForViewOrEditUsers() {
		// TODO Auto-generated constructor stub
	}

	public AddUserByAdmin getSelectuser() {
		return selectuser;
	}

	public void setSelectuser(AddUserByAdmin selectuser) {
		this.selectuser = selectuser;
	}

	public List<AddUserByAdmin> getSelectUserList() {
		return selectUserList;
	}

	public void setSelectUserList(List<AddUserByAdmin> selectUserList) {
		this.selectUserList = selectUserList;
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
		usernameSession.put("userNameOfUser", selectuser.getUserName());
		try
		{
			Database database = new Database();
			Connection con = database.Get_Connection();
			PreparedStatement ps = con
					.prepareStatement("SELECT EMPLOYEE_NAME,PASSWORD,ADDRESS,CONTACT_NO,EMAIL,ROLE,date_format(DOB,'%d/%m/%Y') as dateAsBirth from employee_info where USERNAME=?");
			//ps.setString(1,selectuser.getUserName());
			ps.setString(1,(String) usernameSession.get("userNameOfUser"));
			String usernameForData=selectuser.getUserName();
			ResultSet rs = ps.executeQuery();
			selectUserList=new ArrayList<AddUserByAdmin>();
			while(rs.next())
			{
				selectUserList.add(new AddUserByAdmin(rs.getString("EMPLOYEE_NAME"),rs.getString("PASSWORD"),rs.getString("ADDRESS"),rs.getString("CONTACT_NO"),rs.getString("EMAIL"),rs.getString("dateAsBirth"),rs.getString("ROLE")));
			}
			System.out.println("success");
		
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return "success";
	}

	@Override
	public void setSession(Map<String, Object> arg0) {
		// TODO Auto-generated method stub
		usernameSession = (SessionMap) arg0;
		
	}
	

}
