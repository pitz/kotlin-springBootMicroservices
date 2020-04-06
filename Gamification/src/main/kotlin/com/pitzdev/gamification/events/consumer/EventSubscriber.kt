package com.pitzdev.gamification.events.consumer

import com.google.gson.Gson
import com.pitzdev.gamification.dtos.events.MultiplicationSolvedDTO
import com.pitzdev.gamification.services.game.GameServiceImpl
import com.rabbitmq.client.AMQP
import com.rabbitmq.client.ConnectionFactory
import com.rabbitmq.client.DefaultConsumer
import com.rabbitmq.client.Envelope
import org.springframework.boot.CommandLineRunner
import org.springframework.stereotype.Component

@Component
class EventSubscriber(private val gameServiceImpl: GameServiceImpl) : CommandLineRunner {

    private val EVENT_HOST = "localhost"

    override fun run(vararg args: String?) {
        println("EventSubscriber -> multiplicationSolvedListener() :: Starting listener.")
        multiplicationSolvedListener()
        println("EventSubscriber -> multiplicationSolvedListener() :: Listener started.")
    }

    private fun multiplicationSolvedListener() {
        consume("multiplication.solved", callback())
    }

    private fun callback() : (String) -> Unit {
        return { body: String ->
            try {
                val multiplicationSolved = Gson().fromJson(body, MultiplicationSolvedDTO::class.java)
                if (multiplicationSolved != null) gameServiceImpl.newAttemptForUser(multiplicationSolved.userId, multiplicationSolved.multiplicationResultAttemptId, multiplicationSolved.correct)
            } catch (e: Exception) {
                println("EventSubscriber -> Erro ao consumir mensagem $body")
            }

        }
    }

    private fun consume(queueName: String, callback: (String) -> Unit) {
        val factory = ConnectionFactory()
        factory.host = EVENT_HOST

        val connection = factory.newConnection()
        val channel = connection.createChannel()

        channel.queueDeclare(queueName, false,false,false,null)

        val consumer = object : DefaultConsumer(channel) {
            override fun handleDelivery(consumerTag: String, envelope: Envelope, properties: AMQP.BasicProperties, body: ByteArray) {
                callback(String(body, charset("UTF-8")))
            }
        }

        channel.basicConsume(queueName, true, consumer)
    }

}