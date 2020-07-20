package com.deonbabushka.dronbabushka.repos;

import com.deonbabushka.dronbabushka.entities.ChildToParent;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChildToParentRepo extends CrudRepository<ChildToParent, Long> {
    ChildToParent findOneByParentId(Long parentId);
    ChildToParent findOneByChildId(Long parentId);

}
