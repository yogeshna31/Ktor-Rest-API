package com.example.route

import com.example.data.model.Employee
import com.example.data.model.EmployeeRequest
import com.example.module
//import com.fasterxml.jackson.databind.MappingIterator
//import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
//import com.fasterxml.jackson.module.kotlin.readValues
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.string.shouldContain
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.server.testing.*

class employeeRouteTest: StringSpec({



    "GetAllEmployeeTest"{
        testApplication {
            application { module() }

            val response = client.get("/employee");

            val responseBody = response.bodyAsText()

            responseBody shouldContain "Vishal"
            response.status shouldBe HttpStatusCode.OK

        }
    }

    "AddEmployeeTest"{
        testApplication {
            application { module() }

            val employeeReq=EmployeeRequest("Vishal",40000.0,"IT")

//            val response = client.post("/employee"){
//                contentType(ContentType.Application.Json)
//                setBody(employeeReq)
//            }
//
//            response.status shouldBe HttpStatusCode.Created


        }
    }

    "GetEmployeeByIdTest"{
        testApplication {
            application { module() }

            val response =client.get("employee/1")
            response.status shouldBe HttpStatusCode.OK
            val responsebody =response.bodyAsText()

            responsebody shouldContain "Rahul"
        }
    }

    "DeleteEmployeeById"{
        testApplication {
            application { module() }

            val response =client.delete("employee/1")

            response.status shouldBe HttpStatusCode.OK

            val response1 = client.get("employee/1")

            response1.status shouldBe HttpStatusCode.NotFound
        }
    }

})
