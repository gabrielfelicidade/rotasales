package br.com.rotaractsorocabaleste.rotasales.dataprovider.repository

import br.com.rotaractsorocabaleste.rotasales.core.entity.User
import org.springframework.data.repository.CrudRepository
import java.util.*

interface UserRepository : CrudRepository<User, UUID>