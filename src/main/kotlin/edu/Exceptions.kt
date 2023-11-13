import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus
import java.lang.RuntimeException

@ResponseStatus(HttpStatus.BAD_REQUEST)
open class genericException(val msg: String) : RuntimeException(msg)

@ResponseStatus(HttpStatus.BAD_REQUEST)
class BusinessException(msg: String) : RuntimeException(msg)

@ResponseStatus(HttpStatus.NOT_FOUND)
class NotFoundException(msg: String) : RuntimeException(msg)
class ExceptionNombre(msg: String="El nombre no es valido") :genericException(msg)
class ExceptionApellido(msg: String="El apellido  no es valido") :genericException(msg)

class InvalidIdException(mensaje: String) : IllegalArgumentException(mensaje)

class InvalidElementException(mensaje: String) : NullPointerException(mensaje)




