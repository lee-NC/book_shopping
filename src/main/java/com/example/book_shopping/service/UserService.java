package com.example.book_shopping.service;

import com.example.book_shopping.entity.Address;
import com.example.book_shopping.entity.User;
import com.example.book_shopping.exception.BadRequestException;
import com.example.book_shopping.exception.DuplicateRecordException;
import com.example.book_shopping.exception.NotFoundException;
import com.example.book_shopping.repository.AddressRepository;
import com.example.book_shopping.repository.UserRepository;
import com.example.book_shopping.request.SignInRequest;
import com.example.book_shopping.request.SignUpRequest;
import com.example.book_shopping.request.UpdateUserRequest;
import com.example.book_shopping.response.UserDetailResponse;
import com.example.book_shopping.response.UserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author lengo
 * created on 3/18/2022
 */
@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AddressRepository addressRepository;

    public UserResponse signIn(SignInRequest request) {
        try {
            User user = userRepository.findByEmailAndPasswordAndIsActive(request.getEmail(), request.getPassword(), true);
            if (user != null) {
                System.out.println("DONE");
                String fullName = user.getLastName() + " " + user.getFirstName();
                return new UserResponse(user.getId(), fullName, user.isActive(), user.isAdmin());
            }
            throw new NotFoundException(HttpStatus.NOT_FOUND.getReasonPhrase());

        } catch (Exception e) {
            e.printStackTrace();
            throw new BadRequestException(e.getMessage());
        }
    }

    public boolean signOut(int userId) {
        try {
            Optional<User> user = userRepository.findById(userId);
            if (user.isPresent()) {
                return true;
            }
            throw new NotFoundException(HttpStatus.NOT_FOUND.getReasonPhrase());
        } catch (Exception e) {
            e.printStackTrace();
            throw new BadRequestException(e.getMessage());
        }
    }

    public UserResponse signUp(SignUpRequest request) {
        try {
            if (!userRepository.existsByEmail(request.getEmail())) {
                User user = new User();
                user.setEmail(request.getEmail().trim());
                user.setFirstName(request.getFirstName().trim());
                user.setPassword(request.getPassword().trim());
                user.setLastName(request.getLastName().trim());
                user.setPhoneNumber(request.getPhoneNumber().trim());
                user.setActive(true);
                user = userRepository.save(user);

                Address address = new Address();
                address.setUser(user);
                if (request.getAddressDesc() != null) address.setDescription(request.getAddressDesc());
                address.setAddressDetail(request.getAddress());
                address.setMain(true);
                addressRepository.save(address);

                String fullName = user.getLastName() + " " + user.getFirstName();
                return new UserResponse(user.getId(), fullName, user.isActive(), user.isAdmin());
            }
            throw new DuplicateRecordException("Email was sign up. Choose another, please!");

        } catch (Exception e) {
            e.printStackTrace();
            throw new BadRequestException(e.getMessage());
        }
    }

    public UserDetailResponse get(int userId) {
        try {
            User user = userRepository.findByIdAndIsActiveAndIsAdmin(userId, true, false);
            if (user != null) {
                return toUserDetailResponse(user);
            }
            throw new NotFoundException(HttpStatus.NOT_FOUND.getReasonPhrase());

        } catch (Exception e) {
            e.printStackTrace();
            throw new BadRequestException(e.getMessage());
        }
    }

    public boolean changeActive(int userId) {
        try {
            User user = userRepository.findByIdAndIsAdmin(userId, false);
            if (user != null) {
                user.setActive(!user.isActive());
                userRepository.save(user);
                return true;
            }
            throw new NotFoundException(HttpStatus.NOT_FOUND.getReasonPhrase());

        } catch (Exception e) {
            e.printStackTrace();
            throw new BadRequestException(e.getMessage());
        }
    }

    public List<User> getAllUser() {
        try {
            return userRepository.findAll();
        } catch (Exception e) {
            e.printStackTrace();
            throw new BadRequestException(e.getMessage());
        }
    }

    public UserDetailResponse update(int userId, UpdateUserRequest request) {
        try {
            User user = userRepository.findByIdAndIsActiveAndIsAdmin(userId, true, false);
            if (user != null) {
                if (request.getEmail() != null && !user.getEmail().equals(request.getEmail())) {
                    if (userRepository.existsByEmail(request.getEmail()))
                        throw new DuplicateRecordException("Email was sign up. Choose another, please!");
                    user.setEmail(request.getEmail().trim());
                }
                if (request.getFirstName() != null && !user.getFirstName().equals(request.getFirstName())) {
                    user.setFirstName(request.getFirstName().trim());
                }

                if (request.getPassword() != null && !user.getPassword().equals(request.getPassword())) {
                    user.setPassword(request.getPassword().trim());
                }

                if (request.getLastName() != null && !user.getLastName().equals(request.getLastName())) {
                    user.setLastName(request.getLastName().trim());
                }

                if (request.getPhoneNumber() != null && !user.getPhoneNumber().equals(request.getPhoneNumber())) {
                    user.setPhoneNumber(request.getPhoneNumber().trim());
                }
                user = userRepository.save(user);
                return toUserDetailResponse(user);
            }
            throw new NotFoundException(HttpStatus.NOT_FOUND.getReasonPhrase());

        } catch (Exception e) {
            e.printStackTrace();
            throw new BadRequestException(e.getMessage());
        }
    }

    private UserDetailResponse toUserDetailResponse(User user) {
        UserDetailResponse response = new UserDetailResponse();
        response.setId(user.getId());
        response.setActive(user.isActive());
        response.setAdmin(user.isAdmin());
        response.setEmail(user.getEmail());
        response.setFirstName(user.getFirstName());
        response.setPassword(user.getPassword());
        response.setLastName(user.getLastName());
        response.setPhoneNumber(user.getPhoneNumber());
        return response;
    }
}
