package com.example.ebankify.Controller;

import com.example.ebankify.Controller.vm.User.Request.CreateUserRequest;
import com.example.ebankify.DTO.UserDTO;
import com.example.ebankify.Entity.Enums.Role;
import com.example.ebankify.Service.UserService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v2/users")
public class UserController {
    private UserService userService;
    private final ModelMapper mapper;

    @GetMapping("/")
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        List<UserDTO> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }
    @GetMapping("/{userId}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable Long userId) {
        UserDTO user = userService.getUserById(userId);
        return ResponseEntity.ok(user);
    }
    @PostMapping("/")
    public ResponseEntity<?> createUser(@RequestBody CreateUserRequest createUserRequest) {
        String role = createUserRequest.getRole().toString();
        UserDTO userDTO = mapper.map(createUserRequest,UserDTO.class);

        if ("ADMIN".equals(role)) {
            UserDTO createdUser = userService.createUser(userDTO);
            return ResponseEntity.ok(createdUser);
        } else if (role == null) {
            // Set the default role to USER if no role is provided
            userDTO.setRole(Role.USER);
            UserDTO createdUser = userService.createUser(userDTO);
            return ResponseEntity.ok(createdUser);
        } else {
            // Return a FORBIDDEN response if the role is not recognized
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(role);
        }
    }

    @PutMapping("/{userId}")
    public ResponseEntity<UserDTO> updateUser(@PathVariable Long userId, @RequestBody UserDTO userDTO) {
        UserDTO updatedUser = userService.updateUser(userId, userDTO);
        return ResponseEntity.ok(updatedUser);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long userId) {
        userService.deleteUser(userId);
        return ResponseEntity.noContent().build();
    }
}
