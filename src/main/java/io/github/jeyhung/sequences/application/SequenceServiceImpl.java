package io.github.jeyhung.sequences.application;

import io.github.jeyhung.sequences.domain.Sequence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.Objects;

import static org.springframework.data.mongodb.core.FindAndModifyOptions.options;
import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

@Service
public class SequenceServiceImpl implements SequenceService {
    private final MongoOperations mongoOperations;

    @Autowired
    public SequenceServiceImpl(MongoOperations mongoOperations) {
        this.mongoOperations = mongoOperations;
    }

    @Override
    public long next(String sequence) {
        Sequence counter = mongoOperations.findAndModify(query(where("_id").is(sequence)),
                new Update().inc("seq",1),
                options().returnNew(true).upsert(true),
                Sequence.class);
        return !Objects.isNull(counter) ? counter.getSeq() : 1;
    }
}
