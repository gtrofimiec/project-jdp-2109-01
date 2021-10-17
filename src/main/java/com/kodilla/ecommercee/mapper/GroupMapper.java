package com.kodilla.ecommercee.mapper;

import com.kodilla.ecommercee.domain.Group;
import com.kodilla.ecommercee.domain.dto.GroupDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GroupMapper {

    public GroupDto mapGroupToGroupDto(Group group) {
        GroupDto groupDto = new GroupDto();
        //groupDto.setId(group.getId());
        groupDto.setName(group.getName());
        return groupDto;
    }

    public Group mapGroupDtoToGroup(GroupDto groupDto) {
        Group group = new Group();
        //group.setId(groupDto.getId());
        group.setName(groupDto.getName());
        return group;
    }

    public List<GroupDto> mapToGroupDtoList(List<Group> groupList) {
        return groupList.stream()
                .map(this::mapGroupToGroupDto)
                .collect(Collectors.toList());
    }
}