/*
 * AdministratorUserAccountRepository.java
 *
 * Copyright (c) 2019 Rafael Corchuelo.
 *
 * In keeping with the traditional purpose of furthering education and research, it is
 * the policy of the copyright owner to permit non-commercial use and redistribution of
 * this software. It has been tested carefully, but it is not guaranteed for any particular
 * purposes. The copyright owner does not offer any warranties or representations, nor do
 * they accept any liabilities with respect to them.
 */

package acme.features.administrator.requestAuditor;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.requestAuditors.RequestAuditor;
import acme.framework.entities.UserAccount;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AdministratorRequestAuditorRepository extends AbstractRepository {

	@Query("select ua from UserAccount ua where ua.id = ?1")
	UserAccount findOneUserAccountById(int id);

	@Query("select ra from RequestAuditor ra where ra.id = ?1")
	RequestAuditor findOneRequestAuditorById(int id);

	@Query("select ra from RequestAuditor ra")
	Collection<RequestAuditor> findAllRequestAuditor();

}
