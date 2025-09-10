package com.project.IMS.entity;

import java.time.OffsetDateTime;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//---------------- LOG ----------------
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "logs")
@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class,property="logId")
public class Log {

 @Id
 @GeneratedValue(strategy = GenerationType.IDENTITY)
 private Long logId;

 @NotBlank
 private String action;

 @NotBlank
 private String entityType;

 private Long entityId;

 private OffsetDateTime timestamp;

 private String details;

 // Associations
 @ManyToOne
 @JoinColumn(name = "user_id", nullable = false)
// @JsonManagedReference
 private User user;

 // Getters and Setters
}



