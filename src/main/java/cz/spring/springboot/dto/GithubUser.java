package cz.spring.springboot.dto;

/**
 * ClassName:GtihubUser
 *
 * @Description: TODO
 * @Author:chenzhen Date:2019/7/16 22:56
 * Version 1.0
 **/
public class GithubUser {
    private String name;
    private  Long id;
    private String bio;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    @Override
    public String toString() {
        return "GithubUser{" +
                "name='" + name + '\'' +
                ", id=" + id +
                ", bio='" + bio + '\'' +
                '}';
    }
}
