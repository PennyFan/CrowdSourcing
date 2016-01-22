package com.sysu.crowdsourcing.services.impl;

import com.sysu.crowdsourcing.dao.TaskDao;
import com.sysu.crowdsourcing.entity.TaskEntity;
import com.sysu.crowdsourcing.services.PostService;
import com.sysu.workflow.Evaluator;
import com.sysu.workflow.SCXMLExecutor;
import com.sysu.workflow.env.MulitStateMachineDispatcher;
import com.sysu.workflow.env.SimpleErrorReporter;
import com.sysu.workflow.env.jexl.JexlEvaluator;
import com.sysu.workflow.io.SCXMLReader;
import com.sysu.workflow.model.SCXML;

import javax.annotation.Resource;
import java.net.URL;

/**
 * Created by zhengshouzi on 2015/9/7.
 */
public class PostServiceImpl implements PostService {

    @Resource(name = "taskDao")
    TaskDao taskDao;


    public boolean postTask(TaskEntity taskEntity) {

        long id = taskDao.addTask(taskEntity);
        boolean flag = false;
        //�����ɹ��������ڰ����̣���������
        if (id != 0) {
            try {
                URL url = this.getClass().getClassLoader().getResource("crowdsourcingMyTest.xml");
                SCXML scxml = new SCXMLReader().read(url);
                //ʵ��������ģ�ͽ�����
                Evaluator evaluator = new JexlEvaluator();
                //ʵ��������
                SCXMLExecutor executor = new SCXMLExecutor(evaluator, new MulitStateMachineDispatcher(), new SimpleErrorReporter());
                executor.setStateMachine(scxml);
                //������ǰ�����Ӧ��״̬��
                executor.go();
                flag = true;
            } catch (Exception e) {
                e.printStackTrace();
                taskDao.deleteTaskById(id);
                flag = false;
            }
        }
        return flag;
    }
}
