package com.example.routes

import com.example.data.model.Employee
import com.example.data.model.EmployeeRequest
import io.ktor.http.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*


private val employeeList = mutableListOf(Employee(1, "Rahul",50000.0,"IT"),
    Employee(2, "Vishal",40000.0,"IT"),
    Employee(3, "Vijay",52000.0,"IT"),
    Employee(4, "Rahul",25000.0,"HR"),
    Employee(5, "Vinod",35000.0,"SALES"),
    Employee(6, "Amit",18000.0,"HR"),
    Employee(7, "Amita",75000.0,"HR"),
    Employee(8, "Piyush",45000.0,"IT")
);

fun Route.employeeRoute(){

    get("/employee"){
        call.respond(HttpStatusCode.OK, employeeList)
    }

    post("/employee") {
        try {
           val employeeRequest = call.receive<EmployeeRequest>();
            val count =employeeList.count()+1;
            val emp = Employee(count, employeeRequest.name,employeeRequest.salary,employeeRequest.dept);
            employeeList.add(emp);
            call.respond(HttpStatusCode.Created,emp)
        }catch (e : Exception){
            call.respond(HttpStatusCode.BadRequest,"Missing some Field")
        }
    }

    get("/employee/{id}"){
        val id = call.pathParameters["id"];
        val employee = employeeList.filter {employee ->  employee.id == id?.toInt()}
        if (!employee.equals(null) && employee.count() != 0){
            call.respond(HttpStatusCode.OK,employee);
        }else{
            call.respond(HttpStatusCode.NotFound,"Employee Id Not Found")
        }

    }

    delete("/employee/{id}") {
        val id = call.pathParameters["id"];
        val employee =employeeList.filter { employee -> employee.id == id?.toInt() }
        if (!employee.equals(null) && employee.count() != 0){
            employeeList.removeAll(employee)
            call.respond(HttpStatusCode.OK,"Deleted Successfully.")
        }else{
            call.respond(HttpStatusCode.NotFound,"Employee Id Not Found")
        }

    }

}