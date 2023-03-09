package com.yysw.general;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface AIModelRepository extends JpaRepository<AIModel, Long> {

}