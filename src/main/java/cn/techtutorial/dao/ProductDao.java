package cn.techtutorial.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.*;
import cn.techtutorial.model.*;



public class ProductDao {
private Connection con;
	
	private String  query;
	
	private PreparedStatement pst;
	
	private ResultSet rs;

	public ProductDao(Connection con) {
		
		this.con = con;
	}

	public List<Product> getAllproducts(){
		
		List<Product> products =new ArrayList<Product>();
		try {
			
			query="select * from products";
			pst=this.con.prepareStatement(query);
			rs=pst.executeQuery();
			
			while(rs.next()) {
				Product row=new Product();
				row.setId(rs.getInt("id"));
				row.setName(rs.getString("name"));
				row.setCategory(rs.getString("category"));
				row.setPrice(rs.getDouble("price"));
				row.setImage(rs.getString("image"));
				
				products.add(row);
			}
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return products;
	}
	
	
	public List<Cart> getCartProducts(ArrayList<Cart> cartList){
		List<Cart> products=new ArrayList<Cart>();
		
		try {
			
			if(cartList.size() > 0) {
				for(Cart item:cartList) {
					query="select * from products where id=?";
					pst= this.con.prepareStatement(query);
					pst.setInt(1, item.getId());
					rs=pst.executeQuery();
					while(rs.next()) {
						Cart row= new Cart();
						row.setId(rs.getInt("id"));
						row.setName(rs.getString("name"));
						row.setCategory(rs.getString("category"));
						row.setPrice(rs.getDouble("price")*item.getQuantity());
						row.setQuantity(item.getQuantity());
						products.add(row);
						
					}
				}
			}
			
			
			
		}catch(Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			
		}
		return products;
		}
	
	public Product getSingleProduct(int id) {
		Product   row = null;
		
		
		try {
			
			query="select * from  products where id=? ";
			pst=this.con.prepareStatement(query);
			pst.setInt(1, id);
			rs=pst.executeQuery();
			
			
			while(rs.next()) {
				
				row=new Product();
				row.setId(rs.getInt("id"));
				row.setName(rs.getString("name"));
				row.setCategory(rs.getString("category"));
				row.setPrice(rs.getDouble("price"));
				row.setImage(rs.getString("image"));
				
				
			}
			
			
			
		}catch(Exception e) {
			
			e.printStackTrace();
			
			
		}
		
		return row;
		
		
		
		
	}
	
	public double getTotalCartPrice(ArrayList<Cart>  cartList) {
		double sum=0;
		
		try {
			
			if(cartList.size()>0) {
				for(Cart item:cartList) {
					
					query="select price from products where id=?";
					pst=this.con.prepareStatement(query);
					pst.setInt(1,item.getId());
					rs=pst.executeQuery();
					
					while(rs.next()) {
						sum+=rs.getDouble("price")*item.getQuantity();
						
					}
					
				}
				
			}
			
			
			
		}catch(Exception e) {
			e.printStackTrace();
			
		}
		
		return sum;
		 
	}
	
	public List<Product> getCartBySearch(String ch){
		 List<Product> list=new ArrayList<Product>();
		 Product pp=null;
		 
		 try {
			 
			 String query="select * from products where name like ? or category like ?";
			 pst=this.con.prepareStatement(query);
			 pst.setString(1, "%"+ch+"%");
			 pst.setString(2, "%"+ch+"%");
			 
			 ResultSet rs=pst.executeQuery();

			 while(rs.next()) {
				 pp=new Product();
				 pp.setId(rs.getInt(1));
				 pp.setName(rs.getString(2));
				 pp.setCategory(rs.getString(3));
				 pp.setPrice(rs.getDouble(4));
				 pp.setImage(rs.getString(5));
				 list.add(pp);
				
			 }
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return list;
		
	}
	
}
