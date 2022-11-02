package com.iot.relay.consumer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.iot.relay.consumer.mapper.SensorDataMapper;
import com.iot.relay.model.SensorData;
import com.iot.relay.model.SensorDataEntity;
import com.iot.relay.repo.SensorDataRepository;

@Service
public class SensorDataService {
	
	@Autowired
	private SensorDataMapper sensorDataMapper;
	@Autowired
	private SensorDataRepository sensorDataRepository;

	/**
	 * Save the sensor data in repository
	 * 
	 * @param sensorDataEntity
	 */
	public void save(SensorData sensorData) {
		SensorDataEntity sensorDataEntity = sensorDataMapper.fromEventToEntity(sensorData);
		sensorDataRepository.save(sensorDataEntity);
	}

}
