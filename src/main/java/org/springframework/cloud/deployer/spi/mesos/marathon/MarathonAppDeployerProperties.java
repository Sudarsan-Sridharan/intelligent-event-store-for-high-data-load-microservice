
package org.springframework.cloud.deployer.spi.mesos.marathon;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("spring.cloud.deployer.mesos.marathon")
public class MarathonAppDeployerProperties {
    private String apiEndpoint = "http://89.163.237.128:8080/";
    private String imagePullSecret;
    private double memory = 512.0D;
    private double cpu = 0.5D;
    private String[] environmentVariables = new String[0];

    public MarathonAppDeployerProperties() {
    }

    public double getMemory() {
        return this.memory;
    }

    public void setMemory(double memory) {
        this.memory = memory;
    }

    public double getCpu() {
        return this.cpu;
    }

    public void setCpu(double cpu) {
        this.cpu = cpu;
    }

    public String getApiEndpoint() {
        return this.apiEndpoint;
    }

    public void setApiEndpoint(String apiEndpoint) {
        this.apiEndpoint = apiEndpoint;
    }

    public String getImagePullSecret() {
        return this.imagePullSecret;
    }

    public void setImagePullSecret(String imagePullSecret) {
        this.imagePullSecret = imagePullSecret;
    }

    public String[] getEnvironmentVariables() {
        return this.environmentVariables;
    }

    public void setEnvironmentVariables(String[] environmentVariables) {
        this.environmentVariables = environmentVariables;
    }
}
