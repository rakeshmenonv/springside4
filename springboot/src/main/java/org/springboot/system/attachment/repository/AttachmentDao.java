package org.springboot.system.attachment.repository;


import java.util.List;

import org.springboot.system.attachment.entity.Attachment;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface AttachmentDao extends PagingAndSortingRepository<Attachment, Long>, JpaSpecificationExecutor<Attachment>  {

	List<Attachment> findByRid(String rid);

}