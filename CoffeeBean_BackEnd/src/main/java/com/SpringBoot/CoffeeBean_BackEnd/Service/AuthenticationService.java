package com.SpringBoot.CoffeeBean_BackEnd.Service;

import com.SpringBoot.CoffeeBean_BackEnd.Model.DatabaseSequence;
import com.SpringBoot.CoffeeBean_BackEnd.Model.Enum.Role;
import com.SpringBoot.CoffeeBean_BackEnd.Model.Login;
import com.SpringBoot.CoffeeBean_BackEnd.Model.User;
import com.SpringBoot.CoffeeBean_BackEnd.Repository.UserRepository;
import com.SpringBoot.CoffeeBean_BackEnd.Security.JwtUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.HashMap;
import java.util.Objects;
import java.util.Optional;

import static org.springframework.data.mongodb.core.FindAndModifyOptions.options;
import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

@Service
@RequiredArgsConstructor
public class AuthenticationService implements UserDetailsService {

    private final UserRepository userRepository;
    private final MongoOperations mongoOperations;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtils jwtUtils;

    public long generateSequence(String seqName) {
        DatabaseSequence counter = mongoOperations.findAndModify(query(where("_id").is(seqName)),
                new Update().inc("seq",1), options().returnNew(true).upsert(true),
                DatabaseSequence.class);
        return !Objects.isNull(counter) ? counter.getSeq() : 1;
    }

    public ResponseEntity<String > register(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        Optional<User> email = userRepository.findByEmail(user.getEmail());
        if (email.isEmpty()) {
            user.setId(generateSequence(User.SEQUENCE_NAME));
            if(user.getRole()==null)
                user.setRole(Role.USER);
            userRepository.save(user);
            return new ResponseEntity<>("Registered Successfully.....!", HttpStatus.CREATED);
        }
        return new ResponseEntity<>("Email Already Exist.....!", HttpStatus.CREATED);
    }

    public ResponseEntity<?> login (Login login){
        try{
            Authentication authentication = new UsernamePasswordAuthenticationToken(login.getEmail(),login.getPassword());
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }catch (BadCredentialsException ex){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(" UserName And Password is inValid ......! ");
        }
        Optional<User> username = userRepository.findByEmail(login.getEmail());
        if(username.isPresent()){
            String Token = jwtUtils.generateToken(new HashMap<>(),username.get());
            return ResponseEntity.ok(Token);
        }else{
            return ResponseEntity.noContent().build();
        }
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException(" User Not Found with UserName : " + email));
    }

}

