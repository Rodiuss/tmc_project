package com.project.tmc.repository.product;

import com.project.tmc.model.product.ProductGroup;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;

@Repository
public interface ProductGroupRepository extends CrudRepository<ProductGroup, Long> {
    Optional<ProductGroup> findByName(String name);

    @Query(value = """
            with recursive temp1 (id, parent_group_id, name, path) as (
            select t1.id, t1.parent_group_id, t1.name, cast (t1.name as varchar (100)) as path
            from tmc_product_group t1 where t1.parent_group_id is null
            union
            select t2.id, t2.parent_group_id, t2.name, cast (temp1.path || ' \\ '|| t2.name as varchar(100))
            from tmc_product_group t2 inner join temp1 on (temp1.id = t2.parent_group_id))
            select path from temp1 where id = :id""", nativeQuery = true)
    String findPath(@Param("id") Long id);
}
