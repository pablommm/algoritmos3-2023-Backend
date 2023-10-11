import ar.edu.unsam.algo3.repository.Repositorio

interface iProcesoDeAministracion{
    fun run(program: List<Proceso>, repositorio: Repositorio<Entidad>)

}

class ProcesoDeAdministracion():iProcesoDeAministracion{
    override fun run(program: List<Proceso>, repositorio: Repositorio<Entidad>) {
        program.forEach { instruction -> instruction.execute() }
    }


}