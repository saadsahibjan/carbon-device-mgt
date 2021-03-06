/*
 * Copyright (c) 2014, WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
 *
 * WSO2 Inc. licenses this file to you under the Apache License,
 * Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License.
 * you may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.wso2.carbon.device.mgt.core;

import org.wso2.carbon.device.mgt.common.*;
import org.wso2.carbon.device.mgt.common.app.mgt.ApplicationManager;
import org.wso2.carbon.device.mgt.common.general.GeneralConfig;
import org.wso2.carbon.device.mgt.common.policy.mgt.PolicyMonitoringManager;
import org.wso2.carbon.device.mgt.common.pull.notification.PullNotificationSubscriber;
import org.wso2.carbon.device.mgt.common.push.notification.PushNotificationConfig;
import org.wso2.carbon.device.mgt.common.spi.DeviceManagementService;

import java.util.ArrayList;
import java.util.List;

public class TestDeviceManagementService implements DeviceManagementService {

    private String providerType;
    private String tenantDomain;
    private String operationCode;

    public TestDeviceManagementService(String deviceType, String tenantDomain, String operationCode) {
        providerType = deviceType;
        this.tenantDomain = tenantDomain;
        this.operationCode = operationCode;
    }

    public TestDeviceManagementService(String deviceType, String tenantDomain) {
        providerType = deviceType;
        this.tenantDomain = tenantDomain;
        this.operationCode = "DEVICE_INFO";
    }

    @Override
    public String getType() {
        return providerType;
    }

    @Override
    public OperationMonitoringTaskConfig getOperationMonitoringConfig() {
        OperationMonitoringTaskConfig taskConfig = new OperationMonitoringTaskConfig();
        taskConfig.setEnabled(true);
        taskConfig.setFrequency(3000);
        List<MonitoringOperation> monitoringOperations = new ArrayList<>();
        MonitoringOperation monitoringOperation = new MonitoringOperation();
        monitoringOperation.setTaskName(operationCode);
        monitoringOperation.setRecurrentTimes(2);
        monitoringOperations.add(monitoringOperation);
        taskConfig.setMonitoringOperation(monitoringOperations);
        return taskConfig;
    }

    @Override
    public void init() throws DeviceManagementException {

    }

    @Override
    public DeviceManager getDeviceManager() {
        return new TestDeviceManager();
    }

    @Override
    public ApplicationManager getApplicationManager() {
        return null;
    }

    @Override
    public ProvisioningConfig getProvisioningConfig() {
        return new ProvisioningConfig(tenantDomain, false);
    }

    @Override
    public PushNotificationConfig getPushNotificationConfig() {
        return null;
    }

    @Override
    public PolicyMonitoringManager getPolicyMonitoringManager() {
        return null;
    }

    @Override
    public InitialOperationConfig getInitialOperationConfig() {
        return null;
    }

    @Override
    public PullNotificationSubscriber getPullNotificationSubscriber() {
        return null;
    }

    @Override
    public DeviceStatusTaskPluginConfig getDeviceStatusTaskPluginConfig() {
        return null;
    }

    @Override
    public GeneralConfig getGeneralConfig() {
        return null;
    }
}
