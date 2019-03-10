package br.com.tiago.demo.repository

import br.com.tiago.demo.entity.ProductEntity
import org.springframework.data.repository.CrudRepository

interface ProductRepository: CrudRepository<ProductEntity, Long>

