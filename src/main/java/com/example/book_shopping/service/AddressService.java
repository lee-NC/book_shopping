package com.example.book_shopping.service;

import com.example.book_shopping.entity.Address;
import com.example.book_shopping.entity.User;
import com.example.book_shopping.exception.BadRequestException;
import com.example.book_shopping.exception.NotFoundException;
import com.example.book_shopping.repository.AddressRepository;
import com.example.book_shopping.repository.UserRepository;
import com.example.book_shopping.request.AddressRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author lengo
 * created on 3/19/2022
 */
@Service
public class AddressService {
    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private UserRepository userRepository;

    public List<Address> getAll(int userId) {
        try {
            User user = userRepository.findByIdAndIsActiveAndIsAdmin(userId, true, false);
            if (user != null) {
                List<Address> addresses = addressRepository.findAllByUser(user);
                if (addresses == null) throw new NotFoundException(HttpStatus.NOT_FOUND.getReasonPhrase());
                return addresses;
            }
            throw new NotFoundException(HttpStatus.NOT_FOUND.getReasonPhrase());
        } catch (Exception e) {
            e.printStackTrace();
            throw new BadRequestException(e.getMessage());
        }
    }

    public Address update(int id, AddressRequest request) {
        try {
            Optional<Address> address = addressRepository.findById(id);
            if (address.isPresent()) {
                Address data = address.get();
                if (request.getAddressDetail() != null && !data.getAddressDetail().equals(request.getAddressDetail().trim())) {
                    data.setAddressDetail(request.getAddressDetail().trim());
                }
                if (request.getDescription() != null && !data.getDescription().equals(request.getDescription().trim())) {
                    data.setDescription(request.getDescription().trim());
                }
                return addressRepository.save(data);
            }
            throw new NotFoundException(HttpStatus.NOT_FOUND.getReasonPhrase());
        } catch (Exception e) {
            e.printStackTrace();
            throw new BadRequestException(e.getMessage());
        }
    }

    public Address add(int userId, AddressRequest request) {
        try {
            User user = userRepository.findByIdAndIsActiveAndIsAdmin(userId, true, false);
            if (user != null) {
                Address address = new Address();
                address.setAddressDetail(request.getAddressDetail().trim());
                address.setDescription(request.getDescription().trim());
                address.setUser(user);
                return addressRepository.save(address);
            }
            throw new NotFoundException(HttpStatus.NOT_FOUND.getReasonPhrase());
        } catch (Exception e) {
            e.printStackTrace();
            throw new BadRequestException(e.getMessage());
        }
    }

    public Address setMain(int id) {
        try {
            Optional<Address> address = addressRepository.findById(id);
            if (address.isPresent()) {
                Address data = address.get();
                if (!data.isMain()) {
                    List<Address> addresses = addressRepository.findAllByUser(data.getUser());
                    if (addresses != null) {
                        for (Address check : addresses) {
                            if (check.isMain()) {
                                check.setMain(false);
                                addressRepository.save(check);
                                break;
                            }
                        }
                    }
                    data.setMain(true);
                    return addressRepository.save(data);
                }
            }
            throw new NotFoundException(HttpStatus.NOT_FOUND.getReasonPhrase());
        } catch (Exception e) {
            e.printStackTrace();
            throw new BadRequestException(e.getMessage());
        }
    }

    public boolean delete(int id) {
        try {
            Optional<Address> address = addressRepository.findById(id);
            if (address.isPresent()) {
                addressRepository.delete(address.get());
                return true;
            }
            throw new NotFoundException(HttpStatus.NOT_FOUND.getReasonPhrase());
        } catch (Exception e) {
            e.printStackTrace();
            throw new BadRequestException(e.getMessage());
        }
    }
}
