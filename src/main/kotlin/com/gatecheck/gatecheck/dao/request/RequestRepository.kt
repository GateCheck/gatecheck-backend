import com.gatecheck.gatecheck.model.entity.Request
import org.springframework.data.mongodb.repository.MongoRepository
import java.util.*

interface RequestRepository: MongoRepository<Request,UUID>{
    fun findById(id:UUID):Request?
}