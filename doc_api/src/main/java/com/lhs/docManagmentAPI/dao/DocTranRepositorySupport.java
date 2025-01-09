package com.lhs.docManagmentAPI.dao;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import com.lhs.docManagmentAPI.model.entity.DocTran;
import com.lhs.docManagmentAPI.model.entity.dto.DashboardAllDataDTO;
import com.lhs.docManagmentAPI.model.entity.dto.DashboardFilterDTO;
import com.lhs.docManagmentAPI.model.entity.dto.UploadFormDTO;
import com.lhs.docManagmentAPI.util.Utility;

@Transactional
@Repository
public class DocTranRepositorySupport {

	@PersistenceContext
	  EntityManager em;
	  
	  @Autowired
	  Utility utl;
	  
	  @Autowired
	  public DocTranRepository docTranrepo;
	
	  public List<DashboardAllDataDTO> getAllDocsDashboardList(DashboardFilterDTO dto, String userCode,String indexPath) {
		    List<DashboardAllDataDTO> list = null;
		    
		    try {
		    	
		    	 String query ="select t.doc_type,\r\n"
		    	 		+ "t.to_file_name ,\r\n"
		    	 		+ "t.doc_desc, \r\n"
		    	 		+ "t.file_code,\r\n"
		    	 		+ "to_char(t.createddate, 'dd-MM-rrrr HH:mi:ss AM') created, \r\n"
		    	 		+ "to_char(t.reviewdate, 'dd-MM-rrrr HH:mi:ss AM') reviewed, \r\n"
		    	 		+ "t.doc_code,\r\n"
		    	 		+ "t.doc_date,\r\n"
		    	 		+ "t.vrno,\r\n"
		    	 		+ "t.doc_id,\r\n"
		    	 		+ "t.slno\r\n"
		    	 		+ "from doc_tran t where t.doc_id='1614'";
		    	 
		    	 System.out.println("query.22.." + query);
			     Query q = this.em.createNativeQuery(query, DashboardAllDataDTO.class);
			     list = q.getResultList();
		    	System.out.println("list."+list);
		    }catch(Exception e) {
		    	e.printStackTrace();
		    }
		    
		    return (list != null && list.size() > 0) ? list : null;   
	  }
	  
	  
	  public List<DocTran> getAllDocsDashboardList1(DashboardFilterDTO dto, String userCode,String indexPath){
		  List<DocTran>  list  =null;
		  try {
System.out.println("DTO--------->"+dto.getDocCode()+",,"+dto.getDoc_type());
		  String query = " select DOC_TYPE,\r\n"
		  		+ "       DOC_CODE,\r\n"
		  		+ "       DOC_DESC,\r\n"
		  		+ "       DOC_ID,\r\n"
		  		+ "       ENTITY_CODE,\r\n"
		  		+ "       DIV_GROUP,\r\n"
		  		+ "       DIV_CODE,\r\n"
		  		+ "       COST_CODE,\r\n"
		  		+ "       PERIOD_CODE,\r\n"
		  		+ "       ACC_YEAR,\r\n"
		  		+ "       TNATURE,\r\n"
		  		+ "       SERIES,\r\n"
		  		+ "       TCODE,\r\n"
		  		+ "       VRNO,\r\n"
		  		+ "       VRDATE,\r\n"
		  		+ "       REF_COLUMN_NAME,\r\n"
		  		+ "       REF_COLUMN_VALUE,\r\n"
		  		+ "       DOC_DATE,\r\n"
		  		+ "       DOC_REFNO,\r\n"
		  		+ "       EXPIRY_DATE,\r\n"
		  		+ "       DOC_TAGS,\r\n"
		  		+ "       DOC_MEDIUM,\r\n"
		  		+ "       FILE_FLAG,\r\n"
		  		+ "       FILE_CODE,\r\n"
		  		+ "       FROM_FILE_PATH,\r\n"
		  		+ "       FROM_FILE_NAME,\r\n"
		  		+ "       TO_FILE_PATH,\r\n"
		  		+ "       TO_FILE_NAME,\r\n"
		  		+ "       WS_SEQID,\r\n"
		  		+ "       OBJECT_CODE,\r\n"
		  		+ "       REVIEWBY,\r\n"
		  		+ "       REVIEWDATE,\r\n"
		  		+ "       UPLOADEDBY,\r\n"
		  		+ "       UPLOADEDDATE,\r\n"
		  		+ "       CREATEDBY,\r\n"
		  		+ "       CREATEDDATE,\r\n"
		  		+ "       ENTRY_ROWID_SEQ,\r\n"
		  		+ "       USER_CODE,\r\n"
		  		+ "       LASTUPDATE,\r\n"
		  		+ "       FLAG,\r\n"
		  		+ "       CVRDATE,\r\n"
		  		+ "       AMENDNO,\r\n"
		  		+ "       ROW_SLNO,\r\n"
		  		
		  		+ "       rownum slno,\r\n"
		  		+ "       DOC_TYPE_NAME\r\n"
		  		+ "  from (select t.doc_type,\r\n"
		  		+ "               t.doc_code,\r\n"
		  		+ "               t.doc_desc,\r\n"
		  		+ "               t.doc_id,\r\n"
		  		+ "               t.entity_code,\r\n"
		  		+ "               t.div_group,\r\n"
		  		+ "               t.div_code,\r\n"
		  		+ "               t.cost_code,\r\n"
		  		+ "               t.period_code,\r\n"
		  		+ "               t.acc_year,\r\n"
		  		+ "               t.tnature,\r\n"
		  		+ "               t.series,\r\n"
		  		+ "               t.tcode,\r\n"
		  		+ "               t.vrno,\r\n"
		  		+ "               t.vrdate,\r\n"
		  		+ "               t.ref_column_name,\r\n"
		  		+ "               t.ref_column_value,\r\n"
		  		+ "               t.doc_date,\r\n"
		  		+ "               t.doc_refno,\r\n"
		  		+ "               t.expiry_date,\r\n"
		  		+ "               t.doc_tags,\r\n"
		  		+ "               t.doc_medium,\r\n"
		  		+ "               t.file_flag,\r\n"
		  		+ "               t.file_code,\r\n"
		  		+ "               t.from_file_path,\r\n"
		  		+ "               t.from_file_name,\r\n"
		  		+ "               t.to_file_path,\r\n"
		  		+ "               t.to_file_name,\r\n"
		  		+ "               t.ws_seqid,\r\n"
		  		+ "               t.object_code,\r\n"
		  		+ "               t.reviewby,\r\n"
		  		+ "               t.reviewdate,\r\n"
		  		+ "               t.uploadedby,\r\n"
		  		+ "               t.uploadeddate,\r\n"
		  		+ "               t.createdby,\r\n"
		  		+ "               t.createddate,\r\n"
		  		+ "               t.entry_rowid_seq,\r\n"
		  		+ "               t.user_code,\r\n"
		  		+ "               t.lastupdate,\r\n"
		  		+ "               t.flag,\r\n"
		  		+ "               t.cvrdate,\r\n"
		  		+ "               t.amendno,\r\n"
		  		+ "               t.row_slno, \r\n"
		  		+ "					null slno,"
		  		+ "               (select doc_type_name from doc_type_mast where doc_type = t.doc_type) doc_type_name\r\n"
		  		+ "          from doc_tran t\r\n"
		  		+ "         where t.doc_code = '"+dto.getDocCode()+"'\r\n"
		  		+ "           AND t.doc_type = '"+dto.getDoc_type()+"'\r\n"
		  		+ "         order by doc_id desc)\r\n";

		  
		  System.out.println("query : "+query);
		  Query q = this.em.createNativeQuery(query, DocTran.class);
		     list = q.getResultList();
		  }catch(Exception e) {
			  e.printStackTrace();
		  }
		  
		  return list;
	  }
	  
	  
		public Long getMaxDocId() {
			Long list = null;
			try {
				String query = "SELECT nvl((max(DOC_ID)+1),1) FROM DOC_TRAN T ";
				System.out.println("query..." + query);
				Query q = this.em.createNativeQuery(query);
				BigDecimal num = (BigDecimal) q.getSingleResult();
				list = Long.valueOf(num.longValue());
				System.out.println("list.."+list);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return list;
		}

		public Long getNextSeqDocTranId() {
			BigDecimal seq ;
			Long str=null;
			String query = "select doc_id_seq.nextval from dual";
			try {
				seq =(BigDecimal)em.createNativeQuery(query).getSingleResult();
				if(seq!=null) {
					str = seq.longValue();
				}
			}catch(Exception e) {
				e.printStackTrace();
			}
			System.out.println("max docId.."+str);
			
			return str;
			
		}
		
		
		
//		public boolean saveNewDocMastTranAndImage(UploadFormDTO dto) {
//			System.out.println("In New Method-------------");
//			boolean result = false;
//			System.out.println("dto.."+dto);
//			try {
//				if (dto != null) {
//					if ( dto.getFiles() != null && (dto.getFiles()).length > 0) {
//						int count = 0;
//						for (Long i = 0l; i < dto.getFiles().length; i++) {
//							Date d1 = null;
//							String newFileName=null;
//							
//							if (dto.getExpiryDate() != null && (dto.getExpiryDate()).length > 0) {
//								try {
//									String dateStr = dto.getExpiryDate()[count];
//									if (!dateStr.equals("NA"))
//										d1 = (new SimpleDateFormat("dd-MM-yyyy")).parse(dto.getExpiryDate()[count]);
//								} catch (Exception e) {
//									e.printStackTrace();
//								}
//								DocTran doclist =  new DocTran();
//								doclist.setDoc_type(dto.getDoc_type());
//								doclist.setDoc_code(dto.getDoc_code());
//								doclist.setDoc_desc(dto.getDoc_details());
//								doclist.setDoc_id(getNextSeqDocTranId());
//								doclist.setEntity_code(dto.getEntity_code());
//								doclist.setDiv_code(dto.getDiv_code());
//								doclist.setAcc_year(dto.getAcc_year());
//								doclist.setDoc_date(new Date());
//								doclist.setFrom_file_name(dto.getFiles()[count].getOriginalFilename());
//								if(dto.getDoc_code() != null && dto.getDoc_type() != null && dto.getUser_code() != null) {
//									String FileName=dto.getDoc_type()+"_"+dto.getDoc_code()+"_"+dto.getUser_code();
//							        String fileExtension = getFileExtension(dto.getFiles()[count].getOriginalFilename());
//							        doclist.setTo_file_name(FileName+"."+fileExtension);
//								}
//								doclist.setTo_file_path("C:\\X\\U\\MAN_W\\PURCB\\");
//								
//								doclist.setDoc_type(dto.getDoc_type());
//								doclist.setCreatedby(dto.getUser_code());
//								doclist.setCreateddate(new Date());
//								doclist.setLastupdate(new Date());
//								if (dto.getFileCode() != null && (dto.getFileCode()).length > 0
//										&& !dto.getFileCode()[count].equalsIgnoreCase("NA"))
//									doclist.setFile_code(dto.getFileCode()[count]);
//
//								File f = convert(dto.getFiles()[count]);
//								MultipartFile file1 = dto.getFiles()[count];
//							//	boolean newFileResult = false;
//
//								DocTran docObj = (DocTran) this.docTranrepo.save(doclist);
//								System.out.println("docObj-------->"+docObj);
//								if (docObj != null) 
//									result = true;
//								System.out.println("OLD DOC TRAN RESULT----->" + result);
//							String docTranImageQuery = "insert into doc_tran_image(doc_id,doc_image,createdby,createddate,user_code,lastupdate) values(?,?,?,?,?,?)";
//							byte[] byteArr = dto.getFiles()[count].getBytes();
//							InputStream inputStream = new ByteArrayInputStream(byteArr);
//							FileInputStream in = new FileInputStream(f);
//							Session hibernateSession = (Session) this.em.unwrap(Session.class);
//							Integer res = Integer.valueOf(0);
//							String I = (i.longValue() + 1L) + "";
//							ArrayList<Boolean> rest = new ArrayList<>();
//							int C = count;
//							hibernateSession.doWork(new org.hibernate.jdbc.Work() {
//
//								@Override
//								public void execute(Connection connection) throws SQLException {
//									rest.clear();
//									PreparedStatement pb = connection.prepareStatement(docTranImageQuery);
//									pb.setString(1, dto.getDoc_code());
//									pb.setBinaryStream(2,in, (int)f.length());
//									pb.setString(3, dto.getUser_code());
//									pb.setDate(4, new java.sql.Date(new Date().getTime()));
//									pb.setString(5, dto.getUser_code());
//									pb.setDate(6,new java.sql.Date(new Date().getTime()));
//									int res = pb.executeUpdate();
//									System.out.println("res------>"+res);
//									rest.add(res > 0);
//								}
//							});
//							count++;
//							result = (rest.size() > 0) ? ((Boolean) rest.get(0)).booleanValue() : false;
//							}
//							
//						}
//					}else {
//						
//						System.out.println("In Else Block");	
//					}
//					
//					
//					
//				}
//				
//							
////						}
////						}
////					}
////				}
//			}catch(Exception e) {
//				e.printStackTrace();
//			}
//			
//			return result ;
//		}
		
		public boolean saveNewDocMastTranAndImage(UploadFormDTO dto) {
			System.out.println("In save document Method-------------");
			boolean result = false;
			System.out.println("dto.......... in save method---->"+dto);
			try {
				if (dto != null) {
					if ( dto.getFiles() != null && (dto.getFiles()).length > 0) {
						int count = 0;
						for (Long i = 0l; i < dto.getFiles().length; i++) {
							Date d1 = null;
							
							if (dto.getExpiryDate() != null && (dto.getExpiryDate()).length > 0) {
								try {
									String dateStr = dto.getExpiryDate()[count];
									if (!dateStr.equals("NA"))
										d1 = (new SimpleDateFormat("dd-MM-yyyy")).parse(dto.getExpiryDate()[count]);
								} catch (Exception e) {
									e.printStackTrace();
								}
								DocTran doclist =  new DocTran();
								doclist.setDoc_type(dto.getDoc_type());
								doclist.setDoc_code(dto.getDoc_code());
								doclist.setDoc_desc(dto.getDoc_details());
								doclist.setDoc_id(getNextSeqDocTranId());
								doclist.setEntity_code(dto.getEntity_code());
								doclist.setDiv_code(dto.getDiv_code());
								doclist.setAcc_year(dto.getAcc_year());
								doclist.setDoc_date(new Date());
								doclist.setFrom_file_name(dto.getFiles()[count].getOriginalFilename());
								if(dto.getDoc_code() != null && dto.getDoc_type() != null && dto.getUser_code() != null) {
									String FileName=dto.getDoc_type()+"_"+dto.getDoc_code()+"_"+dto.getUser_code();
							        String fileExtension = getFileExtension(dto.getFiles()[count].getOriginalFilename());
							        doclist.setTo_file_name(FileName+"."+fileExtension);
								}
								doclist.setTo_file_path("C:\\X\\U\\MAN_W\\PURCB\\");
								doclist.setUser_code(dto.getUser_code());
								doclist.setDoc_type(dto.getDoc_type());
								doclist.setCreatedby(dto.getUser_code());
								doclist.setCreateddate(new Date());
								doclist.setLastupdate(new Date());
								doclist.setWs_seqid(getWsSeqId());
								doclist.setObject_code("LJDOC001");
								if (dto.getFileCode() != null && (dto.getFileCode()).length > 0
										&& !dto.getFileCode()[count].equalsIgnoreCase("NA"))
									doclist.setFile_code(dto.getFileCode()[count]);

								File f = convert(dto.getFiles()[count]);
								MultipartFile file1 = dto.getFiles()[count];

								DocTran docObj = (DocTran) this.docTranrepo.save(doclist);
								System.out.println("docObj-------->"+docObj);
								if (docObj != null) 
									result = true;
							String docTranImageQuery = "insert into doc_tran_image(doc_id,doc_image,createdby,createddate,user_code,lastupdate) values(?,?,?,?,?,?)";
							byte[] byteArr = dto.getFiles()[count].getBytes();
							InputStream inputStream = new ByteArrayInputStream(byteArr);
							FileInputStream in = new FileInputStream(f);
							Session hibernateSession = (Session) this.em.unwrap(Session.class);
							Integer res = Integer.valueOf(0);
							String I = (i.longValue() + 1L) + "";
							ArrayList<Boolean> rest = new ArrayList<>();
							int C = count;
							hibernateSession.doWork(new org.hibernate.jdbc.Work() {

								@Override
								public void execute(Connection connection) throws SQLException {
									rest.clear();
									PreparedStatement pb = connection.prepareStatement(docTranImageQuery);
									pb.setString(1, docObj.getDoc_id().toString());
									pb.setBinaryStream(2,in, (int)f.length());
									pb.setString(3, dto.getUser_code());
									pb.setDate(4, new java.sql.Date(new Date().getTime()));
									pb.setString(5, dto.getUser_code());
									pb.setDate(6,new java.sql.Date(new Date().getTime()));
									int res = pb.executeUpdate();
									rest.add(res > 0);
								}
							});
							count++;
							result = (rest.size() > 0) ? ((Boolean) rest.get(0)).booleanValue() : false;
							}
							
						}
					}else {
						
						System.out.println("In Else Block");	
					}
				}
			}catch(Exception e) {
				e.printStackTrace();
			}
			
			return result ;
		}

		
		public synchronized File convert(MultipartFile file) throws IOException {
			File convFile = new File(file.getOriginalFilename());
			convFile.createNewFile();
			FileOutputStream fos = new FileOutputStream(convFile);
			fos.write(file.getBytes());
			fos.close();
			return convFile;
		}
		
		
		private static String getFileExtension(String file) {
			 if (file == null) {
		            return "";  // or throw an exception, depending on your requirements
		        }

		        int dotIndex = file.lastIndexOf('.');
		        
		        if (dotIndex == -1 || dotIndex == file.length() - 1) {
		            return ""; // No file extension found
		        }

		        return file.substring(dotIndex + 1);
		    }
	    
	  
//	 public List<DashboardAllDataDTO> getAllDocsDashboardList(DashboardFilterDTO dto, String userCode,String indexPath) {
//		    List<DashboardAllDataDTO> list = null;
//		    try {
//		      String query = "select rownum slno,\r\n    t.doc_search_str,   t.file_name,\r\n       t.doc_type,\r\n       t.doc_name,\r\n       t.doc_desc,\r\n       t.DOC_TYPE_NAME,\r\n       t.file_code,\r\n       to_char(t.file_date, 'dd-MM-rrrr HH:mi:ss AM') created,\r\n       to_char(t.review_date, 'dd-MM-rrrr HH:mi:ss AM') reviewed,\r\n       t.doc_code,\r\n       t.ref_key,\r\n       t.REF_KEY_INFO,\r\n       t.key_code,\r\n       t.key_mast,\r\n       t.doc_detail, t.doc_date, t.vrno,  t.doc_slno ,       (case when t.doc_expiry_date < sysdate then 'E' when  t.doc_expiry_date between sysdate and sysdate+5 then 'O' end) as expiry_flag\r\n  from view_doc_tran t where 1=1 ";
//		      System.out.println("dto123.." + dto);
//		      if (dto != null) {
//		        if (!this.utl.isNull(dto.getDocCode()))
//		          query = query + " and t.doc_code='" + dto.getDocCode() + "'"; 
//		        if (!this.utl.isNull(dto.getKeyCode()))
//		          query = query + " and t.key_code='" + dto.getKeyCode() + "'"; 
//		        if (!this.utl.isNull(dto.getTnature()))
//		          query = query + " and t.tnature='" + dto.getTnature() + "'"; 
//		        if (!this.utl.isNull(dto.getTnature()))
//		          query = query + " and t.tnature='" + dto.getTnature() + "'"; 
//		        if (!this.utl.isNull(dto.getRef_key()))
//		          query = query + " and t.ref_key='" + dto.getRef_key() + "'"; 
//		        if (!this.utl.isNull(dto.getFile_code()))
//		          query = query + " and t.FILE_CODE='" + dto.getFile_code() + "'"; 
//		        if (!this.utl.isNull(dto.getSoftFileName()))
//		          query = query + "AND UPPER(t.FILE_NAME) LIKE upper('%" + dto.getSoftFileName() + "%')"; 
//		        if (!this.utl.isNull(dto.getDoc_type()))
//		          query = query + " and t.doc_type='" + dto.getDoc_type() + "'"; 
//		        if (!this.utl.isNull(dto.getExpiryDays()))
//		          query = query + " and (t.doc_expiry_date between sysdate and sysdate+" + dto.getExpiryDays() + ")"; 
//		        if (this.utl.isNull(dto.getDoc_type()) && dto.getDoc_type_array() != null && (dto.getDoc_type_array()).length > 0) {
//		          query = query + " and t.doc_type in(";
//		          for (int i = 0; i < (dto.getDoc_type_array()).length; i++) {
//		            if (i != (dto.getDoc_type_array()).length - 1) {
//		              query = query + "'" + dto.getDoc_type_array()[i] + "',";
//		            } else {
//		              query = query + "'" + dto.getDoc_type_array()[i] + "')";
//		            } 
//		          } 
//		        }
//		        if(!utl.isNull(dto.getKeyword())) {
//		        	 query = query + "AND UPPER(t.doc_search_str) LIKE upper('%" + dto.getKeyword() + "%')"; 
//		        }
//		        
//		        if (!this.utl.isNull(dto.getBreadcumDocType()))
//		          query = query + " and t.doc_type in(select j.doc_type\r\n  from doc_type_mast j\r\n start with j.doc_type='" + dto.getBreadcumDocType() + "'\r\n connect by j.parent_code = prior j.doc_type)"; 
//		        if (!this.utl.isNull(dto.getDoc_name()))
//		          query = query + " and upper(t.doc_name) like upper('%" + dto.getDoc_name() + "%')"; 
//		        if (!this.utl.isNull(dto.getDoc_detail()))
//		          query = query + " and upper(t.doc_desc) like upper('%" + dto.getDoc_detail() + "%')"; 
//		        if (!this.utl.isNull(dto.getCaptured_by()))
//		          query = query + " and t.user_code='" + dto.getCaptured_by() + "'"; 
//		        if (!this.utl.isNull(dto.getReviewed_by()))
//		          query = query + " and t.review_by='" + dto.getReviewed_by() + "'"; 
//		        if (!this.utl.isNull(dto.getAcc_year_code()))
//		          query = query + " and t.acc_year='" + dto.getAcc_year_code() + "'"; 
//		        if (!this.utl.isNull(dto.getEntity_code()))
//		          query = query + " and t.entity_code='" + dto.getEntity_code() + "'"; 
//		        if (!this.utl.isNull(dto.getDiv_code()))
//		          query = query + " and t.div_code='" + dto.getDiv_code() + "'"; 
//		        if (!this.utl.isNull(dto.getDate_type())) {
//		          if (dto.getDate_type().equalsIgnoreCase("R") && !this.utl.isNull(dto.getFrom_date()) && !this.utl.isNull(dto.getTo_date()))
//		            query = query + " and to_date(t.review_date,'dd-MM-rrrr') between to_date('" + dto.getFrom_date() + "','dd-MM-rrrr') and   to_date('" + dto.getTo_date() + "','dd-MM-rrrr') "; 
//		          if (dto.getDate_type().equalsIgnoreCase("C") && !this.utl.isNull(dto.getFrom_date()) && !this.utl.isNull(dto.getTo_date()))
//		            query = query + " and to_date(t.doc_date,'dd-MM-rrrr') between to_date('" + dto.getFrom_date() + "','dd-MM-rrrr') and   to_date('" + dto.getTo_date() + "','dd-MM-rrrr') "; 
//		          if (!dto.getDate_type().equalsIgnoreCase("E") || this.utl.isNull(dto.getFrom_date()) || !this.utl.isNull(dto.getTo_date()));
//		        } 
//		        
//		        
//		        if(!utl.isNull(dto.getContentSearch())) {
//		        	String test="";
//		        	List<String> str= new ContentSearch().Searcher(dto.getContentSearch(), indexPath);
//		        	
//		        	if(str!=null && str.size()>0) {
//		        		int i=0;
//		        		for (String s : str) {
//		        			test+="('"+s.split("#")[0]+"','"+s.split("#")[1]+"')";
//		        			if(i!= str.size()-1) {
//		        				test+=",";
//		        			}
//							i++;
//						}
//		        		query = query+" and (t.doc_code,t.doc_slno) in ("+test+")";
//		        	}else {
//		        		query = query+" and (t.doc_code,t.doc_slno) in (('',''))";
//		        	}
//		        	
//		        	
//		        }
//		        
//		        
//		        
//		        
//		        
//		        
//		        query = query + " and t.doc_type in(select doc_type\r\nfrom doc_type_mast T\r\nSTART WITH instr((select  ' '||m.doc_type_str||' '  from user_mast m where m.user_code='" + userCode + "'),' '||t.DOC_TYPE||' ')<>0\r\nCONNECT BY doc_type = PRIOR parent_code) ";
//		        if (!this.utl.isNull(dto.getRecent_flag()) && dto.getRecent_flag().equalsIgnoreCase("Y"))
//		          query = "select * from (" + query + " order by lastupdate desc) where slno < 21 order by slno"; 
//		      } 
//		      
//		      System.out.println("query.22.." + query);
//		      Query q = this.em.createNativeQuery(query, DashboardAllDataDTO.class);
//		      list = q.getResultList();
//		    } catch (Exception e) {
//		      e.printStackTrace();
//		    } 
//		    return (list != null && list.size() > 0) ? list : null;
//		  }
		
		
		public Long getWsSeqId() {
			Long wsseq_id = null;
			try {
				String query = "select ws_seqid.nextval from dual";
				System.out.println("query..." + query);
				Query q = this.em.createNativeQuery(query);
				BigDecimal num = (BigDecimal) q.getSingleResult();
				wsseq_id = Long.valueOf(num.longValue());
				System.out.println("wsseq_id.."+wsseq_id);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return wsseq_id;
		}
}
