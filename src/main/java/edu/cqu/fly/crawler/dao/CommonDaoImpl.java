package edu.cqu.fly.crawler.dao;

import java.util.List;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import edu.cqu.fly.crawler.util.TypedQueryBuilder;
@Repository("commonDao")
public class CommonDaoImpl extends AbstractBaseDaoImpl implements CommonDao{

	@PersistenceContext(unitName = "crawlerPU")
	protected EntityManager em;

	@Resource(name = "jdbcTemplate")
	private JdbcTemplate jdbcTemplate;

	@Resource(name = "namedParameterJdbcTemplate")
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	@Override
	protected EntityManager getEntityManager() {
		return em;
	}

	@Override
	protected JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	@Override
	protected NamedParameterJdbcTemplate getNamedParameterJdbcTemplate() {
		return namedParameterJdbcTemplate;
	}

	public <T> List<T> queryByParas(TypedQueryBuilder<T> tqBuilder) {
		TypedQuery<Long> countTQ = tqBuilder.toCountQuery(em);
		final long allCounts = countTQ.getSingleResult();
		TypedQuery<T> tq = tqBuilder.toQuery(em);
	
		List<?> list = tq.getResultList();
		return (List<T>) list;
	}

	public <T> List<T> selectObjectForPage(TypedQueryBuilder<T> tqBuilder,
			int startrecord, int endrecord) {
		TypedQuery<Long> countTQ = tqBuilder.toCountQuery(em);
		final long allCounts = countTQ.getSingleResult();
		
		TypedQuery<T> tq = tqBuilder.toQuery(em);
		tq.setFirstResult(startrecord);
		tq.setMaxResults(endrecord);
		List<?> list = tq.getResultList();
		return (List<T>) list;
	}


}
