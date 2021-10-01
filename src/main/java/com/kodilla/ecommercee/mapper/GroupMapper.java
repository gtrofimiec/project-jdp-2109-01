package com.kodilla.ecommercee.mapper;

import com.kodilla.ecommercee.domain.Group;
import com.kodilla.ecommercee.domain.dto.GroupDto;

public class GroupMapper {
    public Group mapGroupDtoToGroup(GroupDto groupDto){
        return new Group(groupDto.getId(), groupDto.getName());
    }
    public GroupDto mapGroupToGroupDto(Group group){
        return new GroupDto(group.getId(), group.getName());
    }
}
