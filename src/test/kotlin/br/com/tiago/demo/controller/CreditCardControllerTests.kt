package br.com.tiago.demo.controller

import br.com.tiago.demo.business.CreditCardBusiness
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

class CreditCardControllerTests {

    private lateinit var creditCardController: CreditCardController

    private lateinit var mockMvc: MockMvc

    private var creditCardBusiness: CreditCardBusiness = mock()

    @Before
    fun init() {
        MockitoAnnotations.initMocks(this)
        creditCardController = CreditCardController(creditCardBusiness)
        mockMvc = MockMvcBuilders.standaloneSetup(creditCardController)
                .setControllerAdvice(ExceptionHandler())
                .build()
    }

    @Test
    fun shouldReturnBadRequestWhenTriesToEditCardWithNoId() {
        mockMvc.perform(
                MockMvcRequestBuilders.put("/cards")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{}")
        ).andExpect(MockMvcResultMatchers.status().isBadRequest)
    }

}