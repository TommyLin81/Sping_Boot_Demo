package com.example.demo_test.bean;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "attendances")
@Getter
@Setter
public class Attendance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Integer id;

    @Column(name = "student_id")
    @ApiModelProperty(value = "學生 ID")
    private Integer studentId;

    @Column(name = "classroom_id")
    @ApiModelProperty(value = "教室 ID")
    private Integer classroomId;

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

    @ManyToOne(cascade=CascadeType.ALL)
    @ApiModelProperty(value = "學生資料")
    @JoinColumn(name="student_id", insertable = false, updatable = false)
    @JsonIgnore
    private Student student;
}
