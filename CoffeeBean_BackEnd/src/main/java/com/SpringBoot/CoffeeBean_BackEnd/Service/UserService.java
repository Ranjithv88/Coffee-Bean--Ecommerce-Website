package com.SpringBoot.CoffeeBean_BackEnd.Service;

import com.SpringBoot.CoffeeBean_BackEnd.Model.DatabaseSequence;
import com.SpringBoot.CoffeeBean_BackEnd.Model.Enum.Role;
import com.SpringBoot.CoffeeBean_BackEnd.Model.User;
import com.SpringBoot.CoffeeBean_BackEnd.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import static org.springframework.data.mongodb.core.FindAndModifyOptions.options;
import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository repository;
    private final MongoOperations mongoOperations;

    public List<User> getAll(){
        return repository.findAll();
    }

    public long generateSequence(String seqName) {
        DatabaseSequence counter = mongoOperations.findAndModify(query(where("_id").is(seqName)),
                new Update().inc("seq",1), options().returnNew(true).upsert(true),
                DatabaseSequence.class);
        return !Objects.isNull(counter) ? counter.getSeq() : 1;
    }

    public String PostService(User user) {
        Optional<User> email = repository.findByEmail(user.getEmail());
        if (email.isEmpty()) {
            user.setId(generateSequence(User.SEQUENCE_NAME));
            if(user.getRole()==null)
                user.setRole(Role.USER);
            repository.save(user);
            return "Register Successfully!";
        }
        return "Email Already Exit.....!";
    }

}

