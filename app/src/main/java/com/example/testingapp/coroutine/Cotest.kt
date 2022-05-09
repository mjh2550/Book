package com.example.testingapp.coroutine

import kotlinx.coroutines.*


fun main() {
    var scope : CoroutineScope
    println("Test-------------->")

/*    runBlocking {
//        val scope = CoroutineScope(Dispatchers.Default)
        scope = CoroutineScope(GlobalScope.coroutineContext)
        val coroutineA = scope.launch {

            for(i in 1..3){
                print("A")
                println(i)
                delay(20)

            }

        }
        coroutineA.join()

        val coroutineB = scope.launch {
            for(i in 1..3){


                print("B")
                println(i)
                delay(20)

            }
        }
        coroutineB.join()
        var d = async {
            for(i in 1..3){
                print("D")
                println(i)
                delay(20)

            }

        }
        d.await()
        var c = launch {
            for(i in 1..3){
                print("C")
                println(i)
                delay(20)

            }
        }



       val result =  withTimeoutOrNull(5){
            for (i in 1..3){
                println(i)
                delay(10)
            }
            "Finish"
        }

        println(result.toString())
    }*/

    runBlocking {
        val a = launch {
        var score : Int =0
            for(i in 1..3){
                score++
            }
            score
        }
        val b = async {
            var score : Int =0
            for(i in 1..3){
                score++
            }
            score
        }
        val ar  = a.join()
        val br = b.await()
        println("a : $ar")
        println("b : $br")
    }

    println("TestEND-------------->")

}

