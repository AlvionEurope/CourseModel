package ru.alvion.coursemodel.service.dto;

import java.io.Serializable;
import java.util.Objects;

public class StudentDTO implements Serializable {

    private Long id;

    private String fullName;

    private String fullAddres;

    private String phone;

    private String eMail;

    private Integer recordBook;

    private Float gpa;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getFullAddres() {
        return fullAddres;
    }

    public void setFullAddres(String fullAddres) {
        this.fullAddres = fullAddres;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String geteMail() {
        return eMail;
    }

    public void seteMail(String eMail) {
        this.eMail = eMail;
    }

    public Integer getRecordBook() {
        return recordBook;
    }

    public void setRecordBook(Integer recordBook) {
        this.recordBook = recordBook;
    }

    public Float getGpa() {
        return gpa;
    }

    public void setGpa(Float gpa) {
        this.gpa = gpa;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof StudentDTO)) {
            return false;
        }

        StudentDTO studentDTO = (StudentDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, studentDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    @Override
    public String toString() {
        return "StudentDTO{" +
            "id=" + getId() +
            ", fullName='" + getFullName() + "'" +
            ", fullAddres='" + getFullAddres() + "'" +
            ", phone='" + getPhone() + "'" +
            ", eMail='" + geteMail() + "'" +
            ", recordBook=" + getRecordBook() +
            ", gpa=" + getGpa() +
            "}";
    }
}
