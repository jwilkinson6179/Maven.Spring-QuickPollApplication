package io.zipcoder.tc_spring_poll_application.repositories;

import io.zipcoder.tc_spring_poll_application.domain.Vote;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface VoteRepository extends PagingAndSortingRepository<Vote, Long>
{
    @Query(value = "SELECT v.* " +
            "FROM Options o, Vote v " +
            "WHERE o.POLL_ID = ?1 " +
            "AND v.OPTIONS_ID = o.OPTIONS_ID", nativeQuery = true)
    public Iterable<Vote> findVotesByPoll(Long pollId);
}