package com.example.pocgp.resolver;

import com.example.pocgp.entity.Address;
import com.example.pocgp.repo.AddressRepository;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AddressResolver {

    @Autowired
    private AddressRepository addressRepository;

    @QueryMapping
    public Address getAddress(Long id) {
        return addressRepository.findById(id).orElse(null);
    }

    @MutationMapping
    public Address createAddress(String street, String city, Long employeeId) {
        Address address = new Address();
        address.setStreet(street);
        address.setCity(city);
        // Example: address.setEmployee(employeeRepository.findById(employeeId).orElse(null));
        return addressRepository.save(address);
    }

    @MutationMapping
    public Address updateAddress(Long id, String street, String city) {
        Address address = addressRepository.findById(id).orElseThrow();
        address.setStreet(street);
        address.setCity(city);
        return addressRepository.save(address);
    }

    @MutationMapping
    public boolean deleteAddress(Long id) {
        addressRepository.deleteById(id);
        return true;
    }
}
