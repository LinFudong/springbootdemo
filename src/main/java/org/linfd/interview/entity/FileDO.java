package org.linfd.interview.entity;

import javax.persistence.*;

@Entity
@Table(name = "tb_file")
public class FileDO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    private String content;

    /**
     * latest edit time
     */
    private Long latestEditTime;

    /**
     * isolated lock
     */
    private String uuid;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getLatestEditTime() {
        return latestEditTime;
    }

    public void setLatestEditTime(Long latestEditTime) {
        this.latestEditTime = latestEditTime;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }
}
