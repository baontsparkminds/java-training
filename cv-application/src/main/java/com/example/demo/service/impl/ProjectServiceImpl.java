package com.example.demo.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.demo.entity.Project;
import com.example.demo.exception.DataNotFoundException;
import com.example.demo.repository.ProjectRepository;
import com.example.demo.service.ProjectService;
import com.example.demo.service.dto.ProjectDto;
import com.example.demo.service.mapper.ProjectMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProjectServiceImpl implements ProjectService {

    private final ProjectRepository projectRepository;
    private final ProjectMapper projectMapper;

    @Override
    public List<ProjectDto> getProjects() {
        List<Project> projects = projectRepository.findAll();
        return projects.stream().map(projectMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public ProjectDto getProject(Long id) {
        Optional<Project> project = projectRepository.findById(id);

        if (!project.isPresent()) {
            throw new DataNotFoundException("Prject not found", "common.notfound");
        }

        return projectMapper.toDto(project.get());
    }

}
