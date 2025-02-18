package com.mirasystems.files.upload.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mirasystems.files.upload.model.FilesInfo;

@Repository
public interface FilesInfoRepository extends JpaRepository<FilesInfo, String> { }