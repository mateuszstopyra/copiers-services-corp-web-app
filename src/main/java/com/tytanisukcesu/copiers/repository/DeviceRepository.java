package com.tytanisukcesu.copiers.repository;

import com.tytanisukcesu.copiers.entity.Customer;
import com.tytanisukcesu.copiers.entity.Device;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DeviceRepository extends JpaRepository<Device, Long> {

    Optional<Device> getDeviceBySerialNumber(String serialNumber);

    List<Device> getAllByCustomer_Id(Long customerId);
}
