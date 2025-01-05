package com.example.pocgp.service;



import com.example.pocgp.entity.Address;
import com.example.pocgp.repo.AddressRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AddressService {

    private final AddressRepository addressRepository;

    // Create a new address
    public Address createAddress(Address address) {
        return addressRepository.save(address);
    }

    // Get all addresses
    public List<Address> getAllAddresses() {
        return addressRepository.findAll();
    }

    // Get an address by ID
    public Optional<Address> getAddressById(Long id) {
        return addressRepository.findById(id);
    }

    // Update an address
    public Address updateAddress(Long id, Address updatedAddress) {
        if (addressRepository.existsById(id)) {
            updatedAddress.setId(id);  // Make sure to keep the existing ID
            return addressRepository.save(updatedAddress);
        }
        return null; // Return null or throw an exception if not found
    }

    // Delete an address
    public boolean deleteAddress(Long id) {
        if (addressRepository.existsById(id)) {
            addressRepository.deleteById(id);
            return true;
        }
        return false;
    }
}

