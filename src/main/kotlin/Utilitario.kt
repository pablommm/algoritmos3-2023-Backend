import java.time.LocalDate
import java.time.temporal.ChronoUnit

fun Int.esPar(): Boolean = this % 2 == 0
fun LocalDate.edad() = ChronoUnit.YEARS.between(this, LocalDate.now())
fun String.esValido(): Boolean = !this.isEmpty()
fun Int.esValidoNumero():Boolean = this >= 0
