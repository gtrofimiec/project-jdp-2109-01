package com.kodilla.ecommercee.service;

import com.kodilla.ecommercee.domain.Group;
import com.kodilla.ecommercee.repository.GroupRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GroupService {

    private final GroupRepository groupRepository;

    public GroupService(GroupRepository groupRepository) {
        this.groupRepository = groupRepository;
    }

    public List<Group> getAll() {
        return groupRepository.findAll();
    }

    public Group saveGroup(Group group) {
        return groupRepository.save(group);
    }

    public Optional<Group> getGroup(Long groupId) {
        return groupRepository.findById(groupId);
    }

    public void deleteGroup(Long groupId) {
        groupRepository.deleteById(groupId);
    }
}
