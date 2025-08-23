package com.school.dormitory.student.bed.press.core.model;



import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(collection = "dormitoryStudentBedPress")
@JsonInclude(JsonInclude.Include.NON_NULL)
@Schema(name = "DormitoryStudentBedPress response")
public class DormitoryStudentBedPress {
    @Id
    private String dormitoryStudentBedPressId;
    private String code;
    private String dormitory;
    private String dormitoryName;
    private String student;
    private String studentFullname;
    private String bed;
    private String bedName;
    private String press;
    private String pressName;
    private String logCreatedAt;
    private String logCreatedDate;
    private String logCreatedMonth;
    private String logCreatedYear;
}
