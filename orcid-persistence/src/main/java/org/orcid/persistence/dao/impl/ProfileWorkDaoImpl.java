package org.orcid.persistence.dao.impl;

import javax.persistence.Query;

import org.orcid.jaxb.model.message.Visibility;
import org.orcid.persistence.dao.ProfileWorkDao;
import org.orcid.persistence.jpa.entities.ProfileWorkEntity;
import org.orcid.persistence.jpa.entities.keys.ProfileWorkEntityPk;
import org.springframework.transaction.annotation.Transactional;

public class ProfileWorkDaoImpl extends GenericDaoImpl<ProfileWorkEntity, ProfileWorkEntityPk> implements ProfileWorkDao {

    
    public ProfileWorkDaoImpl(){
        super(ProfileWorkEntity.class);
    }
    /**
     * Removes the relationship that exists between a work and a profile.
     * 
     * @param workId
     *          The id of the work that will be removed from the client profile
     * @param clientOrcid
     *          The client orcid 
     * @return true if the relationship was deleted
     * */
    @Override
    @Transactional
    public boolean removeWork(String clientOrcid, String workId) {
        Query query = entityManager.createQuery("delete from ProfileWorkEntity where profile.id=:clientOrcid and work.id=:workId");
        query.setParameter("clientOrcid", clientOrcid);
        query.setParameter("workId", Long.valueOf(workId));
        return query.executeUpdate() > 0 ? true : false;
    }

    /**
     * Updates the visibility of an existing profile work relationship
     * 
     * @param clientOrcid
     *          The client orcid
     *          
     * @param workId
     *          The id of the work that will be updated
     *          
     * @param visibility
     *          The new visibility value for the profile work relationship         
     *                     
     * @return true if the relationship was updated
     * */
    @Override
    @Transactional
    public boolean updateWork(String clientOrcid, String workId, Visibility visibility){
        Query query = entityManager.createQuery("update ProfileWorkEntity set visibility=:visibility, lastModified=now() where profile.id=:clientOrcid and work.id=:workId");
        query.setParameter("clientOrcid", clientOrcid);
        query.setParameter("workId", Long.valueOf(workId));
        query.setParameter("visibility", visibility);
        return query.executeUpdate() > 0 ? true : false;
    }
    
}
