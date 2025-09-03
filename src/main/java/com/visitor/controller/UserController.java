package com.visitor.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.visitor.entity.LoginUser;
import com.visitor.repository.LoginRepository;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;


@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {
	
	@Autowired
    private LoginRepository loginRepository;

	
	
    @PostMapping("/login")
    public String login(@RequestBody LoginUser loginUser) {
    	
//    	String user2= loginRepository.findByLoginId(loginUser.getLoginId()).filter(user -> user.getPassword().equals(loginUser.getPassword()))
//    	.map(user -> "Login successful").orElse("Invalid credentials");
//    	
//    	return  ResponseEntity.ok("Login successful");
    	
        return loginRepository.findByLoginId(loginUser.getLoginId())
                .filter(user -> user.getPassword().equals(loginUser.getPassword()))
                .map(user -> "Login successful")
                .orElse("Invalid credentials");
    }
	
//	@PostMapping("/login")
//	public String login(@RequestBody LoginUser loginUser) {
////	    String validUsername = "test123";
////	    String validPassword = "test@123";
//
//	    if (validUsername.equals(loginUser.getLoginId()) &&
//	        validPassword.equals(loginUser.getPassword())) {
//	        return "Login successful";
//	    } else {
//	        return "Invalid credentials...";
//	    }
//	}
//    @PostMapping("/login")
//    public ResponseEntity<String> loginUser(@RequestBody LoginUser loginUser) {
//        if ("test123".equals(loginUser.getLoginId()) &&
//            "test@123".equals(loginUser.getPassword())) {
//            return ResponseEntity.ok("Login Successful");
//        } else {
//            return ResponseEntity.status(401).body("Invalid Login ID or Password");
//        }
//    }

 // Get login user by loginId
    @GetMapping("/{loginId}")
    public ResponseEntity<LoginUser> getLoginUser(@PathVariable String loginId) {
        LoginUser loginUser = loginRepository.findById(loginId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return ResponseEntity.ok(loginUser);
    }
    
 // ✅ Logout endpoint
    @PostMapping("/logout")
    public ResponseEntity<String> logout(HttpServletRequest request, HttpServletResponse response) {
        // Invalidate session
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }

        // Clear authentication if using Spring Security
        //SecurityContextHolder.clearContext();

        return ResponseEntity.ok("Logout successful");
    }
}