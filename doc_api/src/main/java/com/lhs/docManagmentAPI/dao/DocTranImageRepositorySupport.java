/**
 * 
 */
package com.lhs.docManagmentAPI.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * @author lavanya.badham
 *
 */
@Transactional
@Repository
public class DocTranImageRepositorySupport {

	@Autowired
	DocTranImageRepository repo;
	
	@PersistenceContext
	EntityManager em;
	
	private int result;
	public String imgSql;
	
	
	public DocTranImageRepository getRepository() {
		return this.repo;
	}
	
	public int uploadImage(byte[] imageByte, String ws_seqid, String row_slno, long doc_id,String data_type) {
		System.out.println("inside repo...");
		
		Session hibernateSession = (Session) this.em.unwrap(Session.class);
		String	imgUpdateSql ="";
		
		if(data_type.equalsIgnoreCase("LONG_RAW_JAVA")) {
			imgSql = "update DOC_TRAN_IMAGE  set  doc_image = ?, lastupdate =sysdate  where row_slno =? and ws_seqid=? and doc_id =?";
		}else {
			imgSql = "update DOC_TRAN_IMAGE  set  doc_image_blob = ?, lastupdate =sysdate  where row_slno =? and ws_seqid=? and doc_id =?";
		}	
		
		hibernateSession.doWork(new org.hibernate.jdbc.Work() {
			@Override
			public void execute(Connection connection) throws SQLException {
				PreparedStatement pb = connection.prepareStatement(imgSql);
				pb.setBytes(1, imageByte);
				pb.setString(2, row_slno);
				pb.setString(3, ws_seqid);
				pb.setLong(4, doc_id);
				
				int c = pb.executeUpdate();
				System.out.println("c"+c);
				if(c==1) {
					result=1;
				}
			}
		});
		
		return result;
	}
	
	
	public int insertImage(byte[] imageByte, String ws_seqid, String row_slno, long doc_id,String data_type) {
		
		Session hibernateSession = (Session) this.em.unwrap(Session.class);
		
		if(data_type.equalsIgnoreCase("LONG_RAW_JAVA")) {
			imgSql = "insert into DOC_TRAN_IMAGE (doc_image,doc_id,createddate,lastupdate,ws_seqid,row_slno) values(?,?,sysdate,sysdate,?,?)";
		}else {
			imgSql =  "insert into DOC_TRAN_IMAGE (doc_image_blob,doc_id,createddate,lastupdate,ws_seqid,row_slno) values(?,?,sysdate,sysdate,?,?)";
		}
		
		hibernateSession.doWork(new org.hibernate.jdbc.Work() {
			@Override
			public void execute(Connection connection) throws SQLException {
				PreparedStatement pb = connection.prepareStatement(imgSql);
				pb.setBytes(1, imageByte);
				pb.setLong(2, doc_id);
				pb.setString(3, ws_seqid);
				pb.setString(4, row_slno);
				
				int c = pb.executeUpdate();
				System.out.println("c"+c);
				if(c==1) {
					result=1;
				}
			}
		});
		
		return result;
	}
	
}
