package br.com.rotaractsorocabaleste.rotasales.dataprovider.repository

import br.com.rotaractsorocabaleste.rotasales.core.entity.UserRole
import org.springframework.data.repository.CrudRepository
import java.util.*

interface UserRoleRepository : CrudRepository<UserRole, UUID>