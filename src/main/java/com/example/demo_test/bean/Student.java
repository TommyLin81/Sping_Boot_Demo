package com.example.demo_test.bean;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name="students")
@Getter
@Setter
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Integer id;

    @Column(name="name")
    @ApiModelProperty(value = "學生姓名", required = true)
    private String name;

    @Column(name = "graduate")
    @ApiModelProperty(value = "是否畢業", required = true)
    private Boolean graduate;

    @Column(name = "created_date")
    @ApiModelProperty(value = "建立時間")
    @CreationTimestamp
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createdDate;

    @Column(name = "updated_date")
    @ApiModelProperty(value = "更新時間")
    @UpdateTimestamp
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updatedDate;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @OneToMany(cascade=CascadeType.ALL, mappedBy="student")
    @ApiModelProperty(value = "學生的簽到資訊")
    private Set<Attendance> attendances;
}
