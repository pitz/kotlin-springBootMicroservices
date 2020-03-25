package com.pitzdev.gamification.events.consumer

import com.rabbitmq.client.AMQP
import com.rabbitmq.client.ConnectionFactory
import com.rabbitmq.client.DefaultConsumer
import com.rabbitmq.client.Envelope

class EventSubscriber() {

    private val EVENT_HOST = "localhost"

    private fun startLstener(queueName: String, callback: (String) -> Unit) {
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

    fun callListener() {
        val queueName: String = "multiplication.solved"
        val callback: (String) -> Unit = { body: String -> println(" JSON recebido: $body") }

        startLstener(queueName, callback)
    }
}