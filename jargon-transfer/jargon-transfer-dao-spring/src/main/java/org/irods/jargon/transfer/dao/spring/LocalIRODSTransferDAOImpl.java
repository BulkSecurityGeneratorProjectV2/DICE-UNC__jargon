package org.irods.jargon.transfer.dao.spring;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.irods.jargon.transfer.dao.LocalIRODSTransferDAO;
import org.irods.jargon.transfer.dao.TransferDAOException;
import org.irods.jargon.transfer.dao.domain.LocalIRODSTransfer;
import org.irods.jargon.transfer.dao.domain.TransferState;
import org.irods.jargon.transfer.dao.domain.TransferStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 * 
 * @author jdr0887
 * 
 */
public class LocalIRODSTransferDAOImpl extends HibernateDaoSupport implements
		LocalIRODSTransferDAO {

	private static final Logger log = LoggerFactory
			.getLogger(LocalIRODSTransferDAOImpl.class);

	public LocalIRODSTransferDAOImpl() {
		super();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.irods.jargon.transfer.dao.LocalIRODSTransferDAO#save(org.irods.jargon
	 * .transfer.dao.domain.LocalIRODSTransfer)
	 */
	@Override
	public void save(final LocalIRODSTransfer localIRODSTransfer)
			throws TransferDAOException {
		logger.info("entering save(LocalIRODSTransfer)");
		this.getSessionFactory().getCurrentSession()
				.saveOrUpdate(localIRODSTransfer);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.irods.jargon.transfer.dao.LocalIRODSTransferDAO#findById(java.lang
	 * .Long)
	 */
	@Override
	public LocalIRODSTransfer findById(final Long id)
			throws TransferDAOException {
		logger.debug("entering findById(Long)");
		return (LocalIRODSTransfer) this.getSessionFactory()
				.getCurrentSession().get(LocalIRODSTransfer.class, id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.irods.jargon.transfer.dao.LocalIRODSTransferDAO#findInitializedById
	 * (java.lang.Long)
	 */
	@Override
	public LocalIRODSTransfer findInitializedById(final Long id)
			throws TransferDAOException {
		logger.debug("entering findInitializedById(Long)");
		LocalIRODSTransfer localIrodsTransfer = (LocalIRODSTransfer) this
				.getSessionFactory().getCurrentSession()
				.get(LocalIRODSTransfer.class, id);
		Hibernate.initialize(localIrodsTransfer);
		return localIrodsTransfer;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.irods.jargon.transfer.dao.LocalIRODSTransferDAO#findByTransferState
	 * (org.irods.jargon.transfer.dao.domain.TransferState[])
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<LocalIRODSTransfer> findByTransferState(
			final TransferState... transferState) throws TransferDAOException {
		log.debug("entering findByTransferState(TransferState...)");

		try {
			Criteria criteria = this.getSessionFactory().getCurrentSession()
					.createCriteria(LocalIRODSTransfer.class);
			criteria.add(Restrictions.in("transferState", transferState));
			criteria.addOrder(Order.desc("transferStart"));
			return criteria.list();
		} catch (HibernateException e) {
			log.error("HibernateException", e);
			throw new TransferDAOException(e);
		} catch (Exception e) {
			log.error("error in findByTransferState(TransferState...)", e);
			throw new TransferDAOException(
					"Failed findByTransferState(TransferState...)", e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.irods.jargon.transfer.dao.LocalIRODSTransferDAO#findByTransferState
	 * (int, org.irods.jargon.transfer.dao.domain.TransferState[])
	 */
	@Override
	public List<LocalIRODSTransfer> findByTransferState(final int maxResults,
			final TransferState... transferState) throws TransferDAOException {
		log.debug("entering findByTransferState(int, TransferState...)");
		List<LocalIRODSTransfer> ret = null;
		try {
			Criteria criteria = this.getSessionFactory().getCurrentSession()
					.createCriteria(LocalIRODSTransfer.class);
			criteria.add(Restrictions.in("transferState", transferState));
			criteria.setMaxResults(maxResults);
			criteria.addOrder(Order.desc("transferStart"));
			ret = criteria.list();
			return ret;

		} catch (HibernateException e) {
			log.error("HibernateException", e);
			throw new TransferDAOException(e);
		} catch (Exception e) {
			log.error("error in findByTransferState(int, TransferState...)", e);
			throw new TransferDAOException(
					"Failed findByTransferState(int, TransferState...)", e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.irods.jargon.transfer.dao.LocalIRODSTransferDAO#findByTransferStatus
	 * (int, org.irods.jargon.transfer.dao.domain.TransferStatus[])
	 */
	@Override
	public List<LocalIRODSTransfer> findByTransferStatus(final int maxResults,
			final TransferStatus... transferStatus) throws TransferDAOException {
		log.debug("entering findByTransferState(int, TransferStatus...)");
		
		try {
			Criteria criteria = this.getSessionFactory().getCurrentSession()
					.createCriteria(LocalIRODSTransfer.class);
			criteria.add(Restrictions.in("transferStatus", transferStatus));
			criteria.setMaxResults(maxResults);
			criteria.addOrder(Order.desc("transferStart"));
			return criteria.list();
		} catch (HibernateException e) {
			log.error("HibernateException", e);
			throw new TransferDAOException(e);
		} catch (Exception e) {
			log.error("error in findByTransferState(int, TransferStatus...)", e);
			throw new TransferDAOException(
					"Failed findByTransferState(int, TransferStatus...)", e);
		} 
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.irods.jargon.transfer.dao.LocalIRODSTransferDAO#findAllSortedDesc
	 * (int)
	 */
	@Override
	public List<LocalIRODSTransfer> findAllSortedDesc(final int maxResults)
			throws TransferDAOException {

		try {
			Criteria criteria = this.getSessionFactory().getCurrentSession()
					.createCriteria(LocalIRODSTransfer.class);
			criteria.setMaxResults(maxResults);
			criteria.addOrder(Order.desc("transferStart"));
			return criteria.list();
		} catch (HibernateException e) {
			log.error("HibernateException", e);
			throw new TransferDAOException(e);
		} catch (Exception e) {
			log.error("error in findAllSortedDesc(int)", e);
			throw new TransferDAOException("Failed findAllSortedDesc(int)", e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.irods.jargon.transfer.dao.LocalIRODSTransferDAO#findAll()
	 */
	@Override
	public List<LocalIRODSTransfer> findAll() throws TransferDAOException {
		log.debug("entering findAll()");
		try {
			Criteria criteria = this.getSessionFactory().getCurrentSession()
					.createCriteria(LocalIRODSTransfer.class);
			criteria.addOrder(Order.desc("transferStart"));
			return criteria.list();
		} catch (HibernateException e) {
			log.error("HibernateException", e);
			throw new TransferDAOException(e);
		} catch (Exception e) {
			log.error("error in findAll()", e);
			throw new TransferDAOException("Failed findAll()", e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.irods.jargon.transfer.dao.LocalIRODSTransferDAO#purgeQueue()
	 */
	@Override
	public void purgeQueue() throws TransferDAOException {
		log.debug("entering purgeQueue()");

		try {

			int rows = super.getHibernateTemplate().bulkUpdate(
					"delete from LocalIRODSTransfer where transferState <> ?",
					TransferState.PROCESSING);
			log.debug("updated rows: {}", rows);

		} catch (HibernateException e) {
			log.error("HibernateException", e);
			throw new TransferDAOException(e);
		} catch (Exception e) {
			log.error("error in purgeQueue()", e);
			throw new TransferDAOException("Failed purgeQueue()", e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.irods.jargon.transfer.dao.LocalIRODSTransferDAO#purgeSuccessful()
	 */
	@Override
	public void purgeSuccessful() throws TransferDAOException {
		log.debug("entering purgeSuccessful()");

		try {

			int rows = super
					.getHibernateTemplate()
					.bulkUpdate(
							"delete from LocalIRODSTransfer where transferState = ? or transferState = ? and transferErrorStatus = ?",
							new Object[] { TransferState.COMPLETE,
									TransferState.CANCELLED, TransferStatus.OK });
			log.debug("updated rows: {}", rows);

		} catch (HibernateException e) {
			log.error("HibernateException", e);
			throw new TransferDAOException(e);
		} catch (DataAccessException e) {

			log.error("error in purgeSuccessful()", e);
			throw new TransferDAOException("Failed purgeSuccessful()", e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.irods.jargon.transfer.dao.LocalIRODSTransferDAO#purgeQueueByDate(int)
	 */
	@Override
	public void purgeQueueByDate(final int retentionDays)
			throws TransferDAOException {

		try {

			int rows = super
					.getHibernateTemplate()
					.bulkUpdate(
							"delete from LocalIRODSTransfer as transfer where transfer.transferState = ? or transferState = ?",
							new Object[] { TransferState.COMPLETE,
									TransferState.CANCELLED });
			log.debug("updated rows: {}", rows);

		} catch (HibernateException e) {
			log.error("HibernateException", e);
			throw new TransferDAOException(e);
		} catch (DataAccessException e) {

			log.error("error in purgeQueueByDate(int)", e);
			throw new TransferDAOException("Failed purgeQueueByDate(int)", e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.irods.jargon.transfer.dao.LocalIRODSTransferDAO#delete(org.irods.
	 * jargon.transfer.dao.domain.LocalIRODSTransfer)
	 */
	@Override
	public void delete(final LocalIRODSTransfer ea) throws TransferDAOException {
		logger.debug("entering delete()");

		try {

			this.getSessionFactory().getCurrentSession().delete(ea);
		} catch (HibernateException e) {
			log.error("HibernateException", e);
			throw new TransferDAOException(e);
		} catch (Exception e) {

			log.error("error in delete(LocalIRODSTransfer entity)", e);
			throw new TransferDAOException(
					"Failed delete(LocalIRODSTransfer entity)", e);
		}
	}

}
