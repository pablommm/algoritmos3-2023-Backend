package ar.edu.unsam.algo3.bootstrap

import Confederacion
import Seleccion
import ar.edu.unsam.algo3.repository.RepoSeleccion
import org.springframework.beans.factory.InitializingBean
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class WorldCappBootstrap: InitializingBean {

    @Autowired(required=true)
    lateinit var repoSelecciones: RepoSeleccion
    val argentina = Seleccion("Argentina",Confederacion.CONCACAF,1,1)
    val brasil = Seleccion("brasil",Confederacion.CONCACAF,1,1)
    val alemania = Seleccion("alemania",Confederacion.CONCACAF,1,1)
    val francia = Seleccion("francia",Confederacion.CONCACAF,1,1)

    val seleciones = listOf(argentina,brasil,alemania,francia)

    fun crearSeleciones(){
        seleciones.forEach { repoSelecciones.create(it) }
    }

    override fun afterPropertiesSet() {
        crearSeleciones()
    }

}