package mg.unidev.app.repository;


import mg.unidev.app.entities.Operation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OperationRepository extends JpaRepository<Operation, Long> {

    public Page<Operation> getOperationsByAccount_CodeOrderByDateOperationDesc(String account_code, Pageable pageable);

    @Query("select op from Operation op where op.account.code=:accCd order by op.dateOperation desc ")
    public Page<Operation> listOperation(@Param(value = "accCd") String accountCd, Pageable pageable);

    public List<Operation> findAllByAccount_CodeOrderByDateOperationDesc(String account_code);
}
