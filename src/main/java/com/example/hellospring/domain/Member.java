package com.example.hellospring.domain;


import javax.persistence.*;

//멤버 도메인 클래스를 정의한다.
//멤버 도메인에는 id, name의 필드를 갖는다
//클래스이기 때문에 getter setter를 이용하여 데이터 접근
@Entity
public class Member {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
