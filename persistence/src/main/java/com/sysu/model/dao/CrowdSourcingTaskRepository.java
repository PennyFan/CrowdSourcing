package com.sysu.model.dao;

import com.sysu.model.crowdsourcing.CrowdSourcingTaskEntity;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by zhengshouzi on 2015/9/7.
 */
public interface CrowdSourcingTaskRepository extends CrudRepository<CrowdSourcingTaskEntity, Long> {

}