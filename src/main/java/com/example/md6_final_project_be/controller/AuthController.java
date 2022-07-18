package com.example.md6_final_project_be.controller;

import com.example.md6_final_project_be.dto.request.*;
import com.example.md6_final_project_be.dto.response.JwtResponse;
import com.example.md6_final_project_be.dto.response.ResponseMessage;
import com.example.md6_final_project_be.model.AppUser;
import com.example.md6_final_project_be.model.Role;
import com.example.md6_final_project_be.model.RoleName;
import com.example.md6_final_project_be.security.jwt.JwtProvider;
import com.example.md6_final_project_be.security.jwt.JwtTokenFilter;
import com.example.md6_final_project_be.security.useprinciple.UserPrinciple;
import com.example.md6_final_project_be.service.role.RoleServiceIMPL;
import com.example.md6_final_project_be.service.user.UserServiceIMPL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping("/auth")
@CrossOrigin("*")
public class AuthController {
    @Autowired
    UserServiceIMPL userService;
    @Autowired
    RoleServiceIMPL roleService;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    JwtProvider jwtProvider;
    @Autowired
    JwtTokenFilter jwtTokenFilter;
    @GetMapping("/home1")
    public  ResponseEntity<?> findAll(){
        return new ResponseEntity<>(userService.findAll(),HttpStatus.OK);
    }
    @PostMapping("/signup")
    public ResponseEntity<?> register(@Valid @RequestBody SignUpForm signUpForm){
        if(userService.existsByUsername(signUpForm.getUsername())){
            return new ResponseEntity<>(new ResponseMessage("nouser"), HttpStatus.OK);
        }
        if(userService.existsByEmail(signUpForm.getEmail())){
            return new ResponseEntity<>(new ResponseMessage("noemail"), HttpStatus.OK);
        }
        if(signUpForm.getAvatar() == null || signUpForm.getAvatar().trim().isEmpty()){
            signUpForm.setAvatar("https://firebasestorage.googleapis.com/v0/b/chinhbeo-18d3b.appspot.com/o/avatar.png?alt=media&token=3511cf81-8df2-4483-82a8-17becfd03211");
        }
        AppUser user = new AppUser(signUpForm.getName(), signUpForm.getUsername(), signUpForm.getEmail(),signUpForm.getAvatar(),passwordEncoder.encode(signUpForm.getPassword()));
        Set<String> strRoles = signUpForm.getRoles();
        Set<Role> roles = new HashSet<>();
        strRoles.forEach(role ->{
            switch (role){
                case "admin":
                    Role adminRole = roleService.findByName(RoleName.ADMIN).orElseThrow(
                            ()-> new RuntimeException("Role not found")
                    );
                    roles.add(adminRole);
                    break;
                case "user":
                    Role userRole = roleService.findByName(RoleName.USER).orElseThrow( ()-> new RuntimeException("Role not found"));
                    roles.add(userRole);
                    case "pm":
                    Role pmRole = roleService.findByName(RoleName.PM).orElseThrow( ()-> new RuntimeException("Role not found"));
                    roles.add(pmRole);
            }
        });
        user.setRoles(roles);
        userService.save(user);
        return new ResponseEntity<>(new ResponseMessage("yes"), HttpStatus.OK);
    }
    @PostMapping("/signin")
    public ResponseEntity<?> login(@Valid @RequestBody SignInForm signInForm){
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(signInForm.getUsername(), signInForm.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtProvider.createToken(authentication);
        UserPrinciple userPrinciple = (UserPrinciple) authentication.getPrincipal();
        return ResponseEntity.ok(new JwtResponse(token, userPrinciple.getName(),userPrinciple.getAvatar(), userPrinciple.getAuthorities()));
    }
    @PutMapping("/change-password")
    public ResponseEntity<?> changePassword(HttpServletRequest request, @Valid @RequestBody ChangePasswordForm changePasswordForm){
        String jwt = jwtTokenFilter.getJwt(request);
        String username = jwtProvider.getUerNameFromToken(jwt);
        AppUser user;
        try {
            user = userService.findByUsername(username).orElseThrow(()-> new UsernameNotFoundException("User Not Found wiht -> username"+username));
            boolean matches = passwordEncoder.matches(changePasswordForm.getCurrentPassword(), user.getPassword());
            if(changePasswordForm.getNewPassword() != null){
                if(matches){
                    user.setPassword(passwordEncoder.encode(changePasswordForm.getNewPassword()));
                    userService.save(user);
                } else {
                    return new ResponseEntity<>(new ResponseMessage("no"), HttpStatus.OK);
                }
            }
            return new ResponseEntity<>(new ResponseMessage("yes"), HttpStatus.OK);
        } catch (UsernameNotFoundException exception){
            return new ResponseEntity<>(new ResponseMessage(exception.getMessage()), HttpStatus.NOT_FOUND);
        }
    }
    @PutMapping("/change-avatar")
    public ResponseEntity<?> changeAvatar(HttpServletRequest request, @Valid @RequestBody ChangeAvatar changeAvatar){
        String jwt = jwtTokenFilter.getJwt(request);
        String username = jwtProvider.getUerNameFromToken(jwt);
        AppUser user;
        try {
            if(changeAvatar.getAvatar()==null){
                return new ResponseEntity<>(new ResponseMessage("no"), HttpStatus.OK);
            } else {
                user = userService.findByUsername(username).orElseThrow(()-> new UsernameNotFoundException("User Not Found -> username"+username));
                user.setAvatar(changeAvatar.getAvatar());
                userService.save(user);
            }
            return new ResponseEntity<>(new ResponseMessage("yes"), HttpStatus.OK);
        } catch (UsernameNotFoundException exception){
            return new ResponseEntity<>(new ResponseMessage(exception.getMessage()), HttpStatus.NOT_FOUND);
        }
    }
    @PutMapping("/change-profile")
    public ResponseEntity<?> changeProfile(HttpServletRequest request, @Valid @RequestBody ChangeProfileForm changeProfileForm){
        String jwt = jwtTokenFilter.getJwt(request);
        String username = jwtProvider.getUerNameFromToken(jwt);
        AppUser user;
        try {
            if(userService.existsByUsername(changeProfileForm.getUsername())){
                return new ResponseEntity<>(new ResponseMessage("nouser"), HttpStatus.OK);
            }
            if(userService.existsByEmail(changeProfileForm.getEmail())){
                return new ResponseEntity<>(new ResponseMessage("noemail"), HttpStatus.OK);
            }
            user = userService.findByUsername(username).orElseThrow(()-> new UsernameNotFoundException("User Not Found with -> useranme"+username));
            user.setName(changeProfileForm.getName());
            user.setUsername(changeProfileForm.getUsername());
            user.setEmail(changeProfileForm.getEmail());
            userService.save(user);
            return new ResponseEntity<>(new ResponseMessage("yes"), HttpStatus.OK);
        } catch (UsernameNotFoundException exception){
            return new ResponseEntity<>(new ResponseMessage(exception.getMessage()),HttpStatus.NOT_FOUND );
        }
    }
}
