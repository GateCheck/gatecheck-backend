import com.gatecheck.gatecheck.model.entity.Parent
import org.springframework.data.mongodb.repository.MongoRepository
import java.util.*

interface ParentRepository: MongoRepository<Parent,UUID>{
    fun findByName(name:String): Parent?
    fun findByUsername(username:String): Parent?
    fun findByEmail(email:String):Parent?
    fun findById(id:UUID):Parent?
}