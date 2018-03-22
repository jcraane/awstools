package nl.capaxit.awstools.ri

import com.amazonaws.services.ec2.AmazonEC2ClientBuilder

private val normalizationFactors = mapOf(
        "nano" to 0.25,
        "micro" to 0.5,
        "small" to 1.0,
        "medium" to 2.0,
        "large" to 4.0,
        "xlarge" to 8.0,
        "2xlarge" to 16.0,
        "4xlarge" to 32.0,
        "8xlarge" to 64.0,
        "10xlarge" to 80.0,
        "16xlarge" to 128.0,
        "32xlarge" to 256.0
)

fun main(args: Array<String>) {
    val ec2Client = AmazonEC2ClientBuilder.defaultClient()
    ec2Client.describeInstances().reservations
            .flatMap { it.instances }
            .groupBy { it.instanceType }
            .map {
                RiNormalization(it.key, normalizationFactors[it.key.substringAfter(".")]!! * it.value.size)
            }
            .groupBy( { it.instanceType.substringBefore(".") }, {it.normalization})
            .map { RiNormalization(it.key, it.value.sum()) }
            .forEach { println(it) }
}

data class RiNormalization(val instanceType: String, val normalization: Double)