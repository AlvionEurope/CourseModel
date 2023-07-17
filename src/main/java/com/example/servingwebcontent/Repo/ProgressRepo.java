package com.example.servingwebcontent.Repo;

import com.example.servingwebcontent.models.Progress;
import org.springframework.data.repository.CrudRepository;

public interface ProgressRepo extends CrudRepository<Progress,Long> {
}