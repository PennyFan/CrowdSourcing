package com.sysu.crowdsourcing.dao.impl.SessionFactory;

import com.sysu.crowdsourcing.dao.DecomposeVoteTaskDao;
import com.sysu.crowdsourcing.entity.DecomposeVoteTask;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

import javax.annotation.Resource;

/**
 * Created with IntelliJ IDEA
 * Date: 2016/2/15
 * Time: 13:31
 * User: ThinerZQ
 * GitHub: <a>https://github.com/ThinerZQ</a>
 * Blog: <a>http://www.thinerzq.me</a>
 * Email: 601097836@qq.com
 */

public class DecomposeVoteTaskDaoImpl implements DecomposeVoteTaskDao {

    @Resource(name = "sessionFactory")
    public SessionFactory sessionFactory;


    public long saveDecomposeVoteTask(DecomposeVoteTask decomposeVoteTask) {
        long id = -1;
        try {
            Session session = sessionFactory.getCurrentSession();

            id = (Long) session.save(decomposeVoteTask);

        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            return id;
        }
    }

    public DecomposeVoteTask getDecomposeVoteTasks(DecomposeVoteTask decomposeVoteTask) {
        DecomposeVoteTask decomposeVoteTask1 = null;
        try {
            Session session = sessionFactory.getCurrentSession();
            Criteria criteria = session.createCriteria(DecomposeVoteTask.class);

            Criterion allCriterion = Restrictions.allEq(decomposeVoteTask.getNotNullPropertyMap());

            criteria.add(allCriterion);
            criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);

            decomposeVoteTask1 = (DecomposeVoteTask) criteria.list().get(0);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return decomposeVoteTask1;
    }
}