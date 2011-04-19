package com.ocbcmcd.monitoring.service.impl;

import java.util.Map;

public interface IConfigCommand {
	Map<String, String> getPlainConfigs();
	Map<String, String> getEncryptedConfigs();
}
