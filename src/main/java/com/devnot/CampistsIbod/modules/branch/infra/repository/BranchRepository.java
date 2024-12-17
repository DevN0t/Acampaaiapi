package com.devnot.CampistsIbod.modules.branch.infra.repository;

import com.devnot.CampistsIbod.modules.branch.infra.model.Branch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BranchRepository extends JpaRepository<Branch,Integer> {
}
