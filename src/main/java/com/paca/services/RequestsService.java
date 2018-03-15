package com.paca.services;

import java.util.LinkedList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.paca.entities.Request;
import com.paca.entities.User;
import com.paca.repositories.RequestRepository;

@Service
public class RequestsService {
	
	@Autowired
	private RequestRepository requestsRepository;

	public Page<Request> searchRequestReceived(Pageable pageable, User user) {
		Page<Request> requests = new PageImpl<Request>(new LinkedList<Request>());
		requests = requestsRepository.findReceivedRequestsForUser(pageable, user);
		// TODO Auto-generated method stub
		return requests;
	}
	
	public void addRequest(Request request) {
		requestsRepository.save(request);
	}

}
