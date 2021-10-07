package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.domain.Group;
import com.kodilla.ecommercee.domain.dto.GroupDto;
import com.kodilla.ecommercee.mapper.GroupMapper;
import com.kodilla.ecommercee.service.GroupService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("v1/ecommerce/groups")
public class GroupController {

    private final GroupService groupService;
    private final GroupMapper groupMapper;

    public GroupController(GroupService groupService, GroupMapper groupMapper) {
        this.groupService = groupService;
        this.groupMapper = groupMapper;
    }

    @GetMapping
    public List<GroupDto> getGroups(){
//        return Arrays.asList(
//                new GroupDto(1L, "underwear"),
//                new GroupDto(2L, "jackets")
//        );
        return groupMapper.mapToGroupDtoList(groupService.getAll());
    }
    @GetMapping("/{groupId}")
    public GroupDto getGroup(@PathVariable ("groupId") Long groupId){
//        return new GroupDto (1L, "underwear");
        return groupMapper.mapGroupToGroupDto(groupService.getGroup(groupId).get());
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public GroupDto createGroup(@RequestBody GroupDto groupDto){
        Group group = groupMapper.mapGroupDtoToGroup(groupDto);
        return groupMapper.mapGroupToGroupDto(groupService.saveGroup(group));
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, value = "/{groupId}")
    public GroupDto updateGroup(@PathVariable ("groupId") Long groupId, @RequestBody GroupDto groupDto){
//        return new GroupDto (1L, "updated name");
        groupService.getGroup(groupId);

        Group updatedGroup = groupMapper.mapGroupDtoToGroup(groupDto);
        updatedGroup.setId(groupId);
        updatedGroup = groupService.saveGroup(updatedGroup);

        return groupMapper.mapGroupToGroupDto(updatedGroup);
    }
}