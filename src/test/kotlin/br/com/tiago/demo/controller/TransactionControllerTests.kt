package br.com.tiago.demo.controller

import br.com.tiago.demo.business.TransactionBusiness
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

class TransactionControllerTests {

    private lateinit var transactionController: TransactionController

    private lateinit var mockMvc: MockMvc

    private var transactionBusiness: TransactionBusiness = mock()

    @Before
    fun init() {
        MockitoAnnotations.initMocks(this)
        transactionController = TransactionController(transactionBusiness)
        mockMvc = MockMvcBuilders.standaloneSetup(transactionController)
                .setControllerAdvice(ExceptionHandler())
                .build()
    }

    @Test
    fun shouldReturnBadRequestWhenTriesToMakeTransactionWithNoUser() {
        mockMvc.perform(
                MockMvcRequestBuilders.post("/transactions")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{'productId':1}")
        ).andExpect(MockMvcResultMatchers.status().isBadRequest)
    }

    @Test
    fun shouldReturnBadRequestWhenTriesToMakeTransactionWithNoProduct() {
        mockMvc.perform(
                MockMvcRequestBuilders.post("/transactions")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{'userId':1}")
        ).andExpect(MockMvcResultMatchers.status().isBadRequest)
    }

    @Test
    fun shouldReturnBadRequestWhenTriesToMakeTransactionWithNoCard() {
        mockMvc.perform(
                MockMvcRequestBuilders.post("/transactions")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{'userId':1,'productId':1}")
        ).andExpect(MockMvcResultMatchers.status().isBadRequest)
    }

}