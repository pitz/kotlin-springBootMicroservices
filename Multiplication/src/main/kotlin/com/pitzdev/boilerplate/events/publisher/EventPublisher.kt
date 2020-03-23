package com.pitzdev.boilerplate.events.publisher

import com.rabbitmq.client.Channel
import com.rabbitmq.client.Connection
import com.rabbitmq.client.ConnectionFactory

class EventPublisher() {

    private val EVENT_HOST = "localhost"

    public fun publish(queueName: String, info: String) {
        println("EventPublisher.publish() -> Publicando em $queueName o pacote $info.")

        val factory = ConnectionFactory()
        factory.host = EVENT_HOST

        val connection : Connection = factory.newConnection()
        val channel : Channel = connection.createChannel()

        channel.queueDeclare(queueName, false, false, false, null)
        channel.basicPublish("", queueName, null, info.toByteArray())

        channel.close()
        connection.close()

        println("EventPublisher.publish() -> Publicado.")
    }
}