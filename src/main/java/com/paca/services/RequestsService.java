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
import com.paca.repositories.UsersRepository;

@Service
public class RequestsService {
	
	@Autowired
	private RequestRepository requestsRepository;
	
	@Autowired
	private UsersService usersService;
	
	@Autowired
	private UsersRepository usersRepository;

	public Page<Request> searchRequestReceived(Pageable pageable, User user) {
		Page<Request> requests = new PageImpl<Request>(new LinkedList<Request>());
		requests = requestsRepository.findReceivedRequestsForUser(pageable, user);
		// TODO Auto-generated method stub
		return requests;
	}
	
//	public void addRequest(Request request) {
//		requestsRepository.save(request);
//	}

	public void sendRequest(Long id_to,Long id_from ) {
		User transmitter= usersRepository.findOne(id_from);
		User receiver = usersRepository.findOne(id_to);
		usersService.setUserIsAddFriend(true, id_to);
		Request request = new Request(transmitter.getFullName()+" quiere ser tu amig@", transmitter, receiver);
		requestsRepository.save(request);
	}

	public void cancellRequest(Long id_to,Long id_from ) {
		usersService.setUserIsAddFriend(false, id_to);
		requestsRepository.delete(requestsRepository.findByTransmitterAndReceiver(id_to, id_from));
	}
}
