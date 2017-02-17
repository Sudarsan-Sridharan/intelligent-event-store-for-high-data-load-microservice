package com.deviceinsight.services;

public class KafkaPartitionUtil {

public static Long getPartition(Long gatewayId) {
    return gatewayId % 2;
}

}
