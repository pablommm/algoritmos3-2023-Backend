open class genericException(val msg: String) : RuntimeException(msg)
class ExceptionNombre(msg: String="El nombre no es valido") :genericException(msg)
class ExceptionApellido(msg: String="El apellido  no es valido") :genericException(msg)

class InvalidIdException(mensaje: String) : IllegalArgumentException(mensaje)

class InvalidElementException(mensaje: String) : NullPointerException(mensaje)

