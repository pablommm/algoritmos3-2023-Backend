import org.uqbar.geodds.Point

class Direccion(
    val ubicacion: Point = Point(0,0),
    val provincia: String = "",
    val localidad: String = "",
    val calle: String = "",
    val altura: Int = 0

) {
    fun obtenerCoordenadaX(): Double {
        return ubicacion.x
    }
    fun obtenerCoordenadaY(): Double {
        return ubicacion.y
    }
    fun distancia(direccion: Direccion): Double {
        return ubicacion.distance(direccion.ubicacion)
    }
    fun esValido(): Boolean =
        ubicacion != null && provincia.isNotEmpty() && localidad.isNotEmpty() && calle.isNotEmpty() && altura > 0
}