//package com.wzy.study;

//public class Demo {
//    /**
//     * 批量派发
//     * 1- 得到事项信息、及事项任务（最多1000个）
//     * 2- 推送督办事项下的督办任务到云上时间轴
//     * 3- 整理消息格式并发送消息
//     *
//     * @param ids 事项id集合
//     * @return
//     */
//    @Override
//    public int distributeBatch(List<String> ids) {
//        for (String id : ids) {
//            // 1- 得到事项信息、及事项任务（最多1000个）
//            SuperviseItemDTO superviseItemDTO = this.fetchById(id);
//            SuperviseTaskVO superviseTaskVO = new SuperviseTaskVO();
//            superviseTaskVO.setPid(id);
//            superviseTaskVO.setSize(1000);
//            List<SuperviseTaskDTO> superviseTaskDTOList = superviseTaskService.listQuery(superviseTaskVO);
//            // 2- 推送督办事项下的督办任务到云上时间轴
//            superviseItemDTO.setSuperviseTaskList(superviseTaskDTOList);
//            this.pushSuperviseTaskToTimeline(superviseItemDTO);
//
//            // 3- 整理消息格式并发送消息
//            // 3.1- 给事项分管领导发送通知
//            if (!CollectionUtils.isEmpty(superviseItemDTO.getLeaderArray())) {
//                List<String> allUserIdList = superviseItemDTO.getLeaderArray().stream().map(item -> item.getId()).collect(Collectors.toList());
//                sendMessage(superviseItemDTO.getId(), superviseItemDTO.getName(), "请查看【%C】发起的督办【%T】。", superviseItemDTO.getSysCreateName(), allUserIdList);
//            }
//            // 3.2- 给事项责任人、主办单位发送通知
//            if (!CollectionUtils.isEmpty(superviseItemDTO.getOrganizerArray())) {
//                // 添加责任人
//                List<String> allUserIdList = superviseItemDTO.getSponsorArray().stream().map(item -> item.getId()).collect(Collectors.toList());
//                // 添加主办单位联络人
//                allUserIdList = addContactId(allUserIdList, superviseItemDTO.getOrganizerArray());
//                sendMessage(superviseItemDTO.getId(), superviseItemDTO.getName(), "【%C】派发了督办事项【%T】。", superviseItemDTO.getSysCreateName(), allUserIdList);
//            }
//            // 3.3- 给事项协办单位、协办人发送通知
//            if (!CollectionUtils.isEmpty(superviseItemDTO.getCoOrganizerArray())) {
//                // 添加协办
//                List<String> allUserIdList = superviseItemDTO.getCoSponsorArray().stream().map(item -> item.getId()).collect(Collectors.toList());
//                // 添加协办单位联络人
//                allUserIdList = addContactId(allUserIdList, superviseItemDTO.getCoOrganizerArray());
//                sendMessage(superviseItemDTO.getId(), superviseItemDTO.getName(), "【%C】请你协助督办事项【%T】。", superviseItemDTO.getSysCreateName(), allUserIdList);
//            }
//            // 3.4- 给所有的任务发送消息
//            if (!CollectionUtils.isEmpty(superviseTaskDTOList)) {
//                for (SuperviseTaskDTO superviseTaskDTO : superviseTaskDTOList) {
//                    // 3.4.1- 给任务主办及联络人发送通知
//                    if (!CollectionUtils.isEmpty(superviseTaskDTO.getOrganizerArray())) {
//                        // 添加主办
//                        List<String> allUserIdList = superviseTaskDTO.getSponsorArray().stream().map(item -> item.getId()).collect(Collectors.toList());
//                        // 添加主办单位联络人
//                        allUserIdList = addContactId(allUserIdList, superviseTaskDTO.getOrganizerArray());
//                        sendMessage(superviseItemDTO.getId(), superviseItemDTO.getName(), "【%C】派发了【%T】的一个任务请及时完成。", superviseItemDTO.getSysCreateName(), allUserIdList);
//                    }
//                    // 3.4.2- 给任务协办及联络人发送通知
//                    if (!CollectionUtils.isEmpty(superviseTaskDTO.getCoOrganizerArray())) {
//                        // 添加协办
//                        List<String> allUserIdList = superviseTaskDTO.getCoSponsorArray().stream().map(item -> item.getId()).collect(Collectors.toList());
//                        // 添加协办单位联络人
//                        allUserIdList = addContactId(allUserIdList, superviseTaskDTO.getCoOrganizerArray());
//                        sendMessage(superviseItemDTO.getId(), superviseItemDTO.getName(), "【%C】请你协助【%T】的任务。", superviseItemDTO.getSysCreateName(), allUserIdList);
//                    }
//                }
//            }
//        }
//        return superviseItemMapper.updateDistributeBatch(ids);
//    }
//}
