package com.eungaehospital.member.repository;

import com.eungaehospital.member.domain.Children;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChildrenRepository extends JpaRepository<Children, Long> {

}
