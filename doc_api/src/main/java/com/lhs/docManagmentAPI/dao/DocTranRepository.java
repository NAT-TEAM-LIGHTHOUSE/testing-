package com.lhs.docManagmentAPI.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.lhs.docManagmentAPI.model.entity.DocTran;

@Repository
public interface DocTranRepository extends JpaRepository<DocTran , Long>{

	
	
	@Query("select doc_id as doc_slno from DocTran t where t.row_slno=:row_slno and t.ws_seqid=:ws_seqid")
	public Long findDocId(Float row_slno, Long ws_seqid);
	
	@Query("select t from DocTran t where t.row_slno=:row_slno and t.ws_seqid=:ws_seqid")
	public DocTran findbySeqId(Float row_slno, Long ws_seqid);
}

//select Length(T.ROW_SLNO),Replace(T.ROW_SLNO,7,''),T.doc_id from doc_tran t where t.ws_seqid=9516001 --and t.row_slno=1.002  