import com.zhengshouzi.myweb.dao.DescriptionDao;
import com.zhengshouzi.myweb.dao.TaskDao;
import com.zhengshouzi.myweb.dao.UserDao;
import com.zhengshouzi.myweb.entity.DescriptionEntity;
import com.zhengshouzi.myweb.entity.TaskEntity;
import com.zhengshouzi.myweb.entity.UserEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by zhengshouzi on 2015/9/16.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "/applicationContext.xml")
public class TestConnectionDatabase extends AbstractJUnit4SpringContextTests {

    @Resource(name = "userDao")
    UserDao userDao;
    @Resource(name = "taskDao")
    TaskDao taskDao;
    @Resource(name = "descriptionDao")
    DescriptionDao descriptionDao;

    @Test
    public void testRegister() {
        UserEntity userEntity = new UserEntity();
        userEntity.setEmail("6010978@qq.com");
        userEntity.setPassword("6823");
        userEntity.setRegisterDate(new Timestamp(new Date().getTime()));
        userDao.register(userEntity);
    }
    @Test
    public void testPostTask() {

        SessionFactory sessionFactory = (SessionFactory) applicationContext.getBean("sessionFactory");
        Session session = sessionFactory.openSession();

        TaskEntity taskEntity = new TaskEntity();
        taskEntity.setTitle("写一篇关于众包的文章111");
        taskEntity.setDeadlineTime(new Timestamp(new Date().getTime()));
        taskEntity.setReleaseTime(new Timestamp(new Date().getTime()));
        taskEntity.setTaskType("mainTask");

        DescriptionEntity descriptionEntity = new DescriptionEntity();

        Set<DescriptionEntity> descriptionEntitySet = new HashSet<>();
        descriptionEntity.setDescription("不少于300字");
        descriptionEntity.setTaskByTaskId(taskEntity);

        descriptionEntitySet.add(descriptionEntity);

        taskEntity.getDescriptionsById().add(descriptionEntity);


        session.save(taskEntity);

        session.flush();
        session.close();


        //descriptionEntity.setTaskByTaskId(taskEntity);


        //taskEntity.setUserByUserId(userDao.findUserByEmail("601097836@qq.com"));

        //taskDao.addTask(taskEntity);
        //descriptionDao.addDescriptionTask(descriptionEntity);





    }
}
