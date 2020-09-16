package dtos;

import entities.Members;

public class MembersDTO {
    private Long id;
    private String name;
    private String email;
    private String studentId;

    public MembersDTO() {
    }
    
    public MembersDTO(Members member) {
        this.id = member.getId();
        this.name = member.getName();
        this.email = member.getEmail();
        this.studentId = member.getStudentId();
    }

    public MembersDTO(Long id, String name, String email, String studentId) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.studentId = studentId;
    }

    public Long getId() {
        return id;
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
