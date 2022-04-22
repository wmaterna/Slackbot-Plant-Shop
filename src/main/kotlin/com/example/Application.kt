package com.example

import io.ktor.server.application.*
import com.example.plugins.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.server.routing.*


fun main(args: Array<String>): Unit =
    io.ktor.server.netty.EngineMain.main(args)


fun Application.module() {
    routing {
        homeRoute()
    }
}


