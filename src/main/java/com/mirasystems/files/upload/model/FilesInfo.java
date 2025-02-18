package com.mirasystems.files.upload.model;

import org.hibernate.annotations.UuidGenerator;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;

@Entity
@Table(name = FilesInfo.FILES_INFO_TABLE)
public class FilesInfo {

	private static final String FILES_INFO_TABLE = "files";

	@Id
	@UuidGenerator
	private String id;

	private String name;

	private String type;

	@Lob
	private byte[] data;

	public FilesInfo() { }

	public FilesInfo(String name, String type, byte[] data) {
		this.name = name;
		this.type = type;
		this.data = data;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public byte[] getData() {
		return data;
	}

	public void setData(byte[] data) {
		this.data = data;
	}
}