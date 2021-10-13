package com.kodilla.ecommercee.service;

import com.kodilla.ecommercee.controller.exception.GroupAlreadyExistsException;
import com.kodilla.ecommercee.controller.exception.GroupNotFoundException;
import com.kodilla.ecommercee.domain.Group;
import com.kodilla.ecommercee.repository.GroupRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GroupService {

    private final GroupRepository groupRepository;

    public GroupService(GroupRepository groupRepository) {
        this.groupRepository = groupRepository;
    }

    public List<Group> getAll() {
        return groupRepository.findAll();
    }

    public Group saveGroup(final Group group) throws GroupAlreadyExistsException {
        if(!groupRepository.existsById(group.getId())) {
            return groupRepository.save(group);
        } else {
            throw new GroupAlreadyExistsException();
        }
    }

    public Group getOne(final Long groupId) throws GroupNotFoundException {
        return groupRepository.findById(groupId)
                .orElseThrow(GroupNotFoundException::new);
    }

    public Group update(final Long groupId, Group group) throws GroupNotFoundException {
        if(groupRepository.existsById(groupId)) {
            group.setId(groupId);
            return groupRepository.save(group);
        } else {
            throw new GroupNotFoundException();
        }
    }
}