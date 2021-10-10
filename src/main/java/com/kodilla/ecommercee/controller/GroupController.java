package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.domain.Group;
import com.kodilla.ecommercee.domain.dto.GroupDto;
import com.kodilla.ecommercee.repository.GroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("v1/ecommerce/groups")
public class GroupController {

    @Autowired
    GroupRepository groupRepository;

    @GetMapping
    public List<GroupDto> getGroups(){
        return Arrays.asList(
                new GroupDto(1L, "underwear"),
                new GroupDto(2L, "jackets")
        );
    }
    @GetMapping("/{groupId}")
    public GroupDto getGroup(@PathVariable ("groupId") Long groupId){
        return new GroupDto (1L, "underwear");
    }

    @PostMapping
    public void createGroup(@RequestBody Group group){
        groupRepository.save(group);
    }

    @PutMapping("/{groupId}")
    public GroupDto updateGroup(@PathVariable ("groupId") Long groupId, @RequestBody GroupDto groupDto){
        return new GroupDto (1L, "updated name");
    }


}
