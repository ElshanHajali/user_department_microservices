package com.example.user.service;

import com.example.user.VO.Department;
import com.example.user.VO.ResponseTemplateVO;
import com.example.user.dto.User;
import com.example.user.repository.IUserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class UserServiceImpl implements IUserService {
    private final IUserRepository userRepository;

    // It brings another service object into this service,
    // in my case department.
    private final RestTemplate restTemplate;

    public UserServiceImpl(IUserRepository userRepository, RestTemplate restTemplate) {
        this.userRepository = userRepository;
        this.restTemplate = restTemplate;
    }

    @Override
    public ResponseEntity<User> save(User user) {
        if (user != null) {
            return new ResponseEntity<>(userRepository.save(user), HttpStatus.CREATED);
        } else{
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }

    public ResponseTemplateVO getUserWithDepartmentByUserId(long id) {
        ResponseTemplateVO responseTemplateVO = new ResponseTemplateVO();

        User user =
                userRepository.
                        findByUserId(id).isPresent() ?
                        userRepository.findByUserId(id).get() :
                        null;

        if (user != null) {
            Department department = restTemplate.getForObject("http://localhost:9001/api/department/id/" + user.getDepartmentId(), Department.class);
            responseTemplateVO.setUser(user);
            responseTemplateVO.setDepartment(department);
        }

        return responseTemplateVO;
    }
}
