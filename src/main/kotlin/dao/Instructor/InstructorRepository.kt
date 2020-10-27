import com.gatecheck.gatecheck.model.entity.Instructor
import org.springframework.data.mongodb.repository.MongoRepository
import java.util.*

interface InstructorRepository: MongoRepository<Instructor,UUID>{
    fun findByName(name:String): Instructor?
    fun findByUsername(username:String): Instructor?
    fun findByEmail(email:String):Instructor?
    fun findById(id:UUID):Instructor?
}