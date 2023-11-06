package dao;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import database.ConnectToDB;
import entity.Customer;

public class CustomerDaoo {
	public int countCus() {
		int count=0;
		try (
			var con = ConnectToDB.getConn();
			var cs = con.prepareCall("{call countCus}");
			var rs = cs.executeQuery();
		){
			while(rs.next()) {
				count = rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
//			JOptionPane.showMessageDialog(null, e.getMessage()); 
		}
		return count;
	}
	public List<Customer> getListCus(int pageNumber, int rowsofPage){
		List<Customer> list = new ArrayList<>();
		
		try (
			var con = ConnectToDB.getConn();
		){
			var cs = con.prepareCall("{call selCus(?,?)}");
			cs.setInt(1, pageNumber);
			cs.setInt(2, rowsofPage);
			var rs= cs.executeQuery();
			while(rs.next()) {
				var cus = new Customer();
				cus.setIdCus(rs.getInt("idCus"));
				cus.setFullname(rs.getString("fullname"));
				cus.setGender(rs.getBoolean("gender"));
				cus.setPicture(rs.getString("picture"));
				cus.setDob(rs.getDate("dob").toLocalDate());
				list.add(cus);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	public void updateCus(Customer cus) {
		try( var con = ConnectToDB.getConn();
				var cs = con.prepareStatement("update Customer set fullname = ?,gender= ? , picture=?,dob=? where idCus = ?");
				) {
			cs.setString(1,cus.getFullname());
			cs.setBoolean(2,cus.isGender());
			cs.setString(3, cus.getPicture());
			cs.setDate(4, java.sql.Date.valueOf(cus.getDob()));
			cs.setInt(5, cus.getIdCus());
			if(cs.executeUpdate()>0) {
				JOptionPane.showMessageDialog(null, "success");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
