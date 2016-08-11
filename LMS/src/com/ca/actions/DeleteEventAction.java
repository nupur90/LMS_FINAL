package com.ca.actions;

import java.sql.Connection;
import java.sql.PreparedStatement;

import com.ca.database.Database;
import com.ca.pojo.Event;
import com.opensymphony.xwork2.ActionSupport;

public class DeleteEventAction extends ActionSupport {
	Event delEvent;

	public DeleteEventAction() {
		// TODO Auto-generated constructor stub
	}

	public Event getDelEvent() {
		return delEvent;
	}

	public void setDelEvent(Event delEvent) {
		this.delEvent = delEvent;
	}

	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		try {
			Database database = new Database();
			Connection con = database.Get_Connection();
			PreparedStatement ps = con
					.prepareStatement("DELETE FROM EVENT WHERE EVENT_ID=?");
			ps.setString(1, delEvent.getEventId());
			int i = ps.executeUpdate();
			if (i > 0) {
				System.out.println("Deleted na baaa..!!");
				return "success";
			} else {
				return "error";
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return "success";
	}

}
