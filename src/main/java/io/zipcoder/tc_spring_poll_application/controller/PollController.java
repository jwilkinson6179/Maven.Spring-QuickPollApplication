package io.zipcoder.tc_spring_poll_application.controller;

import io.zipcoder.tc_spring_poll_application.domain.Poll;
import io.zipcoder.tc_spring_poll_application.exception.ResourceNotFoundException;
import io.zipcoder.tc_spring_poll_application.repositories.PollRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import javax.validation.Valid;
import java.net.URI;

@RestController
public class PollController
{
    private PollRepository pollRepository;

    @Autowired
    public PollController(PollRepository pollRepository)
    {
        this.pollRepository = pollRepository;
    }

//    NO PAGINATION
//    @GetMapping("/polls")
//    public ResponseEntity<Iterable<Poll>> getAllPolls() {
//        Iterable<Poll> allPolls = pollRepository.findAll();
//        return new ResponseEntity<>(allPolls, HttpStatus.OK);
//    }

// TODO: Get this type of pagination

//    @RequestMapping(value = "admin/foo",params = { "page", "size" },method = RequestMethod.GET)
//    @ResponseBody
//    public List<Poll> findPaginated(@RequestParam("page") int page, @RequestParam("size") int size,
//            UriComponentsBuilder uriBuilder, HttpServletResponse response)
//    {
//        Page<Poll> resultPage = service.findPaginated(page, size);
//        if( page > resultPage.getTotalPages() ){
//            throw new ResourceNotFoundException();
//        }
//        eventPublisher.publishEvent(new PaginatedResultsRetrievedEvent<Poll>
//                (Poll.class, uriBuilder, response, page, resultPage.getTotalPages(), size));
//
//        return resultPage.getContent();
//    }

    @GetMapping("/polls")
    public Page<Poll> getAllPolls(Pageable pageable) {
        return pollRepository.findAll(pageable);
    }

    @RequestMapping(value="/polls/{pollId}", method=RequestMethod.GET)
    public ResponseEntity<?> getPoll(@PathVariable Long pollId) {
        verifyPoll(pollId);
        Poll p = pollRepository.findOne(pollId);
        return new ResponseEntity<> (p, HttpStatus.OK);
    }

    @Valid
    @RequestMapping(value="/polls", method=RequestMethod.POST)
    public ResponseEntity<?> createPoll(@RequestBody Poll poll)
    {
        poll = pollRepository.save(poll);
        URI newPollUri = ServletUriComponentsBuilder
        .fromCurrentRequest()
        .path("/{id}")
        .buildAndExpand(poll.getId())
        .toUri();

        return new ResponseEntity<>(newPollUri, HttpStatus.CREATED);
    }

    @Valid
    @RequestMapping(value="/polls/{pollId}", method=RequestMethod.PUT)
    public ResponseEntity<?> updatePoll(@RequestBody Poll poll, @PathVariable Long pollId) {
        // Save the entity
        verifyPoll(pollId);
        Poll p = pollRepository.save(poll);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value="/polls/{pollId}", method=RequestMethod.DELETE)
    public ResponseEntity<?> deletePoll(@PathVariable Long pollId) {
        verifyPoll(pollId);
        pollRepository.delete(pollId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    public void verifyPoll(Long pollId)
    {
        if(pollId == null)
        {
            throw new ResourceNotFoundException();
        }
    }
}