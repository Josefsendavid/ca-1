package dtos;

import entities.Members;

public class MembersDTO {
    private String name;
    private String email;
    private String studentId;

    public MembersDTO() {
    }
    
    public MembersDTO(Members member) {
        this.name = member.getName();
        this.email = member.getEmail();
        this.studentId = member.getStudentId();
    }

    public MembersDTO(String name, String email, String studentId) {
        this.name = name;
        this.email = email;
        this.studentId = studentId;
    }


    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getStudentId() {
        return studentId;
    }
}
