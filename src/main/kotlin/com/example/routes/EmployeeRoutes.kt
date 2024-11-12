package com.example.routes

import com.example.data.model.Employee
import io.ktor.http.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

private val employeeList = listOf(Employee(1, "Rahul",50000.0,"IT"),
    Employee(2, "Vishal",40000.0,"IT"),
    Employee(3, "Vijay",52000.0,"IT"),
    Employee(4, "Rahul",25000.0,"HR"),
    Employee(5, "Vinod",35000.0,"SALES"),
    Employee(6, "Amit",18000.0,"HR"),
    Employee(7, "Amita",75000.0,"HR"),
    Employee(8, "Piyush",45000.0,"IT")
);

fun Route.employee(){

    get("/employee"){
        call.respond(HttpStatusCode.OK, employeeList)
    }
}