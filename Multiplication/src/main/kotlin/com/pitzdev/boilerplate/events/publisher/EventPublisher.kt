package com.pitzdev.boilerplate.events.publisher

import com.rabbitmq.client.Channel
import com.rabbitmq.client.Connection
import com.rabbitmq.client.ConnectionFactory

class EventPublisher() {

    private val EVENT_HOST = "localhost"

    public fun publish(queueName: String, info: ByteArray) {
        val factory = ConnectionFactory()
        factory.host = EVENT_HOST

        val connection : Connection = factory.newConnection()
        val channel : Channel = connection.createChannel()

        channel.queueDeclare(queueName, false, false, false, null)
        channel.basicPublish("", queueName, null, info)

        // holderId.toString().toByteArray()
        println("EventPublisher.publish()")

        channel.close()
        connection.close()
    }
}