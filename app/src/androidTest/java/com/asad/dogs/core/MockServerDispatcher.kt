package com.asad.dogs.core

import okhttp3.mockwebserver.Dispatcher
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.RecordedRequest
import java.io.InputStreamReader

class MockServerDispatcher {

    fun successDispatcher(map: Map<String, String>): Dispatcher {
        return object : Dispatcher() {
            override fun dispatch(request: RecordedRequest): MockResponse {
                return when (request.path) {
                    "/breeds/list/all" -> {
                        var json = ""
                        if (map.containsKey("breeds/list/all")) {
                            json = map["breeds/list/all"]!!
                        }

                        val body = getJsonContent(json)
                        MockResponse()
                            .setResponseCode(200)
                            .setBody(body)
                    }
                    "/breed/akita/images" -> {
                        var json = ""
                        if (map.containsKey("breed/akita/images")) {
                            json = map["breed/akita/images"]!!
                        }

                        val body = getJsonContent(json)
                        MockResponse()
                            .setResponseCode(200)
                            .setBody(body)
                    }

                    else -> {
                        MockResponse().setResponseCode(200).setBody("")
                    }
                }
            }
        }
    }

    private fun getJsonContent(fileName: String): String {
        // Returns the runtime Java class of this object.
        return InputStreamReader(this.javaClass.classLoader!!.getResourceAsStream(fileName)).use { it.readText() }
    }
}
