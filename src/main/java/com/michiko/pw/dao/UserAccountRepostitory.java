package com.michiko.pw.dao;

import org.springframework.data.repository.PagingAndSortingRepository; 
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import com.michiko.pw.entities.UserAccount;

@RepositoryRestResource(collectionResourceRel="apiusers", path="apiusers")
public interface UserAccountRepostitory extends PagingAndSortingRepository<UserAccount, Long>{		
}
