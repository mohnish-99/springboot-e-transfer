package springlearning.io.e_transfer_application.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import springlearning.io.e_transfer_application.entity.Transfer;

@Repository
public interface TransferRepository extends JpaRepository<Transfer, String> {
}
