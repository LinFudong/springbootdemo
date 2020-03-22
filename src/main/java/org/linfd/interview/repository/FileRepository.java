package org.linfd.interview.repository;


import org.linfd.interview.entity.FileDO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface FileRepository extends JpaRepository<FileDO,Long> {


}
