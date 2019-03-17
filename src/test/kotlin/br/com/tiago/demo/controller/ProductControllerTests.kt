package br.com.tiago.demo.controller

import br.com.tiago.demo.business.ProductBusiness
import br.com.tiago.demo.handler.ExceptionHandler
import com.nhaarman.mockito_kotlin.mock
import org.junit.Before
import org.junit.Test
import org.mockito.MockitoAnnotations
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import org.springframework.test.web.servlet.setup.MockMvcBuilders

class ProductControllerTests {

    private lateinit var productController: ProductController

    private lateinit var mockMvc: MockMvc

    private var productBusiness: ProductBusiness = mock()

    @Before
    fun init() {
        MockitoAnnotations.initMocks(this)
        productController = ProductController(productBusiness)
        mockMvc = MockMvcBuilders.standaloneSetup(productController)
                .setControllerAdvice(ExceptionHandler())
                .build()
    }

    @Test
    fun shouldReturnBadRequestWhenTriesToEditProductWithNoId() {
        mockMvc.perform(
                MockMvcRequestBuilders.put("/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{'description':'teste', 'price':1.99}")
        ).andExpect(MockMvcResultMatchers.status().isBadRequest)
    }

}