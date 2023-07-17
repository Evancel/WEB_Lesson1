package amargolina.ru.hogwarts.school.dto;

import amargolina.ru.hogwarts.school.model.Student;

public class AvatarDto {
    private String fılePath;
    private long fileSize;
    private String mediaType;
    private long studentId;

    public AvatarDto(){}

    public String getFılePath() {
        return fılePath;
    }

    public void setFılePath(String fılePath) {
        this.fılePath = fılePath;
    }

    public long getFileSize() {
        return fileSize;
    }

    public void setFileSize(long fileSize) {
        this.fileSize = fileSize;
    }

    public String getMediaType() {
        return mediaType;
    }

    public void setMediaType(String mediaType) {
        this.mediaType = mediaType;
    }

    public long getStudentId() {
        return studentId;
    }

    public void setStudentId(long studentId) {
        this.studentId = studentId;
    }
}
