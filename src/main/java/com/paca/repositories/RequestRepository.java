package com.paca.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.paca.entities.Request;
import com.paca.entities.User;

public interface RequestRepository extends CrudRepository<Request, Long>{

	@Query("SELECT r FROM Request r WHERE r.receiver = ?1 ORDER BY r.id ASC ")
	Page<Request> findReceivedRequestsForUser(Pageable pageable, User user);

	@Query("SELECT r.id FROM Request r WHERE r.receiver.id = ?1 AND	r.transmitter.id = ?2")
	Long findByTransmitterAndReceiver(Long id_to, Long id_from);

}
