import com.gatecheck.gatecheck.model.entity.Student
import org.springframework.data.mongodb.repository.MongoRepository
import java.util.*

interface StudentRepository: MongoRepository<Student,UUID>{
    fun findByName(name:String): Student?
    fun findByUsername(username:String): Student?
    fun findByEmail(email:String):Student?
    fun findById(id:UUID):Student?
}