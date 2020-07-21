package com.deonbabushka.dronbabushka.repos;

import com.deonbabushka.dronbabushka.entities.UserToUser;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserToUserRepo extends CrudRepository<UserToUser, Long> {
    UserToUser findOneByParentId(Long parentId);
    UserToUser findOneByChildId(Long parentId);

}
