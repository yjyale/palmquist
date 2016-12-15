package edu.yale.spec;

import edu.yale.domain.Person;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class PersonSpecification implements Specification<Person> {

    private SpecSearchCriteria criteria;

    public PersonSpecification(final SpecSearchCriteria criteria) {
        super();
        this.criteria = criteria;
    }

    public SpecSearchCriteria getCriteria() {
        return criteria;
    }

    @Override
    public Predicate toPredicate(final Root<Person> root, final CriteriaQuery<?> query, final CriteriaBuilder builder) {
        switch (criteria.getOperation()) {
        case EQUALITY:
            return builder.equal(root.get(criteria.getKey()), criteria.getValue());
        case NEGATION:
            return (builder.notEqual((builder.lower(root.get(criteria.getKey()))), (String) criteria.getValue()));
        case GREATER_THAN:
            return builder.greaterThan(root.<String> get(criteria.getKey()), criteria.getValue().toString());
        case LESS_THAN:
            return builder.lessThan(root.<String> get(criteria.getKey()), criteria.getValue().toString());
        case LIKE:
            return builder.like(root.<String> get(criteria.getKey()), criteria.getValue().toString());
        case STARTS_WITH:
            return builder.like(root.<String> get(criteria.getKey()), criteria.getValue() + "%");
        case ENDS_WITH:
            return builder.like(root.<String> get(criteria.getKey()), "%" + criteria.getValue());
        case CONTAINS:
            return builder.like((builder.lower(root.<String>get(criteria.getKey()))), "%" + criteria.getValue() + "%");
        default:
            return null;
        }
    }

}
