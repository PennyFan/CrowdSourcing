package com.sysu.workflow;

import com.sysu.workflow.service.processservice.RuntimeService;
import org.junit.Before;
import org.junit.Test;

/**
 * Created with IntelliJ IDEA
 * Date: 2016/1/20
 * Time: 16:58
 * User: ThinerZQ
 * GitHub: <a>https://github.com/ThinerZQ</a>
 * Blog: <a>http://www.thinerzq.me</a>
 * Email: 601097836@qq.com
 */
public class TestProcessInstanceService {

    RuntimeService runtimeService = null;

    @Before
    public void before() {
        runtimeService = new RuntimeService();
    }

    @Test
    public void testGetAllProcessInstance() {

   /*     ArrayList<ProcessInstanceEntity> processInstanceEntities = RuntimeService.createProcessInstanceQuery().getAllProcessInstance();
        System.out.println(processInstanceEntities.size());
*/
    }

    @Test
    public void findGroupTaskByUser() {

       /* UserEntity currentUserEntity  = IdentityService.createUserQuery().userRealName("judger1").SingleResult();

        ArrayList<UserWorkItemEntity> userWorkItemEntityList = TaskService.createUserTaskQuery().taskAssignee(currentUserEntity).list();

        Map<GroupEntity, ArrayList<GroupWorkItemEntity>> groupWorkItemArrayListMap = new LinkedHashMap<GroupEntity, ArrayList<GroupWorkItemEntity>>();
        //�õ���ǰ�û�����������й�����
        for (GroupEntity groupEntity : currentUserEntity.getGroupEntitySet()) {
            ArrayList<GroupWorkItemEntity> groupWorkItemEntityArrayList = TaskService.createGroupTaskQuery().taskCandidateGroup(groupEntity).list();
            //��ǰ�������񣬾ͼ��뵽map����
            if (groupWorkItemEntityArrayList.size() != 0) {
                groupWorkItemArrayListMap.put(groupEntity, groupWorkItemEntityArrayList);
            }
        }*/
    }

    @Test
    public void findUserTaskByUser() {
/*
  UserEntity currentUserEntity  = IdentityService.createUserQuery().userRealName("judger1").SingleResult();

        ArrayList<UserWorkItemEntity> userWorkItemEntityList = TaskService.createUserTaskQuery().taskAssignee(currentUserEntity).list();

        Map<GroupEntity, ArrayList<GroupWorkItemEntity>> groupWorkItemArrayListMap = new LinkedHashMap<GroupEntity, ArrayList<GroupWorkItemEntity>>();
        //�õ���ǰ�û�����������й�����
       long groupWorkItemId =0;
        for (GroupEntity groupEntity : currentUserEntity.getGroupEntitySet()) {
            ArrayList<GroupWorkItemEntity> groupWorkItemEntityArrayList = TaskService.createGroupTaskQuery().taskCandidateGroup(groupEntity).list();
            //��ǰ�������񣬾ͼ��뵽map����
            if (groupWorkItemEntityArrayList.size() != 0) {
                groupWorkItemArrayListMap.put(groupEntity, groupWorkItemEntityArrayList);
            }
            groupWorkItemId = groupWorkItemEntityArrayList.get(0).getItemId();
        }
        if (groupWorkItemId != 0) {
            GroupWorkItemEntity groupWorkItemEntity = TaskService.createGroupTaskQuery().taskId((int) groupWorkItemId).SingleResult();
            //����group workitem
            int instance;
            instance = groupWorkItemEntity.getItemInstances();
            if (groupWorkItemEntity.getItemInstances() <= 0) {
                //���أ���ʾ�����������ˡ�
                System.out.println("group work done ");
            } else {
                TaskService taskService = new TaskService();
                UserWorkItemEntity userWorkItemEntity = taskService.newWorkItem();
                //����user workitem
                userWorkItemEntity.setItemName(groupWorkItemEntity.getItemName())
                        .setItemCreateTime(new Date().toLocaleString())
                        .setItemStateId(groupWorkItemEntity.getItemStateId())
                        .setItemProcessId(groupWorkItemEntity.getItemProcessId())
                        .setItemAssigneeEntity(currentUserEntity)
                        .setItemFormEntity(groupWorkItemEntity.getItemFormEntity())
                        .setItemGroupWorkItemEntity(groupWorkItemEntity);

                taskService.saveUserWorkItem(userWorkItemEntity);

                instance = groupWorkItemEntity.getItemInstances() - 1;
                groupWorkItemEntity.setItemInstances(instance);
                taskService.updateGroupWorkItem(groupWorkItemEntity);
            }
        }*/

    }

    @Test
    public void testCheckOutTwoItem() {
       /* UserEntity currentUserEntity  = IdentityService.createUserQuery().userRealName("judger1").SingleResult();

        ArrayList<UserWorkItemEntity> userWorkItemEntityList = TaskService.createUserTaskQuery().taskAssignee(currentUserEntity).list();

        Map<GroupEntity, ArrayList<GroupWorkItemEntity>> groupWorkItemArrayListMap = new LinkedHashMap<GroupEntity, ArrayList<GroupWorkItemEntity>>();
        //�õ���ǰ�û�����������й�����
        for (GroupEntity groupEntity : currentUserEntity.getGroupEntitySet()) {
            ArrayList<GroupWorkItemEntity> groupWorkItemEntityArrayList = TaskService.createGroupTaskQuery().taskCandidateGroup(groupEntity).list();

            //��ǰ�������񣬾ͼ��뵽map����
            if (groupWorkItemEntityArrayList.size() != 0) {
                groupWorkItemArrayListMap.put(groupEntity, groupWorkItemEntityArrayList);
            }
            //�޳��������û������б����������
            for (UserWorkItemEntity uwie : userWorkItemEntityList) {
                int size = groupWorkItemEntityArrayList.size();
                for (int i = 0; i < size; i++) {
                    GroupWorkItemEntity gwie = groupWorkItemEntityArrayList.get(i);
                    if (gwie.getItemProcessId().equals(uwie.getItemProcessId()) && gwie.getItemStateId().equals(uwie.getItemStateId()) && gwie.getItemName().equals(uwie.getItemName())) {
                        groupWorkItemEntityArrayList.remove(gwie);
                        size--;
                    }
                }
            }
            //��ǰ�������񣬾ͼ��뵽map����
            if (groupWorkItemEntityArrayList.size() != 0) {
                groupWorkItemArrayListMap.put(groupEntity, groupWorkItemEntityArrayList);
            } else {
                groupWorkItemArrayListMap.remove(groupEntity);
            }
        }
*/
    }

}