package com.example.pocgp.resolver;

import com.example.pocgp.entity.Address;
import com.example.pocgp.repo.AddressRepository;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

@Controller
public class AddressResolver {

    @Autowired
    private AddressRepository addressRepository;

    @QueryMapping
    public Address getAddress(@Argument Long id) {
        return addressRepository.findById(id).orElse(null);
    }

    @MutationMapping
    public Address createAddress(@Argument String street, @Argument String city, @Argument Long employeeId) {
        Address address = new Address();
        address.setStreet(street);
        address.setCity(city);
        // Example: address.setEmployee(employeeRepository.findById(employeeId).orElse(null));
        return addressRepository.save(address);
    }

    @MutationMapping
    public Address updateAddress(@Argument Long id, @Argument String street,@Argument String city) {
        Address address = addressRepository.findById(id).orElseThrow();
        address.setStreet(street);
        address.setCity(city);
        return addressRepository.save(address);
    }

    @MutationMapping
    public boolean deleteAddress(@Argument Long id) {
        addressRepository.deleteById(id);
        return true;
    }
}
