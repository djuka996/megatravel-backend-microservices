package Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.megatravel.model.system_user_info.UserReview;

@Repository
public interface RatingRepository extends JpaRepository<UserReview, Long>{
	
}
