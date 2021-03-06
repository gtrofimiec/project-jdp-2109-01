package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.controller.exception.GroupAlreadyExistsException;
import com.kodilla.ecommercee.controller.exception.GroupNotFoundException;
import com.kodilla.ecommercee.domain.Group;
import com.kodilla.ecommercee.domain.dto.GroupDto;
import com.kodilla.ecommercee.mapper.GroupMapper;
import com.kodilla.ecommercee.service.GroupService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

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
    public List<GroupDto> getGroups() {
        return groupMapper.mapToGroupDtoList(groupService.getAll());
    }

    @GetMapping("/{groupId}")
    public GroupDto getGroup(@PathVariable ("groupId") Long groupId) throws GroupNotFoundException {
        return groupMapper.mapGroupToGroupDto(groupService.getOne(groupId));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public GroupDto createGroup(@RequestBody GroupDto groupDto) throws GroupAlreadyExistsException {
        Group group = groupMapper.mapGroupDtoToGroup(groupDto);
        Group createdGroup = groupService.saveGroup(group);
        return groupMapper.mapGroupToGroupDto(createdGroup);
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, value = "/{groupId}")
    public GroupDto updateGroup(@PathVariable ("groupId") Long groupId, @RequestBody GroupDto groupDto)
            throws GroupNotFoundException {
        Group updatedGroup = groupMapper.mapGroupDtoToGroup(groupDto);
        updatedGroup.setId(groupId);
        updatedGroup = groupService.update(groupId, updatedGroup);
        return groupMapper.mapGroupToGroupDto(updatedGroup);
    }
}